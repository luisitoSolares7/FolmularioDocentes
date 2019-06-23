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
                   <asp:BoundField DataField="Fecha" HeaderText="Fecha" />
                   <asp:BoundField DataField="FechaVista" HeaderText="FechaAceptacion " />
                   <asp:TemplateField HeaderText="Ver Detalle Formulario">
                        <ItemTemplate>
         
                            <asp:Button Text="Ver Descripcion Detallada   "  OnClientClick='<%# String.Format("javascript:return verificarPedidos(\"{0}\",\"{1}\",\"{2}\",\"{3}\",\"{4}\",\"{5}\")", Eval("descripcion"),Eval("fecha"),Eval("fechaVista"),Eval("NombreDePersona"),Eval("ApellidoP"),Eval("ApellidoM")) %>'  runat="server"/>  
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
                    <asp:BoundField DataField="Fecha" HeaderText="Fecha" />
                    <asp:BoundField DataField="FechaVistaFormulario" HeaderText="FechaAceptacion " />
                    <asp:TemplateField HeaderText="Ver Detalle Formulario">                                                                                                  
                       <ItemTemplate>
                            
                                  <asp:Button Text="Ver Descripcion Detallada"  OnClientClick='<%# String.Format("javascript:return verificarPedidosClases(\"{0}\",\"{1}\",\"{2}\",\"{3}\",\"{4}\",\"{5}\",\"{6}\",\"{7}\",\"{8}\",\"{9}\",\"{10}\")", Eval("NombreDePersona"),Eval("ApellidoP"),Eval("ApellidoM"),Eval("fecha"),Eval("materia"),Eval("grupo"),Eval("descripActividad"),Eval("motivoActividad"),Eval("fechaActividad"),Eval("lugarActividad"),Eval("FechaVistaFormulario")) %>'  runat="server"/>                              
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
                    <asp:BoundField DataField="Fecha" HeaderText="Fecha" />
                    <asp:BoundField DataField="FechaVistaFormulario" HeaderText="FechaAceptacion " />
                    <asp:TemplateField HeaderText="Ver Detalle Formulario">
                        <ItemTemplate>
                              <asp:Button Text="Ver Descripcion Detallada"  OnClientClick='<%# String.Format("javascript:return verificarPedidosReprogramacion(\"{0}\",\"{1}\",\"{2}\",\"{3}\",\"{4}\",\"{5}\",\"{6}\",\"{7}\",\"{8}\",\"{9}\",\"{10}\",\"{11}\"),\"{12}\"))", Eval("NombreDePersona"),Eval("ApellidoP"),Eval("ApellidoM"),Eval("fecha"),Eval("materia"),Eval("grupo"),Eval("modalidad"),Eval("horaI"),Eval("horaF"),Eval("dias"),Eval("motivoSolicitud"),Eval("fechaActividad"),Eval("FechaVistaFormulario")) %>'  runat="server"/>                              
                        </ItemTemplate>
                    </asp:TemplateField>

                </Columns>
            </asp:GridView>
        </div>
  
       
       
        <div id="myModal" class="modal fade formularios" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <strong class="modal-title">Descripcion del Docente</strong>
                   
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    
                    <div class="form-group" style="display: none">
                        <label>ID</label>
                        <asp:TextBox CssClass="form-control" ID="txtID" runat="server"></asp:TextBox>
                    </div>
                   
                    <div class="form-group">
                        <strong>fecha</strong>
                      <p id ="fecha"></p>
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
                   
                      
                </div>
                <div class="modal-footer">
                    
                
                <button type="button" class="btn btn-danger" data-dismiss="modal" >Cancelar</button>
                </div>
            </div>
        </div>
    </div>
       


    
        <div id="myModal1" class="modal fade formularios" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <strong class="modal-title">Descripcion del Docente</strong>
                   
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    
                    <div class="form-group" style="display: none">
                        <label>ID</label>
                        <asp:TextBox CssClass="form-control" ID="TextBox1" runat="server"></asp:TextBox>
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
                       <div class="form-group">
                        <strong>fecha</strong>
                      <p id ="fecha"></p>
                    </div> 

                    
                     <div class="form-group">
                        <strong>Materia</strong>
                      <p id ="materia"></p>
                    </div>


                     <div class="form-group">
                        <strong>Grupo</strong>
                      <p id ="grupo"></p>
                    </div>

                       <div class="form-group">
                        <strong>Motivo de la Actividad</strong>
                      <p id ="motivoActividad"></p>
                    </div>

                    <div class="form-group">
                        <strong>Fecha de la Clase que no se Pasara</strong>
                      <p id ="fechaActividad"></p>
                    </div>

                      <div class="form-group">
                        <strong>Descripcion de la Actividad </strong>
                      <p id ="descripActividad"></p>
                    </div>  
                      <div class="form-group">
                        <strong>Lugar de la Actividad</strong>
                      <p id ="lugarActividad"></p>
                    </div>

                   
                </div>
                <div class="modal-footer">
                    
                
                <button type="button" class="btn btn-danger" data-dismiss="modal" >Cancelar</button>
                </div>
            </div>
        </div>
    </div>


     <div="myModa2" class="modal fade formularios" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <strong class="modal-title">Descripcion del Docente</strong>
                   
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    
                    <div class="form-group" style="display: none">
                        <label>ID</label>
                        <asp:TextBox CssClass="form-control" ID="TextBox2" runat="server"></asp:TextBox>
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

                    <div class="form-group">
                        <strong>fecha</strong>
                      <p id ="fecha"></p>
                    </div>

                      <div class="form-group">
                        <strong>Carrera</strong>
                      <p id ="carrera"></p>
                    </div>

                      <div class="form-group">
                        <strong>grupo</strong>
                      <p id ="grupo"></p>
                    </div>

                      <div class="form-group">
                        <strong>Modalidad</strong>
                      <p id ="modalidad"></p>
                    </div>

                      <div class="form-group">
                        <strong>Hora Fin</strong>
                      <p id ="horaI"></p>
                    </div>

                      <div class="form-group">
                        <strong>Hora Inicial</strong>
                      <p id ="horaF"></p>
                    </div>

                      <div class="form-group">
                        <strong>Dias</strong>
                      <p id ="dias"></p>
                    </div>

                      <div class="form-group">
                        <strong>Motivo de Reprogramacion</strong>
                      <p id ="motivoSolicitud"></p>
                    </div>

                      <div class="form-group">
                        <strong>Fecha de la Clase no Ejecutada</strong>
                      <p id ="fechaActividad"></p>
                    </div>
                    
                      
                </div>
                <div class="modal-footer">
                    
                
                <button type="button" class="btn btn-danger" data-dismiss="modal" >Cancelar</button>
                </div>
            </div>
        </div>
    </div>



   <script  type="text/javascript">
    
         
       function verificarPedidos(descrp, fecha, fechaVista, NombreDePersona, ApellidoP, ApellidoM) {


           $('#fecha').text(fecha);
           $('#NombreDePersona').text(NombreDePersona);
           $('#apellidoP').text(ApellidoP);
           $('#apellidoM').text(ApellidoM);
           $('#descripcion').text(descrp);
       



           $('#myModal').modal('show');
           return false;
       }
         
         
    </script>



     <script  type="text/javascript">
    
         
         function verificarPedidosClases(NombreDePersona,ApellidoP,ApellidoM,fecha,materia,grupo,descripActividad,motivoActividad,fechaActividad,lugarActividad,FechaVistaFormulario) {

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
        
       
         
         
           
                         

           $('#myModal1').modal('show');
           return false;
       }
         
         
    </script>


     <script  type="text/javascript">
    
         
         function verificarPedidosReprogramacion(NombreDePersona,ApellidoP,ApellidoM,materia,grupo,modalidad,horaI,horaF,dias,motivoSolicitud,fechaActividad,FechaVistaFormulario) {

          $('#NombreDePersona').text(NombreDePersona);
          $('#apellidoP').text(ApellidoP);
          $('#apellidoM').text(ApellidoM);
          $('#fecha').text(fecha);
          $('#materia').text(materia);
          $('#grupo').text(grupo);
          $('#modalidad').text(modalidad);
          $('#horaI').text(horaI);
          $('#horaF').text(horaF);
          $('#dias').text(dias);
          $('#motivoSolicitud').text(motivoSolicitud);
          $('#fechaActividad').text(fechaActividad);
        
       
         
         
           
                         

           $('#myModal2').modal('show');
           return false;
       }
         
         
    </script>

        
</asp:Content>