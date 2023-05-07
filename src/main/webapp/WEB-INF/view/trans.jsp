<%@ page import="org.taw.gestorbanco.entity.CuentaBancariaEntity" %>
<%@ page import="org.taw.gestorbanco.dto.CuentaBancariaDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: albas
  Date: 01/05/2023
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CuentaBancariaDTO corigen = (CuentaBancariaDTO) request.getAttribute("cuentaorigen");
%>
<html>
<head>
    <title>Transferencia</title>
</head>
<body>
<form:form action="/transRealizada" method="post" modelAttribute="operacion">
    <form:hidden path="id" />
    <form:hidden path="cuentaBancariaByIdCuentaOrigen.id" value="<%=corigen.getId()%>" />
    <label for="cantidad">Cantidad:</label>
    <form:input path="cantidad" id="cantidad" />
    <label for="cuenta">Cuenta:</label>
    <form:input path="cuentaBancariaByIdCuentaDestino.id" id="cuenta"/>
    <form:button>Realizar</form:button>
</form:form>
</body>
</html>
