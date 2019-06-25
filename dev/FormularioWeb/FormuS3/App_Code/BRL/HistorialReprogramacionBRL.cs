using HistorialReprogramacionDSTableAdapters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de HistorialReprogramacionBRL
/// </summary>
public class HistorialReprogramacionBRL
{
   
    
        public static List<HistorialPedidosReprogramacion> GetHistorialReprogramacion()
        {
        HistorialPeticionesReprogramacionTableAdapter adapter = new HistorialPeticionesReprogramacionTableAdapter();
            HistorialReprogramacionDS.HistorialPeticionesReprogramacionDataTable table = adapter.GetHistorialReprogramacion();
            List<HistorialPedidosReprogramacion> list = new List<HistorialPedidosReprogramacion>();
            foreach (var row in table)
            {
                list.Add(new HistorialPedidosReprogramacion()
                {
                    id = row.id,

                    nombre = row.nombre,
                    fecha = row.fecha,
                    FechaVistaFormulario = row.FechaVistaFormulario,
                    NombreDePersona = row.Nombre_de_la_persona,
                    apellidoP = row.apellidoP,
                    apellidoM = row.apellidoM,
                    modalidad = row.modalidad,
                    materia = row.materia,
                    grupo = row.grupo,
                    horaI = row.horaI,
                    horaF = row.horaF,
                    dias = row.dias,
                    fechaActividad = row.fechaActividad,
                    motivoSolicitud = row.motivoSolicitud,
                    carrera = row.carrera,






                });
            }
            return list;
        }
    
}