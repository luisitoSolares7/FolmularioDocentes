/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfas.EstructuraCuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Cuenta;

/**
 *
 * @author User
 */
public class CuentaDao extends Conexion implements EstructuraCuenta {

    @Override
    public Cuenta getAcceso(String user, String contraseña) {
        Cuenta docente = null;
        try {
            Conexion.conectar();
            String secuencia = "select * from p_verificacionUsuarios('" + user + "','" + contraseña + "') where tipo=2;";
            PreparedStatement st = conexion.prepareStatement(secuencia);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String usuario = rs.getString("nombreCuenta");
                String pas = rs.getString("contracena");
                int id = rs.getInt("id");
                docente = new Cuenta(id, pas, usuario);

            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        Conexion.cerrarConexion();
        return docente;

    }

    @Override
    public Cuenta creacionCuenta(String user, String contraseña, String token) {
        Cuenta docente = null;
        try {
            Conexion.conectar();
            PreparedStatement stmt;

            String secuencia = "insert into tblCuenta(nombreCuenta,contracena,tipo,estado) "
                    + "values ('" + user + "',(select [dbo].[p_verificacionLogin]('" + contraseña + "')),2,1);";
            stmt = Conexion.conexion.prepareStatement(secuencia);
            stmt.executeUpdate();
            PreparedStatement ps = conexion.prepareStatement(
                    "UPDATE tblInvitacion SET fkCuenta = (select max(id) from tblCuenta), fechaRespuesta = getDate() , estado = 1 WHERE id = " + token+";");
            ps.executeUpdate();
            
            secuencia = "select * from tblCuenta where id= (select max(id) from tblCuenta);";
            stmt = conexion.prepareStatement(secuencia);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String usuario = rs.getString("nombreCuenta");
                docente = new Cuenta(usuario);
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        Conexion.cerrarConexion();
        return docente;
    }

}
