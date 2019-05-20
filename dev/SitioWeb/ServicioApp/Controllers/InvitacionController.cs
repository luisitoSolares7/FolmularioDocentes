
using App.Model;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ServicioApp.Controllers
{   
    [RoutePrefix("api/invitacion")]
    public class InvitacionController : ApiController
    {
        [HttpGet]
        [Route("verify/{token}")]
        public HttpResponseMessage getVerificacion(string token)
        {
            HttpResponseMessage msg = null;
            Invitacion invitacion = InvitacionBRL.getInvitacion(token);
            if (invitacion != null)
            {
                msg = Request.CreateResponse<Invitacion>(HttpStatusCode.OK, invitacion);
            }
            else {
                msg = Request.CreateResponse<Invitacion>(HttpStatusCode.NotFound, null);
            }
            return msg;
        }
        [HttpPost]
        [Route("actualizarInvitacion")]
        public HttpResponseMessage setInvitacion([FromBody] Persona cuenta) {
            HttpResponseMessage msg = null;
            Invitacion invitacion = InvitacionBRL.verificacionCorreo(cuenta.correo);

            if (invitacion!=null)
             {
                 Random random = new Random();
                 int num = random.Next(10);
                 int num1 = random.Next(10);
                 int num2 = random.Next(10);
                 int num3 = random.Next(10);
                 string token = num + "-" + num1 + "-" + num2 + "-" + num3;
                 InvitacionBRL.desactivarEstado(invitacion.fkCuenta, token);
                 Correo correo = new Correo(cuenta.correo, "Recuperacion de Cuenta", "copia el siguiente Codigo y pegalo en la APP \n"+ token);
                 msg = Request.CreateResponse<Invitacion>(HttpStatusCode.OK, invitacion);
             }
             else
             {
                 msg = Request.CreateResponse<Invitacion>(HttpStatusCode.NotFound, invitacion);
             }
             return msg;
        }

    }
}
