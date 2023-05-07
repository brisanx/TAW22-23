<%@ page import="org.taw.gestorbanco.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.taw.gestorbanco.entity.EmpleadoEntity" %>
<%@ page import="org.taw.gestorbanco.entity.UsuarioEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ConversacionEntity> conversaciones = (List<ConversacionEntity>) request.getAttribute("conversaciones");
    List<EmpleadoEntity> gestores = (List< EmpleadoEntity>) request.getAttribute("gestores");
    List<UsuarioEntity> usuarios = (List< UsuarioEntity>) request.getAttribute("usuarios");
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
    <h3>Gestión conversaciones gestores</h3>

    <c:if test="${error != null}" >
        <p style="color:red;">
                ${error}
        </p>
    </c:if>

    <h1>Listado de conversaciones</h1>
    <h3>Busqueda conversaciones gestores</h3>
    <form action="/home/gestor/conversacion/buscar" method="post">
      <table>
        <tr>
        <td>
        Asistente:
            <select name="empleadoId">
                <option value="0">  </option>
                <%
                    for (EmpleadoEntity conv: gestores) {
                %>
                <option value="<%=conv.getIdGestor()%>"><%=conv.getNombre()%> <%=conv.getEmail()%></option>
                <% } %>
            </select>
        </td>
           <td>
               Usuario:
               <select name="userId">
                   <option value="0">  </option>
                   <%
                       for (UsuarioEntity user: usuarios) {
                   %>
                   <option value="<%=user.getId()%>"><%=user.getNombre()%> <%=user.getEmail()%></option>
                   <% } %>
               </select>
           </td>
           <td>
               Estado:
               <select name="estado">
                   <option value="0">  </option>
                   <option value="1">Abierto</option>
                     <option value="2">Cerrado</option>
               </select>
           </td>
        </tr>
         <tr>
           <td>
               Numero mensajes:
               <input type="number" name="numeroMensajesInternal">
              </td>
             <td>
                 Ordernacion:
                 <select name="order">
                     <option value="idConver"> Id </option>
                     <option value="empleadoByEmpleadoIdGestor">Asistente</option>
                     <option value="usuarioByUsuarioId">Usuario</option>
                     <option value="estado">Estado</option>
                 </select>
             </td>
         <td>
          <button type="submit">Buscar conversacion</button>
         </td>
       </tr>
        </table>
    </form>
    <table border="1">
        <tr>
            <th>ASISTENTE</th>
            <th>USUARIO</th>
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
            <td><%= conv.getEmpleadoByEmpleadoIdGestor().getIdGestor() %> <%= conv.getEmpleadoByEmpleadoIdGestor().getNombre() %></td>
            <td><%= conv.getUsuarioByUsuarioId().getId() %> <%= conv.getUsuarioByUsuarioId().getEmail() %></td>
            <td><%= conv.getNumeroMensaje() %></td>
            <td><% if (conv.getEstado()==2)  {%> Cerrado <% }  else {%> Abierto <% }%>  </td>
            <td><%= conv.getFechaApertura() %></td>
            <td><%= conv.getFechaCierre() %></td>
            <td> <% if (conv.getEstado()!=2)  {%> <form action="/home/gestor/conversacion/chat/<%=conv.getIdConver()%>" method="get">
                <button type="submit">Acceder chat</button>
            </form> <% }%>
                <% if (conv.getEstado()==2)  {%> <form action="/home/gestor/conversacion/lectura/chat/<%=conv.getIdConver()%>" method="get">
                    <button type="submit">Leer chat</button>
                </form> <% }%>
            </td>

        </tr>

        <% } %>

    </table>
    <h1>Mensajes usuario</h1>
    <tr>

        <td>
            <form action="/home/gestor/conversacion/chat/user" method="post">

                Usuario:
                <select name="id">
                    <option value="0">  </option>
                    <%
                        for (UsuarioEntity user: usuarios) {
                    %>
                    <option value="<%=user.getId()%>"><%=user.getNombre()%> <%=user.getEmail()%></option>
                    <% } %>
                </select>
                <button type="submit">Ver mensajes usuarios</button>
            </form>
        </td>
    </tr>
</div>


</body>
</html>
