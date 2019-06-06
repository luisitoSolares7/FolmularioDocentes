package com.example.formulariodocente;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.EditText;
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

public class DialogoFrmIncidentes {
    final Dialog dialogo;
    private Context context1;
    private EditText fecha;
    private EditText horario;
    private EditText descActividad;
    private ProgressDialog dialog;
    private int cuentaID;

    public DialogoFrmIncidentes(final Context context, int CuentaId) {
        context1 = context;
        dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.vista_frm_incidente);
        dialogo.setCancelable(true);
        cuentaID = CuentaId;
        init();
        volley();
        dialogo.show();
    }

    private void init() {
        fecha = dialogo.findViewById(R.id.fechaIncidente);
        horario = dialogo.findViewById(R.id.horaIncidente);
        descActividad = dialogo.findViewById(R.id.descripIncidente);

        fecha.setEnabled(false);
        horario.setEnabled(false);
        descActividad.setEnabled(false);
    }

    public void volley() {
        RequestQueue queue = Volley.newRequestQueue(dialogo.getContext());
        String urlM = dialogo.getContext().getString(R.string.url) + "Formularios/getIncidente";
        dialog = ProgressDialog.show(dialogo.getContext(), "Cargando datos", "Por favor espere...");

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                descActividad.setText(jsonObject.getString("descripcion"));
                                String[] fechaO = jsonObject.getString("fecha").split("T");
                                fecha.setText(fechaO[0]);
                                horario.setText(fechaO[1]);

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

