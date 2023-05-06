<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.taw.gestorbanco.entity.CuentaBancariaEntity" %><%--
  Created by IntelliJ IDEA.
  User: albas
  Date: 05/05/2023
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  CuentaBancariaEntity cuentaEmpresa = (CuentaBancariaEntity) request.getAttribute("cuentaempresa");
%>
<html>
<head>
    <title>Cambio de divisa</title>
</head>
<body>
<form:form method="post" action="/empresa/guardarcambiodivisa" modelAttribute="cuentaempresa">
  Seleccione a qué divisa quiere cambiar:<form:select path="divisaByDivisaId" items="${divisas}" itemLabel="nombre"></form:select>
  <form:hidden path="id"></form:hidden>
    <form:hidden path="activo"></form:hidden>
    <form:hidden path="moneda"></form:hidden>
    <form:hidden path="saldo"></form:hidden>
  <form:hidden path="sospechosa"></form:hidden>
    <form:button>Guardar cambio</form:button>
</form:form>
</body>
</html>
