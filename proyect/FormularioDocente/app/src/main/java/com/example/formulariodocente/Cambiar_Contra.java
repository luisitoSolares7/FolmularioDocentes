package com.example.formulariodocente;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cambiar_Contra extends AppCompatActivity {
    private View vista;
    private TextInputEditText Contraseña;
    private TextInputEditText ContraseñaNueva;
    private Button Confirmar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar__contra);
        Contraseña=vista.findViewById(R.id.contraseña);
        ContraseñaNueva=vista.findViewById(R.id.nuevacontraseña);
        Confirmar1=(Button) findViewById(R.id.Confirmar);
    }
}
