using App.Model;
using LoginTableAdapters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de CuentaBRL
/// </summary>
public class CuentaBRL
{
    public CuentaBRL()
    {
        
    }
    public static Cuenta LoginUSer(String userName , String Password)
    {
        
        LoginUserTableAdapter adapter = new LoginUserTableAdapter();
        Login.LoginUserDataTable table = adapter.GetCuenta(userName,Password);

        Cuenta obj = null;

        if (table != null && table.Rows.Count == 1)
        {
            Login.LoginUserRow row = table[0];
            obj = new Cuenta()
            {
                id = row.id,
                nombreCuenta = row.nombreCuenta,
                password = row.contracena,
                estado = row.estado,
                tipo = row.tipo,
            };
        }
        return obj;

    }
    public static Cuenta GetCuentaID(int id)
    {

        LoginUserTableAdapter adapter = new LoginUserTableAdapter();
        Login.LoginUserDataTable table = adapter.GetIDCuenta(id);

        Cuenta obj = null;

        if (table != null && table.Rows.Count == 1)
        {
            Login.LoginUserRow row = table[0];
            obj = new Cuenta()
            {
                id = row.id,
                nombreCuenta = row.nombreCuenta,
                password = row.contracena,
                estado = row.estado,
                tipo = row.tipo,
            };
        }
        return obj;

    }
}