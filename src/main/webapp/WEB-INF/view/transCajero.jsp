<%@ page import="org.taw.gestorbanco.dto.CuentaBancariaDTO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDateTime" %>
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
    CuentaBancariaDTO corigen = (CuentaBancariaDTO) request.getAttribute("cOrigen");
%>
<head>
    <title>Transferencia</title>
    <style>
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
<form:form action="/cajero/confirmaTrans" method="post" modelAttribute="operacion">
    <form:hidden path="id"/>
    <form:hidden path="usuario"/>
    <form:hidden path="fecha" value="<%=Timestamp.valueOf(LocalDateTime.now())%>"/>
    Cuenta origen: <form:input readonly="true" path="cuentaBancariaByIdCuentaOrigen" value="<%=corigen.getId()%>"/><br>
    Cantidad: <form:input path="cantidad"/><br>
    Cuenta: <form:input path="cuentaBancariaByIdCuentaDestino"/><br>
    <form:button>Realizar</form:button>
</form:form>

</body>
</html>
