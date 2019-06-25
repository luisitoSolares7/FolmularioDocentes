using App_Com.BRL.Formularios;
using App_Com.Modelo.Formularios;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
[RoutePrefix("api/Formularios")]
public class ReprogramacionController : ApiController
{
    [HttpPost]
    [Route("insertReprogramacion")]
    public HttpResponseMessage insertFormFueraClases([FromBody] Reprogramacion reprogramacion)
    {
        HttpResponseMessage msg = null;
        Boolean bandera = ReprogramacionBRL.insertReprogramacion(reprogramacion);
        if (bandera)
        {
            msg = Request.CreateResponse<Boolean>(HttpStatusCode.OK, true);
        }
        else
        {
            msg = Request.CreateResponse<Reprogramacion>(HttpStatusCode.NotFound, null);
        }
        return msg;
    }
    [HttpPost]
    [Route("getReprogramacion")]
    public HttpResponseMessage getFormFueraClase([FromBody] Reprogramacion reprogramacion)
    {
        HttpResponseMessage msg = null;

        Reprogramacion form = ReprogramacionBRL.getFormularioReprogramacion(reprogramacion.id);
        if (form != null)
        {
            msg = Request.CreateResponse<Reprogramacion>(HttpStatusCode.OK, form);
            return msg;

        }
        else
        {
            msg = Request.CreateResponse<Reprogramacion>(HttpStatusCode.NotFound, form);
        }

        return msg;
    }
}
