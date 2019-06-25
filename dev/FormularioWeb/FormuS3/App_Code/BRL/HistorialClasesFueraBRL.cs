using HistorialFueraClasesDSTableAdapters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de HistorialClasesFueraBRL
/// </summary>
public class HistorialClasesFueraBRL
{
    
        public static List<HistorialPedidosClasesFuera> GetHistorialClasesFuera()
    {
        HistorialPeticionesClasesFueraTableAdapter adapter = new HistorialPeticionesClasesFueraTableAdapter();
        HistorialFueraClasesDS.HistorialPeticionesClasesFueraDataTable table = adapter.GetHistorialClasesFuera();
        List<HistorialPedidosClasesFuera> list = new List<HistorialPedidosClasesFuera>();
        foreach (var row in table)
        {
            list.Add(new HistorialPedidosClasesFuera()
            {
                id = row.id,

               
                fecha = row.fecha,
                FechaVistaFormulario = row.FechaVistaFormulario,
                materia = row.materia,
                grupo = row.grupo,
                motivoActividad = row.motivoActividad,
                fechaActividad = row.fechaActividad,
                descripActividad = row.descripActividad,
                lugarActividad = row.lugarActividad,
                nombre = row.nombre,
                NombreDePersona = row.Nombre_de_la_persona,
                apellidoP = row.apellidoP,
                apellidoM = row.apellidoM,
            






            });
        }
        return list;
    }

}