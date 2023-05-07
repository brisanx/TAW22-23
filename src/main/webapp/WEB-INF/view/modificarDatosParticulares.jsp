<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Jose Torres
  Date: 04/05/2023
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Modifica tus datos</title>
</head>

<body>
<form:form modelAttribute="cliente" method="post" action="/usuario/guardarDatos">
    <form:hidden path="id"/>
    <form:hidden path="rol"/>
    <table>
        <tr><td>NIF(*): <form:input path="identificacion"/><td></tr>
        <tr><td>Nombre(*): <form:input path="nombre"/></td></tr>
        <tr><td>Apellidos: <form:input path="apellido"/></td></tr>
        <tr><td>Email(*): <form:input path="email"/></td></tr>
        <tr><td>Dirección: <form:input path="direccion"/></td></tr>
        <tr><td>Teléfono: <form:input path="telefono"/></td></tr>
        <tr><td>Contraseña(*): <form:input path="contrasena"/></td></tr>
        <tr><td> <form:button>Guardar cambios</form:button></td></tr>
    </table>
</form:form>
</body>
</html>