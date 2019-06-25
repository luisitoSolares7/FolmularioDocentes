using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de ClasesFuera
/// </summary>ç
namespace App.Model
{
    public class ClasesFuera
    {
        public ClasesFuera()
        {
            //
            // TODO: Agregar aquí la lógica del constructor
            //
        }
        public int id { get; set; }
        public int fkCuenta { get; set; }
        public int fkTbl { get; set; }
        public Boolean estado { get; set; }
        public DateTime fecha { get; set; }
        public int autorizador { get; set; }
        public int tipo { get; set; }
        public String nombreForm { get; set; }

        public int idPersona { get; set; }
        public String nombre { get; set; }
        public String apellidoP { get; set; }
        public String apellidoM { get; set; }
        public String descripcion { get; set; }
        public DateTime fechaEnvio { get; set; }
    }
}