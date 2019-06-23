using InvitacionDSTableAdapters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de InvitacionBRL
/// </summary>
public class InvitacionBRL
{

    public static void invitarUsuario(String token, DateTime fechaInvitacion, int fkPersona)
    {
        InvitacionTableAdapter adapter = new InvitacionTableAdapter();
        adapter.InvitarUsuario(token,fechaInvitacion,fkPersona);
    }
    public static void eliminarInvitacion(int id)
    {
        InvitacionTableAdapter adapter = new InvitacionTableAdapter();
    }

    public static void verPedidos(int id)
    {
        InvitacionTableAdapter adapter = new InvitacionTableAdapter();

    }
    public static List<Invitacion> GetInvitacion()
    {
        InvitacionTableAdapter adapter = new InvitacionTableAdapter();
        //añadir getPErsona ID para buscar
       InvitacionDS.InvitacionDataTable table = adapter.GetInvitacion();
        List<Invitacion> list = new List<Invitacion>();
        foreach (var row in table)
        {
            list.Add(new Invitacion()
            {
                id = row.id,
                token = row.token,
                fechaInvitacion= row.fechaInvitacion,
                fechaRespuesta= row.fechaRespuesta,
                estado = row.estado,
                fkCuenta = row.fkCuenta,
                fkPersona = row.fkPersona
            });
        }
        return list;
    }
}


