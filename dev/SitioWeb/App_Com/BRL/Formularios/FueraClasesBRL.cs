using System;
using App_Com.DAL.Formularios;
using App_Com.DAL.Formularios.FueraClasesDSTableAdapters;

public class FueraClasesBRL
{
    public static Boolean insertFormulario(FueraClases fueraClases)
    {
        try
        {
            pr_getFormularioFueraCTableAdapter adapter = new pr_getFormularioFueraCTableAdapter();
            adapter.Insert(fueraClases.id, fueraClases.fecha, fueraClases.materia, fueraClases.grupo, fueraClases.motivoActividad, fueraClases.fechaActividad, fueraClases.descripActividad, fueraClases.lugarActividad);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public static FueraClases getFormularioFueraClases(int id) {
        try
        {
            FueraClases fueraClases=null;
            pr_getFormularioFueraCTableAdapter adapter = new pr_getFormularioFueraCTableAdapter();
            FueraClasesDS.pr_getFormularioFueraCDataTable tabla = adapter.getFormFueraClase(id);
            if (tabla != null && tabla.Rows.Count > 0)
            {
                FueraClasesDS.pr_getFormularioFueraCRow fila = tabla[0];
                fueraClases = new FueraClases(fila.id, fila.fecha, fila.materia, fila.grupo, fila.motivoActividad,fila.fechaActividad,fila.descripActividad,fila.lugarActividad);
            }
            return fueraClases;
        }
        catch (Exception e) {
            return null;
        }
    }
}

