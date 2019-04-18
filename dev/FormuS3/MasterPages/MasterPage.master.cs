using App.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class MasterPages_MasterPage : System.Web.UI.MasterPage
{
    protected void Page_Load(object sender, EventArgs e)
    {

        if (IsPostBack)
        {
            return;
        }
        bool isValidVersion = false;
        try
        {
            isValidVersion = VersionUtilities.VerificarVersion();

        }
        catch (Exception ex)
        {
            Session["ErrorMessage"] = ex.Message;
            Response.Redirect("~/Error.aspx");
        }
        if (! isValidVersion)
        {
            Session["ErrorMessage"] = "La Version No es la Misma que la Base de Datos. Por Favor Actualizar";
            Response.Redirect("~/Error.aspx");
        }
    }
}
