using App.Model;
using Gma.QrCodeNet.Encoding;
using Gma.QrCodeNet.Encoding.Windows.Render;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.Web.Services;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Peticiones : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        /*  if ((Session["tipoUsuario"] + "") != ("1"))
          {
              Response.Redirect("~/Index.aspx");
          }*/
        RecargarTablaPeticiones();
        RecargarTablaReprogramacion();
        RecargarTablaClases();
        RecargarTablaFotocopia();
    }
    private void RecargarTablaPeticiones()
    {
        List<Incidente> peticiones = PeticionesBRL.GetPeticionesAll();
        if (peticiones.Count >0 ) {
            IncidentesVacio.Visible = false;
            tblPeticiones.DataSource = peticiones;
           tblPeticiones.DataBind();

        }else
        {
            IncidentesVacio.Visible = true;
        }
    }
    private void RecargarTablaClases()
    {
        List<ClasesFuera> peticiones = PeticionesBRL.GetClasesFuera();
       

        if (peticiones.Count > 0)
        {
            ClasesVacia.Visible = false;
            tblClases.DataSource = peticiones;
            tblClases.DataBind();

        }
        else
        {
            ClasesVacia.Visible = true;
        }
    }
    private void RecargarTablaReprogramacion()
    {
        List<Reprogramacion> peticiones = PeticionesBRL.GetReprogramacion();
        if (peticiones.Count > 0)
        {
            ReprogramacionVacia.Visible = false;
            tblReprogramacion.DataSource = peticiones;
            tblReprogramacion.DataBind();

        }
        else
        {
            ReprogramacionVacia.Visible = true;
        }
    }
    private void RecargarTablaFotocopia()
    {
        List<Fotocopia> peticiones = PeticionesBRL.GetFotocopia();
        if (peticiones.Count > 0)
        {
            tblFotocopias.Visible = false;
            tblFotocopias.DataSource = peticiones;
            tblFotocopias.DataBind();

        }
        else
        {
            FotocopiasVacia.Visible = true;
        }
    }

    protected void AceptarPeticion(object sender, EventArgs e)
    {

        try
        {
            int id = Convert.ToInt32(TxtID.Text);
            int idAdmin = Convert.ToInt32(Session["IDAdmin"]+"");
            PeticionesBRL.aceptarPeticion(1, idAdmin, 1);
            RecargarTablaPeticiones();
            RecargarTablaReprogramacion();
            RecargarTablaClases();
            RecargarTablaFotocopia();
        }
        catch (Exception ex)
        {
            Response.Write("<script language=javascript>alert('" + ex + "');</script>");
        }
    }
    protected void RechazarPeticion(object sender, EventArgs e)
    {
        
        try
        {
            int id = Convert.ToInt32(TxtID.Text);
            int idAdmin = Convert.ToInt32(Session["IDAdmin"]+"");
            PeticionesBRL.aceptarPeticion(1, idAdmin, 0);
            RecargarTablaPeticiones();
            RecargarTablaReprogramacion();
            RecargarTablaClases();
            RecargarTablaFotocopia();
        }
        catch (Exception ex)
        {
            Response.Write("<script language=javascript>alert('" + ex + "');</script>");
        }
    }

    private String GenerarQR()
    {
        QrEncoder encoder = new QrEncoder();
        QrEncoder qrEncoder = new QrEncoder(ErrorCorrectionLevel.H);
        QrCode qrCode = new QrCode();
        qrEncoder.TryEncode("Nombre :Elmejorcito \r\n" + "Guzman", out qrCode);

        GraphicsRenderer renderer = new GraphicsRenderer(new FixedCodeSize(400, QuietZoneModules.Zero), Brushes.Black, Brushes.White);

        MemoryStream ms = new MemoryStream();

        renderer.WriteToStream(qrCode.Matrix, ImageFormat.Png, ms);
        var imageTemporal = new Bitmap(ms);
        var imagen = new Bitmap(imageTemporal, new Size(new Point(200, 200)));
        byte[] imagenByte= ImageToByte(imagen);
        String imagen64= Convert.ToBase64String(imagenByte);
        return imagen64;
    }

    public static byte[] ImageToByte(System.Drawing.Image img)
    {
        ImageConverter converter = new ImageConverter();
        return (byte[])converter.ConvertTo(img, typeof(byte[]));
    }

}