package com.example.formulariodocente;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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


            }
        });
    }
}
