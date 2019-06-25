package com.example.formulariodocente.modelos;

public class Listado {
    int id;
    int fkCuenta;
    int fkTbl;
    boolean estado;
    String nombre;
    int tipo;
    int autorizacion;

    public Listado(int id, int fkCuenta, int fkTbl, boolean estado, String nombre, int tipo, int autorizacion) {
        this.id = id;
        this.fkCuenta = fkCuenta;
        this.fkTbl = fkTbl;
        this.estado = estado;
        this.nombre = nombre;
        this.tipo = tipo;
        this.autorizacion = autorizacion;
    }

    public int getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(int autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Listado(int id, int fkCuenta, int fkTbl, boolean estado, String nombre, int tipo) {
        this.id = id;
        this.fkCuenta = fkCuenta;
        this.fkTbl = fkTbl;
        this.estado = estado;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkCuenta() {
        return fkCuenta;
    }

    public void setFkCuenta(int fkCuenta) {
        this.fkCuenta = fkCuenta;
    }

    public int getFkTbl() {
        return fkTbl;
    }

    public void setFkTbl(int fkTbl) {
        this.fkTbl = fkTbl;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
