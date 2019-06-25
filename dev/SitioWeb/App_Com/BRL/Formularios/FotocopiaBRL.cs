using System;
using System.Collections.Generic;

public class FotocopiaBRL
{
    /*
    public static Fotocopia getFotocopiaID(int id)
    {
        Fotocopia fotocopia = null;
            pr_getFormularioFotocopiaTableAdapter adapter = new pr_getFormularioFotocopiaTableAdapter();
        FotocopiaDS.pr_getFormularioFotocopiaDataTable tabla = adapter.getFotocopia(id);
        if (tabla != null && tabla.Rows.Count > 0)
        {
            FotocopiaDS.pr_getFormularioFotocopiaRow fila = tabla[0];
           fotocopia = new Fotocopia(fila.id, fila.cantidad, fila.materia, fila.fecha, fila.tipoDocuento, fila.Imagen);
        }
        return fotocopia;
    }
    public static List<Fotocopia> getListaPorCuenta(int id)
    {
        pr_getFormularioFotocopiaTableAdapter adapter = new pr_getFormularioFotocopiaTableAdapter();
        FotocopiaDS.pr_getFormularioFotocopiaDataTable tabla = adapter.getFotocopiasAll(id);
        List<Fotocopia> list = new List<Fotocopia>();
        foreach (var fila in tabla)
        {
            Fotocopia fotocopia = null;
            try
            {
                fotocopia = new Fotocopia(fila.id, fila.cantidad, fila.materia, fila.fecha, fila.tipoDocuento, fila.Imagen);
            }
            catch (Exception e)
            {
                fotocopia = new Fotocopia(fila.id, fila.cantidad, fila.materia, fila.fecha, fila.tipoDocuento);
            }

            list.Add(fotocopia);
        }
        return list;
    }*/
}