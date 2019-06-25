package com.example.formulariodocente;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DialogoFrmReprogramacion {
    final Dialog dialogo;
    private Context context1;
    private int cuentaID;
    private EditText carrera;
    private EditText materia;
    private EditText grupo;
    private EditText hora;
    private EditText fechaI;
    private EditText dias;
    private EditText fechaF;
    private TextView modalidad;
    private TextView obj_solicitud;
    private ProgressDialog dialog;

    public DialogoFrmReprogramacion(final Context context, int CuentaId) {
        context1 = context;
        dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.vista_frm_reprogramacion);
        dialogo.setCancelable(true);
        cuentaID = CuentaId;
        init();
        volley();
        dialogo.show();
    }

    private void init() {
        this.carrera = dialogo.findViewById(R.id.carreraRepro);
        this.materia = dialogo.findViewById(R.id.materiaRepro);
        this.grupo = dialogo.findViewById(R.id.grupoRepro);
        this.hora = dialogo.findViewById(R.id.horaRepro);
        this.fechaI = dialogo.findViewById(R.id.fechaRepro);
        this.fechaF = dialogo.findViewById(R.id.fechaF);
        this.dias = dialogo.findViewById(R.id.diasRepro);
        this.modalidad = dialogo.findViewById(R.id.modalidad_spinner);
        this.obj_solicitud = dialogo.findViewById(R.id.obj_solicitud);
        carrera.setEnabled(false);
        materia.setEnabled(false);
        grupo.setEnabled(false);
        hora.setEnabled(false);
        fechaI.setEnabled(false);
        fechaF.setEnabled(false);
        dias.setEnabled(false);
        modalidad.setEnabled(false);
        obj_solicitud.setEnabled(false);
    }

    public void volley() {
        RequestQueue queue = Volley.newRequestQueue(dialogo.getContext());
        String urlM = dialogo.getContext().getString(R.string.url) + "Formularios/getReprogramacion";
        dialog = ProgressDialog.show(dialogo.getContext(), "Cargando datos", "Por favor espere...");

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                carrera.setText(jsonObject.getString("carrera"));
                                materia.setText(jsonObject.getString("materia"));
                                obj_solicitud.setText(jsonObject.getString("motivoSolicitud"));
                                grupo.setText(jsonObject.getString("grupo"));
                                String laHora=jsonObject.getString("horaI").substring(0,5)+" "+jsonObject.getString("horaF").substring(0,5);
                                hora.setText(laHora);
                                fechaI.setText(jsonObject.getString("fecha").substring(0,10));
                                fechaF.setText(jsonObject.getString("fechaActividad").substring(0,10));
                                modalidad.setText(jsonObject.getString("modalidad"));
                                dias.setText(jsonObject.getString("dias"));
                            }
                        } catch (Exception e) {

                        }
                        dialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e("ERROR", error.getMessage());
                dialog.dismiss();
                Toast.makeText(dialogo.getContext(), "Ocurrio un error al cargar los DATOS!!!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", cuentaID + "");
                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }
}
