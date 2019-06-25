package com.example.formulariodocente.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.formulariodocente.R;
import com.example.formulariodocente.adapter.ListadoImagenes;
import com.example.formulariodocente.adapter.ListadoImagenesClick;
import com.example.formulariodocente.modelos.ModeloImg;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

@SuppressLint("ValidFragment")
public class Fragment_form_Fotocopia extends Fragment implements ListadoImagenesClick {
    public static final String REGEX_LETRAS = "[0-9]*$";
    private View vista;
    public ArrayList<ModeloImg> list;
    private Button btnCa;
    RecyclerView recyclerView;
    private Spinner spinner;
    private EditText fecha;
    private EditText cantidad;
    private EditText materia;
    private int id_cuenta;
    ProgressDialog dialog;
    private int la_cantidad = 0;
    private ImageView pulsador;

    public Fragment_form_Fotocopia(int cuenta) {
        list = new ArrayList<>();
        this.id_cuenta = cuenta;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.form_fotocopia, container, false);
        init();
        return vista;
    }

    public void init() {
        btnCa = vista.findViewById(R.id.btn_form);
        recyclerView = vista.findViewById(R.id.recyclerImages);
        this.spinner = vista.findViewById(R.id.modalidadFotocopia_spinner);
        this.materia = vista.findViewById(R.id.materiaFotocipia);
        ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(vista.getContext(), R.array.array_tipoFotocopia, android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_adapter);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.fecha = vista.findViewById(R.id.fechaFotocopia);
        this.fecha.setText(date);
        this.cantidad = vista.findViewById(R.id.cantidadFotocipia);
        this.pulsador = vista.findViewById(R.id.pulsador);
        pulsador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicacion"), 10);
            }
        });
        btnCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validacion(cantidad.getText().toString().trim(), materia.getText().toString().trim());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri dataUri = data.getData();

            if (list.size() < 3) {
                new ConversAsyntask(dataUri).execute();
                la_cantidad++;
                Toast.makeText(vista.getContext(), la_cantidad + "", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(vista.getContext(), "solo se puede Cargar 3 imagenes", Toast.LENGTH_SHORT).show();
            }
            if (la_cantidad == 3) {
                pulsador.setVisibility(View.GONE);
            }
        }
    }

    public void validacion(String cantidad, String materia) {
        String modalida = this.spinner.getSelectedItem().toString();
        Pattern patron = Pattern.compile(REGEX_LETRAS);
        if (cantidad.equals("") && materia.equals("") && modalida.equals("******")) {
            Toast.makeText(vista.getContext(), "rellene los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (cantidad.equals("")) {
            Toast.makeText(vista.getContext(), "el campo cantidad no puede ser vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!patron.matcher(cantidad).matches()) {
            Toast.makeText(vista.getContext(), "el campo cantidad solo puede contener numeros", Toast.LENGTH_SHORT).show();
            this.cantidad.setText("");
            return;
        }
        if (materia.equals("")) {
            Toast.makeText(vista.getContext(), "el campo materia no puede ser vacio", Toast.LENGTH_SHORT).show();
            this.materia.setText("");
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
        if (!(list.size() > 0)) {
            Toast.makeText(vista.getContext(), "selecione una foto", Toast.LENGTH_SHORT).show();
            return;
        }
        volley();
    }

    public void volley() {
        RequestQueue queue = Volley.newRequestQueue(vista.getContext());
        String urlM = getString(R.string.url) + "Formularios/insertFotocopia";
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
                params.put("id", id_cuenta + "");
                params.put("cantidad", cantidad.getText().toString());
                params.put("materia", materia.getText().toString());
                params.put("fecha", fecha.getText().toString());
                params.put("tipoDocumento", spinner.getSelectedItem().toString());

                if (la_cantidad == 3) {
                    params.put("img1", list.get(0).getEncode64());
                    params.put("img2", list.get(1).getEncode64());
                    params.put("img3", list.get(2).getEncode64());
                }
                if (la_cantidad == 2) {
                    params.put("img1", list.get(0).getEncode64());
                    params.put("img2", list.get(1).getEncode64());
                    params.put("img3", " ");
                }
                if (la_cantidad == 1) {
                    params.put("img1", list.get(0).getEncode64());
                    params.put("img2", " ");
                    params.put("img3", " ");
                }
                return params;
            }

        };
        queue.add(jsonObjectRequest);
    }

    @Override
    public void actionListener(ModeloImg obj, View view) {
        Toast.makeText(vista.getContext(), obj.getEncode64() + "", Toast.LENGTH_SHORT).show();
    }

    private class ConversAsyntask extends AsyncTask<Uri, Void, ModeloImg> {

        private ProgressDialog progreso;
        private Uri uri;

        public ConversAsyntask(Uri uri) {
            progreso = ProgressDialog.show(vista.getContext(), "Cargando datos", "Por favor espere...");
            this.uri = uri;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ModeloImg doInBackground(Uri... uril) {
            ModeloImg modeloImg = null;
            try {
                ParcelFileDescriptor parcelFileDescriptor =
                        vista.getContext().getContentResolver().openFileDescriptor(uri, "r");
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                parcelFileDescriptor.close();
                modeloImg = new ModeloImg(image);
            } catch (Exception e) {

            }
            return modeloImg;
        }

        @Override
        protected void onPostExecute(ModeloImg resp) {
            super.onPostExecute(resp);
            progreso.dismiss();
            list.add(resp);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            ListadoImagenes adapter = new ListadoImagenes(list, Fragment_form_Fotocopia.this);
            recyclerView.setAdapter(adapter);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }

    public void vaciar() {
        this.cantidad.setText("");
        this.materia.setText("");
        spinner.setSelection(0);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ListadoImagenes adapter = new ListadoImagenes(list, Fragment_form_Fotocopia.this);
        recyclerView.setAdapter(adapter);

    }

}
