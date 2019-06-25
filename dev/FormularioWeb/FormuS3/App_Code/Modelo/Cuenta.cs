using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Cuenta
/// </summary>
namespace App.Model
{
    public class Cuenta
    {
        public Cuenta()
        {
            
        }
        public int id { get; set; }
        public String nombreCuenta { get; set; }
        public String password { get; set; }
        public int tipo { get; set; }
        public Boolean estado { get; set; }
    }
}