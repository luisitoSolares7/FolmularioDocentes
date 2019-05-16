using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
public class Invitacion
{
    public int id { get; set; }
    public String token { get; set; }
    public DateTime fechaInvitacion { get; set; }
    public DateTime fechaRespuesta { get; set; }
    public Boolean estado { get; set; }
    public int fkPersona { get; set; }
    public int fkCuenta { get; set; }
    public string nombre { get; set; }    

    public Invitacion()
    {
        //
        // TODO: Agregar aquí la lógica del constructor
        //
    }

    
    
    public Invitacion(int codigoId, string nombre)
    {
        this.id = codigoId;
        this.nombre = nombre;
    }
    
}
