<%@ page import="org.taw.gestorbanco.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: FERCA
  Date: 22/04/2023
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    UsuarioEntity usr = (UsuarioEntity) request.getAttribute("cliente");
%>
<table>
    <thead>
    <tr>
        <th>DNI/CIF</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>E-mail</th>
        <th>Particular/Empresa</th>
        <th>Rol</th>
        <th>Dirección</th>
        <th>Teléfono de Contacto</th>
    </tr>
    </thead>
    <tr>
        <td><%=usr.getIdentificacion()%></td>
        <td><%=usr.getNombre()%></td>
        <td><%=(usr.getApellido()==null)?"":usr.getApellido()%></td>
        <td><%=usr.getEmail()%></td>
        <td><%=usr.getRol()%></td>
        <td><%=(usr.getSubrol()==null)?"":usr.getSubrol()%></td>
        <td><%=usr.getDireccion()%></td>
        <td><%=usr.getTelefono()%></td>
    </tr>
</table>
</body>
</html>
