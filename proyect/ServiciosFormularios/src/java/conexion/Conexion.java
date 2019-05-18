/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author User
 */
public class Conexion {
    private static String url = "jdbc:sqlserver://DESKTOP-KBJ9AML\\SQLEXPRESS:1433;"
            + "databaseName=Formularios; user=luis; password=luis;";
    protected static Connection conexion;

    public static void conectar() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(url);
            if (conexion != null) {
                System.out.println("Connected");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void cerrarConexion() {

        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("se cerro la conexion");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static Connection getConexion() {
        return conexion;
    }

    public static void setConexion(Connection conexion) {
        Conexion.conexion = conexion;
    }
}
