<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.taw.gestorbanco.entity.UsuarioEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: albas
  Date: 26/04/2023
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity e = (UsuarioEntity) request.getAttribute("usuariosocio");
    List<UsuarioEntity> listaPersonal = (List<UsuarioEntity>) request.getAttribute("personalempresa");
%>
<html>
<head>
    <title><%=e.getNombre()%> | Cuenta </title>
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
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
            max-width: 400px;
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
        input[type="text"], input[type="password"] {
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
    </style>
</head>
<body>
<h1>Usuario <%=e.getNombre()%></h1>
<div class="button-container">
    <a href="/logout"><button>Salir</button></a>
</div>
<h1>Modificar mis datos</h1>
<form:form action="/otrosave" method="post" modelAttribute="usuariosocio">
    <form:hidden path="id"/>
    <form:hidden path="rol"/>
    <form:hidden path="identificacion"/>
    <table>
        <tr><td>Nombre: <form:input path="nombre"/></td></tr>
        <tr><td>Apellidos: <form:input path="apellido"/></td></tr>
        <tr><td>Email(*): <form:input path="email"/></td></tr>
        <tr><td>Dirección <form:input path="direccion"/></td></tr>
        <tr><td>Teléfono <form:input path="telefono"/></td></tr>
        <tr><td>Selecciona rol... <form:select path="subrol">
            <form:option value="socio">Socio</form:option>
            <form:option value="autorizado">Autorizado</form:option>
        </form:select></td></tr>
        <tr><td>Contraseña(*) <form:password path="contrasena"/></td></tr>
        <tr><td> <form:button>Cambiar</form:button></td></tr>
    </table>
</form:form>
<div class="button-container">
    <a href="/cambiodatos?id=<%=e.getIdentificacion()%>"><button type="submit">Modificar datos de la empresa</button></a>
</div>

<%
    if(e.getSubrol().equalsIgnoreCase("socio")){
%>
<h1>Listado de socios/autorizados</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Identificación</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Email</th>
        <th>Contraseña</th>
        <th>Rol</th>
        <th>Subrol</th>
        <th>Dirección</th>
        <th>Teléfono</th>
        <th>Bloqueo/Desbloqueo</th>
    </tr>
    <%
        for(UsuarioEntity u : listaPersonal) {
    %>
    <tr>
        <td><%=u.getId()%></td>
        <td><%=u.getIdentificacion()%></td>
        <td><%=u.getNombre()%></td>
        <td><%=u.getApellido()%></td>
        <td><%=u.getEmail()%></td>
        <td><%=u.getContrasena()%></td>
        <td><%=u.getRol()%></td>
        <td><%=u.getSubrol()%></td>
        <td><%=u.getDireccion()%></td>
        <td><%=u.getTelefono()%></td>
        <td>
           <%
               if(u.getBloqueo()) {
           %>
            <a href="/bloquear?id=<%=u.getId()%>"><button>Desbloquear</button></a>
            <%
                } else {
            %>
            <a href="/bloquear?id=<%=u.getId()%>"><button>Bloquear</button></a>
            <%
                }
            %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>

<div class="button-container">
    <a href="/transferencia?id=<%=e.getId()%>"><button type="submit">Realizar transferencia</button></a>
</div>

<h1>Cambio de divisas</h1>
<h1>Listado de operaciones realizadas por cualquier s/a de la cuenta de la empresa y filtrarlos</h1>
<h1>Activación/desbloqueo de mi cuenta</h1>
</body>
</html>
