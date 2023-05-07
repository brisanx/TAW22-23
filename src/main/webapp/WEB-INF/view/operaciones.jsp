<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.taw.gestorbanco.entity.OperacionBancariaEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.taw.gestorbanco.dto.OperacionBancariaDTO" %><%--
  Created by IntelliJ IDEA.
  User: Alba Sánchez Ibáñez
  Date: 06/05/2023
  Time: 1:44
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
<h1>Listado de operaciones realizadas por cualquier s/a de la cuenta de la empresa y filtrarlos</h1>
<div class="table-container">
    <div class="formulario">
        <form:form action="/empresa/filtrarOperacion" modelAttribute="filtro" method="post">
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
            <th>ID</th>
            <th>Fecha</th>
            <th>Cantidad</th>
            <th>Cuenta origen</th>
            <th>Cuenta destino</th>
            <th>Personal encargado</th>
            <th>Nombre del encargado</th>
        </tr>
        <%
            for(OperacionBancariaDTO o : op) {
        %>
        <tr>
            <td><%= o.getId() %></td>
            <td><%= o.getFecha() %></td>
            <td><%= o.getCantidad()%></td>
            <td><%= o.getCuentaBancariaByIdCuentaOrigen().getId()%></td>
            <td><%= o.getCuentaBancariaByIdCuentaDestino().getId() %></td>
            <td><%= o.getUsuario().getIdentificacion() %></td>
            <td><%= o.getUsuario().getNombre()%></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<br>
<a href="/empresa/paginaempresa"><button>Volver</button></a>
</body>
</html>