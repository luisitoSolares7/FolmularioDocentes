using HistoriaDSTableAdapters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
/// <summary>
/// Descripción breve de Class1
/// </summary>
public class HistorialBRL
{

    public static List<HistorialPedidos> GetHistorial()
    {
        //añadir getPErsona ID para buscar
        HistorialTableAdapter adapter = new HistorialTableAdapter();
        HistoriaDS.HistorialPeticionesDataTable table = adapter.GetPeticiones();
        List<HistorialPedidos> list = new List<HistorialPedidos>();
        foreach (var row in table)
        {
            list.Add(new HistorialPedidos()
            {
                id = row.id,
                descripcion = row.descripcion,
                fecha = row.Fecha,
                fechaVista = row.FechaVistaFormulario,
                nombre = row.nombre,
                NombreDePersona = row.Nombre_de_la_persona,
                apellidoP = row.apellidoP,
                apellidoM = row.apellidoM,
                correo = row.correo,
                telefono = row.telefono





            });
        }
        return list;
    }
    public static HistorialPedidos GetPeticiones(int id)
    {
        if (id <= 0)
        {
            throw new ArgumentException("Error con la id");
        }
        HistorialTableAdapter adapter = new HistorialTableAdapter();
        HistoriaDS.HistorialPeticionesDataTable table = adapter.GetPeticiones();
        HistorialPedidos obj = null;

        if (table != null && table.Rows.Count == 1)
        {
            HistoriaDS.HistorialPeticionesRow row = table[0];
            obj = new HistorialPedidos()
            {
                id = row.id,
                descripcion = row.descripcion,
                fecha = row.Fecha,
                fechaVista = row.FechaVistaFormulario,
                nombre = row.nombre,
                NombreDePersona = row.Nombre_de_la_persona,
                apellidoP = row.apellidoP,
                apellidoM = row.apellidoM,
                correo = row.correo,
         

            };
        }
        return obj;
    }

    public static void VerPedidos(HistorialPedidos user)
    {
        HistorialTableAdapter adapter = new HistorialTableAdapter();



    }
}

