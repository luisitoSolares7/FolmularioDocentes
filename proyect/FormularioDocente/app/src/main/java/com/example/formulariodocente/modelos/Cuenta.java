package com.example.formulariodocente.modelos;

public class Cuenta {
    int id;
    String nombreCuenta;
    String contracena;

    public Cuenta() {
    }

    public Cuenta(int id,String nombreCuenta, String contracena) {
        this.id=id;
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
}
