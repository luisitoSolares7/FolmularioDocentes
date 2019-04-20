using App.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de InvitacionBRL
/// </summary>
public class InvitacionBRL
{
    public InvitacionBRL()
    { 
    }
    public static Invitacion getInvitacionID(int id)
    {
        GetInvitacionDSTableAdapters.InvitacionTableAdapter adapter = new GetInvitacionDSTableAdapters.InvitacionTableAdapter();
        GetInvitacionDS.InvitacionDataTable table=adapter.GetInvitacionID(id);
        Invitacion obj = null;

        if (table != null && table.Rows.Count == 1)
        {
            GetInvitacionDS.InvitacionRow row = table[0];
            obj = new Invitacion()
            {
                id = row.id,
                token = row.token,
                fechaInvitacion = row.fechaInvitacion,
                fechaRespuesta = row.fechaRespuesta,
                estado = row.estado,
                fkCuenta = row.fkCuenta,
                fkPersona = row.fkPersona
            };
            return obj;
        }
        return obj;
    }
    public static void invitarUsuario(String token, DateTime fechaInvitacion, int fkPersona)
    {
        GetInvitacionDSTableAdapters.InvitacionTableAdapter adapter = new GetInvitacionDSTableAdapters.InvitacionTableAdapter();
        adapter.Insert(token,fechaInvitacion,fkPersona);
    }
    public static void eliminarInvitacion(int id)
    {
        GetInvitacionDSTableAdapters.InvitacionTableAdapter adapter = new GetInvitacionDSTableAdapters.InvitacionTableAdapter();
        adapter.Delete(id);
    }

}