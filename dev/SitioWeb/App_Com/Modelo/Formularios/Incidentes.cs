using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class Incidentes
{
    public int id { get; set; }
    public DateTime fecha { get; set; }
    public string descripcion { get; set; }

    public Incidentes()
    {
    }

    public Incidentes(int id, DateTime fecha, string descripcion)
    {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
}
