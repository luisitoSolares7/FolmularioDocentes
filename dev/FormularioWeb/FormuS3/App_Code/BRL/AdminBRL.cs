using AdminFotocopiaTableAdapters;
using App.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de AdminBRL
/// </summary>
public class AdminBRL
{
    public AdminBRL()
    {
        //
        // TODO: Agregar aquí la lógica del constructor
        //
    }
    public static List<Persona> GetAdminFotocopia()
    {
        AdminFotocopiaTableAdapter adapter = new AdminFotocopiaTableAdapter();
        //añadir getPErsona ID para buscar
        AdminFotocopia.AdminFotocopiaDataTable table = adapter.GetAdminFotocopia();
        List<Persona> list = new List<Persona>();
        foreach (var row in table)
        {
            list.Add(new Persona()
            {
                id = row.PersonaID,
                nombre = row.nombre,
                apellidoP = row.apellidoP,
                apellidoM = row.apellidoM,
                correo = row.correo,
                telefono = row.telefono,
                fkCuenta = row.id
            });
        }
        return list;
    }
    public static void InsertPersonaAdmin(Persona user,String NombreCuenta,String password)
    {
        if (user == null)
            return;
        try { 
        AdminFotocopiaTableAdapter adapter = new AdminFotocopiaTableAdapter();
        adapter.InsertarPersonaCuentaAdmin(user.nombre,user.apellidoP,user.apellidoM,user.correo,user.telefono,DateTime.Now,NombreCuenta,password);
        }
        catch
        {
            return;
        }
    }

    public static void ActualizarPersonaAdmin(Persona user, String NombreCuenta, String password ,int idCuenta)
    {
       
            AdminFotocopiaTableAdapter adapter = new AdminFotocopiaTableAdapter();
            adapter.ActualizarCuentaAdmin(user.nombre,user.apellidoP,user.apellidoM,user.correo,user.telefono,DateTime.Now,NombreCuenta,password,user.id,idCuenta);
    }
}