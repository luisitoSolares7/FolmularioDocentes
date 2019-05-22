package com.example.formulariodocente;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.modelos.Invitacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Recuperacion_Cuenta extends AppCompatActivity {
    private View vista;
    private ProgressDialog dialog;
    private TextInputEditText Recuperar;
    private Button Confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion__cuenta);
        Recuperar = findViewById(R.id.recuperar);
        Confirmar = (Button) findViewById(R.id.Confirmar);
        Confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validacion(Recuperar.getText().toString().trim());
            }
        });
    }

    public void validacion(String correo) {
        if (correo.equals("")) {
            Recuperar.setText("");
            return;
        }
        if (!correo.contains("@gmail.com")) {
            Toast.makeText(Recuperacion_Cuenta.this, "el correo debe de ser Gmail", Toast.LENGTH_SHORT).show();
            Recuperar.setText("");
            return;
        }
        volley(correo);
    }

    private void volley(final String correo) {
        dialog = ProgressDialog.show(Recuperacion_Cuenta.this, "Cargando datos", "Por favor espere...");
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = this.getString(R.string.url) + "invitacion/actualizarInvitacion";

        StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            dialog.dismiss();
                            JSONObject json = new JSONObject(response);
                            Toast.makeText(Recuperacion_Cuenta.this, "Se le envio un correo...", Toast.LENGTH_SHORT).show();
                            finish();
                        } catch (Exception e) {
                            dialog.dismiss();
                            Toast.makeText(Recuperacion_Cuenta.this, "Correo invalido", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        Toast.makeText(Recuperacion_Cuenta.this, "Correo invalido", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("correo", correo);
                return params;
            }
        };
        queue.add(getRequest);
    }
}
