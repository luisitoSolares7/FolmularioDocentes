package com.example.formulariodocente.modelos;

public class Invitacion {
    private String id;
    private String token;
    private int fkCuenta;
    public Invitacion() {
    }

    public Invitacion(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public Invitacion(String id, String token, int fkCuenta) {
        this.id = id;
        this.token = token;
        this.fkCuenta = fkCuenta;
    }

    public int getFkCuenta() {
        return fkCuenta;
    }

    public void setFkCuenta(int fkCuenta) {
        this.fkCuenta = fkCuenta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
