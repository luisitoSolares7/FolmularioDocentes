package com.example.formulariodocente;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DialogoFrmFueraClases {
    final Dialog dialogo;
    private Context context1;
    private EditText fecha;
    private EditText horario;
    private EditText fechaActividad;
    private EditText horaActividad;
    private EditText materia;
    private EditText grupo;
    private EditText objActividad;
    private EditText descActividad;
    private EditText lugActividad;
    private Button btnFueraClases;
    private ProgressDialog dialog;
    private int cuentaID;

    public DialogoFrmFueraClases(final Context context) {
        context1 = context;
        dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.form_fuera_de_clases);
        dialogo.setCancelable(true);
        init();
        dialogo.show();
    }

    private void init() {
        fecha = dialogo.findViewById(R.id.fechaFueraClases);
        horario = dialogo.findViewById(R.id.HoraFueraClases);
        fechaActividad = dialogo.findViewById(R.id.fechaFueraClasesActivid);
        horaActividad = dialogo.findViewById(R.id.HoraFueraClasesActivid);
        materia = dialogo.findViewById(R.id.materiaFueraClases);
        grupo = dialogo.findViewById(R.id.grupoFueraClases);
        descActividad = dialogo.findViewById(R.id.descripActividad);
        lugActividad = dialogo.findViewById(R.id.lugarActividad);
        objActividad = dialogo.findViewById(R.id.objActividad);
        btnFueraClases=dialogo.findViewById(R.id.bt_Fuera_clases);
        fecha.setEnabled(false);
        btnFueraClases.setVisibility(View.INVISIBLE);
    }
    public void volley(){

    }
}
