<%@ page import="org.taw.gestorbanco.dto.CuentaBancariaDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Jose Torres
  Date: 05/05/2023
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CuentaBancariaDTO> cuentas = (List<CuentaBancariaDTO>) request.getAttribute("cuentas");
%>
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
<br>
<br>
<table border="1">
    <tr>
        <th>ID CUENTA</th>
        <th>SALDO</th>
        <th>ACTIVIDAD</th>
        <th>SOSPECHOSA</th>
        <th></th>
    </tr>
    <%
        for(CuentaBancariaDTO cuenta:cuentas){

    %>
    <tr>
        <td><%=cuenta.getId()%></td>
        <td><%=cuenta.getSaldo()%></td>
        <td>
            <%
                if(cuenta.getActivo() == 1){
            %>
            Activa
            <%
            } else {
            %>
            Inactiva
            <%
                }
            %>
        </td>
        <td>
            <%
                if(cuenta.getSospechosa() == 0){
            %>
            No
            <%
            } else {
            %>
            Si
            <%
                }
            %>
        </td>
        <td>
            <%
                if(cuenta.getSospechosa() == 1 || cuenta.getActivo() == 0) {
            %>
            <a href="/solicitarActivacion">Solicitar activación</a>
            <%
                }
            %>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>