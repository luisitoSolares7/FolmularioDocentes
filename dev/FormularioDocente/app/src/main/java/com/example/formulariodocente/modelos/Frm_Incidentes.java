package com.example.formulariodocente.modelos;

public class Frm_Incidentes {
    private int id;
    private String fecha;
    private String descripcion;
    private int idCuenta;

    public Frm_Incidentes() {
    }

    public Frm_Incidentes(int id, String fecha, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Frm_Incidentes(String fecha, String descripcion, int idCuenta) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.idCuenta = idCuenta;
    }

    public Frm_Incidentes(int id, String fecha, String descripcion, int idCuenta) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.idCuenta = idCuenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
