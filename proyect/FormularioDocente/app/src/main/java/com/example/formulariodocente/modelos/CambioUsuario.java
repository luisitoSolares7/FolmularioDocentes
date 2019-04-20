package com.example.formulariodocente.modelos;

public class CambioUsuario {
    String nombreusuario;

    public CambioUsuario() {
    }

    public CambioUsuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;

    }

    public String getnombreusuario() {
        return nombreusuario;
    }

    public void setnombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

}