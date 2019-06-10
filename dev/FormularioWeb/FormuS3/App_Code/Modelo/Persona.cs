using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Persona
/// </summary>

   namespace App.Model
{
    public class Persona
    {
        public Persona()
        {
            //
            // TODO: Agregar aquí la lógica del constructor
            //
        }
        public int id { get; set; }
        public String nombre { get; set; }
        public String apellidoP { get; set; }
        public String apellidoM { get; set; }
        public String correo { get; set; }
        public int telefono { get; set; }


    }
}