using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Peticiones
namespace App.Model
{
    public class Peticiones
    {
        public Peticiones()
        {

        }
        public int id { get; set; }
        public int fkCuenta { get; set; }
        public int fkTbl { get; set; }
        public Boolean estado { get; set; }
        public DateTime fecha { get; set; }
        public int autorizador { get; set; }
        public int tipo { get; set; }
        public String nombreForm { get; set; }
        public String nombre { get; set; }
        
    }
}