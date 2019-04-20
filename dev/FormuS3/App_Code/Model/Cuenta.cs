using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
namespace App.Model
{
    /// <summary>
    /// Descripción breve de Cuenta
    /// </summary>
    public class Cuenta
    {
        public String nombreCuenta { get; set; }
        public String contracena { get; set; }
        public int tipo { get; set; }
        public Boolean estado { get; set; }

        public Cuenta()
        {
            //
            // TODO: Agregar aquí la lógica del constructor
            //
        }
    }
}