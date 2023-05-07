<%@ page import="org.taw.gestorbanco.dto.OperacionBancariaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.taw.gestorbanco.dto.CuentaBancariaDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: Jose Torres
  Date: 04/05/2023
  Time: 0:52
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
        h4 {
            text-align: center;
        }
    </style>
    <title>CajaSoftix</title>
</head>

<body>

    <jsp:include page="cabeceraCliente.jsp"/>
    <br>
    <a href="/modificarDatos"><button>Modificar mis Datos</button></a>

    <div class="button-container">
        <a href="/operaciones"><button type="submit">Ver operaciones</button></a>
    </div>
    <div class="button-container">
        <a href="/nuevaCuentaBancaria"><button>Solicitar una cuenta nueva</button></a>
    </div>
    <a href="/logout"><button>Salir</button></a>
</body>
</html>