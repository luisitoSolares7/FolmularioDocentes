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
import com.example.formulariodocente.modelos.Cuenta;
import com.example.formulariodocente.modelos.Invitacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

public class Creacion_Cuenta extends AppCompatActivity {
    private TextInputEditText user, pass, pass2;
    private Button btn;
    private ProgressDialog dialog;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion__cuenta);
        this.user = findViewById(R.id.userCuenta);
        this.pass = findViewById(R.id.passCuenta);
        this.pass2 = findViewById(R.id.pass2Contra);
        this.btn = findViewById(R.id.btnCuenta);
        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            user.setText(parametros.getString("nombre") + "");
            token = parametros.getString("token");
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((pass.getText().toString()).equals(pass2.getText().toString())) {
                    if ((pass.getText().toString().length()) >= 6) {
                        AsyncTask<Void, String, Cuenta> task = new AsyncTask<Void, String, Cuenta>() {

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                dialog = ProgressDialog.show(Creacion_Cuenta.this, "Cargando datos", "Por favor espere...");
                            }

                            @Override
                            protected Cuenta doInBackground(Void... voids) {
                                Cuenta persona = null;
                                try {
                                    persona = cargar();
                                } catch (Exception ex) {
                                    Log.e("", "Error al cargar la lista de carreras", ex);
                                    persona = null;
                                }

                                return persona;
                            }

                            @Override
                            protected void onPostExecute(Cuenta usuarios) {
                                super.onPostExecute(usuarios);
                                dialog.dismiss();
                                if (usuarios != null) {
                                    finish();
                                    Toast.makeText(Creacion_Cuenta.this, "Se creo la cuenta", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Creacion_Cuenta.this, "No se creo la cuenta", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };
                        task.execute();

                    }
                }
            }
        });

    }

    public void hasit() {
        AsyncTask<Void, String, Cuenta> task = new AsyncTask<Void, String, Cuenta>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(Creacion_Cuenta.this, "Cargando datos", "Por favor espere...");
            }

            @Override
            protected Cuenta doInBackground(Void... voids) {
                Cuenta persona = null;
                try {
                    persona = cargar();
                } catch (Exception ex) {
                    Log.e("", "Error", ex);
                    persona = null;
                }

                return persona;
            }

            @Override
            protected void onPostExecute(Cuenta usuarios) {
                super.onPostExecute(usuarios);
                dialog.dismiss();
                if (usuarios != null) {
                    finish();
                } else {
                    Toast.makeText(Creacion_Cuenta.this, "No se creo la cuenta", Toast.LENGTH_SHORT).show();
                }
            }
        };
        task.execute();
    }

    public Cuenta cargar() {
        Hashtable<String, String> parametros = new Hashtable<>();
        parametros.put("accion", "Cuenta");
        parametros.put("contrasena", pass.getText().toString());
        parametros.put("usuario", user.getText().toString());
        parametros.put("token", token);

        StandarRequestConfiguration config = new StandarRequestConfiguration(getString(R.string.url), MethodType.GET, parametros);
        String json = HttpConnection.sendRequest(config);
        Cuenta invitacion = null;
        try {
            if (json != null) {
                JSONObject obje = new JSONObject(json);
                invitacion = new Cuenta(obje.getString("nombreCuenta"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return invitacion;

    }
}
