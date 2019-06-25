<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage/MasterPage.master" AutoEventWireup="true" CodeFile="Invitar.aspx.cs" Inherits="Invitar" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
     <asp:ScriptManager runat="server" EnablePageMethods="true" ID ="metodos"></asp:ScriptManager>
        <div class="col-12 titulo">
            <h1>Lista Docentes</h1>
            <asp:Button ID="btnRegistrarDocente" runat="server" CssClass="btn btn-primary" Text="Registrar Docente" OnClientClick="return openModal()"/>
        </div>
        <div class="table-wrapper-scroll-y my-custom-scrollbar tabla">
            <asp:GridView ID="tblDocentes" runat="server" CssClass="table table-dark table-bordered table-striped mb-0"
                OnRowCommand="enviarCorreo" AutoGenerateColumns="false">

                <Columns>
                    <asp:TemplateField HeaderText="Editar">
                        <ItemTemplate>
                            <asp:Button Text="Editar"  OnClientClick='<%# String.Format("javascript:return verificar(\"{0}\")", Eval("id").ToString()) %>'  runat="server"/>
                        </ItemTemplate>
                    </asp:TemplateField>
                    <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                    <asp:BoundField DataField="apellidoP" HeaderText="Apellido Paterno" />
                    <asp:BoundField DataField="apellidoM" HeaderText="Apellido Materno" />
                    <asp:BoundField DataField="Correo" HeaderText="Correo" />

                    <asp:TemplateField HeaderText="Invitar">
                        <ItemTemplate>
                            <asp:LinkButton ID="btnInvitar" runat="server" ToolTip="Enviar"
                                CommandName="EnviarCorreo" CommandArgument='<%# Eval("id")%>' OnClientClick="return confirm('Estas Seguro de enviar Invitacion')">
                              <i class="glyphicon glyphicon-envelope">
                              
                              </i>
                            </asp:LinkButton>
                        </ItemTemplate>
                    </asp:TemplateField>

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
                    <div class="form-group" style="display: none">
                        <label>ID</label>
                        <asp:TextBox CssClass="form-control" ID="txtID" runat="server"></asp:TextBox>
                    </div>
                    <div class="form-group">
                        <label>Nombre</label>
                        <asp:TextBox CssClass="form-control" ID="txtNombre" runat="server" required="required"></asp:TextBox>
                    </div>
                    <div class="form-group">
                        <label>Apellido Paterno</label>
                        <asp:TextBox CssClass="form-control" ID="txtApellidoP" runat="server" required="required"></asp:TextBox>
                    </div>
                    <div class="form-group">
                        <label>Apellido Materno</label>
                        <asp:TextBox CssClass="form-control" ID="txtApellidoM" runat="server" required="required"></asp:TextBox>
                    </div>
                    <div class="form-group">
                        <label>Correo</label>
                        <asp:TextBox CssClass="form-control" ID="txtCorreo" type="email" aria-describedby="emailHelp" runat="server" required="required"></asp:TextBox>
                    </div>
                    <div class="form-group">
                        <label>Telefono</label>
                        <asp:TextBox CssClass="form-control" ID="txtTelefono" type="number" aria-describedby="emailHelp" runat="server" required="required"></asp:TextBox>
                    </div>
                </div>
                <div class="modal-footer">
                     <asp:Button id="btnRegistrar" CssClass="btn btn-primary" type="submit" OnClick="RegistrarDocente"  runat="server" Text="Registrar" />
                    <asp:Button id="btnActuaizar" CssClass="btn btn-primary" type="submit" runat="server" Text="Actualizar" />
                <button type="button" class="btn btn-danger" data-dismiss="modal" >Cancelar</button>
                </div>
            </div>
        </div>
    </div>
       
   <script  type="text/javascript">
      function openModal() {
            $('#<%=txtID.ClientID%>').val('');
            $('#<%=txtNombre.ClientID%>').val('');
            $('#<%=txtApellidoP.ClientID%>').val('');
            $('#<%=txtApellidoM.ClientID%>').val('');
            $('#<%=txtTelefono.ClientID%>').val('');
            $('#<%=txtCorreo.ClientID%>').val('');
            $('#myModal').modal('show');
            return false;
      }
         
       function verificar(id) {
            PageMethods.verificarPersona(id,onsuccess,onfailed);
           function onsuccess(result) {
               var array = result;
               var persona = array.split(',');
              $('#<%=txtID.ClientID%>').val(persona[0]);
              $('#<%=txtNombre.ClientID%>').val(persona[1]);
              $('#<%=txtApellidoP.ClientID%>').val(persona[2]);
              $('#<%=txtApellidoM.ClientID%>').val(persona[3]);
              $('#<%=txtTelefono.ClientID%>').val(persona[5]);
              $('#<%=txtCorreo.ClientID%>').val(persona[4]);
               $('#myModal').modal('show');
               return false;
           }
           function onfailed(result) {
               alert("no entro al metodo");
           }
            return false;
        } 
    </script>
</asp:Content>

