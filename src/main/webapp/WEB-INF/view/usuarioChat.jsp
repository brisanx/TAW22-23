<%@ page import="org.taw.gestorbanco.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.taw.gestorbanco.entity.EmpleadoEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ConversacionEntity> conversaciones = (List<ConversacionEntity>) request.getAttribute("conversaciones");
    List<EmpleadoEntity> gestores = (List< EmpleadoEntity>) request.getAttribute("gestores");
%>
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
            max-width: 950px;
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
    <h3>Gestión conversaciones</h3>

    <c:if test="${error != null}" >
        <p style="color:red;">
                ${error}
        </p>
    </c:if>

    <h1>Listado de conversaciones</h1>

    <table border="1">
        <tr>
            <th>GESTOR</th>
            <th>NUMERO MENSAJES</th>
            <th>ESTADO</th>
            <th>FECHA APERTURA</th>
            <th>FECHA CIERRE</th>
            <th>ACCIONES</th>
        </tr>
        <%
            for (ConversacionEntity conv: conversaciones) {
        %>
        <tr>
            <td><%= conv.getEmpleadoByEmpleadoIdGestor().getNombre() %></td>
            <td><%= conv.getNumeroMensaje() %></td>
            <td><% if (conv.getEstado()==2)  {%> Cerrado <% }  else {%> Abierto <% }%>  </td>
            <td><%= conv.getFechaApertura() %></td>
            <td><%= conv.getFechaCierre() %></td>
            <td> <% if (conv.getEstado()!=2)  {%> <form action="/home/user/conversacion/chat/<%=conv.getIdConver()%>" method="get">
                <button type="submit">Acceder chat</button>
            </form> <% }%> </td>
            <td><% if (conv.getEstado()!=2)  {%> <form action="/home/user/conversacion/chat/cerrar/<%=conv.getIdConver()%>" method="get">
                <button type="submit">Cerrar chat</button>
            </form> <% }%> </td>

        </tr>

        <% } %>
    </table>
    <form action="/home/user/conversacion/insertar" method="post">
        <select name="gestor">
            <%
                for (EmpleadoEntity conv: gestores) {
            %>
            <option value="<%=conv.getIdGestor()%>"><%=conv.getNombre()%> <%=conv.getEmail()%></option>
            <% } %>
        </select>

        <button type="submit">Crear chat</button>
    </form>
</div>
</body>
</html>