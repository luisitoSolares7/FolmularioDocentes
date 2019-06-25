using App.Model;
using FormularioçTableAdapters;
using InvitacionDSTableAdapters;
using PeticionClasesDSTableAdapters;
using PeticionesDSTableAdapters;
using PeticionFotocopiaDSTableAdapters;
using PeticionIncidenteDSTableAdapters;
using PeticionReprogramacionDSTableAdapters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de PeticionesBRL
/// </summary>
public class PeticionesBRL
{
    public PeticionesBRL()
    {
        
    }
   public static List<Incidente> GetPeticionesAll()
    {
        PeticionesIncidentesTableAdapter adapter = new PeticionesIncidentesTableAdapter();
        PeticionIncidenteDS.PeticionesIncidentesDataTable table = adapter.GetPeticionesIncidentes();
        List<Incidente> list = new List<Incidente>();
        foreach (var row in table)
        {
            list.Add(new Incidente()
            {
                id = row.id,
                nombreForm=row.NombrePersona + " " + row.apellidoP + " " +row.apellidoM,
                descripcion=row.descripcion
            });
        }

        return list;
    }
    public static void aceptarPeticion(int id,int fkAdmin,int estado)
    {
        PeticionesTableAdapter adapter = new PeticionesTableAdapter();
        adapter.AceptarPeticion(id,DateTime.Today,fkAdmin,estado);
    }
    public static List<ClasesFuera> GetClasesFuera()
    {
        GetClasesFueraTableAdapter adapter = new GetClasesFueraTableAdapter();
        PeticionClasesDS.GetClasesFueraDataTable table = adapter.GetClasesFuera();
        List<ClasesFuera> list = new List<ClasesFuera>();
        foreach (var row in table)
        {
            list.Add(new ClasesFuera()
            {
                id = row.id,
                nombreForm = row.Expr10 + " " + row.apellidoP + " " + row.apellidoM,
                fechaEnvio = row.fechaActividad,
                descripcion = row.descripActividad
            });
        }

        return list;
    }
    public static List<Reprogramacion> GetReprogramacion()
    {
        GetReprogramacionTableAdapter adapter = new GetReprogramacionTableAdapter();
        PeticionReprogramacionDS.GetReprogramacionDataTable table = adapter.GetReprogramacion();
        List<Reprogramacion> list = new List<Reprogramacion>();
        foreach (var row in table)
        {
            list.Add(new Reprogramacion()
            {
                id = row.id,
                nombreForm = row.Expr10 + " " + row.apellidoP + " " + row.apellidoM,
                fechaEnvio = row.fechaActividad,
                descripcion = row.dias
            });
        }

        return list;
    }
    public static List<Fotocopia> GetFotocopia()
    {
        GetFotocopiaTableAdapter adapter = new GetFotocopiaTableAdapter();
        PeticionFotocopiaDS.GetFotocopiaDataTable table = adapter.GetFotocopia();
        List<Fotocopia> list = new List<Fotocopia>();
        foreach (var row in table)
        {
            list.Add(new Fotocopia()
            {
                id = row.id,
                idPersona = row.Expr1,
                nombreForm = row.Expr10 + " " + row.apellidoP + " " + row.apellidoM,
                descripcion = "Cantidad"+row.cantidad 
            });
        }

        return list;
    }

}