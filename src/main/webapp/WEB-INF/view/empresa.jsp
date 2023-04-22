<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: albas
  Date: 19/04/2023
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        h1 {
            text-align: center;
        }
        table {
            margin: auto;
            width: 50%;
            border-collapse: collapse;
            border: 2px solid black;
        }
        td {
            padding: 10px;
        }
        input[type="text"], input[type="password"] {
            padding: 5px;
            border-radius: 5px;
            border: 1px solid black;
            width: 100%;
        }
    </style>
    <title >Cajasoft</title>
</head>
<body>
<h1>REGISTRO DE EMPRESA</h1>
    <form:form action="/guardar" method="post" modelAttribute="usuario">
        <form:hidden path="apellido" value=" "/>

        <form:hidden path="rol" value="empresa"/>
        <table>
            <tr><td>CIF(*): <form:input path="id"/><td></tr>
            <tr><td>Nombre de la empresa(*) <form:input path="nombre"/></td></tr>
            <tr><td>Email(*) <form:input path="email"/></td></tr>
            <tr><td>Dirección <form:input path="direccion"/></td></tr>
            <tr><td>Contraseña(*) <form:password path="contrasena"/></td></tr>
            <tr><td> <form:button>Registrar</form:button></td></tr>
        </table>
    </form:form>

</body>
</html>
