package com.example.formulariodocente;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.formulariodocente.httpclient.HttpConnection;
import com.example.formulariodocente.httpclient.MethodType;
import com.example.formulariodocente.httpclient.StandarRequestConfiguration;
import com.example.formulariodocente.modelos.Invitacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

public class Verificacion_Codigo extends AppCompatActivity {
    private Button btnVerificar;
    private ProgressDialog dialog;
    private TextInputEditText invitacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion__codigo);
        invitacion = findViewById(R.id.invitacion);
        btnVerificar = findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasit();
            }
        });
    }

    public void hasit() {
        AsyncTask<Void, String, Invitacion> task = new AsyncTask<Void, String, Invitacion>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(Verificacion_Codigo.this, "Cargando datos", "Por favor espere...");
            }

            @Override
            protected Invitacion doInBackground(Void... voids) {
                Invitacion persona = null;
                try {
                    persona = cargar();
                } catch (Exception ex) {
                    Log.e("", "Error al cargar la lista de carreras", ex);
                    persona = null;
                }

                return persona;
            }

            @Override
            protected void onPostExecute(Invitacion usuarios) {
                super.onPostExecute(usuarios);
                dialog.dismiss();
                if (usuarios != null) {
                    Intent downloadIntent = new Intent(Verificacion_Codigo.this, Creacion_Cuenta.class);
                    downloadIntent.putExtra("token", usuarios.getId() + "");
                    downloadIntent.putExtra("nombre", usuarios.getToken() + "");
                    invitacion.setText("");
                    startActivity(downloadIntent);
                } else {
                    Toast.makeText(Verificacion_Codigo.this, "El codigo ya fue insertado", Toast.LENGTH_SHORT).show();
                }
            }
        };
        task.execute();
    }

    public Invitacion cargar() {
        Hashtable<String, String> parametros = new Hashtable<>();
        parametros.put("accion", "Codigo");
        parametros.put("contrasena", invitacion.getText().toString());
        StandarRequestConfiguration config = new StandarRequestConfiguration(getString(R.string.url), MethodType.GET, parametros);
        String json = HttpConnection.sendRequest(config);
        Invitacion invitacion = null;
        try {
            if (json != null) {
                JSONObject obje = new JSONObject(json);
                invitacion = new Invitacion(obje.getString("codigoId"), obje.getString("nombre"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return invitacion;

    }
}

