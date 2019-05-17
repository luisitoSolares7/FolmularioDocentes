package com.example.formulariodocente;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.modelos.Invitacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

public class Verificacion_Codigo extends AppCompatActivity {
    private Button btnVerificar;
    private ProgressDialog dialog;
    private TextInputEditText invitacionEd;

    public void VolleyGet(String codigo) {

        RequestQueue queue = Volley.newRequestQueue(Verificacion_Codigo.this);
        final String url = getString(R.string.url) + "invitacion/verify/"+codigo;

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Invitacion invitacion = null;
                            if (response != null) {
                                invitacion = new Invitacion(response.getString("codigoId"), response.getString("nombre"));
                                Intent downloadIntent = new Intent(Verificacion_Codigo.this, Creacion_Cuenta.class);
                                downloadIntent.putExtra("token", invitacion.getId() + "");
                                downloadIntent.putExtra("nombre", invitacion.getToken() + "");
                                invitacionEd.setText("");
                                startActivity(downloadIntent);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(Verificacion_Codigo.this, "Error en el codigo", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Verificacion_Codigo.this, "Error en el codigo", Toast.LENGTH_SHORT).show();

                    }
                }
        );
        queue.add(getRequest);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion__codigo);
        invitacionEd = findViewById(R.id.invitacion);
        btnVerificar = findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (invitacionEd.getText().toString().equals("")) {
                    Toast.makeText(Verificacion_Codigo.this, "Ponga el codigo", Toast.LENGTH_SHORT).show();
                } else {
                    VolleyGet(invitacionEd.getText().toString());
                }
            }
        });
    }

}

