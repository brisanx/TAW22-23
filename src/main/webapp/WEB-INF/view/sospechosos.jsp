<%@ page import="org.taw.gestorbanco.entity.CuentaBancariaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.taw.gestorbanco.entity.OperacionBancariaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: FERCA
  Date: 22/04/2023
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CuentaBancariaEntity> cSospechosas = (List<CuentaBancariaEntity>) request.getAttribute("cSospechosas");
%>
<html>
<head>
    <title>Gestión Sospechosos</title>
    <link rel="stylesheet" type="text/css" href="/tabla.css">
    <link rel="stylesheet" type="text/css" href="/sospechoso.css">
</head>
<body>
<h1>Gestión sospechosos</h1>

<h2>Agregar cuenta sospechosa</h2>
<form:form action="/sospechoso/agregar" modelAttribute="filtroA" method="post">
    <div class="form-group">
        <label for="id">Seleccione una cuenta:</label>
        <form:select path="id" items="${noSospechosos}" itemLabel="id" itemValue="id"
                     class="form-control" multiple="false" size="10" required="true"></form:select>
    </div>
    <div class="form-group">
        <form:button class="btn btn-primary">Agregar Cuenta Sospechosa</form:button>
    </div>
</form:form>

<h2>Eliminar cuenta sospechosa</h2>
<form:form action="/sospechoso/eliminar" modelAttribute="filtroE" method="post">
    <div class="form-group">
        <label for="id">Seleccione una cuenta:</label>
        <form:select path="id" items="${sospechosos}" itemLabel="id" itemValue="id"
                     class="form-control" multiple="false" size="10" required="true"></form:select>
    </div>
    <div class="form-group">
        <form:button class="btn btn-danger">Eliminar Cuenta Sospechosa</form:button>
    </div>
</form:form>
<br><br>

<h2>Las siguientes cuentas han hecho transferencias a cuentas sospechosas:</h2>
<table>
    <thead>
    <tr>
        <th>ID Cuenta Origen</th>
        <th>ID Cuenta Sospechosa</th>
        <th>Fecha Operación</th>
        <th>Cantidad establecida</th>
        <th>Bloquear cuenta</th>
    </tr>
    </thead>
    <%
        if(!cSospechosas.isEmpty()){
            List<OperacionBancariaEntity> operaciones = (List<OperacionBancariaEntity>) request.getAttribute("opSospechosas");
            for (int i=0; i<operaciones.size(); i++) {

    %>
    <tr>
        <td><%=operaciones.get(i).getCuentaBancariaByIdCuentaOrigen().getId()%></td>
        <td><%=operaciones.get(i).getCuentaBancariaByIdCuentaDestino().getId()%></td>
        <td><%=operaciones.get(i).getFecha()%></td>
        <td><%=operaciones.get(i).getCantidad()%></td>
        <td><a href="/bloquear?id=<%=operaciones.get(i).getCuentaBancariaByIdCuentaOrigen().getId()%>">Eliminar</a></td>
    </tr>

    <%
        }
        }
    %>
</table>

</body>
</html>
