package com.example.formulariodocente.modelos;

public class Invitacion {
    String id;
    String token;

    public Invitacion() {
    }

    public Invitacion(String id, String token) {
        this.id = id;
        this.token = token;
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
