using App.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.Services;

public partial class Login : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }


    protected void btnRegistrar_Click(object sender, EventArgs e)
    {
        Cuenta user = CuentaBRL.LoginUSer(txtNombreCuenta.Text,txtContracena.Text);
        if (user!=null){
            Session["tipoUsuario"] = user.tipo;
            Session["IDUsuario"] = user.id;
            Response.Redirect("~/Indes.aspx");
        }
        else{
            
        }

    }
}