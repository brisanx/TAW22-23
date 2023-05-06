<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: albas
  Date: 05/05/2023
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solicitud Alta | Empresa</title>
</head>
<body>
<tr><td><form:form action="/empresa/solicitudalta" method="post" modelAttribute="solicitud">
  <form:hidden path="empleadoByIdGestor"></form:hidden>
  <form:hidden path="fechaSolicitud"></form:hidden>
  <form:hidden path="idSolicitud"></form:hidden>
  <form:hidden path="usuarioByUsuarioId"></form:hidden>
  ¿Qué divisa quieres que tenga tu cuenta?<form:select path="divisaByDivisaId" items="${divisas}" itemLabel="nombre"></form:select>
  <form:button>Registrar solicitud de alta</form:button>
</form:form></td></tr>
</body>
</html>
