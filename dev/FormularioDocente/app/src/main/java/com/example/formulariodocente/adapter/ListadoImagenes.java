package com.example.formulariodocente.adapter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.formulariodocente.R;
import com.example.formulariodocente.modelos.ModeloImg;

import java.util.ArrayList;

public class ListadoImagenes extends  RecyclerView.Adapter<ListadoImagenes.GaleriaViewHolder>{
    ArrayList<ModeloImg> galerias;
    ListadoImagenesClick click;
    public ListadoImagenes(ArrayList<ModeloImg> objGalerias,ListadoImagenesClick click){
        this.galerias =objGalerias;
        this.click=click;
    }
    public static class GaleriaViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;


        public GaleriaViewHolder(View itemView){
            super(itemView);
            this.imagen=itemView.findViewById(R.id.imagenVista);
        }

    }

    @Override
    public GaleriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vista_imagen,parent,false);
        GaleriaViewHolder holder=new GaleriaViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(GaleriaViewHolder holder, int position) {
        final ModeloImg obj= galerias.get(position);
        holder.imagen.setImageBitmap(obj.getBitmap());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click != null)
                    click.actionListener(obj, view);
            }
        });
    }
    @Override
    public int getItemCount() {
        return galerias.size();

    }

    public ArrayList<ModeloImg> getGalerias() {
        return galerias;
    }

    public void setGalerias(ArrayList<ModeloImg> galerias) {
        this.galerias = galerias;
    }
}

