using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de HistorialPedidos
/// </summary>
public class HistorialPedidos
{

    public HistorialPedidos()
    {
    }
    public String descripcion { get; set; }
    public DateTime fecha { get; set; }
   public DateTime fechaVista { get; set; }
    public int idvistaformulario { get; set; }
    public int fkCuenta { get; set; }
    public int fkTbl { get; set; }
    public Boolean estado { get; set; }
    public String Autorizador { get; set; }
    
    public String NombreDePersona { get; set; }
    
    public String tipo { get; set; }
    public String nombre { get; set; }
    public String apellidoP { get; set; }
    public String apellidoM { get; set; }

    public int idDePersona { get; set; }







}
