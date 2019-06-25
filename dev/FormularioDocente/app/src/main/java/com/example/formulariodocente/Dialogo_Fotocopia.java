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

public class Dialogo_Fotocopia {
    private EditText spinner;
    private EditText fecha;
    private EditText cantidad;
    private EditText materia;
    final Dialog dialogo;
    int cuentaID;
    Context context1;
    private ProgressDialog dialog;
    public Dialogo_Fotocopia(final Context context, int CuentaId) {
        context1 = context;
        dialogo = new Dialog(context);
        dialogo.setContentView(R.layout.vista_fotocopia);
        dialogo.setCancelable(true);
        cuentaID = CuentaId;
        init();
        volley();
        dialogo.show();
    }
    public void init(){
        this.spinner = dialogo.findViewById(R.id.modalidadFotocopia_spinner);
        this.materia = dialogo.findViewById(R.id.materiaFotocopiaI);
        this.fecha = dialogo.findViewById(R.id.fechaFotocopiaI);
        this.cantidad = dialogo.findViewById(R.id.cantidadFotocopiaI);
    }
    public void volley() {
        RequestQueue queue = Volley.newRequestQueue(dialogo.getContext());
        String urlM = dialogo.getContext().getString(R.string.url) + "Formularios/getFotocopia";
        dialog = ProgressDialog.show(dialogo.getContext(), "Cargando datos", "Por favor espere...");

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                materia.setText(jsonObject.getString("materia").toString());
                                cantidad.setText(jsonObject.getInt("cantidad")+"");
                                spinner.setText(jsonObject.getString("tipoDocuento").toString());
                                String[] fechaO = jsonObject.getString("fecha").split("T");
                                fecha.setText(fechaO[0]);
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
