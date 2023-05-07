<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="org.taw.gestorbanco.dto.UsuarioDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 05/05/2023
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    //List<CuentaBancariaDTO> corigen = (List<CuentaBancariaDTO>) request.getAttribute("cOrigen");
    UsuarioDTO user = (UsuarioDTO) request.getAttribute("user");
%>
<head>
    <title>Transferencia</title>
    <style>
        table{
            width: 100%;
            text-align: center;
            font-size: 18px;
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
    </style>
</head>
<body>
<h1 style="text-align: center">Realiza tu transferencia</h1>
<table>
    <tr>
        <td>
            <form:form action="/cajero/confirmaTrans" method="post" modelAttribute="operacion"><br>
            <form:hidden path="id"/>
            <form:hidden path="usuario" value="<%=user.getId()%>"/>
            <form:hidden path="fecha" value="<%=Timestamp.valueOf(LocalDateTime.now())%>"/>
            Cuenta origen (solo se muestran activas): <form:select path="cuentaBancariaByIdCuentaOrigen" items="${origen}" itemLabel="id" itemValue="id"/><br><br>
            Cantidad: <form:input path="cantidad" size="6"/><br><br>
            Cuenta destino: <form:input path="cuentaBancariaByIdCuentaDestino" maxlength="5" size="2"/><br><br>
        </td>
        <td><form:button>Realizar</form:button></td>
    </form:form>
    </tr>
</table>


</body>
</html>
