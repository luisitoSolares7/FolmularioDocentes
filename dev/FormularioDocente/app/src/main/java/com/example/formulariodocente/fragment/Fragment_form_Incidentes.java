package com.example.formulariodocente.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.R;
import com.example.formulariodocente.modelos.Frm_Incidentes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("ValidFragment")
public class Fragment_form_Incidentes extends Fragment {
    private View vista;
    private TextView fecha;
    private TextView hora;
    private TextView descripcion;
    private Button btnEnviar;
    private int horas, minuto;
    private ProgressDialog dialog;
    private int cuentaID;

    public Fragment_form_Incidentes(int id) {
        this.cuentaID = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.form_incidentes, container, false);
        init();
        initAcciones();
        return vista;
    }

    private void init() {
        this.descripcion = vista.findViewById(R.id.descripIncidente);
        this.fecha = vista.findViewById(R.id.fechaIncidente);
        this.hora = vista.findViewById(R.id.horaIncidente);
        this.btnEnviar = vista.findViewById(R.id.btn_Incidente);
    }

    private void initAcciones() {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.fecha.setText(date);
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarHora();
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaciones(fecha.getText().toString().trim(), hora.getText().toString().trim(), descripcion.getText().toString().trim());
            }
        });
    }

    private void validaciones(String fecha, String hora, String descripcion) {
        if (fecha.equals("") && hora.equals("") && descripcion.equals("")) {
            Toast.makeText(vista.getContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }
        if (fecha.equals("")) {
            Toast.makeText(vista.getContext(), "ponga la Fecha", Toast.LENGTH_SHORT).show();
        }
        if (hora.equals("")) {
            Toast.makeText(vista.getContext(), "ponga la Hora", Toast.LENGTH_SHORT).show();
        }
        if (descripcion.equals("")) {
            Toast.makeText(vista.getContext(), "Rellene el campo de descripcion", Toast.LENGTH_SHORT).show();
        }
        if (!fecha.equals("") && !hora.equals("") && !descripcion.equals("")) {
            volley(new Frm_Incidentes(fecha + " " + hora, descripcion, cuentaID));
        }
    }

    private void volley(final Frm_Incidentes incidentes) {
        RequestQueue queue = Volley.newRequestQueue(vista.getContext());
        String urlM = getString(R.string.url) + "Formularios/insertIncidentes";
        dialog = ProgressDialog.show(vista.getContext(), "Enviando datos", "Por favor espere...");

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, urlM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        if (response.equals("true")) {
                            vaciar();
                            Toast.makeText(vista.getContext(), "Se envio Correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(vista.getContext(), "ocurrio un error al enviar los datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(vista.getContext(), "ocurrio un error al enviar los datos", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", incidentes.getIdCuenta() + "");
                params.put("fecha", incidentes.getFecha());
                params.put("descripcion", incidentes.getDescripcion());
                return params;
            }

        };
        queue.add(jsonObjectRequest);
    }

    private void vaciar() {
        this.hora.setText("");
        this.descripcion.setText("");
    }

    private void mostrarFecha() {
        final Calendar calendario = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                final int mesActual = monthOfYear + 1;
                String diaFormateado = (dayOfMonth < 10) ? 0 + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10) ? 0 + String.valueOf(mesActual) : String.valueOf(mesActual);
                String fechaq = String.valueOf(year) + "-" + mesFormateado
                        + "-" + diaFormateado;
                fecha.setText(fechaq);

            }
        }, calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    private void mostrarHora() {

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String horaFormateada =  (hourOfDay < 10)?  0 + String.valueOf(hourOfDay) : String.valueOf(hourOfDay);
                String minutoFormateado = (minute < 10)?  0 + String.valueOf(minute):String.valueOf(minute);
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                String laHora=(horaFormateada + ":" + minutoFormateado);
                hora.setText(laHora);
            }
        }, horas, minuto, true);
        timePickerDialog.show();
    }
}
