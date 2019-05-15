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
            CuentaDS.pr_verificacionUsuariosDataTable tabla = adapter.GetLogin(user, password);
            if (tabla != null && tabla.Rows.Count > 0)
            {
                CuentaDS.pr_verificacionUsuariosRow fila = tabla[0];
                persona = new Cuenta(fila.id, fila.contracena, fila.nombreCuenta);

            }
            return persona;
        }
    }
