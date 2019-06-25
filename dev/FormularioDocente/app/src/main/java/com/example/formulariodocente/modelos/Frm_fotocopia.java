package com.example.formulariodocente.modelos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Frm_fotocopia {
    private int id;
    private String fecha;
    private int cantidad;
    private String tipoDocuento;
    private String img1;
    private String img2;
    private String img3;
    private String materia;
    private String Imagen;
    private int desicion;
    private Bitmap imagen;

    public Frm_fotocopia(int id, String fecha, int cantidad, String tipoDocuento, String materia, int desicion) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipoDocuento = tipoDocuento;
        this.materia = materia;
        this.desicion = desicion;
    }

    public Frm_fotocopia(int id, String fecha, int cantidad, String tipoDocuento, String materia, String imagen, int desicion) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipoDocuento = tipoDocuento;
        this.materia = materia;
        Imagen = imagen;
        this.imagen=decodeBase64(imagen);
        this.desicion = desicion;
    }

    public Frm_fotocopia(int id, String fecha, int cantidad, String tipoDocuento, String img1, String img2, String img3, String materia, String imagen) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipoDocuento = tipoDocuento;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.materia = materia;
        Imagen = imagen;
        this.imagen=decodeBase64(imagen);
    }

    public Frm_fotocopia(int id, String fecha, int cantidad, String tipoDocuento, String materia, String imagen) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipoDocuento = tipoDocuento;
        this.materia = materia;
        Imagen = imagen;
        this.imagen=decodeBase64(imagen);
    }

    public Frm_fotocopia(int id, String fecha, int cantidad, String tipoDocuento, String materia) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipoDocuento = tipoDocuento;
        this.materia = materia;
    }

    private Bitmap decodeBase64(String imageString) {

        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        return decodedImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoDocuento() {
        return tipoDocuento;
    }

    public void setTipoDocuento(String tipoDocuento) {
        this.tipoDocuento = tipoDocuento;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public int getDesicion() {
        return desicion;
    }

    public void setDesicion(int desicion) {
        this.desicion = desicion;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
    public Bitmap getImagenQR() {
        return imagen;
    }

}
