using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Invitacion
/// </summary>
public class Invitacion
{
    public Invitacion()
    {
        //
        // TODO: Agregar aquí la lógica del constructor
        //
    }
    public int id { get; set; }
    public String token { get; set; }
    public DateTime fechaInvitacion { get; set; }
    public DateTime fechaRespuesta { get; set; }
    public Boolean estado { get; set; }
    public String nombre { get; set; }
    public int fkPersona { get; set; }
    public int fkCuenta { get; set; }
    public string Fecha { get; set; }

    
    public Invitacion(int codigoID)
    {
        this.fkCuenta = codigoID;
    }

    public Invitacion(int codigoId, string nombre, int cuenta)
    {
        this.id = codigoId;
        this.nombre = nombre;
        this.fkCuenta = cuenta;
    }
    public Invitacion(int codigoId, string nombre)
    {
        this.id = codigoId;
        this.nombre = nombre;
    }

}