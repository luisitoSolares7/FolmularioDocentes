using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class Listado
{
    public int id { get; set; }
    public int fkCuenta { get; set; }
    public int fkTbl { get; set; }
    public Boolean estado { get; set; }
    public DateTime fecha { get; set; }
    public int autorizador { get; set; }
    public string nombre { get; set; }
    public int tipo { get; set; }

    public Listado()
    {
    }

    public Listado(int id, int fkCuenta, int fkTbl, bool estado, string nombre, int tipo)
    {
        this.id = id;
        this.fkCuenta = fkCuenta;
        this.fkTbl = fkTbl;
        this.estado = estado;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Listado(int id, int fkCuenta, int fkTbl, bool estado, DateTime fecha, int autorizador, string nombre, int tipo)
    {
        this.id = id;
        this.fkCuenta = fkCuenta;
        this.fkTbl = fkTbl;
        this.estado = estado;
        this.fecha = fecha;
        this.autorizador = autorizador;
        this.nombre = nombre;
        this.tipo = tipo;
    }
}