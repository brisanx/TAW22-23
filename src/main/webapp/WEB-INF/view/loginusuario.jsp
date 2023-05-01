<%--
  Created by IntelliJ IDEA.
  User: albas
  Date: 21/04/2023
  Time: 18:02
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
            font-size: 48px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        h3 {
            font-size: 24px;
            margin-bottom: 50px;
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
    <title>Cajasoft</title>
</head>
<body>
<div class="container">
    <h1>Bienvenido a Cajasoft</h1>
    <h3>Inicie sesión para poder continuar</h3>

    <c:if test="${error != null}" >
        <p style="color:red;">
                ${error}
        </p>
    </c:if>

    <form action="/autenticar" method="post">
        <table>
            <tr>
                <td>Correo del usuario</td> <td><input type="text" name="usuario"></td>
            </tr>
            <tr>
                <td>Contraseña:</td> <td><input type="password" name="contrasena"> </td>
            </tr>
            <tr>
                <td colspan="2"> <button>Enviar</button></td>
            </tr>
        </table>
    </form>
    <form action="/registrocliente" method="get">
        <button type="submit">Registrarse como cliente</button>
    </form>
    <form action="/registro" method="get">
        <button type="submit">Registrarse como empresa</button>
    </form>
</div>
</body>
</html>
