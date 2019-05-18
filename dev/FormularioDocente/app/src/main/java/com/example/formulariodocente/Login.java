package com.example.formulariodocente;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class Login extends AppCompatActivity {

    private ProgressBar progreso;
    private FloatingActionButton fab;
    private View vista;
    private TextInputEditText user, password;
    TextView tengoCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vista = findViewById(android.R.id.content);
        progreso = (ProgressBar) findViewById(R.id.progress_bar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        tengoCodigo = findViewById(R.id.tengoCodigo);
        user = vista.findViewById(R.id.usuario);
        password = vista.findViewById(R.id.contrasena);
        tengoCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrir();
            }
        });
        (findViewById(R.id.olvidoContra)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Recuperacion_Cuenta.class);
                startActivity(intent);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                validadacion(user.getText().toString().trim(), password.getText().toString().trim());
            }
        });
    }

    public void validadacion(String nombre, String password) {
        if (nombre.equals("") && password.equals("")) {
            Toast.makeText(Login.this, "Ingrese bien los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nombre.equals("")) {
            Toast.makeText(Login.this, "Ingrese el nombre de usuario", Toast.LENGTH_SHORT).show();
            vaciar();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(Login.this, "Ingrese la contraseña", Toast.LENGTH_SHORT).show();
            vaciar();
            return;
        }
        if (password.length()<7) {
            Toast.makeText(Login.this, "la contraseña no puede ser menor a 7 digitos", Toast.LENGTH_SHORT).show();
            vaciar();
            return;
        }

        progreso.setVisibility(View.VISIBLE);
        fab.setAlpha(0f);
        vollyAc(nombre, password);

    }

    public void vollyAc(final String usuario, final String pass) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlM = getString(R.string.url) + "login";

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progreso.setVisibility(View.GONE);
                        fab.setAlpha(1f);

                        Cuenta cuenta = null;
                        try {
                            JSONObject json = new JSONObject(response);
                            if (response != null) {
                                cuenta = new Cuenta(json.getInt("cuentaId"), json.getString("nombreCuenta"), json.getString("contracena"), json.getInt("tipo"), json.getBoolean("estado"));
                            }
                            vaciar();
                            Intent intent = new Intent(Login.this, MenuDrawerDocente.class);
                            startActivity(intent);

                        } catch (JSONException e) {
                          //  e.printStackTrace();

                        }

                        Toast.makeText(Login.this, cuenta.getNombreCuenta(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e("ERROR", error.getMessage());
                vaciar();
                progreso.setVisibility(View.GONE);
                fab.setAlpha(1f);
                Toast.makeText(Login.this, "no existe usuario", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombreCuenta", usuario);
                params.put("contracena", pass);
                return params;
            }

        };
        queue.add(jsonObjectRequest);
    }

    public void abrir() {
        Toast.makeText(Login.this, "abrio", Toast.LENGTH_SHORT).show();
        DialogoToken dialog = new DialogoToken(this);
    }
    public void vaciar(){
        this.user.setText("");
        this.password.setText("");
    }
}
