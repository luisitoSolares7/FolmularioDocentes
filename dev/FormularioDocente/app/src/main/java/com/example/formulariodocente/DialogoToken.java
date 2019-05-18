package com.example.formulariodocente;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.modelos.Invitacion;

import org.json.JSONException;
import org.json.JSONObject;

public class DialogoToken {
    private Button btnVerificar;
    private ProgressDialog dialog;
    private TextInputEditText invitacionEd;
    private Context context1;
    final Dialog dialogo;

    public DialogoToken(final Context context) {
        context1 = context;
        dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.activity_verificacion__codigo);
        dialogo.setCancelable(true);
        init();
        dialogo.show();
    }
    public void init(){

        invitacionEd = dialogo.findViewById(R.id.invitacion);
        btnVerificar = dialogo.findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (invitacionEd.getText().toString().equals("")) {
                    Toast.makeText(dialogo.getContext(), "Ponga el codigo", Toast.LENGTH_SHORT).show();
                } else {
                    VolleyGet(invitacionEd.getText().toString());
                }
            }
        });
    }
    public void VolleyGet(String codigo) {

        RequestQueue queue = Volley.newRequestQueue(dialogo.getContext());
        final String url = dialogo.getContext().getString(R.string.url) + "invitacion/verify/"+codigo;

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Invitacion invitacion = null;
                            if (response != null) {
                                invitacion = new Invitacion(response.getString("codigoId"), response.getString("nombre"));
                                Intent downloadIntent = new Intent(dialogo.getContext(), Creacion_Cuenta.class);
                                downloadIntent.putExtra("token", invitacion.getId() + "");
                                downloadIntent.putExtra("nombre", invitacion.getToken() + "");
                                invitacionEd.setText("");
                                dialogo.getContext().startActivity(downloadIntent);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(dialogo.getContext(), "Error en el codigo", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(dialogo.getContext(), "Error en el codigo", Toast.LENGTH_SHORT).show();

                    }
                }
        );
        queue.add(getRequest);
    }

}
