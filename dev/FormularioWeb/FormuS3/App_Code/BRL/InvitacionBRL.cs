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
}


