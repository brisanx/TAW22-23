<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.taw.gestorbanco.entity.UsuarioEntity" %>
<%@ page import="org.taw.gestorbanco.entity.CuentaBancariaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.taw.gestorbanco.entity.OperacionBancariaEntity" %>
<%@ page import="org.taw.gestorbanco.dto.OperacionBancariaDTO" %><%--
  Created by IntelliJ IDEA.
  User: FERCA
  Date: 22/04/2023
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/tabla.css">
    <link rel="stylesheet" type="text/css" href="/formularioOp.css">
    <link rel="stylesheet" type="text/css" href="/volverMenu.css">
</head>
<body>
    <div class="container">
        <a href="/gestoria/inicio" class="boton">Volver menú</a>
    </div>
    <br><br>

<h1>Datos del cliente:</h1>
    <jsp:include page="cabeceraUsuario.jsp"></jsp:include>
    <br><br>
    <div class="formulario">
    <form:form action="/gestoria/filtrarOperacion" modelAttribute="filtro" method="post">
        <form:hidden path="id"></form:hidden>
        <label>Fecha </label><br>
        <label>Anterior/igual a:</label>
        <input type="radio" name="mm" id="anterior" value="true"/>
        <label>Posterior/igual a:</label>
        <input type="radio" name="mm" id="posterior" value="false"/>
        <input type="date" name="fecha" id="fecha" /><br>

        <label>Cantidad</label><br>
        <label>Menor/igual a:</label>
        <input type="radio" name="mmc" id="anterior" value="true"/>
        <label>Mayor/igual a:</label>
        <input type="radio" name="mmc" id="posterior" value="false"/>
        <input type="number" name="cantidad" id="cantidad" placeholder="Valor numérico"/><br><br>

        <button type="submit">Filtrar</button>

    </form:form>
    </div>
    <table>
        <thead>
        <tr>
            <th>Cuenta Bancaria</th>
            <th>Fecha</th>
            <th>Cantidad</th>
            <th>Cuenta Destino</th>
        </tr>
        </thead>
        <tbody>
    <h2>Cuentas bancarias e historial de operaciones</h2>
    <%
        List<OperacionBancariaDTO> op = (List<OperacionBancariaDTO>) request.getAttribute("op");
        for(OperacionBancariaDTO o:op){
    %>
    <tr>
        <td><%=o.getCuentaBancariaByIdCuentaOrigen().getId()%></td>
        <td><%=o.getFecha()%></td>
        <td><%=o.getCantidad()%></td>
        <td><%=o.getCuentaBancariaByIdCuentaDestino().getId()%></td>

    </tr>
    <%
        }
    %>
        </tbody>
    </table>
</body>
</html>
