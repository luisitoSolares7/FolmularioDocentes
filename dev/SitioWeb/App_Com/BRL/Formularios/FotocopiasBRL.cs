using App_Com.DAL.Formularios;
using App_Com.DAL.Formularios.FotocopiasDSTableAdapters;
using App_Com.Modelo.Formularios;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace App_Com.BRL.Formularios
{
    public class FotocopiasBRL
    {
        public static Boolean insertFormulario(Fotocopias fotocopia)
        {
            pr_getFormularioFotocopiaTableAdapter adapter = new pr_getFormularioFotocopiaTableAdapter();
            adapter.Insert(fotocopia.id, fotocopia.fecha, fotocopia.cantidad, fotocopia.tipoDocuento, fotocopia.materia, fotocopia.img1, fotocopia.img2, fotocopia.img3);
            return true;

        }
        public static Fotocopias getFotocopiaID(int id)
        {
            Fotocopias fotocopia = null;
            pr_getFormularioFotocopiaTableAdapter adapter = new pr_getFormularioFotocopiaTableAdapter();
            FotocopiasDS.pr_getFormularioFotocopiaDataTable tabla = adapter.GetFotocopia(id);
            if (tabla != null && tabla.Rows.Count > 0)
            {
                FotocopiasDS.pr_getFormularioFotocopiaRow fila = tabla[0];
                fotocopia = new Fotocopias();
                fotocopia.id = fila.id;
                fotocopia.cantidad = fila.cantidad;
                fotocopia.fecha = fila.fecha;
                fotocopia.materia = fila.materia;
                fotocopia.tipoDocuento = fila.tipoDocuento;

            }
            return fotocopia;
        }
        public static List<Fotocopias> getListaPorCuenta(int id)
        {
            pr_getFormularioFotocopiaTableAdapter adapter = new pr_getFormularioFotocopiaTableAdapter();
            FotocopiasDS.pr_getFormularioFotocopiaDataTable tabla = adapter.GetFotocopiaAllID(id);
            List<Fotocopias> list = new List<Fotocopias>();
            foreach (var fila in tabla)
            {
                Fotocopias fotocopia = null;
                try
                {
                    fotocopia = new Fotocopias();
                    fotocopia.id = fila.id;
                    fotocopia.cantidad = fila.cantidad;
                    fotocopia.fecha = fila.fecha;
                    fotocopia.materia = fila.materia;
                    fotocopia.tipoDocuento = fila.tipoDocuento;
                    fotocopia.Imagen = fila.Imagen;

                }
                catch (Exception e)
                {
                    fotocopia = new Fotocopias();
                    fotocopia.id = fila.id;
                    fotocopia.cantidad = fila.cantidad;
                    fotocopia.fecha = fila.fecha;
                    fotocopia.materia = fila.materia;
                    fotocopia.tipoDocuento = fila.tipoDocuento;
                }

                list.Add(fotocopia);
            }
            return list;
        }
    }
}
