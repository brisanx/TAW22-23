<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.taw.gestorbanco.entity.*" %>
<%@ page import="org.taw.gestorbanco.dto.UsuarioDTO" %>
<%@ page import="org.taw.gestorbanco.dto.AsignacionDTO" %>
<%@ page import="org.taw.gestorbanco.dto.SolicitudActivacionDTO" %>
<%@ page import="org.taw.gestorbanco.dto.CuentaBancariaDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: Alba Sánchez Ibáñez
  Date: 22/04/2023
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    AsignacionDTO asi = (AsignacionDTO) request.getAttribute("asignacion");
    UsuarioDTO e = (UsuarioDTO) request.getAttribute("usuariosocio");

    SolicitudActivacionDTO solicitud = (SolicitudActivacionDTO) request.getAttribute("solicitudactivacion");
    CuentaBancariaDTO cb = null;

    if(asi!=null){
        cb = (CuentaBancariaDTO) request.getAttribute("cuenta");
    }
%>
<html>
<head>
    <title><%= e.getNombre() %> | Cuenta</title>
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1400px;
            margin: 50px auto;
            padding: 50px 20px;
            text-align: center;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            font-size: 30px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        table {
            margin: 0 auto;
            width: 100%;
            max-width: 800px;
            border-collapse: separate;
            border-spacing: 0 10px;
        }
        td {
            padding: 10px;
            text-align: left;
            vertical-align: middle;
            font-size: 16px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"],
        input[type="password"],
        select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        button {
            background-color: #007bff;
            border: none;
            color: #fff;
            padding: 15px 50px;
            font-size: 18px;
            font-weight: bold;
            border-radius: 30px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-top: 20px;
        }
        button:hover {
            background-color: #0062cc;
        }
        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .button-container button {
            margin: 0 10px;
        }
        .table-container {
            max-width: 100%;
            overflow-x: auto;
        }
        .table-container table {
            width: auto;
        }
        .table-container table th,
        .table-container table td {
            white-space: nowrap;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Usuario <%= e.getNombre() %></h1>
    <div class="button-container">
        <a href="/logout"><button>Salir</button></a>
    </div>

    <h1>Modificar mis datos</h1>
    <form:form action="/empresa/guardarcambiospersonal" method="post" modelAttribute="usuariosocio">
        <form:hidden path="id" />
        <form:hidden path="rol" />
        <form:hidden path="identificacion" />
        <table>
            <tr>
                <td>Nombre:</td>
                <td><form:input path="nombre" /></td>
            </tr>
            <tr>
                <td>Apellidos:</td>
                <td><form:input path="apellido" /></td>
            </tr>
            <tr>
                <td>Email(*):</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Dirección:</td>
                <td><form:input path="direccion" /></td>
            </tr>
            <tr>
                <td>Teléfono:</td>
                <td><form:input path="telefono" /></td>
            </tr>
            <tr>
                <td>Selecciona rol:</td>
                <td>
                    <form:select path="subrol">
                        <form:option value="socio">Socio</form:option>
                        <form:option value="autorizado">Autorizado</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
            <tr>
                <td>Contraseña(*):</td>
                <td><input type="text" name="contrasena" value="<%= e.getContrasena() %>" /></td>
            </tr>
            </tr>
            <tr>
                <td colspan="2"><form:button>Cambiar</form:button></td>
            </tr>
        </table>
    </form:form>

    <div class="button-container">
        <a href="/empresa/cambiodatos?id=<%= e.getIdentificacion() %>"><button type="submit">Modificar datos de la empresa</button></a>
    </div>

    <h1>Cuenta de la empresa</h1>
    <%
        if(asi!=null){
    %>
    <table border="1">
        <tr>
            <th>Número de cuenta</th>
            <th>Saldo</th>
            <th>Activa</th>
            <%
                if(cb!= null && cb.getActivo()==0){
            %>
            <th>Desbloqueo</th>
            <%
                }
            %>
        </tr>
        <tr> <%
            if(cb!=null){
        %>
            <td><%= cb.getId() %> </td>
            <td><%= cb.getSaldo() %> <%=cb.getMoneda()%></td>
            <td><%=cb.getActivo()%></td>
            <%
                if(cb.getActivo()==0 && solicitud==null){
            %>
            <td>
                <div class="button-container">
                    <a href="/empresa/solicitudactivacion?id=<%=cb.getId() %>"><button type="submit">Desbloquear</button></a>
                </div>
            </td>
            <%
                } else if(cb.getActivo()==0){
            %>
            <td>Solicitud en curso</td>
            <%
                }
            %>
            <%
                }
            %>
        </tr>
    </table>
<%
    } else {
            out.println("Su solicitud de alta no ha sido aceptada todavía. Por lo tanto, no tiene acceso a la cuenta de la empresa");
    }
%>
    <%
        if(cb!=null && cb.getActivo()!=0){
    %>
    <div class="button-container">
        <a href="/empresa/transferencia?id=<%= e.getId() %>"><button type="submit">Realizar transferencia</button></a>
    </div>
    <div class="button-container">
        <a href="/empresa/cambiodivisa?id=<%= cb.getId() %>"><button type="submit">Realizar cambio de divisa de cuenta</button></a>
    </div>
    <%
        }
    %>
    <%
        if(e.getSubrol().equalsIgnoreCase("socio")){
    %>
    <div class="button-container">
        <a href="/empresa/personal"><button type="submit">Ver personal de la empresa</button></a>
    </div>
    <%
        }
    %>
    <%
        if(cb!=null){
    %>
    <div class="button-container">
        <a href="/empresa/operaciones"><button type="submit">Ver operaciones</button></a>
    </div>
    <%
        }
    %>
</div>
</body>
</html>

