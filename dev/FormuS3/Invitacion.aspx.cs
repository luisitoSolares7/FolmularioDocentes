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
    protected void enviarCorreo(object sender,GridViewCommandEventArgs e)
    {
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
                Correo correo = new Correo(per.correo, "Creacion de Cuenta", "Te invitamos a crear una cuenta.... copia el siguiente Codigo y pegalo en la APP");
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
}