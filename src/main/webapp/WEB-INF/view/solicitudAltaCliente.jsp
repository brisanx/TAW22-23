<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Jose Torres
  Date: 06/05/2023
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solicitud Alta | Cliente</title>
</head>
<body>
<form:form action="/solicitudAltaCliente" method="post" modelAttribute="solicitud">
    <form:hidden path="idSolicitud"/>
    <form:hidden path="usuarioByUsuarioId.id"/>
    <form:hidden path="empleadoByIdGestor.idGestor"/>
    ¿Qué divisa quieres que tenga la cuenta?<br>
    <form:select path="divisaByDivisaId.id">
        <form:options items="${divisas}" itemValue="id" itemLabel="nombre" />
    </form:select>
    <form:button>Registrar solicitud de alta</form:button>
</form:form>
</body>
</html>
