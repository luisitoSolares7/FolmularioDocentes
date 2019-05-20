package com.example.formulariodocente.modelos;

public class Cuenta {
    private int id;
    private String nombreCuenta;
    private String contracena;
    private int tipo;
    private boolean estado;

    public Cuenta() {
    }

    public Cuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public Cuenta(int id, String nombreCuenta, String contracena, int tipo, boolean estado) {
        this.id = id;
        this.nombreCuenta = nombreCuenta;
        this.contracena = contracena;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Cuenta(int id, String nombreCuenta, String contracena) {
        this.id = id;
        this.nombreCuenta = nombreCuenta;
        this.contracena = contracena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getContracena() {
        return contracena;
    }

    public void setContracena(String contracena) {
        this.contracena = contracena;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
