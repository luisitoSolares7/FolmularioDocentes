/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfas.EstructuraInvitacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Cuenta;
import modelo.Invitacion;

/**
 *
 * @author User
 */
public class InvitacionDao extends Conexion implements EstructuraInvitacion {

    @Override
    public Invitacion getRegistro(String codigo) {
        Invitacion invitacion = null;
        try {
            Conexion.conectar();
            String secuencia = "select * from p_verificacionCodigo('"+codigo+"');";
            PreparedStatement st = conexion.prepareStatement(secuencia);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String usuario = rs.getString("nombre");
                int id = rs.getInt("invitacion");
                invitacion = new Invitacion(id,usuario);
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        Conexion.cerrarConexion();
        return invitacion;

    }

}
