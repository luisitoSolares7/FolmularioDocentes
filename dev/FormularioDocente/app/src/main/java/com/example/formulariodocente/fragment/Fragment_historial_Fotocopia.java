package com.example.formulariodocente.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.DialogoFrmFueraClases;
import com.example.formulariodocente.DialogoFrmIncidentes;
import com.example.formulariodocente.DialogoFrmReprogramacion;
import com.example.formulariodocente.DialogoQR;
import com.example.formulariodocente.R;
import com.example.formulariodocente.adapter.FotocopiaClick;
import com.example.formulariodocente.adapter.ListadoAdapter;
import com.example.formulariodocente.adapter.Listado_Fotocopia;
import com.example.formulariodocente.modelos.Frm_fotocopia;
import com.example.formulariodocente.modelos.Listado;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("ValidFragment")
public class Fragment_historial_Fotocopia extends Fragment implements FotocopiaClick {
    private RecyclerView recyclerView;
    View vista;
    int idCuenta;
    private ArrayList<Frm_fotocopia> formularios;

    private ProgressDialog dialog;


    public Fragment_historial_Fotocopia() {
    }

    public Fragment_historial_Fotocopia(int id) {
        this.idCuenta = id;
        this.formularios = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_historial_fotocopias, container, false);
        recyclerView = vista.findViewById(R.id.recyclerHistorial);
        this.vollyAc();
        return vista;
    }

    public void vollyAc() {
        RequestQueue queue = Volley.newRequestQueue(vista.getContext());
        String urlM = getString(R.string.url) + "Formularios/getFormularioFotocopia";
        dialog = ProgressDialog.show(vista.getContext(), "Cargando datos", "Por favor espere...");

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                if (jsonObject.isNull("Imagen")) {
                                    Frm_fotocopia user = new Frm_fotocopia(jsonObject.getInt("id"), jsonObject.getString("fecha"), jsonObject.getInt("cantidad"), jsonObject.getString("tipoDocuento"), jsonObject.getString("materia"), 0);
                                    formularios.add(user);
                                } else {
                                    Frm_fotocopia user = new Frm_fotocopia(jsonObject.getInt("id"), jsonObject.getString("fecha"), jsonObject.getInt("cantidad"), jsonObject.getString("tipoDocuento"), jsonObject.getString("materia"), jsonObject.getString("Imagen"), 1);
                                    formularios.add(user);
                                }
                            }
                        } catch (Exception e) {

                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        Listado_Fotocopia adapter = new Listado_Fotocopia(formularios, Fragment_historial_Fotocopia.this);
                        recyclerView.setAdapter(adapter);
                        dialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e("ERROR", error.getMessage());
                dialog.dismiss();
                Toast.makeText(vista.getContext(), "Ocurrio un error al cargar los DATOS!!!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", idCuenta + "");
                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }

    @Override
    public void actionListener(Frm_fotocopia obj, View view) {
        // Toast.makeText(vista.getContext(),obj.getFkTbl()+" "+obj.getTipo(),Toast.LENGTH_SHORT).show();
        if(obj.getDesicion()==1){
            new DialogoQR(vista.getContext(),obj.getImagenQR());
        }else{
            Toast.makeText(vista.getContext(),"no cuenta con un cotigo",Toast.LENGTH_SHORT).show();
        }

    }
}
