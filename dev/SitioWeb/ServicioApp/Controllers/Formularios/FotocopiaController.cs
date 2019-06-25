using App_Com.BRL.Formularios;
using App_Com.Modelo.Formularios;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

[RoutePrefix("api/Formularios")]
public class FotocopiaController : ApiController
{
    [HttpPost]
    [Route("insertFotocopia")]
    public HttpResponseMessage insertFotocopia([FromBody] Fotocopias fotocopia)
    {
        HttpResponseMessage msg = null;
        Boolean bandera = FotocopiasBRL.insertFormulario(fotocopia);
        if (bandera)
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
    [Route("getFotocopia")]
    public HttpResponseMessage getFormFotocopia([FromBody] Fotocopias fotocopia)
    {
        HttpResponseMessage msg = null;

        Fotocopias form = FotocopiasBRL.getFotocopiaID(fotocopia.id);
        if (form != null)
        {
            msg = Request.CreateResponse<Fotocopias>(HttpStatusCode.OK, form);
            return msg;

        }
        else
        {
            msg = Request.CreateResponse<Fotocopias>(HttpStatusCode.NotFound, form);
        }

        return msg;
    }
    [HttpPost]
    [Route("getFormularioFotocopia")]
    public HttpResponseMessage getFormFotocopiaAll([FromBody] Fotocopias listado)
    {
        HttpResponseMessage msg = null;
        List<Fotocopias> listados = FotocopiasBRL.getListaPorCuenta(listado.id);

        if (listados != null)
        {
            msg = Request.CreateResponse<List<Fotocopias>>(HttpStatusCode.OK, listados);
        }
        else
        {
            msg = Request.CreateResponse<List<Fotocopias>>(HttpStatusCode.OK, null);
        }
        return msg;
    }
}
