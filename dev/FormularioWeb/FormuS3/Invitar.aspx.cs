﻿using App.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Invitar : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        RecargarTablaDocente();
        cargarInvitados();
    }
    protected void enviarCorreo(object sender, GridViewCommandEventArgs e)
    {
        if (e.CommandName == "Eliminar")
        {
            int personaID = Convert.ToInt32(e.CommandArgument);
            PersonaBRL.EliminarPersona(personaID);
            RecargarTablaDocente();
        }
        if (e.CommandName == "EditarPersona")
        {
            int personaID = Convert.ToInt32(e.CommandArgument);
            Persona per = PersonaBRL.GetPersonaID(personaID);
            cargarFormulario(per);
            ScriptManager.RegisterStartupScript(this.Page, this.GetType(), "key", " $('#myModal').modal(" + "'show'" + ");", true);
        }
        if (e.CommandName == "EnviarCorreo")
        {
            try
            {
                Random random = new Random();
                int personaID = Convert.ToInt32(e.CommandArgument);
                InvitacionBRL.eliminarInvitacion(personaID);
                int num = random.Next(10);
                int num1 = random.Next(10);
                int num2 = random.Next(10);
                int num3 = random.Next(10);
                Persona per = PersonaBRL.GetPersonaID(personaID);
                Correo correo = new Correo(per.correo, "Creacion de Cuenta", "Te invitamos a crear una cuenta.... copia el siguiente Codigo y pegalo en la APP " + num + "-" + num1 + "-" + num2 + "-" + num3);
                InvitacionBRL.eliminarInvitacion(personaID);
                InvitacionBRL.invitarUsuario(num + "-" + num1 + "-" + num2 + "-" + num3, DateTime.Today, personaID);
                RecargarTablaDocente();
                Response.Write("<script language=javascript>alert('Se Envio la Invitacion Correctamente');</script>");

            }
            catch (Exception ex)
            {
                Response.Write("<script language=javascript>alert('Error al Eviar la Invitacion ');</script>");
            }
        }
    }
    private void RecargarTablaDocente()
    {
        List<Persona> personas = PersonaBRL.GetPersona();
        tblDocentes.DataSource = personas;
        tblDocentes.DataBind();
        tblDocentes.HeaderRow.TableSection = TableRowSection.TableHeader;
    }
    public void cargarFormulario(Persona per)
    {
        if (per != null)
        {
            txtID.Text = per.id + "";
            txtNombre.Text = per.nombre;
            txtApellidoM.Text = per.apellidoM;
            txtApellidoP.Text = per.apellidoP;
            txtCorreo.Text = per.correo;
            txtTelefono.Text = per.telefono + "";
        }

    }
    private void cargarInvitados()
    {
        List<Persona> personas = PersonaBRL.GetDocentesInvitar();
        tblInvitar.DataSource = personas;
        tblInvitar.DataBind();
    }

    [WebMethod]
    public static String verificarPersona(string id)
    {
        int personaID = Convert.ToInt32(id);
        Persona per = PersonaBRL.GetPersonaID(personaID);
        String userN = per.id+"" + ',' + per.nombre + ',' + per.apellidoP + ',' +
            per.apellidoM + ',' + per.correo + ',' + per.telefono+"";
        return userN;
    }

    protected void RegistrarDocente(object sender, EventArgs e)
    {
        try
        {
            Persona user = new Persona();
            user.nombre = txtNombre.Text;
            user.apellidoM = txtApellidoM.Text;
            user.apellidoP = txtApellidoP.Text;
            user.correo = txtCorreo.Text;
            user.telefono = Convert.ToInt32(txtTelefono.Text);
            PersonaBRL.InsertPersona(user);
            RecargarTablaDocente();

        }
        catch (Exception ex)
        {
            Response.Write("<script language=javascript>alert('" + ex + "');</script>");
        }
    }

    protected void btnActuaizar_Click(object sender, EventArgs e)
    {
        String nombre = txtNombre.Text;
        String apellidoP = txtApellidoP.Text;
        String apellidoM = txtApellidoM.Text;
        String correo = txtCorreo.Text;
        int telefono = Convert.ToInt32(txtTelefono.Text);
        Persona per = new Persona();
        per.nombre = nombre;
        per.apellidoP = apellidoP;
        per.apellidoM = apellidoM;
        per.correo = correo;
        per.telefono = telefono;
        per.id = Convert.ToInt32(txtID.Text);

        PersonaBRL.ActualizarPersona(per);
        RecargarTablaDocente();
    }
}