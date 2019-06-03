using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class FueraClases
{
    public int id { get; set; }
    public DateTime fecha { get; set; }
    public string materia { get; set; }
    public string grupo { get; set; }
    public string motivoActividad { get; set; }
    public DateTime fechaActividad { get; set; }
    public string descripActividad { get; set; }
    public string lugarActividad { get; set; }

    public FueraClases()
    {
    }

    public FueraClases(int id, DateTime fecha, string materia, string grupo, string motivoActividad, DateTime fechaActividad, string descripActividad, string lugarActividad)
    {
        this.id = id;
        this.fecha = fecha;
        this.materia = materia;
        this.grupo = grupo;
        this.motivoActividad = motivoActividad;
        this.fechaActividad = fechaActividad;
        this.descripActividad = descripActividad;
        this.lugarActividad = lugarActividad;
    }
    
}
