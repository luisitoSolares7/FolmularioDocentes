package com.example.formulariodocente.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.Login;
import com.example.formulariodocente.R;
import com.example.formulariodocente.menu.MenuDrawerDocente;
import com.example.formulariodocente.modelos.Cuenta;
import com.example.formulariodocente.modelos.Frm_fueraClases;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("ValidFragment")
public class Fragment_form_fuera_clases extends Fragment {
    private View vista;
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
    private int hora, minutos;

    public Fragment_form_fuera_clases(int idCuenta) {
        this.cuentaID = idCuenta;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.form_fuera_de_clases, container, false);
        init();
        initComponent();
        return vista;
    }

    public void init() {
        btnFueraClases = vista.findViewById(R.id.bt_Fuera_clases);
        fecha = vista.findViewById(R.id.fechaFueraClases);
        horario = vista.findViewById(R.id.HoraFueraClases);
        fechaActividad = vista.findViewById(R.id.fechaFueraClasesActivid);
        horaActividad = vista.findViewById(R.id.HoraFueraClasesActivid);
        materia = vista.findViewById(R.id.materiaFueraClases);
        grupo = vista.findViewById(R.id.grupoFueraClases);
        descActividad = vista.findViewById(R.id.descripActividad);
        lugActividad = vista.findViewById(R.id.lugarActividad);
        objActividad = vista.findViewById(R.id.objActividad);
    }

    private void initComponent() {
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarFecha(1);
            }
        });
        fechaActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarFecha(2);
            }
        });
        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarHora(1);
            }
        });
        horaActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarHora(2);
            }
        });

        btnFueraClases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });
    }

    public void validar() {
        String materia = this.materia.getText().toString().trim();
        String grupo = this.grupo.getText().toString().trim();
        String fecha = this.fecha.getText().toString().trim();
        String hora = this.horario.getText().toString().trim();
        String fechaA = this.fechaActividad.getText().toString().trim();
        String horaA = this.horaActividad.getText().toString().trim();
        String descA = this.descActividad.getText().toString().trim();
        String lugA = this.lugActividad.getText().toString().trim();
        String objA = this.objActividad.getText().toString().trim();

        if (materia.equals("") || materia.length() < 5) {
            Toast.makeText(vista.getContext(), "ponga BIEN el nombre de la materia", Toast.LENGTH_SHORT).show();
            this.materia.setText("");
            return;
        }
        if (grupo.equals("") || grupo.length() > 2) {
            Toast.makeText(vista.getContext(), "ponga BIEN el nombre del grupo", Toast.LENGTH_SHORT).show();
           this.grupo.setText("");
            return;
        }
        if (fecha.equals("")) {
            Toast.makeText(vista.getContext(), "ponga la Fecha", Toast.LENGTH_SHORT).show();
            return;
        }
        if (hora.equals("")) {
            Toast.makeText(vista.getContext(), "ponga la Hora", Toast.LENGTH_SHORT).show();
            return;
        }
        if (fechaA.equals("")) {
            Toast.makeText(vista.getContext(), "ponga la Fecha", Toast.LENGTH_SHORT).show();
            return;
        }
        if (horaA.equals("")) {
            Toast.makeText(vista.getContext(), "ponga la Hora", Toast.LENGTH_SHORT).show();
            return;
        }
        if (descA.equals("") || descA.length() < 10) {
            Toast.makeText(vista.getContext(), "detalle bien en el campo descripcion de la Actividad", Toast.LENGTH_SHORT).show();
            descActividad.setText("");
            return;
        }
        if (lugA.equals("")) {
            Toast.makeText(vista.getContext(), "detalle el campo lugar de la Actividad", Toast.LENGTH_SHORT).show();
            return;
        }
        if (objA.equals("")) {
            Toast.makeText(vista.getContext(), "detalle bien en el campo objectivo de la Actividad", Toast.LENGTH_SHORT).show();
            return;
        }
        Frm_fueraClases clases = new Frm_fueraClases(fecha + " " + hora, materia, grupo, objA, fechaA + " " + horaA, descA, lugA, cuentaID);
        vollyAc(clases);
    }

    public void vollyAc(final Frm_fueraClases frm_fueraClases) {
        RequestQueue queue = Volley.newRequestQueue(vista.getContext());
        String urlM = getString(R.string.url) + "Formularios/insertFueraClases";
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
                params.put("id", frm_fueraClases.getCuentaID() + "");
                params.put("fecha", frm_fueraClases.getFecha());
                params.put("materia", frm_fueraClases.getMateria());
                params.put("grupo", frm_fueraClases.getGrupo());
                params.put("motivoActividad", frm_fueraClases.getMotivoA());
                params.put("fechaActividad", frm_fueraClases.getFechaA());
                params.put("descripActividad", frm_fueraClases.getDescA());
                params.put("lugarActividad", frm_fueraClases.getLugar());
                return params;
            }

        };
        queue.add(jsonObjectRequest);
    }

    public void vaciar() {
        fecha.setText("");
        horaActividad.setText("");
        horario.setText("");
        fechaActividad.setText("");
        materia.setText("");
        grupo.setText("");
        descActividad.setText("");
        lugActividad.setText("");
        objActividad.setText("");
    }

    private void mostrarFecha(final int num) {
        final Calendar calendario = Calendar.getInstance();

        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                String fechaq = String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                        + "-" + String.valueOf(dayOfMonth);
                if (num == 1) {
                    fecha.setText(fechaq);
                } else {
                    fechaActividad.setText(fechaq);
                }
            }
        }, calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    private void mostrarHora(final int num) {
        final Calendar calendario = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String laHora = hourOfDay + ":" + minute;
                if (num == 1) {
                    horario.setText(laHora);
                } else {
                    horaActividad.setText(laHora);
                }
            }
        }, hora, minutos, true);
        timePickerDialog.show();
    }

}
