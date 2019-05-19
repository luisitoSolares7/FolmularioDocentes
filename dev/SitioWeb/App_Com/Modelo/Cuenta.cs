using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Cuenta
/// </summary>
public class Cuenta
{
    public int cuentaId { get; set; }
    public String nombreCuenta { get; set; }
    public String contracena { get; set; }
    public int tipo { get; set; }
    public Boolean estado { get; set; }
    public string newContracena { get; set; }

    public Cuenta()
    {
        //
        // TODO: Agregar aquí la lógica del constructor
        //
    }



    public Cuenta(string nombreCuenta)
    {
        this.nombreCuenta = nombreCuenta;
    }

    public Cuenta(int cuentaId, string contracena, string nombreCuenta,int tipo,Boolean estado)
    {
        this.cuentaId = cuentaId;
        this.contracena = contracena;
        this.tipo = tipo;
        this.estado = estado;
        this.nombreCuenta = nombreCuenta;
    }

    public Cuenta(string nombreCuenta, string contracena)
    {
        this.contracena = contracena;
        this.nombreCuenta = nombreCuenta;
    }




}
