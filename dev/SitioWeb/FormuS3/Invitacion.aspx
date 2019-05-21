<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPages/MasterPage.master" AutoEventWireup="true" CodeFile="Invitacion.aspx.cs" Inherits="Invitacion" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <script src="App_Themes/Default/js/jquery.min.js"></script>
    <link href="App_Themes/Estilos/css/Invitacion.css" rel="stylesheet" />
    <script src="App_Themes/Default/js/bootstrap.min.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
     
    <div class="contenedor">
        <div id="titulo" class="col-12"><h1>Lista Docentes</h1>
       <asp:Button  runat="server" CssClass="btn btn-primary" Text="Registrar Docente" OnClick="btnRegistraDocente_Click"/>
        </div>
         <div id="tabla" class="table-wrapper-scroll-y my-custom-scrollbar">
          <asp:GridView  id="tblDocentes" runat="server" CssClass="table table-dark table-bordered table-striped mb-0" 
             OnRowCommand="enviarCorreo" AutoGenerateColumns="false" >
              <Columns>

                 <asp:TemplateField HeaderText="Editar">
                      <ItemTemplate>
                          <asp:LinkButton id="btnEditar" ToolTip="Enviar" runat="server"
                              CommandName="EditarPersona" CommandArgument='<%# Eval("id")%>'>
                              <i class="glyphicon glyphicon-envelope" data-toggle="modal" data-target="#myModal" Text="Registrar Docente" >
                              
                              </i>
                          </asp:LinkButton>  
                      </ItemTemplate>
                  </asp:TemplateField>
                  <asp:BoundField DataField="nombre" HeaderText="Nombre"/>
                  <asp:BoundField DataField="apellidoP" HeaderText="Apellido Paterno"/>
                  <asp:BoundField DataField="apellidoM" HeaderText="Apellido Materno"/>
                  <asp:BoundField DataField="Correo" HeaderText="Correo"/>

                  <asp:TemplateField HeaderText="Invitar">
                      <ItemTemplate>
                          <asp:LinkButton id="btnInvitar" runat="server" ToolTip="Enviar"
                              CommandName="EnviarCorreo" CommandArgument='<%# Eval("id")%>' OnClientClick="return confirm('Estas Seguro de enviar Invitacion')">
                              <i class="glyphicon glyphicon-envelope">
                              
                              </i>
                          </asp:LinkButton>  
                      </ItemTemplate>
                  </asp:TemplateField>

              </Columns>
          </asp:GridView>  
        </div>
    </div>
    <!--#include file="FormularioDocente.html"--> 
</asp:Content>

