package com.example.formulariodocente.modelos;

public class Cuenta {
    String id;
    String nombreCuenta;
    String contracena;

    public Cuenta() {
    }

    public Cuenta(String nombreCuenta, String contracena) {
        this.nombreCuenta = nombreCuenta;
        this.contracena = contracena;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
