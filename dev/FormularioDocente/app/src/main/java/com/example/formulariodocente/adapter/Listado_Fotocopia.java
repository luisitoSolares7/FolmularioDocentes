package com.example.formulariodocente.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.formulariodocente.R;
import com.example.formulariodocente.modelos.Frm_fotocopia;
import com.example.formulariodocente.modelos.Listado;

import java.util.ArrayList;

public class Listado_Fotocopia extends RecyclerView.Adapter<Listado_Fotocopia.ListadoViewHolder> {
    ArrayList<Frm_fotocopia> ListaFotocopia;
    FotocopiaClick accionListener;


    public Listado_Fotocopia(ArrayList<Frm_fotocopia> objFotocopia, FotocopiaClick listener) {
        this.accionListener = listener;
        this.ListaFotocopia = objFotocopia;

    }

    public static class ListadoViewHolder extends RecyclerView.ViewHolder {

        TextView texto_Nombre_materia;
        ImageView imageView;

        public ListadoViewHolder(View itemView) {
            super(itemView);
            this.texto_Nombre_materia = itemView.findViewById(R.id.nombreMateria);
            this.imageView=itemView.findViewById(R.id.imagenIte);
        }

    }

    @Override
    public Listado_Fotocopia.ListadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_fotocopia_item, parent, false);
        Listado_Fotocopia.ListadoViewHolder holder = new Listado_Fotocopia.ListadoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Listado_Fotocopia.ListadoViewHolder holder, int position) {
        final Frm_fotocopia obj = ListaFotocopia.get(position);
        holder.texto_Nombre_materia.setText(obj.getMateria()+" "+obj.getTipoDocuento());
        if(obj.getDesicion()== 1){
            holder.imageView.setImageResource(R.drawable.si);
        }
        if(obj.getDesicion()== 0){
            holder.imageView.setImageResource(R.drawable.not);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accionListener != null)
                    accionListener.actionListener(obj, view);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ListaFotocopia.size();

    }

    public ArrayList<Frm_fotocopia> getListaSemestre() {
        return ListaFotocopia;
    }

    public void setListaSemestre(ArrayList<Frm_fotocopia> listaSemestre) {
        this.ListaFotocopia = listaSemestre;
    }
}

