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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.R;
import com.example.formulariodocente.modelos.Frm_fueraClases;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@SuppressLint("ValidFragment")
public class Fragment_form_reprogramacion extends Fragment {
    public static final String REGEX_LETRAS = "[0-9]*$";
    private View vista;
    private int horas, minutos;
    private int idCuenta;
    private Spinner spinner;
    private EditText carrera;
    private EditText materia;
    private EditText grupo;
    private EditText hora;
    private EditText fechaI;
    private EditText dias;
    private EditText fechaF;
    private RadioButton rb_enfermedad;
    private RadioButton rb_viaje;
    private RadioButton rb_personal;
    private RadioButton rb_nur;
    private Button btn_enviar;
    private RadioGroup rg;
    private ProgressDialog dialog;
    private String nombre = "";

    public Fragment_form_reprogramacion(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.form_reprogramacion_clases, container, false);
        init();
        return vista;
    }

    private void initAccion() {
        int des = 0;
        if (rg.getCheckedRadioButtonId() == rb_nur.getId()) {
            des = 1;
            nombre = rb_nur.getText() + "";
        }
        if (rg.getCheckedRadioButtonId() == rb_personal.getId()) {
            des = 1;
            nombre = rb_personal.getText() + "";
        }
        if (rg.getCheckedRadioButtonId() == rb_viaje.getId()) {
            des = 1;
            nombre = rb_viaje.getText() + "";
        }
        if (rg.getCheckedRadioButtonId() == rb_enfermedad.getId()) {
            des = 1;
            nombre = rb_enfermedad.getText() + "";
        }
        if (des == 0) {
            Toast.makeText(vista.getContext(), "Tiene que selecionar el motivo de la solicitud", Toast.LENGTH_SHORT).show();
            return;
        }

        validacion(nombre);
    }

    private void validacion(String nombreM) {
        Pattern patron = Pattern.compile(REGEX_LETRAS);
        String fechai = this.fechaI.getText().toString().trim();
        String fechaf = this.fechaF.getText().toString().trim();
        String materia = this.materia.getText().toString().trim();
        String grupo = this.grupo.getText().toString().trim();
        String dia = this.dias.getText().toString().trim();
        String carrera = this.carrera.getText().toString().trim();
        String hora = this.hora.getText().toString().trim();
        String modalida = this.spinner.getSelectedItem().toString();

        if (fechai.equals("") && fechaf.equals("") && carrera.equals("") && materia.equals("") && dia.equals("") && grupo.equals("") && hora.equals("") && modalida.equals("******")) {
            Toast.makeText(vista.getContext(), "rellene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (fechai.equals("") || fechaf.equals("")) {
            Toast.makeText(vista.getContext(), "selecione una fecha", Toast.LENGTH_SHORT).show();
            return;
        }
        if (carrera.equals("") || carrera.length() < 5) {
            Toast.makeText(vista.getContext(), "Ponga bien la carrera", Toast.LENGTH_SHORT).show();
            return;
        }
        if (patron.matcher(carrera).matches()) {
            Toast.makeText(vista.getContext(), "el campo carrera no puede contener numeros", Toast.LENGTH_SHORT).show();
            this.carrera.setText("");
            return;
        }
        if (carrera.contains("0") || carrera.contains("1") || carrera.contains("2") || carrera.contains("3") || carrera.contains("4") || carrera.contains("5") || carrera.contains("6") || carrera.contains("7") || carrera.contains("8") || carrera.contains("9")) {
            Toast.makeText(vista.getContext(), "el campo carrera no puede contener numeros", Toast.LENGTH_SHORT).show();
            this.carrera.setText("");
            return;
        }
        if (dia.equals("")) {
            Toast.makeText(vista.getContext(), "el campo de dias no puede estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dia.contains("0") || dia.contains("1") || dia.contains("2") || dia.contains("3") || dia.contains("4") || dia.contains("5") || dia.contains("6") || dia.contains("7") || dia.contains("8") || dia.contains("9")) {
            Toast.makeText(vista.getContext(), "el campo DIA no puede contener numeros", Toast.LENGTH_SHORT).show();
            this.dias.setText("");
            return;
        }
        if (hora.equals("")) {
            Toast.makeText(vista.getContext(), "el campo hora no puede estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (grupo.equals("") || grupo.length() > 4) {
            Toast.makeText(vista.getContext(), "el campo grupo esta mal", Toast.LENGTH_SHORT).show();
            return;
        }

        if (patron.matcher(grupo).matches()) {
            Toast.makeText(vista.getContext(), "el campo grupo no puede contener numeros", Toast.LENGTH_SHORT).show();
            this.grupo.setText("");
            return;
        }
        if (grupo.contains("0") || grupo.contains("1") || grupo.contains("2") || grupo.contains("3") || grupo.contains("4") || grupo.contains("5") || grupo.contains("6") || grupo.contains("7") || grupo.contains("8") || grupo.contains("9")) {
            Toast.makeText(vista.getContext(), "el campo GRUPO no puede contener numeros", Toast.LENGTH_SHORT).show();
            this.grupo.setText("");
            return;
        }
        if (materia.equals("")) {
            Toast.makeText(vista.getContext(), "el campo materia no puede estar vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (patron.matcher(materia).matches()) {
            Toast.makeText(vista.getContext(), "el campo materia no puede contener numeros", Toast.LENGTH_SHORT).show();
            this.materia.setText("");
            return;
        }
        if (materia.contains("0") || materia.contains("1") || materia.contains("2") || materia.contains("3") || materia.contains("4") || materia.contains("5") || materia.contains("6") || materia.contains("7") || materia.contains("8") || materia.contains("9")) {
            Toast.makeText(vista.getContext(), "el campo MATERIA no puede contener numeros", Toast.LENGTH_SHORT).show();
            this.materia.setText("");
            return;
        }
        if (modalida.equals("******")) {
            Toast.makeText(vista.getContext(), "selecione una modalidad", Toast.LENGTH_SHORT).show();
            return;
        }
        vollyAc();
    }

    private void init() {
        this.carrera = vista.findViewById(R.id.carreraRepro);
        this.materia = vista.findViewById(R.id.materiaRepro);
        this.grupo = vista.findViewById(R.id.grupoRepro);
        this.hora = vista.findViewById(R.id.horaRepro);
        this.fechaI = vista.findViewById(R.id.fechaRepro);
        this.fechaF = vista.findViewById(R.id.fechaF);
        this.rb_enfermedad = vista.findViewById(R.id.opcion_enfermedad);
        this.rb_viaje = vista.findViewById(R.id.opcion_viaje);
        this.rb_nur = vista.findViewById(R.id.opcion_otro);
        this.rb_personal = vista.findViewById(R.id.opcion_personal);
        this.btn_enviar = vista.findViewById(R.id.btn_Repro);
        this.dias = vista.findViewById(R.id.diasRepro);
        this.rg = vista.findViewById(R.id.grupo_opciones);
        this.spinner = vista.findViewById(R.id.modalidad_spinner);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(vista.getContext(), R.array.array_modalidad, android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_adapter);
        this.btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAccion();
            }
        });
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.fechaI.setText(date);
        this.fechaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarFecha(1);
            }
        });
        this.hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarHora();
            }
        });
    }

    private void mostrarFecha(final int num) {
        final Calendar calendario = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                final int mesActual = monthOfYear + 1;
                String diaFormateado = (dayOfMonth < 10) ? 0 + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10) ? 0 + String.valueOf(mesActual) : String.valueOf(mesActual);
                String fechaq = String.valueOf(year) + "-" + mesFormateado
                        + "-" + diaFormateado;
                if (num == 0) {
                    fechaI.setText(fechaq);

                } else {
                    fechaF.setText(fechaq);
                }

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
                String horaFormateada = (hourOfDay < 10) ? 0 + String.valueOf(hourOfDay) : String.valueOf(hourOfDay);
                String minutoFormateado = (minute < 10) ? 0 + String.valueOf(minute) : String.valueOf(minute);
                String AM_PM;
                if (hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                String laHora = (horaFormateada + ":" + minutoFormateado);
                hora.setText(laHora);
                mostrarHoraa();
            }
        }, horas, minutos, true);
        timePickerDialog.show();
    }

    private void mostrarHoraa() {
        final Calendar calendario = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String horaFormateada = (hourOfDay < 10) ? 0 + String.valueOf(hourOfDay) : String.valueOf(hourOfDay);
                String minutoFormateado = (minute < 10) ? 0 + String.valueOf(minute) : String.valueOf(minute);
                String AM_PM;
                if (hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                String laHora = (horaFormateada + ":" + minutoFormateado);
                hora.setText(hora.getText() + " " + laHora);
            }
        }, horas, minutos, true);
        timePickerDialog.show();
    }

    public void vollyAc() {
        RequestQueue queue = Volley.newRequestQueue(vista.getContext());
        String urlM = getString(R.string.url) + "Formularios/insertReprogramacion";
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
                params.put("id", idCuenta + "");
                params.put("carrera", carrera.getText().toString().trim());
                params.put("dias", dias.getText().toString().trim());
                params.put("fecha", fechaI.getText().toString().trim());
                params.put("materia", materia.getText().toString().trim());
                params.put("grupo", grupo.getText().toString().trim());
                params.put("motivoSolicitud",nombre);
                params.put("fechaActividad", fechaF.getText().toString().trim());
                params.put("modalidad", spinner.getSelectedItem().toString());
                String[] horas = hora.getText().toString().split(" ");
                params.put("horaI", horas[0]);
                params.put("horaF", horas[1]);

                return params;
            }

        };
        queue.add(jsonObjectRequest);
    }

    public void vaciar() {
        this.carrera.setText("");
        this.materia.setText("");
        this.fechaF.setText("");
        this.grupo.setText("");
        this.hora.setText("");
        this.dias.setText("");
        rb_enfermedad.setChecked(false);
        rb_viaje.setChecked(false);
        rb_nur.setChecked(false);
        rb_personal.setChecked(false);
        spinner.setSelection(0);
    }
}
