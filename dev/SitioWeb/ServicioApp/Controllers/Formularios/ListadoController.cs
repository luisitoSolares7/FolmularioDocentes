using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ServicioApp.Controllers.Formularios
{
    [RoutePrefix("api/listado")]
    public class ListadoController : ApiController
    {
        [HttpPost]
        [Route("getFormularios")]
        public HttpResponseMessage insertFormFueraClases([FromBody] Listado listado)
        {
            HttpResponseMessage msg = null;
            List<Listado> listados = ListadoBRL.getListaPorCuenta(listado.id);
            
            if (listados!=null)
            {
                msg = Request.CreateResponse<List<Listado>>(HttpStatusCode.OK, listados);
            }
            else
            {
                msg = Request.CreateResponse<List<Listado>>(HttpStatusCode.OK, null);
            }
            return msg;
        }
    }
}
