package com.example.formulariodocente;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.formulariodocente.httpclient.HttpConnection;
import com.example.formulariodocente.httpclient.MethodType;
import com.example.formulariodocente.httpclient.StandarRequestConfiguration;
import com.example.formulariodocente.modelos.Cuenta;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

public class Login extends AppCompatActivity {

    private ProgressBar progreso;
    private FloatingActionButton fab;
    private View vista;
    private TextInputEditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vista = findViewById(android.R.id.content);
        progreso = (ProgressBar) findViewById(R.id.progress_bar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        user = vista.findViewById(R.id.usuario);
        password = vista.findViewById(R.id.contrasena);

        ((View) findViewById(R.id.tengoCodigo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Verificacion_Codigo.class);
                startActivity(intent);
            }
        });
        ((View) findViewById(R.id.olvidoContra)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Recuperacion_Cuenta.class);
                startActivity(intent);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (user.getText().toString().equals("") && password.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Ingrese bien los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (user.getText().toString().equals("")) {
                        Toast.makeText(Login.this, "Ingrese el nombre de usuario", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.getText().toString().equals("")) {
                            Toast.makeText(Login.this, "Ingrese la contraceña", Toast.LENGTH_SHORT).show();
                        } else {
                            hasit();
                        }
                    }
                }
            }

    });
}

    private void searchAction() {
        progreso.setVisibility(View.VISIBLE);
        fab.setAlpha(0f);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                progreso.setVisibility(View.GONE);
                fab.setAlpha(1f);
            }

            ;
        }, 1000);
    }

    public void hasit() {


        AsyncTask<Void, String, Cuenta> task = new AsyncTask<Void, String, Cuenta>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progreso.setVisibility(View.VISIBLE);
                fab.setAlpha(0f);

            }

            @Override
            protected Cuenta doInBackground(Void... voids) {
                Cuenta docente = null;
                try {
                    docente = cargar();
                } catch (Exception ex) {
                    Log.e("", "Error al cargar la lista de carreras", ex);
                    docente = null;
                }

                return docente;
            }

            @Override
            protected void onPostExecute(Cuenta usuarios) {
                super.onPostExecute(usuarios);
                progreso.setVisibility(View.GONE);
                fab.setAlpha(1f);
                if (usuarios != null) {
                    Toast.makeText(Login.this, "Bienvenido " + usuarios.getNombreCuenta().toString(), Toast.LENGTH_SHORT).show();
                    user.setText("");
                    password.setText("");
                    //finish();
                } else {
                    Toast.makeText(Login.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                    user.setText("");
                    password.setText("");
                }
            }
        };
        task.execute();
    }

    public Cuenta cargar() {
        Hashtable<String, String> parametros = new Hashtable<>();
        parametros.put("accion", "Login");
        parametros.put("usuario", user.getText().toString() + "");
        parametros.put("contrasena", password.getText().toString() + "");
        StandarRequestConfiguration config = new StandarRequestConfiguration(getString(R.string.url), MethodType.GET, parametros);
        String json = HttpConnection.sendRequest(config);
        Cuenta cuenta = null;
        try {
            if (json != null) {
                JSONObject obje = new JSONObject(json);
                cuenta = new Cuenta(obje.getInt("cuentaId"), obje.getString("nombreCuenta"), obje.getString("contracena"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (cuenta == null) {
            Toast.makeText(null, "eroor", Toast.LENGTH_SHORT).show();
        }

        return cuenta;

    }
}
