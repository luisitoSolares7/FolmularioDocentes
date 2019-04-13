package com.example.formulariodocente;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private ProgressBar progreso;
    private FloatingActionButton fab;
    private View vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vista = findViewById(android.R.id.content);
        progreso = (ProgressBar) findViewById(R.id.progress_bar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        ((View)findViewById(R.id.olvidoContra)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(vista, "Iniciando", Snackbar.LENGTH_SHORT).show();
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
    }
}
