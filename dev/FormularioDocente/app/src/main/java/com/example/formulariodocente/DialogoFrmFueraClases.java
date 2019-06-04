package com.example.formulariodocente;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.adapter.ListadoAdapter;
import com.example.formulariodocente.fragment.Fragment_Historial;
import com.example.formulariodocente.modelos.Listado;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    private ProgressDialog dialog;
    private int cuentaID;

    public DialogoFrmFueraClases(final Context context, int CuentaId) {
        context1 = context;
        dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.vista_frm_fdc);
        dialogo.setCancelable(true);
        cuentaID = CuentaId;
        init();
        volley();
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

        fecha.setEnabled(false);
        materia.setEnabled(false);
        horario.setEnabled(false);
        fechaActividad.setEnabled(false);
        horaActividad.setEnabled(false);
        grupo.setEnabled(false);
        descActividad.setEnabled(false);
        lugActividad.setEnabled(false);
        objActividad.setEnabled(false);
    }

    public void volley() {
        RequestQueue queue = Volley.newRequestQueue(dialogo.getContext());
        String urlM = dialogo.getContext().getString(R.string.url) + "Formularios/getFueraClases";
        dialog = ProgressDialog.show(dialogo.getContext(), "Cargando datos", "Por favor espere...");

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                materia.setText(jsonObject.getString("materia"));
                                grupo.setText(jsonObject.getString("grupo"));
                                objActividad.setText(jsonObject.getString("motivoActividad"));
                                descActividad.setText(jsonObject.getString("descripActividad"));
                                lugActividad.setText(jsonObject.getString("lugarActividad"));
                                String[] fechaO = jsonObject.getString("fecha").split("T");

                                fecha.setText(fechaO[0]);
                                horario.setText(fechaO[1]);
                                fechaO = jsonObject.getString("fecha").split("T");
                                fechaActividad.setText(fechaO[0]);
                                horaActividad.setText(fechaO[1]);

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
