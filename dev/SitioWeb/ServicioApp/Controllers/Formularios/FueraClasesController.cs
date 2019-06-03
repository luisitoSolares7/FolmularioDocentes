using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

[RoutePrefix("api/Formularios")]
public class FueraClasesController : ApiController
{
    [HttpPost]
    [Route("insertFueraClases")]
    public HttpResponseMessage insertFormFueraClases([FromBody] FueraClases fueraClases)
    {
        HttpResponseMessage msg = null;
        Boolean bandera = FueraClasesBRL.insertFormulario(fueraClases);
        if (bandera)
        {
            msg = Request.CreateResponse<Boolean>(HttpStatusCode.OK,true);
        }
        else
        {
            msg = Request.CreateResponse<Cuenta>(HttpStatusCode.NotFound, null);
        }
        return msg;
    }
    [HttpPost]
    [Route("getFueraClases")]
    public HttpResponseMessage getFormFueraClase([FromBody] FueraClases fueraClases)
    {
        HttpResponseMessage msg = null;
     
        FueraClases form = FueraClasesBRL.getFormularioFueraClases(fueraClases.id);
        if (form != null)
        {
                msg = Request.CreateResponse<FueraClases>(HttpStatusCode.OK, form);
                return msg;
         
        }
        else
        {
            msg = Request.CreateResponse<FueraClases>(HttpStatusCode.NotFound, form);
        }

        return msg;
    }
}

