using App_Com.DAL.Formularios;
using App_Com.DAL.Formularios.ReprogramacionDSTableAdapters;
using App_Com.Modelo.Formularios;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace App_Com.BRL.Formularios
{
    public class ReprogramacionBRL
    {
        public static Boolean insertReprogramacion(Reprogramacion reprogramacion) {
            try
            {
                pr_getFormularioReprogramacionTableAdapter adapter = new pr_getFormularioReprogramacionTableAdapter();
                adapter.Insert(reprogramacion.id, reprogramacion.carrera, reprogramacion.materia, reprogramacion.grupo, reprogramacion.modalidad,
                    reprogramacion.horaI, reprogramacion.horaF, reprogramacion.dias, reprogramacion.motivoSolicitud,
                    reprogramacion.fecha,reprogramacion.fechaActividad);
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
        public static Reprogramacion getFormularioReprogramacion(int id) {
            try
            {
                Reprogramacion reprogramacion = null;
                pr_getFormularioReprogramacionTableAdapter adapter = new pr_getFormularioReprogramacionTableAdapter();
                ReprogramacionDS.pr_getFormularioReprogramacionDataTable tabla = adapter.getReprogramacion(id);
                if (tabla != null && tabla.Rows.Count > 0)
                {
                    ReprogramacionDS.pr_getFormularioReprogramacionRow fila = tabla[0];
                    reprogramacion = new Reprogramacion(fila.id, fila.carrera, fila.materia, fila.grupo, fila.modalidad, fila.horaI, fila.horaF, fila.dias, fila.motivoSolicitud, fila.fechaActividad, fila.fecha);
                }
                return reprogramacion;
            }
            catch (Exception e)
            {
                return null;
            }
           
        }
    }
}
