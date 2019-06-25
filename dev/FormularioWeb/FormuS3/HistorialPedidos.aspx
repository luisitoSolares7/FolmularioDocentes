<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage/MasterPage.master" AutoEventWireup="true" CodeFile="HistorialPedidos.aspx.cs" Inherits="Historial" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
  <asp:ScriptManager runat="server" EnablePageMethods="true" ID ="metodos"></asp:ScriptManager>
    <div id="contenedor">
        <div class="col-12 titulo">
            <h1>Historial de Pedidos</h1>
              
           </div>
        
        </div>
        <div class="table-wrapper-scroll-y my-custom-scrollbar tabla">
            <asp:GridView ID="tblFormAccidente" runat="server" CssClass="table table-dark table-bordered table-striped mb-0"
           AutoGenerateColumns="false">

                <Columns>

                   <asp:BoundField DataField="Nombre" HeaderText="Nombre Formulario" />
                 <asp:BoundField DataField="Fecha" HeaderText="Fecha"   
                        SortExpression="Fecha" DataFormatString="{0:d}"  HtmlEncode=false />
                   <asp:BoundField DataField="FechaVista" HeaderText="Fecha de Aceptacion "
                         SortExpression="FechaVista" DataFormatString="{0:d}"  HtmlEncode=false />
                   <asp:TemplateField HeaderText="Ver Detalle Formulario">
                        <ItemTemplate>
         
                            <asp:Button Text="Ver Descripcion Detallada   "  OnClientClick='<%# String.Format("javascript:return verificarPedidos(\"{0}\",\"{1}\",\"{2}\",\"{3}\",\"{4}\")", Eval("descripcion"),Eval("fecha"),Eval("NombreDePersona"),Eval("ApellidoP"),Eval("ApellidoM")) %>'  runat="server"/>  
                        </ItemTemplate>
                    </asp:TemplateField>

                </Columns>
            </asp:GridView>
        </div>

      <div class="table-wrapper-scroll-y my-custom-scrollbar tabla">
            <asp:GridView ID="tblFormClasesFuera" runat="server" CssClass="table table-dark table-bordered table-striped mb-0"
           AutoGenerateColumns="false">
               
                <Columns>
                    <asp:BoundField DataField="nombre" HeaderText="Nombre Formulario" />
                 <asp:BoundField DataField="Fecha" HeaderText="Fecha"   
                        SortExpression="Fecha" DataFormatString="{0:d}"  HtmlEncode=false />
                    <asp:BoundField DataField="FechaVistaFormulario" HeaderText="Fecha de Aceptacion " 
                          SortExpression="FechaVistaFormulario" DataFormatString="{0:d}"  HtmlEncode=false />
                    <asp:TemplateField HeaderText="Ver Detalle Formulario">                                                                                                  
                       <ItemTemplate>
                            
                                  <asp:Button Text="Ver Descripcion Detallada"  OnClientClick='<%# String.Format("javascript:return verificarPedidosClases(\"{0}\",\"{1}\",\"{2}\",\"{3}\",\"{4}\",\"{5}\",\"{6}\",\"{7}\",\"{8}\",\"{9}\")", Eval("NombreDePersona"),Eval("ApellidoP"),Eval("ApellidoM"),Eval("fecha"),Eval("materia"),Eval("grupo"),Eval("descripActividad"),Eval("motivoActividad"),Eval("fechaActividad"),Eval("lugarActividad")) %>'  runat="server"/>                              
                        </ItemTemplate>
                    </asp:TemplateField>

                </Columns>
            </asp:GridView>
        </div>



     <div class="table-wrapper-scroll-y my-custom-scrollbar tabla">
            <asp:GridView ID="tblFormReprogramacion" runat="server" CssClass="table table-dark table-bordered table-striped mb-0"
           AutoGenerateColumns="false">
               
                <Columns>
                    <asp:BoundField DataField="nombre" HeaderText="Nombre Formulario" />
                    <asp:BoundField DataField="Fecha" HeaderText="Fecha"   
                        SortExpression="Fecha" DataFormatString="{0:d}"  HtmlEncode=false />
                    <asp:BoundField DataField="FechaVistaFormulario" HeaderText="Fecha de Aceptacion "
                          SortExpression="FechaVistaFormulario" DataFormatString="{0:d}"  HtmlEncode=false />
                    <asp:TemplateField HeaderText="Ver Detalle Formulario">
                        <ItemTemplate>
                                <asp:Button Text="Ver Descripcion Detallada"  OnClientClick='<%# String.Format("javascript:return verificarPedidosReprogramacion(\"{0}\",\"{1}\",\"{2}\",\"{3}\",\"{4}\",\"{5}\",\"{6}\",\"{7}\",\"{8}\",\"{9}\",\"{10}\",\"{11}\",\"{12}\")", Eval("NombreDePersona"),Eval("ApellidoP"),Eval("ApellidoM"),Eval("fecha"),Eval("carrera"),Eval("materia"),Eval("grupo"),Eval("modalidad"),Eval("horaI"),Eval("horaF"),Eval("dias"),Eval("motivoSolicitud"),Eval("fechaActividad")) %>'  runat="server"/>                              
                        </ItemTemplate>
                    </asp:TemplateField>

                </Columns>
            </asp:GridView>

        </div>


     <div class="table-wrapper-scroll-y my-custom-scrollbar tabla">
            <asp:GridView ID="tblFormFotocopia" runat="server" CssClass="table table-dark table-bordered table-striped mb-0"
           AutoGenerateColumns="false">
               
                <Columns>
                    <asp:BoundField DataField="nombre" HeaderText="Nombre Formulario" />
                    <asp:BoundField DataField="Fecha" HeaderText="Fecha" 
                     SortExpression="Fecha" DataFormatString="{0:d}"  HtmlEncode=false />
                    <asp:BoundField DataField="FechaVistaFormulario" HeaderText="Fecha de Aceptacion " 
                         SortExpression="FechaVistaFormulario" DataFormatString="{0:d}"  HtmlEncode=false />
                    <asp:TemplateField HeaderText="Ver Detalle Formulario">                                                                                                  
                       <ItemTemplate>
                            
                                  <asp:Button Text="Ver Descripcion Detallada"  OnClientClick='<%# String.Format("javascript:return verificarPedidosFotocopias(\"{0}\",\"{1}\",\"{2}\",\"{3}\",\"{4}\",\"{5}\",\"{6}\")", Eval("NombreDePersona"),Eval("ApellidoP"),Eval("ApellidoM"),Eval("fecha"),Eval("materia"),Eval("cantidad"),Eval("tipoDocuento")) %>'  runat="server"/>                              
                        </ItemTemplate>
                    </asp:TemplateField>

                </Columns>
            </asp:GridView>
        </div>

    </div>
       
          
        <div id="myModal" class="modal fade formularios" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <strong class="modal-title">Descripcion del Docente</strong>
                   
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <div class ="dv">







                  <div class ="dv14">
                   
                    <div class="form-group">
                        <strong>Fecha</strong>
                      <p id ="fecha"></p>
                    </div>
                      </div>

                          <div class ="dv15">
                        <div class="form-group">
                        <strong>descripcion</strong>
                      <p id ="descripcion"></p>
                    </div>
                       </div>
                    <div class="form-group">
                         <strong>Nombre del Docente</strong>
                      <p id ="NombreDePersona"></p>
                    </div>
                     <div class="form-group">
                       <strong>Apellido Paterno</strong>
                      <p id ="apellidoP"></p>
                    </div>
                     <div class="form-group">
                         <strong>Apellido Materno</strong>
                      <p id ="apellidoM"></p>
                    </div>

                      
                        <div class ="dv7">
                      <div class="form-group">
                        <strong>Carrera</strong>
                      <p id ="carrera"></p>
                    </div>
                       </div>
                          <div class ="dv1">
                         <div class="form-group">
                        <strong>Materia</strong>
                      <p id ="materia"></p>
                    </div>
                   </div>
                     <div class ="dv8">
                      <div class="form-group">
                        <strong>Modalidad</strong>
                      <p id ="modalidad"></p>
                    </div>
                    </div>


                    
                         <div class ="dv2">
                     <div class="form-group">
                        <strong>Grupo</strong>
                      <p id ="grupo"></p>
                    </div>
                    </div>         
                           
                     <div class ="dv9">
                    <div class="form-group">
                        <strong>Hora Inicial</strong>
                      <p id ="horaI"></p>
                    </div>
                    </div>
                          <div class ="dv10">
                      <div class="form-group">
                        <strong>Hora Fin</strong>
                      <p id ="horaF"></p>
                    </div>
                     </div>
                         <div class ="dv11">
                      <div class="form-group">
                        <strong>Dias</strong>
                      <p id ="dias"></p>
                    </div>
                      </div>
                            <div class ="dv12">
                      <div class="form-group">
                        <strong>Motivo de Reprogramacion</strong>
                      <p id ="motivoSolicitud"></p>
                    </div>
                     </div>

                       <div class ="dv3">
                       <div class="form-group">
                        <strong>Motivo de la Actividad</strong>
                      <p id ="motivoActividad"></p>
                     </div>
                     </div> 

                     
                    <div class ="dv5">
                      <div class="form-group">
                        <strong>Descripcion de la Actividad </strong>
                      <p id ="descripActividad"></p>
                    </div>  
                    </div>  
                           <div class ="dv6">
                      <div class="form-group">
                        <strong>Lugar de la Actividad</strong>
                      <p id ="lugarActividad"></p>
                    </div> 
                    </div> 

                      <div class ="dv100">
                    <div class="form-group">
                        <strong>Fecha de la Clase que no Ejecutara</strong>
                      <p id ="fechaActividad"></p>
                    </div>

                    </div>
                    
                   <div class ="dv4">
                    <div class="form-group">
                        <strong>Fecha de la Clase que no se Pasara</strong>
                      <p id ="fechaActividad"></p>
                    </div>

                    </div>

                         <div class ="dv31">
                    <div class="form-group">
                        <strong>Cantidad de Copias</strong>
                      <p id ="cantidad"></p>
                    </div>


                    </div>

                          <div class ="dv32">
                    <div class="form-group">
                        <strong>Tipo de Documento</strong>
                      <p id ="tipoDocuento"></p>
                    </div>


                    </div>


                   
                <div class="modal-footer">
                    
                
                <button type="button" class="btn btn-danger" data-dismiss="modal" >Cancelar</button>
                </div>
            </div>
        </div>
    </div>
       


        </div>


      

   <script  type="text/javascript">
    
         
       function verificarPedidos(descrp,fecha,NombreDePersona,ApellidoP,ApellidoM) {


           $('#fecha').text(fecha);
           $('#NombreDePersona').text(NombreDePersona);
           $('#apellidoP').text(ApellidoP);
           $('#apellidoM').text(ApellidoM);
           $('#descripcion').text(descrp);
       
              
           $('.dv1').hide();
           $('.dv2').hide();
           $('.dv3').hide();
           $('.dv4').hide();
           $('.dv5').hide();
           $('.dv6').hide();
           $('.dv7').hide();
           $('.dv8').hide();
           $('.dv9').hide();
           $('.dv10').hide();
           $('.dv11').hide();
           $('.dv12').hide();
           $('.dv20').hide();
           $('.dv30').hide();
           $('.dv31').hide();
           $('.dv32').hide();
           $('.dv33').hide();
           $('.dv15').show();
           $('.dv100').hide();

           
                   

           $('#myModal').modal('show');
           return false;
       }
         
         
    </script>



     <script  type="text/javascript">
    
         
         function verificarPedidosClases(NombreDePersona,ApellidoP,ApellidoM,fecha,materia,grupo,descripActividad,motivoActividad,fechaActividad,lugarActividad) {

          $('#NombreDePersona').text(NombreDePersona);
          $('#apellidoP').text(ApellidoP);
          $('#apellidoM').text(ApellidoM);
          $('#fecha').text(fecha);
          $('#materia').text(materia);
          $('#grupo').text(grupo);
          $('#descripActividad').text(descripActividad);
          $('#motivoActividad').text(motivoActividad);
          $('#fechaActividad').text(fechaActividad);
          $('#lugarActividad').text(lugarActividad);
        
           $('.dv1').show();
           $('.dv2').show();
           $('.dv3').show();
             $('.dv100').show();
              $('.dv4').hide();
           $('.dv5').show();
           $('.dv6').show();
           $('.dv9').hide();
           $('.dv8').hide();
           $('.dv7').hide();
           $('.dv10').hide();
           $('.dv11').hide();
           $('.dv12').hide();
           $('.dv15').hide();
           $('.dv20').hide();
           $('.dv30').hide();
           $('.dv31').hide();
           $('.dv32').hide();
           $('.dv33').hide();
      
         
           
                         

           $('#myModal').modal('show');
           return false;
       }
         
         
    </script>


     <script  type="text/javascript">
    
         
         function verificarPedidosReprogramacion(NombreDePersona,ApellidoP,ApellidoM,fecha,carrera,materia,grupo,modalidad,horaI,horaF,dias,motivoSolicitud,fechaActividad) {

          $('#NombreDePersona').text(NombreDePersona);
          $('#apellidoP').text(ApellidoP);
          $('#apellidoM').text(ApellidoM);
          $('#fecha').text(fecha);
          $('#materia').text(materia);
          $('#grupo').text(grupo);
          $('#motivoSolicitud').text(motivoSolicitud);
          $('#modalidad').text(modalidad);
          $('#horaI').text(horaI);
          $('#horaF').text(horaF);
          $('#modalidad').text(modalidad);
          $('#fechaActividad').text(fechaActividad);
          $('#dias').text(dias);
          $('#carrera').text(carrera);
     
           $('.dv1').hide();
           $('.dv2').hide();
           $('.dv3').hide();
           $('.dv4').show();
           $('.dv5').hide();
           $('.dv6').hide();
           $('.dv2').show();
           $('.dv7').hide();
           $('.dv1').show(); 
           $('.dv7').show();
           $('.dv8').show();
           $('.dv9').show();
           $('.dv10').show();
           $('.dv11').show();
           $('.dv12').show();
           $('.dv15').hide();
           $('.dv20').show();
           $('.dv30').hide();
           $('.dv31').hide();
           $('.dv32').hide();
           $('.dv33').hide();
           $('.dv4').hide();
           $('#myModal').modal('show');
           return false;
       }
         
         
    </script>


            <script  type="text/javascript">
    
         
                function verificarPedidosFotocopias(NombreDePersona, ApellidoP, ApellidoM, fecha, materia, cantidad, tipoDocumento) {

                    $('#NombreDePersona').text(NombreDePersona);
                    $('#apellidoP').text(ApellidoP);
                    $('#apellidoM').text(ApellidoM);
                    $('#fecha').text(fecha);
                    $('#materia').text(materia);
                    $('#cantidad').text(cantidad);
                    $('#tipoDocuento').text(tipoDocumento);

                   $('.dv1').show();
                   $('.dv2').hide();
                   $('.dv3').hide();
                   $('.dv4').show();
                   $('.dv5').hide();
                   $('.dv6').hide();
                   $('.dv7').show();
                   $('.dv7').hide();
                   $('.dv8').hide();
                   $('.dv9').hide();
                   $('.dv10').hide();
                   $('.dv11').hide();
                   $('.dv30').hide();
                   $('.dv31').show();
                   $('.dv32').show();
                   $('.dv33').show();
                   $('.dv20').show();
                   $('.dv4').hide();
                   $('.dv12').hide();
                   $('.dv15').hide();
                   $('.dv100').hide();
                  

          


           $('#myModal').modal('show');
           return false;
       }
         
         
    </script>



        
</asp:Content>