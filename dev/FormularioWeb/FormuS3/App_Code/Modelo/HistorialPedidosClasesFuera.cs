using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de HistorialPedidosClasesFuera
/// </summary>
public class HistorialPedidosClasesFuera
{
    public HistorialPedidosClasesFuera()
    {
    }

    public int id { get; set; }
    public String descripActividad { get; set; }
    public DateTime fechaActividad { get; set; }
    public String motivoActividad { get; set; }
    public String lugarActividad { get; set; }
    public int idvistaformulario { get; set; }
    public int fkCuenta { get; set; }
    public int fkTbl { get; set; }
    public Boolean estado { get; set; }
    public DateTime fecha { get; set; }
    public DateTime FechaVistaFormulario { get; set; }
    public String Autorizador { get; set; }
    public String NombreDePersona { get; set; }
    public String nombre { get; set; }
    public String apellidoP { get; set; }
    public String apellidoM { get; set; }
    public String tipo { get; set; }
    public int idDePersona { get; set; }
    public String materia { get; set; }
    public String grupo { get; set; }


}