using App.Model;
using FormularioçTableAdapters;
using InvitacionDSTableAdapters;
using PeticionesDSTableAdapters;
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
    public static List<Peticiones> GetPeticionesAll()
    {
        PeticionesTableAdapter adapter = new PeticionesTableAdapter();
        //añadir getPErsona ID para buscar
        PeticionesDS.PeticionesDataTable table = adapter.GetPeticionesAll();
        List<Peticiones> list = new List<Peticiones>();
        foreach (var row in table)
        {
            String tipoForm = "";
            if (row.tipo==1)
            {
                tipoForm = "Formulario Incidentes";
            }
            if (row.tipo ==2)
            {
                tipoForm = "Formulario Clases Fuera de Aula";
            }
            if (row.tipo == 3)
            {
                tipoForm = "Formulario Fotocopias";
            }
            if (row.tipo == 4)
            {
                tipoForm = "Reprogramacion de Clases";
            }
            list.Add(new Peticiones()
            {
                id = row.id,
                fkCuenta = row.fkCuenta,
                estado = row.estado,
                fkTbl = row.fkTbl,
                nombreForm = tipoForm,
                tipo = row.tipo,
                nombre = row.nombre,
            });
        }
        return list;
    }
    public static Formulario AceptarSolucitud(int id)
    {
        if (id <= 0)
        {
            throw new ArgumentException("Error con la id");
        }
        GetFormularioIDTableAdapter adapter = new GetFormularioIDTableAdapter();
        //añadir getPErsona ID para buscar
        Formularioç.GetFormularioIDDataTable table = adapter.GetFormularioID(id);
        Formulario obj = null;

        if (table != null && table.Rows.Count == 1)
        {
            Formularioç.GetFormularioIDRow row = table[0];
            obj = new Formulario()
            {
                id=row.id,
                descripcion=row.descripcion,
                fecha=row.fecha
            };
        }
        return obj;
    }
    public static Formulario GetFormularioFk(int id)
    {
        if (id <= 0)
        {
            throw new ArgumentException("Error con la id");
        }
        PeticionesTableAdapter adapter = new PeticionesTableAdapter();
        //añadir getPErsona ID para buscar
        PeticionesDS.PeticionesDataTable table = adapter.GetFormulariofk(id);
        Formulario obj = null;

        if (table != null && table.Rows.Count == 1)
        {
            PeticionesDS.PeticionesRow row = table[0];
            obj = new Formulario()
            {
                id = row.id,

            };
        }
        return obj;
    }

    public static void Aceptar(int id,int fk)
    {
        PeticionesTableAdapter adapter = new PeticionesTableAdapter();
        DateTime fecha= DateTime.Today;

        adapter.AceptarPeticion(id,fecha, fk);
    }
}