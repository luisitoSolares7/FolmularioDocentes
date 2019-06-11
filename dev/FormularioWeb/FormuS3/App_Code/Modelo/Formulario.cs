using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Formulario
/// </summary>
namespace App.Model
{
    public class Formulario
    {
        public Formulario()
        {

        }
        public int id { get; set; }
        public DateTime fecha { get; set; }
        public String descripcion { get; set; }
    }
}