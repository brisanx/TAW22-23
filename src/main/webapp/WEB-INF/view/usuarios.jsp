<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="org.taw.gestorbanco.entity.UsuarioEntity" %>
<%@ page import="org.taw.gestorbanco.entity.SolicitudAltaEntity" %>
<%@ page import="org.taw.gestorbanco.entity.CuentaBancariaEntity" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="org.taw.gestorbanco.entity.SolicitudActivacionEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios</title>
    <link rel="stylesheet" type="text/css" href="/tabla.css">
    <link rel="stylesheet" type="text/css" href="/usuarioF.css">
</head>

<body>
<p style="display: flex; justify-content: space-between;">
    <a href="#solAlta" style="order: 1"> Solicitudes de Alta </a>
    <a href="#solActivacion" style="order: 2"> Solicitudes Activación </a>
    <a href="#desactivar" style="order: 3"> Desactivar Cuentas </a>
    <a href="/sospechoso" style="order: 4"> Gestión de Cuentas Sospechosas </a></p>
<h1>Usuarios</h1>
<div class="formulario">
    <form:form action="/filtrarNombres" modelAttribute="filtroN" method="post">
        Nombre:<form:input path="nombre" maxlength="20" size="20"></form:input>
        Apellidos:<form:input path="apellido" maxlength="20" size="20"></form:input>
        <br>
        <form:button>Buscar</form:button>
    </form:form>
    <br>
    <form:form action="/filtrarTipo" modelAttribute="filtroT" method="post">
        <form:select path="tipo">
            <form:option value="">--Seleccione--</form:option>
            <form:option value="Particular">Particular</form:option>
            <form:option value="Empresa">Empresa</form:option>
        </form:select>
        <br>
        <form:button>Filtrar</form:button>
    </form:form>
</div>
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
        <th>Información del Cliente</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<UsuarioEntity> usuarios = (List<UsuarioEntity>) request.getAttribute("usuario");
        for (UsuarioEntity usr :usuarios) {
    %>
    <tr>
        <td><%=usr.getId()%></td>
        <td><%=usr.getNombre()%></td>
        <td><%=(usr.getApellido()==null)?"":usr.getApellido()%></td>
        <td><%=usr.getEmail()%></td>
        <td><%=usr.getRol()%></td>
        <td><%=(usr.getSubrol()==null)?"":usr.getSubrol()%></td>
        <td><%=usr.getDireccion()%></td>
        <td><%=usr.getTelefono()%></td>
        <td><a href="/informacion?id=<%=usr.getId()%>">Más Información</a></td>

    </tr>
    <% } %>
    </tbody>
</table>
<br><br>

<div id="solAlta">
<h2>Las siguientes cuentas han solicitado el alta del sistema:</h2>
<table>
    <thead>
    <tr>
        <th>DNI/CIF</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>NºCuentas</th>
        <th>Fecha de Solicitud</th>
        <th>Divisa</th>
        <th>Aceptar Solicitud</th>
        <th>Denegar/Solicitud</th>
    </tr>
    </thead>
    <%
        List<SolicitudAltaEntity> pendientes = (List<SolicitudAltaEntity>) request.getAttribute("pendientes");
        for (SolicitudAltaEntity pen : pendientes) {
    %>
    <tr>
        <td><%=pen.getUsuarioByUsuarioId().getIdentificacion()%></td>
        <td><%=pen.getUsuarioByUsuarioId().getNombre()%></td>
        <td><%=pen.getUsuarioByUsuarioId().getApellido()%></td>
        <td><%=pen.getUsuarioByUsuarioId().getAsignacionsById().size()%></td>
        <td><%=pen.getFechaSolicitud()%></td>
        <td><%=pen.getDivisaByDivisaId().getNombre()%></td>
        <td> <a href="/aceptarAlta?id=<%=pen.getUsuarioByUsuarioId().getId()%>&sol=<%=pen.getIdSolicitud()%>&divisa=<%=pen.getDivisaByDivisaId().getId()%>">Aceptar</a></td>
        <td> <a href="/denegarAlta?sol=<%=pen.getIdSolicitud()%>">Denegar</a></td>
    </tr>

    <%
        }
    %>
</table>
</div>
<br><br>

<div id="solActivacion">
    <h2>Las siguientes cuentas han solicitado activarse:</h2>
    <table>
        <thead>
        <tr>
            <th>ID Cuenta</th>
            <th>DNI/CIF</th>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Fecha de Solicitud</th>
            <th>Aceptar Solicitud</th>
            <th>Denegar/Solicitud</th>
        </tr>
        </thead>
        <%
            List<SolicitudActivacionEntity> activaciones = (List<SolicitudActivacionEntity>) request.getAttribute("activaciones");
            for (SolicitudActivacionEntity pen : activaciones) {
        %>
        <tr>
            <td><%=pen.getCuentaBancariaByCuentaBancariaId().getId()%></td>
            <td><%=pen.getUsuarioByUsuarioId().getIdentificacion()%></td>
            <td><%=pen.getUsuarioByUsuarioId().getNombre()%></td>
            <td><%=pen.getUsuarioByUsuarioId().getApellido()%></td>
            <td><%=pen.getFechaSolicitud()%></td>
            <td> <a href="/aceptarActivacion?id=<%=pen.getCuentaBancariaByCuentaBancariaId().getId()%>&sol=<%=pen.getId()%>">Aceptar</a></td>
            <td> <a href="/denegarActivacion?sol=<%=pen.getId()%>">Denegar</a></td>
        </tr>

        <%
            }
        %>
    </table>
</div>

<div id="desactivar">
    <h1>Las siguientes cuentas han de ser desactivadas</h1>
    <table>
        <thread>
            <tr>
                <th>Id cuenta</th>
                <th>Última operación bancaria</th>
                <th>Desactivar</th>
            </tr>
        </thread>
    <%
        List<Timestamp> fechas = (List<Timestamp>) request.getAttribute("fechas");
        List<CuentaBancariaEntity> menor = (List<CuentaBancariaEntity>) request.getAttribute("cDesactivar");
        for(int i=0; i<menor.size(); i++){
    %>
    <tr>
        <td><%=menor.get(i).getId()%></td>
        <td><%=fechas.get(i)%></td>
        <td><a href="/desactivarcuenta?idCuenta=<%=menor.get(i).getId()%>"> Desactivar </a></td>
    </tr>

    <%
        }
    %>
</table>
</div>
</body>
</html>
