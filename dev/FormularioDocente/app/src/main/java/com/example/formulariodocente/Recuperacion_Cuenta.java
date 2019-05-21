package com.example.formulariodocente;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.formulariodocente.httpclient.HttpConnection;
import com.example.formulariodocente.httpclient.MethodType;
import com.example.formulariodocente.httpclient.StandarRequestConfiguration;
import com.example.formulariodocente.modelos.CambioUsuario;
import com.example.formulariodocente.modelos.Invitacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

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
                hasit();
            }
        });
    }
        public void hasit() {
            AsyncTask<Void, String, CambioUsuario> task = new AsyncTask<Void, String, CambioUsuario>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    dialog = ProgressDialog.show(Recuperacion_Cuenta.this, "Cargando datos", "Por favor espere...");
                }

                @Override
                protected CambioUsuario doInBackground(Void... voids) {
                    CambioUsuario persona = null;
                    try {
                        persona = cargar();
                    } catch (Exception ex) {
                        Log.e("", "Error al cargar la lista de carreras", ex);
                        persona = null;
                    }

                    return persona;
                }

                @Override
                protected void onPostExecute(CambioUsuario usuarios) {
                    super.onPostExecute(usuarios);
                    dialog.dismiss();
                    if (usuarios != null) {
                        Intent downloadIntent = new Intent(Recuperacion_Cuenta.this, Cambiar_Contra.class);
                        downloadIntent.putExtra("nombre", usuarios.getnombreusuario());
                        Recuperar.setText("");
                        startActivity(downloadIntent);
                    } else {
                        Toast.makeText(Recuperacion_Cuenta.this, "El codigo ya fue insertado", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            task.execute();
        }

        public CambioUsuario cargar() {
            Hashtable<String, String> parametros = new Hashtable<>();
            parametros.put("usuario", Recuperar.getText().toString());
            StandarRequestConfiguration config = new StandarRequestConfiguration(getString(R.string.url), MethodType.GET, parametros);
            String json = HttpConnection.sendRequest(config);
            CambioUsuario cambiousuario = null;
            try {
                if (json != null) {
                    JSONObject obje = new JSONObject(json);
                    cambiousuario = new CambioUsuario(obje.getString("usuario"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return cambiousuario;

        }






}
