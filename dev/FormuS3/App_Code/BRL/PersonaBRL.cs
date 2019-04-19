using App.Model;
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
        GetPersonaDSTableAdapters.PersonaTableAdapter adapter = new GetPersonaDSTableAdapters.PersonaTableAdapter();
        //añadir getPErsona ID para buscar
        GetPersonaDS.PersonaDataTable table= adapter.GetPersonas();
        List<Persona> list = new List<Persona>();
        foreach (var row in table)
        {
            list.Add(new Persona() {
                id=row.id,
                nombre=row.nombre,
                apellidoP=row.apellidoP,
                apellidoM=row.apellidoM,
                correo=row.correo,
                telefono=row.telefono
            });
        }
        return list;
    }
     public static Persona GetPersonaID(int id)
    {
        if (id<=0)
        {
            throw new ArgumentException("Error con la id");
        }
        GetPersonaDSTableAdapters.PersonaTableAdapter adapter = new GetPersonaDSTableAdapters.PersonaTableAdapter();
        GetPersonaDS.PersonaDataTable table = adapter.GetPersonaID(id);
        Persona obj = null;

        if(table != null && table.Rows.Count ==1)
        {
            GetPersonaDS.PersonaRow row = table[0];
            obj=new Persona()
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
}