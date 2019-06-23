using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de HistorialPedidosReprogramacion
/// </summary>
public class HistorialPedidosReprogramacion
{
    public HistorialPedidosReprogramacion()
    { }
    public int id { get; set; }
    public String carrera { get; set; }
    public String materia { get; set; }
    public String grupo { get; set; }
    public String modalidad { get; set; }
    public TimeSpan horaI { get; set; }
    public TimeSpan horaF { get; set; }
    public String dias { get; set; }
    public String motivoSolicitud { get; set; }
    public DateTime fechaActividad { get; set; }
    public DateTime fecha { get; set; }
    public int idvistaformulario { get; set; }
    public int fkCuenta { get; set; }
    public int fkTbl { get; set; }
    public Boolean estado { get; set; }
    public DateTime FechaVistaFormulario { get; set; }
    public String Autorizador { get; set; }
    public String nombre { get; set; }
    public String tipo { get; set; }
    public int idDePersona { get; set; }
    public String NombreDePersona { get; set; }
    public String apellidoP { get; set; }
   public String apellidoM { get; set; }
    public String correo { get; set; }
     public int telefono { get; set; }


   

}