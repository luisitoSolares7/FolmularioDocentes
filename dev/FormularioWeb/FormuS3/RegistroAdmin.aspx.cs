using App.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class RegistroAdmin : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        recargarTablaFotocopia();
    }
    private void recargarTablaFotocopia()
    {
        List<Persona> per = AdminBRL.GetAdminFotocopia() ;
        tblAdmin.DataSource = per;
        tblAdmin.DataBind();
    }
    [WebMethod]
    public static String verificarPersona(String id, String idCuenta)
    {
        int personaID = Convert.ToInt32(id);
        int CuentaID = Convert.ToInt32(idCuenta);
        Persona per = PersonaBRL.GetPersonaID(personaID);
        Cuenta cuent = CuentaBRL.GetCuentaID(CuentaID);
        String userN = per.id + "" + ',' + per.nombre + ',' + per.apellidoP + ',' +
            per.apellidoM + ',' + per.correo + ',' + per.telefono + "" + ',' +cuent.id + ',' +cuent.nombreCuenta + ',' +cuent.password;
        return userN;
    }

    protected void btnRegistrar_Click(object sender, EventArgs e)
    {
        try
        {
            Persona user = new Persona();
            user.nombre = txtNombre.Text;
            user.apellidoP = txtApellidoM.Text;
            user.apellidoP = txtApellidoP.Text;
            user.correo = txtCorreo.Text;
            user.telefono = Convert.ToInt32(txtTelefono.Text);
            String userName = txtNombreCuenta.Text;
            String password = txtContraceña.Text;
            AdminBRL.InsertPersonaAdmin(user,userName,password);

        }
        catch (Exception ex)
        {
            Response.Write("<script language=javascript>alert('" + ex + "');</script>");
        }
    }

    protected void btnActuaizar_Click(object sender, EventArgs e)
    {
        try
        {
            Persona user = new Persona();
            user.nombre = txtNombre.Text;
            user.apellidoP = txtApellidoM.Text;
            user.apellidoP = txtApellidoP.Text;
            user.correo = txtCorreo.Text;
            user.telefono = Convert.ToInt32(txtTelefono.Text);
            String userName = txtNombreCuenta.Text;
            String password = txtContraceña.Text;
            int idCuenta = Convert.ToInt32(txtIDCuenta.Text);
            AdminBRL.ActualizarPersonaAdmin(user, userName, password,idCuenta);

        }
        catch (Exception ex)
        {
            Response.Write("<script language=javascript>alert('" + ex + "');</script>");
        }
    }
    protected void enviarCorreo(object sender, GridViewCommandEventArgs e)
    {
        if (e.CommandName == "Eliminar")
        {
            int personaID = Convert.ToInt32(e.CommandArgument);
            PersonaBRL.EliminarPersona(personaID);
            recargarTablaFotocopia();
        }
    }
 }