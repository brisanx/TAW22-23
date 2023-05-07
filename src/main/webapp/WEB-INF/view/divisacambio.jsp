<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.taw.gestorbanco.entity.CuentaBancariaEntity" %>
<%@ page import="org.taw.gestorbanco.dto.CuentaBancariaDTO" %><%--
  Created by IntelliJ IDEA.
  User: Alba Sánchez Ibáñez
  Date: 05/05/2023
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CuentaBancariaDTO cuentaEmpresa = (CuentaBancariaDTO) request.getAttribute("cuentaempresa");
%>
<html>
<head>
    <title>Cambio de divisa</title>
</head>
<body>
<form:form method="post" action="/empresa/guardarcambiodivisa" modelAttribute="cuentaempresa">
    <form:hidden path="id"/>
    <form:hidden path="moneda"/>
    <form:hidden path="saldo"/>
    Seleccione a qué divisa quiere cambiar:
    <form:select path="divisaByDivisaId.id">
        <form:options items="${divisas}" itemValue="id" itemLabel="nombre" />
    </form:select>
    <form:button>Guardar cambio</form:button>
</form:form>
</body>
</html>