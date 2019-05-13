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
public class CambioUsuario {
    String nombreusuario;

public CambioUsuario(String nombreusuario) {
     this.nombreusuario = nombreusuario;
    }

    public CambioUsuario() {
    }

    public String getCnombreusuario() {
        return nombreusuario;
    }

    public void setnombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }
}
