<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage/MasterPage.master" AutoEventWireup="true" CodeFile="ListaPeticiones.aspx.cs" Inherits="ListaPeticiones" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <link href="App_Themes/Estilo/EstiloPeticiones.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:ScriptManager runat="server" EnablePageMethods="true"></asp:ScriptManager>
        <div class="col-12 titulo">
            <h1>Lista Formulario</h1>
        </div>
        <div class="table-wrapper-scroll-y my-custom-scrollbar tabla">
            <asp:GridView ID="tblPeticiones" runat="server" CssClass="table table-dark table-bordered table-striped mb-0"
                OnRowCommand="enviarCorreo" AutoGenerateColumns="false">

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
        </div>





    <div id="myModal" class="modal fade formularios" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Registrar Docente</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <asp:TextBox ID="id"  runat="server"></asp:TextBox>
                   <p id ="descripcion"></p>
                   <p id="fecha"></p> 
                <div class="modal-footer">
                     <asp:Button id="btnRegistrar" CssClass="btn btn-primary" OnClick="btnRegistrar_Click" type="submit"  runat="server" Text="Aceptar Solucitud" />
                     <button type="button" class="btn btn-danger" data-dismiss="modal" >Cancelar</button>
                </div>
            </div>
        </div>
    </div>


         
   <script  type="text/javascript">
         function openModal() {
            
            $('#myModal').modal('show');
            return false;
      }
       function detalle(id) {
            PageMethods.DetalleForm(id,onsuccess,onfailed);
           function onsuccess(result) {
               var array = result;
               $('#<%=id.ClientID%>').hide();
               var persona = array.split(',');
               $('#descripcion').text(persona[0]);
               $('#<%=id.ClientID%>').val(persona[2]);
               $('#fecha').text(persona[1]);
               $('#myModal').modal('show');
               return false;
           }
           function onfailed(result) {
               alert("no entro al metodo");
                return false;
           }
            return false;
        } 
    </script>


</asp:Content>

