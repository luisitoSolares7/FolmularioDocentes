using Newtonsoft.Json.Linq;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ServicioApp.Controllers
{
    [RoutePrefix("api")]
    public class CuentaController : ApiController
    {
        [HttpPost]
        [Route("login")]
        public HttpResponseMessage getVerificacion([FromBody]Cuenta cuenta)
        {
            HttpResponseMessage msg = null;
            string user = cuenta.nombreCuenta;
            string password = cuenta.contracena;
            Cuenta cuentaPer = CuentaBRL.getLogin(user, password);
            if (cuentaPer != null)
            {
                msg = Request.CreateResponse<Cuenta>(HttpStatusCode.OK, cuentaPer);
            }
            else
            {
                msg = Request.CreateResponse<Cuenta>(HttpStatusCode.NotFound, null);
            }

            return msg;
        }
        [HttpPost]
        [Route("creacionCuenta")]
        public HttpResponseMessage getCuenta([FromBody]Cuenta cuenta)
        {
            HttpResponseMessage msg = null;
            int idtoken = cuenta.cuentaId;
            string user = cuenta.nombreCuenta;
            string password = cuenta.contracena;
            Boolean bandera=CuentaBRL.insertarCuenta(user, password);
            Boolean bandera2=InvitacionBRL.actualizarInvitacion(idtoken);
            if (bandera && bandera2)
            {
                msg = Request.CreateResponse<Boolean>(HttpStatusCode.OK, true);
            }
            else
            {
                msg = Request.CreateResponse<Boolean>(HttpStatusCode.NotFound, false);

            }

            return msg;
        }
        [HttpPost]
        [Route("actualizar")]
        public HttpResponseMessage getActualizarCuentaPassword([FromBody]Cuenta cuenta)
        {
            HttpResponseMessage msg = null;
            int id = cuenta.cuentaId;
            string password = cuenta.contracena;
            string newPassword = cuenta.newContracena;
            Cuenta cuentaPer = CuentaBRL.verificacionCuenta(id, password);
            if (cuentaPer != null)
            {
                if (CuentaBRL.actualizacionCuentaPassword(id, newPassword))
                {
                    msg = Request.CreateResponse<Cuenta>(HttpStatusCode.OK, cuentaPer);
                    return msg;
                }
                
            }
            else
            {
                msg = Request.CreateResponse<Cuenta>(HttpStatusCode.NotFound, cuentaPer);
            }

            return msg;
        }
        [HttpPost]
        [Route("recuperacionCuenta")]
        public HttpResponseMessage getRecuperacionCuenta([FromBody]Cuenta cuenta)
        {
            HttpResponseMessage msg = null;

            try
            {
                int id = cuenta.cuentaId;
                string password = cuenta.contracena;
                string nombreCuenta = cuenta.nombreCuenta;
                InvitacionBRL.actualizacionCorreoInvitacion(id, nombreCuenta, password);
                msg = Request.CreateResponse<Cuenta>(HttpStatusCode.OK,cuenta);
            }
            catch (Exception e) {
                msg = Request.CreateResponse<Cuenta>(HttpStatusCode.NotFound, null);
            }
            return msg;
        }
    }
}
