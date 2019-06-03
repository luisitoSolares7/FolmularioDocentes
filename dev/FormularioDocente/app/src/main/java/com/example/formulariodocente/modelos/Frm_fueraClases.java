package com.example.formulariodocente.modelos;

public class Frm_fueraClases {
    private int id;
    private String fecha;
    private String materia;
    private String grupo;
    private String motivoA;
    private String fechaA;
    private String descA;
    private String lugar;
    private int cuentaID;
    public Frm_fueraClases() {
    }

    public Frm_fueraClases(String fecha, String materia, String grupo, String motivoA, String fechaA, String descA, String lugar, int cuentaID) {
        this.fecha = fecha;
        this.materia = materia;
        this.grupo = grupo;
        this.motivoA = motivoA;
        this.fechaA = fechaA;
        this.descA = descA;
        this.lugar = lugar;
        this.cuentaID = cuentaID;
    }

    public Frm_fueraClases(int id, String fecha, String materia, String grupo, String motivoA, String fechaA, String descA, String lugar) {
        this.id = id;
        this.fecha = fecha;
        this.materia = materia;
        this.grupo = grupo;
        this.motivoA = motivoA;
        this.fechaA = fechaA;
        this.descA = descA;
        this.lugar = lugar;
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

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getMotivoA() {
        return motivoA;
    }

    public void setMotivoA(String motivoA) {
        this.motivoA = motivoA;
    }

    public String getFechaA() {
        return fechaA;
    }

    public void setFechaA(String fechaA) {
        this.fechaA = fechaA;
    }

    public String getDescA() {
        return descA;
    }

    public void setDescA(String descA) {
        this.descA = descA;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getCuentaID() {
        return cuentaID;
    }

    public void setCuentaID(int cuentaID) {
        this.cuentaID = cuentaID;
    }
}
