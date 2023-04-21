<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.taw.gestorbanco.entity.EmpleadoEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: J.Torres
  Date: 27/3/23
  Time: 12:07:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<EmpleadoEntity> lista = (List<EmpleadoEntity>) request.getAttribute("empleado");
%>
<html>
<head>
    <title>Titulo</title>
</head>
<body>

<h1>Listado de empleados</h1>

<table border="1">
    <tr>
        <th>ID GESTOR</th>
        <th>NOMBRE</th>
        <th>EMAIL</th>
        <th>ROL</th>
    </tr>
    <%
        for (EmpleadoEntity empleado: lista) {
    %>
    <tr>
        <td><%= empleado.getIdGestor() %></td>
        <td><%= empleado.getNombre() %></td>
        <td><%= empleado.getEmail() %></td>
        <td><%= empleado.getRol() %></td>
    </tr>

    <%
        }
    %>
</body>
</html>
