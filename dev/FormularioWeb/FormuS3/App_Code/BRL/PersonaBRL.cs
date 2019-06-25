using App.Model;
using PersonaDSTableAdapters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de PersonaBRL
/// </summary>
public class PersonaBRL
{
    public PersonaBRL()
    {
        //
        // TODO: Agregar aquí la lógica del constructor
        //
    }
    public static List<Persona> GetPersona()
    {
        PersonaTableAdapter adapter = new PersonaTableAdapter();
        //añadir getPErsona ID para buscar
        PersonaDS.PersonaDataTable table = adapter.GetDocentes();
        List<Persona> list = new List<Persona>();
        foreach (var row in table)
        {
            list.Add(new Persona()
            {
                id = row.id,
                nombre = row.nombre,
                apellidoP = row.apellidoP,
                apellidoM = row.apellidoM,
                correo = row.correo,
                telefono = row.telefono
            });
        }
        return list;
    }
    public static List<Persona> GetDocentesInvitar()
    {
        PersonaTableAdapter adapter = new PersonaTableAdapter();
        //añadir getPErsona ID para buscar
        PersonaDS.PersonaDataTable table = adapter.GetDocentesInvitar();
        List<Persona> list = new List<Persona>();
        foreach (var row in table)
        {
            list.Add(new Persona()
            {
                id = row.id,
                nombre = row.nombre,
                apellidoP = row.apellidoP,
                apellidoM = row.apellidoM,
                correo = row.correo,
                telefono = row.telefono
            });
        }
        return list;

    }
    public static Persona GetPersonaID(int id)
    {
        if (id <= 0)
        {
            throw new ArgumentException("Error con la id");
        }
        PersonaTableAdapter adapter = new PersonaTableAdapter();
        PersonaDS.PersonaDataTable table = adapter.GetPersonaID(id);
        Persona obj = null;

        if (table != null && table.Rows.Count == 1)
        {
            PersonaDS.PersonaRow row = table[0];
            obj = new Persona()
            {
                id = row.id,
                nombre = row.nombre,
                apellidoP = row.apellidoP,
                apellidoM = row.apellidoM,
                correo = row.correo,
                telefono = row.telefono
            };
        }
        return obj;
    }

    public static void InsertPersona(Persona user)
    {
        PersonaTableAdapter adapter = new PersonaTableAdapter();
        adapter.Insert(user.nombre, user.apellidoP, user.apellidoM, user.telefono, user.correo);
    }
    public static void ActualizarPersona(Persona user)
    {
        PersonaTableAdapter adapter = new PersonaTableAdapter();
        adapter.ActualizarPersona(user.nombre, user.apellidoP, user.apellidoM , user.correo,user.telefono,user.id);
    }
    public static void EliminarPersona(int id)
    {
        PersonaTableAdapter adapter = new PersonaTableAdapter();
        adapter.eliminarPersona(id);
    }
}