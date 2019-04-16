package com.example.formulariodocente;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Login extends AppCompatActivity {

    private ProgressBar progreso;
    private FloatingActionButton fab;
    private View vista;
    private TextInputEditText user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        vista = findViewById(android.R.id.content);
        this.user = vista.findViewById(R.id.user);

        progreso = (ProgressBar) findViewById(R.id.progress_bar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        ((View) findViewById(R.id.olvidoContra)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Verificacion_Codigo.class);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                searchAction();
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
        if (user.getText().toString() != null) {
            autentificacion();
        }
    }

    public void autentificacion() {

        Thread hilo = new Thread() {
            String respuesta = "";

            @Override
            public void run() {
                String nameSpace = "";
                String url = "";
                String metodoNombre = "";
                String accionSoap = "";
                SoapObject request = new SoapObject(nameSpace, metodoNombre);
                request.addProperty("usuario", user.getText().toString());
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte = new HttpTransportSE(url);

                try {
                    transporte.call(accionSoap, envelope);
                    SoapPrimitive respuesta_xml = (SoapPrimitive) envelope.getResponse();
                    respuesta = respuesta_xml.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Login.this, respuesta, Toast.LENGTH_LONG).show();
                    }
                });
            }
        };

        hilo.start();
    }
}
