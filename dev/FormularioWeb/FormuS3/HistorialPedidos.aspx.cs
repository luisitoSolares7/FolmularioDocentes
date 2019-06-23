using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.UI;
using System.Web.UI.WebControls;
using App.Model;

public partial class Historial : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        RecargarTablaHistorial();
        RecargarTablaHistorialClasesFuera();
        RecargarTablaReprogramacion();
    }

    private void RecargarTablaHistorial()
    {
        List<HistorialPedidos> historial = HistorialBRL.GetHistorial();
        tblFormAccidente.DataSource = historial;
        tblFormAccidente.DataBind();
    }
    private void RecargarTablaHistorialClasesFuera()
    {
        List<HistorialPedidosClasesFuera> historialClasesFuera = HistorialClasesFueraBRL.GetHistorialClasesFuera();
        tblFormClasesFuera.DataSource = historialClasesFuera;
        tblFormClasesFuera.DataBind();
    }
    private void RecargarTablaReprogramacion()
    {
        List<HistorialPedidosReprogramacion> historialReprogramacion = HistorialReprogramacionBRL.GetHistorialReprogramacion();
        tblFormReprogramacion.DataSource = historialReprogramacion;
        tblFormReprogramacion.DataBind();
    }
    public void cargarFormularioIncidentes(Historial her)
    {
        if(her != null)
        {

        }
    }

}
