/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfas;

import modelo.Cuenta;

/**
 *
 * @author User
 */
public interface  EstructuraCuenta {
    public Cuenta getAcceso(String user,String contraseña);
}
