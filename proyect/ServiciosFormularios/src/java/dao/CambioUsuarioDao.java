/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfas.EstructuraCambioUsuario;
import interfas.EstructuraInvitacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.CambioUsuario;
import modelo.Invitacion;

/**
 *
 * @author User
 */
public class CambioUsuarioDao extends Conexion implements EstructuraCambioUsuario {

    @Override
    public CambioUsuario getRegistro(String nombre) {
         CambioUsuario cambiousuario = null;
          try {
            Conexion.conectar();
            String secuencia = "select * from p_RecuperacionContraseña('" + nombre + "');";
            PreparedStatement st = conexion.prepareStatement(secuencia);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getInt("estado") != 1) {
                    String usuario = rs.getString("nombre");
                    
                    cambiousuario = new CambioUsuario(usuario);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        Conexion.cerrarConexion();
        return cambiousuario;

    }
    }
    
