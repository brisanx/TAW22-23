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
        CIF(*)
        Nombre de la empresa(*)
        Dirección
        <table>
            <tr>
                <td>Calle(*)</td>
                <td>Número (*)</td>
            </tr>
            <tr>
                <td>Planta/Puerta/Oficina (*)</td>
            </tr>
            <tr>
                <td>Ciudad(*)</td>
                <td>Región</td>
            </tr>
            <tr>
                <td>País(*)</td>
                <td>C.P.(*)</td>
            </tr>
        </table>
        <form:checkbox path="direccion" value="false" label="Válida (dirección actual)"/>
        <table>
            <tr>
                <td>Contraseña(*)</td>
                <td>Contraseña.Repetir(*)</td>
            </tr>
        </table>
        <form:button>Registrar</form:button>
    </form:form>
</body>
</html>
