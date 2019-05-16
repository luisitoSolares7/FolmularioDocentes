<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Login.aspx.cs" Inherits="Login" %>

<!DOCTYPE html>
<link href="App_Themes/Estilos/css/Login.css" rel="stylesheet" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <img id="fondoLogin" src="App_Themes/Default/img/fondoLogin.png" />
    <div id="contenedor" class="col-4" >
    <form class="needs-validation" runat="server" novalidate>
            <div class="form-group">
                 <asp:TextBox CssClass="form-control" ID="txtNombreCuenta" placeholder="Nombre de Cuenta" runat="server" required></asp:TextBox>
            </div>
            <div class="form-group">
                <asp:TextBox CssClass="form-control" ID="txtContracena" TextMode="Password" placeholder="Contraceña"  runat="server" required></asp:TextBox>
                
            </div>
            
           <asp:Button id="btnRegistrar" CssClass="btn btn-primary" type="submit"  runat="server" Text="Registrar"/>
 
    </form>
 </div>

<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>
</body>
</html>
