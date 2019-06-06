using App_Com.DAL.Formularios;
using App_Com.DAL.Formularios.IncidentesDSTableAdapters;
using System;

public class IncidentesBRL
{
    public static Boolean insertFormulario(Incidentes incidentes)
    {
        try
        {
            pr_getFormularioAccidenteTableAdapter adapter = new pr_getFormularioAccidenteTableAdapter();
            adapter.Insert(incidentes.id, incidentes.fecha,incidentes.descripcion);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public static Incidentes getFormularioIncidentes(int id)
    {
        try
        {
            Incidentes incidentes = null;
            pr_getFormularioAccidenteTableAdapter adapter = new pr_getFormularioAccidenteTableAdapter();
            IncidentesDS.pr_getFormularioAccidenteDataTable tabla = adapter.getFormIncidentes(id);
            if (tabla != null && tabla.Rows.Count > 0)
            {
                IncidentesDS.pr_getFormularioAccidenteRow fila = tabla[0];
                incidentes = new Incidentes(fila.id, fila.fecha,fila.descripcion);
            }
            return incidentes;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
