package com.example.formulariodocente.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.DialogoFrmFueraClases;
import com.example.formulariodocente.DialogoFrmIncidentes;
import com.example.formulariodocente.R;
import com.example.formulariodocente.adapter.ListadoAdapter;
import com.example.formulariodocente.adapter.ListadoClick;
import com.example.formulariodocente.modelos.Listado;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("ValidFragment")
public class Fragment_Historial extends Fragment implements ListadoClick {
    private RecyclerView recyclerView;
    View vista;
    int idCuenta;
    private ArrayList<Listado> formularios;

    private ProgressDialog dialog;


    public Fragment_Historial() {
    }

    public Fragment_Historial(int id) {
        this.idCuenta = id;
        this.formularios = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_historial, container, false);
        recyclerView = vista.findViewById(R.id.recyclerHistorial);
        this.vollyAc();
        return vista;
    }

    public void vollyAc() {
        RequestQueue queue = Volley.newRequestQueue(vista.getContext());
        String urlM = getString(R.string.url) + "listado/getFormularios";
        dialog = ProgressDialog.show(vista.getContext(), "Cargando datos", "Por favor espere...");

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String g;
                                Listado user = new Listado(jsonObject.getInt("id"), jsonObject.getInt("fkCuenta"), jsonObject.getInt("fkTbl"), jsonObject.getBoolean("estado"), jsonObject.getString("nombre"), jsonObject.getInt("tipo"));
                                formularios.add(user);
                            }
                        } catch (Exception e) {

                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        ListadoAdapter adapter = new ListadoAdapter(formularios, Fragment_Historial.this);
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
    public void actionListener(Listado obj, View view) {
        // Toast.makeText(vista.getContext(),obj.getFkTbl()+" "+obj.getTipo(),Toast.LENGTH_SHORT).show();
        if(obj.getTipo()==0){
            new DialogoFrmIncidentes(vista.getContext(),obj.getFkTbl());
        }
        if (obj.getTipo() == 1) {
            new DialogoFrmFueraClases(vista.getContext(),obj.getFkTbl());
        }


    }
}
