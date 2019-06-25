<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage/MasterPage.master" AutoEventWireup="true" CodeFile="Peticiones.aspx.cs" Inherits="Peticiones" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
       <div class="list-group" id="list-tab" role="tablist">
      <a class="list-group-item list-group-item-action active " id="list-home-list" data-toggle="list" href="#ListaIncidentes" role="tab" aria-controls="home">Incidentes</a>
      <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list" href="#ListaClases" role="tab" aria-controls="home">Clases Fuera del Aula</a>
      <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list" href="#ListaReprogramacion" role="tab" aria-controls="home">Reprogramacion de Clases</a>
        <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list" href="#ListaFotocopias" role="tab" aria-controls="home">Pedido de Fotocopias</a>
    </div>
    <br />
<div class="tab-content">
    <div class="tab-pane  active" id="ListaIncidentes" role="tabpanel">
        <div class="table-wrapper-scroll-y my-custom-scrollbar tabla">
            <asp:GridView ID="tblPeticiones" runat="server" CssClass="table table-dark table-bordered table-striped mb-0"
                 AutoGenerateColumns="false">

                <Columns>
                   <asp:TemplateField HeaderText="Editar">
                        <ItemTemplate>
                            <asp:Button Text="Ver" CssClass="btn btn-primary"  OnClientClick='<%# String.Format("javascript:return detalle(\"{0}\",\"{1}\")", Eval("id").ToString(),Eval("descripcion").ToString()) %>'  runat="server"/>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:BoundField DataField="nombreForm" HeaderText="Nombre Docente" />

                  
                </Columns>
            </asp:GridView>
        </div>
        <asp:Panel runat="server" id="IncidentesVacio" >
            <h1>No hay Incidentes</h1>
        </asp:Panel>
    </div>
    <div class="tab-pane  fade" id="ListaClases" role="tabpanel">
        <div class="table-wrapper-scroll-y my-custom-scrollbar tabla">
            <asp:GridView ID="tblClases" runat="server" CssClass="table table-dark table-bordered table-striped mb-0"
               AutoGenerateColumns="false">

                <Columns>
                   <asp:TemplateField HeaderText="Editar">
                        <ItemTemplate>
                            <asp:Button Text="Editar" CssClass="btn btn-primary"  OnClientClick='<%# String.Format("javascript:return detalle(\"{0}\")", Eval("fkTbl").ToString()) %>'  runat="server"/>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:BoundField DataField="nombreForm" HeaderText="Nombre" />
                    <asp:BoundField DataField="nombre" HeaderText="Nombre Docente" />
                </Columns>
            </asp:GridView>
            <asp:Panel runat="server" id="ClasesVacia" >
            <h1>No hay Peticiones Clases Fuera de Aula</h1>
        </asp:Panel>
        </div>
    </div>
    <div class="tab-pane  fade" id="ListaReprogramacion" role="tabpanel">
        <div class="table-wrapper-scroll-y my-custom-scrollbar tabla">
            <asp:GridView ID="tblReprogramacion" runat="server" CssClass="table table-dark table-bordered table-striped mb-0"
               AutoGenerateColumns="false">

                <Columns>
                   <asp:TemplateField HeaderText="Editar">
                        <ItemTemplate>
                            <asp:Button Text="Editar" CssClass="btn btn-primary"  OnClientClick='<%# String.Format("javascript:return detalle(\"{0}\")", Eval("fkTbl").ToString()) %>'  runat="server"/>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:BoundField DataField="nombreForm" HeaderText="Nombre" />
                    <asp:BoundField DataField="nombre" HeaderText="Nombre Docente" />

                  
                </Columns>
            </asp:GridView>
            <asp:Panel runat="server" id="ReprogramacionVacia" >
            <h1>No hay Peticiones Reprogramacion de Clases</h1>
        </asp:Panel>
        </div>
    </div>
    <div class="tab-pane  fade" id="ListaFotocopias" role="tabpanel">
        <div class="table-wrapper-scroll-y my-custom-scrollbar tabla">
            <asp:GridView ID="tblFotocopias" runat="server" CssClass="table table-dark table-bordered table-striped mb-0"
                AutoGenerateColumns="false">

                <Columns>
                   <asp:TemplateField HeaderText="Editar">
                        <ItemTemplate>
                            <asp:Button Text="Editar" CssClass="btn btn-primary"  OnClientClick='<%# String.Format("javascript:return detalle(\"{0}\")", Eval("fkTbl").ToString()) %>'  runat="server"/>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:BoundField DataField="nombreForm" HeaderText="Nombre" />
                    <asp:BoundField DataField="nombre" HeaderText="Nombre Docente" />

                  
                </Columns>
            </asp:GridView>
            <asp:Panel runat="server" id="FotocopiasVacia" >
            <h1>No hay Peticiones de Fotocopia</h1>
        </asp:Panel>
        </div>
    </div>

</div>


    <div id="myModal" class="modal fade formularios" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Solicitud</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <asp:TextBox ID="TxtID"  runat="server"></asp:TextBox>
                   <p id ="descripcion"></p>
                   <p id="fecha"></p> 
                <div class="modal-footer">
                     <asp:Button id="btnAceptarSolicitud" CssClass="btn btn-primary"  type="submit"  runat="server" Text="Aceptar Solucitud" />
                    <asp:Button id="btnRechazarSolicitud" CssClass="btn btn-primary"  type="submit"  runat="server" Text="Aceptar Solucitud" />
                     <button type="button" class="btn btn-danger" data-dismiss="modal" >Cancelar</button>
                </div>
            </div>
        </div>
    </div>
</div>

         
   <script  type="text/javascript">
         function openModal() {
            
            $('#myModal').modal('show');
            return false;
      }
       function detalle(id, descrip) {
               $('#<%=btnRechazarSolicitud.ClientID%>').hide();
               $('#descripcion').text(descrip);
               $('#<%=TxtID.ClientID%>').val(id);
               $('#<%=TxtID.ClientID%>').hide();
               $('#myModal').modal('show');
       
            return false;
        } 
    </script>


</asp:Content>

