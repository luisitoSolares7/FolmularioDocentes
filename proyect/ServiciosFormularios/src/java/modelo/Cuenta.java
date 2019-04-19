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
public class Cuenta {

    private int cuentaId;
    private String contracena;
    private String nombreCuenta;

    public Cuenta() {
    }

    public Cuenta(int cuentaId, String contracena, String nombreCuenta) {
        this.cuentaId = cuentaId;
        this.contracena = contracena;
        this.nombreCuenta = nombreCuenta;
    }

    public Cuenta(String nombreCuenta, String contracena) {
        this.contracena = contracena;
        this.nombreCuenta = nombreCuenta;
    }

    public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getContracena() {
        return contracena;
    }

    public void setContracena(String contracena) {
        this.contracena = contracena;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

}
