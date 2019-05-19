package com.example.formulariodocente;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.menu.MenuDrawerDocente;
import com.example.formulariodocente.modelos.Cuenta;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Cambiar_Contra {

    private int cuenta;
    private TextInputEditText contraseña;
    private TextInputEditText contraseñaNueva;
    private Button confirmar1;
    private Context context;
    private Dialog dialogo;

    public Cambiar_Contra(Context context, int cuenta) {
        this.context = context;
        this.cuenta = cuenta;
        this.dialogo = new Dialog(context);
        this.dialogo.setContentView(R.layout.activity_cambiar__contra);
        this.dialogo.setCancelable(true);
        this.init();
        this.dialogo.show();
    }

    public void init() {
        this.contraseña = dialogo.findViewById(R.id.contraseña);
        this.contraseñaNueva = dialogo.findViewById(R.id.nuevacontraseña);
        this.confirmar1 = dialogo.findViewById(R.id.Confirmar);
        this.confirmar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validacion(contraseña.getText().toString().trim(), contraseñaNueva.getText().toString().trim());
            }
        });
    }

    public void validacion(String pass, String newPass) {
        if (pass.equals("") || newPass.equals("")) {
            Toast.makeText(dialogo.getContext(), "ingrese todo los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.equals(newPass)) {
            Toast.makeText(dialogo.getContext(), "la contraseña no pueden ser iguales", Toast.LENGTH_SHORT).show();
            vaciar();
            return;
        }
        if (newPass.length() < 7) {
            Toast.makeText(dialogo.getContext(), "la contraseña no puede ser menor a 7 digitos", Toast.LENGTH_SHORT).show();
            vaciar();
            return;
        }
        vollyAc(cuenta + "", pass, newPass);
    }

    public void vaciar() {
        this.contraseñaNueva.setText("");
        this.contraseña.setText("");
    }

    public void vollyAc(final String usuario, final String pass, final String newPass) {
        RequestQueue queue = Volley.newRequestQueue(dialogo.getContext());
        String urlM = dialogo.getContext().getString(R.string.url) + "actualizar";

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json != null) {
                                Toast.makeText(dialogo.getContext(), "Se cambio la contraseña", Toast.LENGTH_SHORT).show();
                                dialogo.dismiss();
                                vaciar();
                            } else {
                                Toast.makeText(dialogo.getContext(), "No se cambio la contraseña", Toast.LENGTH_SHORT).show();
                                vaciar();
                            }
                        } catch (Exception e) {

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vaciar();
                Toast.makeText(dialogo.getContext(), "No se cambio la contraseña", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("cuentaId", usuario);
                params.put("contracena", pass);
                params.put("newContracena", newPass);
                return params;
            }

        };
        queue.add(jsonObjectRequest);
    }

}
