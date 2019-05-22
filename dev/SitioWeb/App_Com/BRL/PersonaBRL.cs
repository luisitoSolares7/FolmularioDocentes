using App.Model;
using App_Com.DAL;
using App_Com.DAL.GetPersonaDSTableAdapters;
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
        GetPersonaDS.PersonaDataTable table = adapter.GetPersonas();
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
        GetPersonaDS.PersonaDataTable table = adapter.GetPersonaID(id);
        Persona obj = null;

        if (table != null && table.Rows.Count == 1)
        {
            GetPersonaDS.PersonaRow row = table[0];
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


    public static void UpdatePersona(Persona obj)
    {
        if (obj == null)
            throw new ArgumentException("El objeto a insertar no puede ser nulo");

        if (obj.id <= 0)
            throw new ArgumentException("El id del producto no puede ser menor o igual que cero");

        if (string.IsNullOrEmpty(obj.nombre))
            throw new Exception("El nombre no puede ser nulo o vacio");

        if (string.IsNullOrEmpty(obj.apellidoP))
            throw new Exception("El apellidoPaterno no puede ser nulo o vacio");

        if (string.IsNullOrEmpty(obj.apellidoM))
            throw new Exception("El ApellidoMaterno no puede ser nulo o vacio");

        if (String.IsNullOrEmpty(obj.correo))
            throw new Exception("El correo no puede ser nulo o vacio");
        if (obj.telefono == null)
            throw new Exception("El telefono no puede ser nulo o vacio");


        PersonaTableAdapter adapter = new PersonaTableAdapter();
        adapter.usp_PROD_UpdatePersona(obj.id, obj.nombre, obj.apellidoP, obj.apellidoM, obj.correo, obj.telefono);
    }

}