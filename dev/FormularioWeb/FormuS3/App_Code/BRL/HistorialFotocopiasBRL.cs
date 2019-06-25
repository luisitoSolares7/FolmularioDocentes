using HistorialFotocopiasTableAdapters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de HistorialFotocopiasBRL
/// </summary>
public class HistorialFotocopiasBRL
{
  

        public static List<HistorialPedidosFotocopia> GetHistorialFotocopias()
        {
        HistorialPeticionesFotocopiasTableAdapter adapter= new HistorialPeticionesFotocopiasTableAdapter();
        HistorialFotocopias.HistorialPeticionesFotocopiasDataTable table = adapter.GetHistorialFotocopias();
        List<HistorialPedidosFotocopia> list = new List<HistorialPedidosFotocopia>();
            foreach (var row in table)
            {
                list.Add(new HistorialPedidosFotocopia()
                {
              


                    fecha = row.fecha,
                    FechaVistaFormulario = row.FechaVistaFormulario,
                    materia = row.materia,
                    cantidad = row.cantidad,
                    tipoDocuento = row.tipoDocuento,
                    nombre = row.nombre,
                    NombreDePersona = row.Nombre_de_la_persona,
                    apellidoP = row.apellidoP,
                    apellidoM = row.apellidoM,







                });
            }
            return list;
        
    }
}