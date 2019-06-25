using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

[RoutePrefix("api/Formularios")]
public class IncidentesController : ApiController
{
    [HttpPost]
    [Route("insertIncidentes")]
    public HttpResponseMessage insertFormFueraClases([FromBody] Incidentes fueraClases)
    {
        HttpResponseMessage msg = null;
        Boolean bandera = IncidentesBRL.insertFormulario(fueraClases);
        if (bandera)
        {
            msg = Request.CreateResponse<Boolean>(HttpStatusCode.OK, true);
        }
        else
        {
            msg = Request.CreateResponse<Cuenta>(HttpStatusCode.NotFound, null);
        }
        return msg;
    }
    [HttpPost]
    [Route("getIncidente")]
    public HttpResponseMessage getFormFueraClase([FromBody] Incidentes incidentes)
    {
        HttpResponseMessage msg = null;

        Incidentes form = IncidentesBRL.getFormularioIncidentes(incidentes.id);
        if (form != null)
        {
            msg = Request.CreateResponse<Incidentes>(HttpStatusCode.OK, form);
            return msg;

        }
        else
        {
            msg = Request.CreateResponse<Incidentes>(HttpStatusCode.NotFound, null);
        }

        return msg;
    }
}
