package com.example.formulariodocente.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.formulariodocente.R;
import com.example.formulariodocente.modelos.Listado;

import java.util.ArrayList;

public class ListadoAdapter extends RecyclerView.Adapter<ListadoAdapter.ListadoViewHolder> {
    ArrayList<Listado> ListaSemestre;
    ListadoClick accionListener;


    public ListadoAdapter(ArrayList<Listado> objGalerias, ListadoClick listener) {
        this.accionListener = listener;
        this.ListaSemestre = objGalerias;

    }

    public static class ListadoViewHolder extends RecyclerView.ViewHolder {

        TextView texto_Nombre;
        ImageView imageView;

        public ListadoViewHolder(View itemView) {
            super(itemView);
            this.texto_Nombre = itemView.findViewById(R.id.nombre);
            this.imageView = itemView.findViewById(R.id.imagenItem);
        }

    }

    @Override
    public ListadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_item, parent, false);
        ListadoViewHolder holder = new ListadoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListadoViewHolder holder, int position) {
        final Listado obj = ListaSemestre.get(position);
        holder.texto_Nombre.setText(obj.getNombre());

        if (obj.getAutorizacion() == 0 && !obj.isEstado()) {
            holder.imageView.setImageResource(R.drawable.no);
        }else{
            holder.imageView.setImageResource(R.drawable.si);
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
        return ListaSemestre.size();

    }

    public ArrayList<Listado> getListaSemestre() {
        return ListaSemestre;
    }

    public void setListaSemestre(ArrayList<Listado> listaSemestre) {
        this.ListaSemestre = listaSemestre;
    }
}

