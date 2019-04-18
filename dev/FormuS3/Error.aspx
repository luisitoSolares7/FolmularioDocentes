<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Error.aspx.cs" Inherits="Error" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <h1>
            <asp:Literal ID="MsgLabel" runat="server" Text="Error"></asp:Literal>
        </h1>
        <asp:HyperLink runat="server"NavigateUrl="~/Default.aspx" 
    </form>
</body>
</html>
