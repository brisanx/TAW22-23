<%@ page import="org.taw.gestorbanco.entity.UsuarioEntity" %><%--
  Created by IntelliJ IDEA.
  User: albas
  Date: 22/04/2023
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioEntity e = (UsuarioEntity) request.getAttribute("empresa");
%>
<html>
<head>
    <title> Cajasoft | Cuenta <%=e.getNombre()%> </title>
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
</head>
<body>
<h1>Empresa <%=e.getNombre()%></h1>

<a href="/guardarr?identificacion=<%=e.getIdentificacion()%>"><button type="submit">Dar de alta a personal de la empresa</button></a>
<a href="/logout">Salir</a>
</body>
</html>
