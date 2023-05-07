<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.taw.gestorbanco.dto.OperacionBancariaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.taw.gestorbanco.dto.UsuarioDTO" %><%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 06/05/2023
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    List<OperacionBancariaDTO> operaciones = (List<OperacionBancariaDTO>) request.getAttribute("ops");
    UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
%>
<head>
    <title>Operaciones</title>
    <style>
        td{
            text-align: center;
        }
        table{
            margin: 0 auto;
            width: 90%;
            margin-bottom: 15px;
        }
        button {
            width: 200px;
            height: 110px;
            background-color: #5b5b5b;
            border: none;
            color: #fff;
            font-size: 18px;
            font-weight: bold;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #939393;
        }
        a{
            margin-left: 42%;
        }
    </style>
</head>
<body>
<h1 style="text-align: center">Operaciones de <%=user.getNombre()%></h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>FECHA</th>
        <th>CANTIDAD</th>
        <th>C.ORIGEN</th>
        <th>C.DESTINO</th>
    </tr>
    <%
        for(OperacionBancariaDTO op:operaciones){
    %>
    <tr>
        <td><%=op.getId()%></td>
        <td><%=op.getFecha()%></td>
        <td><%=op.getCantidad()%></td>
        <td><%=op.getCuentaBancariaByIdCuentaOrigen().getId()%></td>
        <td><%=op.getCuentaBancariaByIdCuentaDestino().getId()%></td>
    </tr>
    <%
        }
    %>
</table>
<a href="/cajero/miCuenta"><button>Volver</button></a>
</body>
</html>
