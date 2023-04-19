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
    </style>
    <title >Cajasoft</title>
</head>
<body>
<h1>REGISTRO DE EMPRESA</h1>
    <form:form action="/guardar" method="post" modelAttribute="usuario">
        <form:hidden path="apellido" value=" "/>
        <form:hidden path="email" value=" "/>
        <form:hidden path="rol" value="empresa"/>
        CIF(*): <br>
        Nombre de la empresa(*): <form:input path="nombre"/> <br>
        Dirección <form:input path="direccion"/><br><br>
         Contraseña(*) <form:password path="contrasena"/><br>
        <form:button>Registrar</form:button>
    </form:form>

</body>
</html>
