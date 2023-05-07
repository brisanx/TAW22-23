<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.taw.gestorbanco.entity.OperacionBancariaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.taw.gestorbanco.dto.OperacionBancariaDTO" %><%--
  Created by IntelliJ IDEA.
  User: Jose Torres
  Date: 06/05/2023
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<OperacionBancariaDTO> op = (List<OperacionBancariaDTO>) request.getAttribute("op");
%>
<html>
<head>
    <title>Operaciones</title>
</head>
<body>
<h1>Listado de operaciones realizadas</h1>
<div class="table-container">
    <div class="formulario">
        <form:form action="/filtrarOperacion" modelAttribute="filtro" method="post">
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
</div>
<div class="table-container">
    <table border="1">
        <tr>
            <th>ID de cuenta</th>
            <th>Fecha</th>
            <th>Cantidad</th>
            <th>Cuenta destino</th>
        </tr>
        <%
            for(OperacionBancariaDTO o : op) {
        %>
        <tr>
            <td><%= o.getCuentaBancariaByIdCuentaOrigen().getId() %></td>
            <td><%= o.getFecha() %></td>
            <td><%= o.getCantidad()%></td>
            <td><%= o.getCuentaBancariaByIdCuentaDestino().getId() %></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<br>
<a href="/homeCliente"><button>Volver</button></a>
</body>
</html>