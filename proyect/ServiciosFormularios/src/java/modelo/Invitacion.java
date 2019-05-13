/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author User
 */
public class Invitacion {
    String codigoId;
    String nombre;

    public Invitacion(String codigoId, String nombre) {
        this.codigoId = codigoId;
        this.nombre = nombre;
    }

    public Invitacion() {
    }

    public String getCodigoId() {
        return codigoId;
    }

    public void setCodigoId(String codigoId) {
        this.codigoId = codigoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
