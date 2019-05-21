using App.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Invitacion : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (IsPostBack) {
            return;
        }
        try
        {
            RecargarTablaDocente();
        }
        catch (Exception ex)
        {

        }

    }
    private void RecargarTablaDocente() 
    {
        List<Persona> personas = PersonaBRL.GetPersona();
        tblDocentes.DataSource = personas;
        tblDocentes.DataBind();
    }
    private void cargarFormulario(Persona per)
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
    private void LimpiarFormulario()
    {
        txtID.Text = string.Empty;
        txtNombre.Text = string.Empty;
        txtApellidoM.Text =string.Empty;
        txtApellidoP.Text = string.Empty;
        txtCorreo.Text = string.Empty;
        txtTelefono.Text = string.Empty;
    }
    protected void enviarCorreo(object sender,GridViewCommandEventArgs e)
    {
        if (e.CommandName== "EditarPersona")
        {
            int personaID = Convert.ToInt32(e.CommandArgument);
            Persona per = PersonaBRL.GetPersonaID(personaID);
            cargarFormulario(per);
            ScriptManager.RegisterStartupScript(this.Page, this.GetType(), "key", " $('#myModal').modal("+"'show'"+");", true);
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
                Correo correo = new Correo(per.correo, "Creacion de Cuenta", "Te invitamos a crear una cuenta.... copia el siguiente Codigo y pegalo en la APP "+ num + "-" + num1 + "-" + num2 + "-" + num3 );
                InvitacionBRL.invitarUsuario(num+"-"+num1+"-"+num2+"-"+num3, DateTime.Today,personaID);
                RecargarTablaDocente();
                Response.Write("<script language=javascript>alert('Se Envio la Invitacion Correctamente');</script>");
                
            }
            catch (Exception ex)
            {
                Response.Write("<script language=javascript>alert('Error al Eviar la Invitacion ');</script>");
            }
        }
    }

    protected void btnRegistraDocente_Click(object sender, EventArgs e)
    {

        LimpiarFormulario();
        ScriptManager.RegisterStartupScript(this.Page, this.GetType(), "key", " $('#myModal').modal(" + "'show'" + ");", true);

    }
    protected void btnPrueba(object sender, EventArgs e)
    {
        
    }
}