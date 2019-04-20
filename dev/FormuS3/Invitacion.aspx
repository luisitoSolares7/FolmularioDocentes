<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPages/MasterPage.master" AutoEventWireup="true" CodeFile="Invitacion.aspx.cs" Inherits="Invitacion" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <link href="App_Themes/Estilos/css/Invitacion.css" rel="stylesheet" />
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">

    <div class="row">
        <div id="titulo" class="col-12"><h1>Lista Docentes</h1></div>
         <div id="tabla">
          <asp:GridView id="tblDocentes" runat="server" CssClass="table table-dark" 
             OnRowCommand="enviarCorreo" AutoGenerateColumns="false" >
              <Columns>
                  <asp:BoundField DataField="nombre" HeaderText="Nombre"/>
                  <asp:BoundField DataField="apellidoP" HeaderText="Apellido Paterno"/>
                  <asp:BoundField DataField="apellidoM" HeaderText="Apellido Materno"/>
                  <asp:BoundField DataField="Correo" HeaderText="Correo"/>

                  <asp:TemplateField HeaderText="Invitar">
                      <ItemTemplate>
                          <asp:LinkButton id="btnInvitar" CssClass="btn " runat="server" ToolTip="Enviar"
                              CommandName="EnviarCorreo" CommandArgument='<%# Eval("id")%>' OnClientClick="return confirm('Estas Segudo de enviar Invitacion')">
                              <i class="glyphicon glyphicon-envelope">
                              
                              </i>

                          </asp:LinkButton>  
                      </ItemTemplate>
                  </asp:TemplateField>

              </Columns>
          </asp:GridView>  
        </div>
    </div>
</asp:Content>

