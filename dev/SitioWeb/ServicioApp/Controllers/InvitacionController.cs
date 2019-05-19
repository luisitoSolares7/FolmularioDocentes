﻿
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
    }
}