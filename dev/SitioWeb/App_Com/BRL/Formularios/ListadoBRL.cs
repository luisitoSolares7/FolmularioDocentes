using App_Com.DAL.Formularios;
using App_Com.DAL.Formularios.ListadoDSTableAdapters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


public class ListadoBRL
{
    public static List<Listado> getListaPorCuenta(int id) {
        pr_getVistaFormulariosTableAdapter adapter = new pr_getVistaFormulariosTableAdapter();
        ListadoDS.pr_getVistaFormulariosDataTable table = adapter.getListadoForm(id);
        List<Listado> list = new List<Listado>();
        foreach (var row in table)
        {
            Listado listado=null;
            try {
                listado = new Listado(row.id, row.fkCuenta, row.fkTbl, row.estado, row.fecha, row.nombre, row.tipo,0);
            }catch (Exception e) {
                listado = new Listado(row.id, row.fkCuenta, row.fkTbl, row.nombre, row.tipo,-1);
            }
          
            list.Add(listado);
        }
        return list;
    }
}
