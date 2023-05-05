<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: erpep
  Date: 05/05/2023
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
</head>
<body>
${pageContext.session.id}
<h3>Bienvenido, ${user.email}</h3>
<table border="1">
    <tr>
        <th>DNI/CIF</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>E-mail</th>
        <th>Dirección</th>
        <th>Teléfono</th>
    </tr>
    <tr>
        <td>${user.identificacion}</td>
        <td>${user.nombre}</td>
        <td>${user.apellido}</td>
        <td>${user.email}</td>
        <td>${user.direccion}</td>
        <td>${user.telefono}</td>
    </tr>
</table>
</body>
</html>