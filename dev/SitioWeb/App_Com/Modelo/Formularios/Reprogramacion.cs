using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace App_Com.Modelo.Formularios
{
   public class Reprogramacion
    {
      public  int id { get; set; }
      public string carrera { get; set; }
      public string materia { get; set; }
      public string grupo { get; set; }
      public string modalidad { get; set; }
      public TimeSpan horaI { get; set; }
      public TimeSpan horaF { get; set; }
      public string dias { get; set; }
      public string motivoSolicitud { get; set; }
      public DateTime fechaActividad { get; set; }
      public DateTime fecha { get; set; }
        
        public Reprogramacion() {
        }

        public Reprogramacion(int id, string carrera, string materia, string grupo, string modalidad, TimeSpan horaI, TimeSpan horaF, string dias, string motivoSolicitud, DateTime fechaActividad, DateTime fecha)
        {
            this.id = id;
            this.carrera = carrera;
            this.materia = materia;
            this.grupo = grupo;
            this.modalidad = modalidad;
            this.horaI = horaI;
            this.horaF = horaF;
            this.dias = dias;
            this.motivoSolicitud = motivoSolicitud;
            this.fechaActividad = fechaActividad;
            this.fecha = fecha;
        }
    }
}
