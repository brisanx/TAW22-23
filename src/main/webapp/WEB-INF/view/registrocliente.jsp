<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: erpep
  Date: 03/05/2023
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
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
    </style>
    <title>CajaSoft</title>
</head>
<body>
<div class="container">
    <h1>REGISTRO DE USUARIO</h1>
    <form:form action="/registrarcliente" modelAttribute="usuario" method="post" >
        <form:hidden path="id"/>
        <form:hidden path="rol" value="cliente"/>

        <table>
            <tr><td>NIF(*): <form:input path="identificacion"/><td></tr>
            <tr><td>Nombre(*): <form:input path="nombre"/></td></tr>
            <tr><td>Apellidos(*): <form:input path="apellido"/></td></tr>
            <tr><td>Email(*): <form:input path="email"/></td></tr>
            <tr><td>Dirección: <form:input path="direccion"/></td></tr>
            <tr><td>Teléfono(*): <form:input path="telefono"/></td></tr>
            <tr><td>Contraseña(*): <form:password path="contrasena"/></td></tr>
            <tr><td> <form:button>Registrar</form:button></td></tr>
        </table>
    </form:form>
</div>
</body>
</html>