<%--
  Created by IntelliJ IDEA.
  User: albas
  Date: 21/04/2023
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 50px 20px;
            text-align: center;
        }
        h1 {
            font-size: 48px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        h3 {
            font-size: 24px;
            margin-bottom: 50px;
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
        }
        button:hover {
            background-color: #0062cc;
        }
    </style>
    <title>Cajasoft</title>
</head>
<body>
<div class="container">
    <h1>Bienvenido a Cajasoft</h1>
    <h3>Por favor, seleccione un tipo de acceso según su estatus</h3>
    <form action="/accesoUsuario" method="get">
        <button type="submit">Acceso usuario</button>
    </form>
    <form action="/accesoEmpleado" method="get">
        <button type="submit">Acceso empleado</button>
    </form>
</div>
</body>
</html>