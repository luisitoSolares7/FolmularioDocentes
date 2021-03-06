﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using App.Model;
using App_Com.DAL;
using App_Com.DAL.InvitacionDSTableAdapters;


public class InvitacionBRL
{
    public static Invitacion verificacionCorreo(string correo) {
        Invitacion invitacion = null;
        pr_verificacionCorreoTableAdapter adapter = new pr_verificacionCorreoTableAdapter();
        InvitacionDS.pr_verificacionCorreoDataTable tabla = adapter.getVerificacionCorreo(correo);
        if (tabla != null && tabla.Rows.Count > 0)
        {
            InvitacionDS.pr_verificacionCorreoRow fila = tabla[0];
            invitacion = new Invitacion(fila.cuenta);
            
        }
        return invitacion;

    }
    public static void actualizacionCorreoInvitacion(int idCuenta, string nombreCuenta, string contrasena) {
        pr_verificacionCorreoTableAdapter adapter = new pr_verificacionCorreoTableAdapter();
        adapter.pr_actualizacionCorreoInvitacion(idCuenta, nombreCuenta, contrasena);
    }
    public static void desactivarEstado(int idCuenta,string token) {
        pr_verificacionCorreoTableAdapter adapter = new pr_verificacionCorreoTableAdapter();
        adapter.pr_desactivacionInvitacion(idCuenta,token);
    }
    public static Invitacion getInvitacion(string token)
    {
        Invitacion invitacion = null;
        pr_verificacionCodigoTableAdapter adapter = new pr_verificacionCodigoTableAdapter();
        InvitacionDS.pr_verificacionCodigoDataTable tabla = adapter.GetVerificacionCodigo(token);
        if (tabla != null && tabla.Rows.Count > 0)
        {
            InvitacionDS.pr_verificacionCodigoRow fila = tabla[0];
            if (fila.estado != true)
            {
                invitacion = new Invitacion(fila.invitacion, fila.nombre,fila.cuenta);
            }
        }
        return invitacion;

    }
    public static Boolean actualizarInvitacion(int idInvitacion)
    {
        try {
            pr_verificacionCodigoTableAdapter adapter = new pr_verificacionCodigoTableAdapter();
            adapter.Update(idInvitacion);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public static Invitacion getInvitacionID(int id)
    {
        App_Com.DAL.GetInvitacionDSTableAdapters.InvitacionTableAdapter adapter = new App_Com.DAL.GetInvitacionDSTableAdapters.InvitacionTableAdapter();
        GetInvitacionDS.InvitacionDataTable table = adapter.GetInvitacionID(id);
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
        App_Com.DAL.GetInvitacionDSTableAdapters.InvitacionTableAdapter adapter = new App_Com.DAL.GetInvitacionDSTableAdapters.InvitacionTableAdapter();
        adapter.Insert(token, fechaInvitacion, fkPersona);
    }
    public static void eliminarInvitacion(int id)
    {
        App_Com.DAL.GetInvitacionDSTableAdapters.InvitacionTableAdapter adapter = new App_Com.DAL.GetInvitacionDSTableAdapters.InvitacionTableAdapter();
        adapter.Delete(id);
    }
}


