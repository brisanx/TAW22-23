<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 24/04/2023
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicia sesión</title>
</head>
<body>
    <h1>Bienvenido al cajero de CajaSoft</h1>
    <h3>Introduce tu email y contraseña:</h3>

    <c:if test="${error != null}" >
        <p style="color:red;">
                ${error}
        </p>
    </c:if>

    <form action="/cajero/iniciar" method="post">
        <table>
            <tr>
                <td>Correo del cliente:</td> <td><input type="text" name="usuario"></td>
            </tr>
            <tr>
                <td>Contraseña:</td> <td><input type="password" name="contrasena"> </td>
            </tr>
            <tr>
                <td colspan="2"> <button>Iniciar sesión</button></td>
            </tr>
        </table>
    </form>

</body>
</html>
