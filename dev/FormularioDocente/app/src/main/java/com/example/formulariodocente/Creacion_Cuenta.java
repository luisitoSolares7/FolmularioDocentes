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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.httpclient.HttpConnection;
import com.example.formulariodocente.httpclient.MethodType;
import com.example.formulariodocente.httpclient.StandarRequestConfiguration;
import com.example.formulariodocente.menu.MenuDrawerDocente;
import com.example.formulariodocente.modelos.Cuenta;
import com.example.formulariodocente.modelos.Invitacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

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
                validacion(pass.getText().toString().trim(), pass2.getText().toString().trim(), user.getText().toString().trim());
            }
        });

    }

    public void validacion(String pass1, String pass2, String user) {
        if (pass1.equals("") || pass2.equals("") || user.equals("")) {
            Toast.makeText(Creacion_Cuenta.this, "debe de llenar todos los campos ", Toast.LENGTH_SHORT).show();
            this.pass.setText("");
            this.pass2.setText("");
            this.user.setText("");
            return;
        }
        if (!(pass1.equals(pass2))) {
            Toast.makeText(Creacion_Cuenta.this, "las contraseñas tiene que ser iguales", Toast.LENGTH_SHORT);
            this.pass.setText("");
            this.pass2.setText("");

            return;
        }
        if (pass1.length() < 7 || pass2.length() < 7) {
            Toast.makeText(Creacion_Cuenta.this, "la contraseña no puede ser menor a 7 digitos", Toast.LENGTH_SHORT).show();
            this.pass2.setText("");
            this.pass.setText("");
            return;
        }
        voleyGet(pass1, user, token);
    }

    private void voleyGet(final String pass, final String user, final String token) {
        dialog = ProgressDialog.show(Creacion_Cuenta.this, "Cargando datos", "Por favor espere...");
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlM = getString(R.string.url) + "creacionCuenta";

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        Toast.makeText(Creacion_Cuenta.this, "Se creo la Cuenta... ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Log.e("ERROR", error.getMessage());
                Toast.makeText(Creacion_Cuenta.this, "No se pudo crear la Cuenta", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("contracena", pass);
                params.put("nombreCuenta", user);
                params.put("cuentaId", token);
                return params;
            }

        };
        queue.add(jsonObjectRequest);
    }

}
