<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.taw.gestorbanco.dto.UsuarioDTO" %><%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 05/05/2023
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Modificaciones</title>
  <style>
    td{
      text-align: center;
      font-size: 20px;
    }
    tr{
      height: 60px;
    }
    table{
      width: 100%;
    }
    button {
      width: 200px;
      height: 110px;
      background-color: #5b5b5b;
      border: none;
      color: #fff;
      font-size: 18px;
      font-weight: bold;
      border-radius: 6px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    button:hover {
      background-color: #939393;
    }
  </style>
</head>
<body>
<h1 style="text-align: center">Modifica tus datos</h1>
<table>
  <tr>
    <td rowspan="2"><form:form modelAttribute="cliente" method="post" action="/cajero/guardarDatos">
      <form:hidden path="id"/>
      <form:hidden path="rol"/>
      <table>
        <tr><td>NIF (*): <form:input path="identificacion"/><td></tr>
        <tr><td>Nombre (*): <form:input path="nombre"/></td></tr>
        <tr><td>Apellidos: <form:input path="apellido"/></td></tr>
        <tr><td>Email (*): <form:input path="email"/></td></tr>
        <tr><td>Dirección: <form:input path="direccion"/></td></tr>
        <tr><td>Teléfono: <form:input path="telefono"/></td></tr>
        <tr><td>Contraseña (*): <form:input path="contrasena"/></td></tr>
        <tr><td></td></tr>
      </table>
    </td>
    <td><form:button>Guardar</form:button></td>
  </tr>
  </form:form>
</table>


</body>
</html>
