<%@ page import="org.taw.gestorbanco.entity.CuentaBancariaEntity" %>
<%@ page import="org.taw.gestorbanco.dto.CuentaBancariaDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Alba Sánchez Ibáñez
  Date: 18/04/2023
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CuentaBancariaDTO cuentaOrigen = (CuentaBancariaDTO) request.getAttribute("cuentaorigen");
%>
<html>
<head>
    <title>Transferencia</title>
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

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        form input[type="text"] {
            width: 300px;
            padding: 10px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        form button {
            background-color: #007bff;
            border: none;
            color: #fff;
            padding: 15px 50px;
            font-size: 18px;
            font-weight: bold;
            border-radius: 30px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        form button:hover {
            background-color: #0062cc;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Transferencia</h1>
    <form:form action="/empresa/transhecha" method="post" modelAttribute="operacion">
        <form:hidden path="id" />
        <form:hidden path="cuentaBancariaByIdCuentaOrigen.id" value="<%=cuentaOrigen.getId()%>" />
        <label for="cantidad">Cantidad:</label>
        <form:input path="cantidad" id="cantidad" />
        <label for="cuenta">Cuenta:</label>
        <form:input path="cuentaBancariaByIdCuentaDestino.id" id="cuenta"/>
        <form:button>Realizar</form:button>
    </form:form>
</div>
</body>
</html>