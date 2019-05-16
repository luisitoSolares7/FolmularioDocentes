using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using App_Com.DAL.CuentaDSTableAdapters;
using App_Com.DAL;


public class CuentaBRL
{
    public static Cuenta getLogin(string user, string password)
    {
        Cuenta persona = null;
        pr_verificacionUsuariosTableAdapter adapter = new pr_verificacionUsuariosTableAdapter();
        CuentaDS.pr_verificacionUsuariosDataTable tabla = adapter.getLogin(user, password);
        if (tabla != null && tabla.Rows.Count > 0)
        {
            CuentaDS.pr_verificacionUsuariosRow fila = tabla[0];
            persona = new Cuenta(fila.id, fila.contracena, fila.nombreCuenta, fila.tipo, fila.estado);

        }
        return persona;
    }
    public static Boolean insertarCuenta(string user, string password)
    {
        try {
            pr_verificacionUsuariosTableAdapter adapter = new pr_verificacionUsuariosTableAdapter();
            adapter.Insert(user, password);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }
}
