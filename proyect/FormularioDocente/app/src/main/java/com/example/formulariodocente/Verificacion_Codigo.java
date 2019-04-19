package com.example.formulariodocente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Verificacion_Codigo extends AppCompatActivity {
    private Button btnVerificar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion__codigo);

        btnVerificar = findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Verificacion_Codigo.this, Creacion_Cuenta.class);
                startActivity(intent);
            }
        });
    }
}
