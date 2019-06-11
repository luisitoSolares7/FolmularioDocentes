using App.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.Services;

public partial class ListaPeticiones : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        RecargarTablaPeticiones();
    }
    protected void enviarCorreo(object sender, GridViewCommandEventArgs e)
    {
    }
    private void RecargarTablaPeticiones()
    {
        List<Peticiones> peticiones = PeticionesBRL.GetPeticionesAll();
        tblPeticiones.DataSource = peticiones;
        tblPeticiones.DataBind();
    }
    [WebMethod]
    public static String DetalleForm(String id)
    {
        String detalle = "";
        int idVista = Convert.ToInt32(id);
        Formulario formu = PeticionesBRL.AceptarSolucitud(idVista);
        detalle = formu.descripcion + "," + formu.fecha.ToString("dd-MMM-yyyy") +","+formu.id;
        return detalle;
    }

    protected void btnRegistrar_Click(object sender, EventArgs e)
    {
        int Aid = Convert.ToInt32(Session["AdminID"]);
        int formuID = Convert.ToInt32(id.Text);
        Formulario formu = PeticionesBRL.GetFormularioFk(formuID);

        int fk = formu.id;
        PeticionesBRL.Aceptar(1,fk);
        RecargarTablaPeticiones();
    }
}