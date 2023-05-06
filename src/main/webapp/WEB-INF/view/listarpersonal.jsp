<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.taw.gestorbanco.entity.UsuarioEntity" %>
<%@ page import="org.taw.gestorbanco.entity.AsignacionEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: albas
  Date: 06/05/2023
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<UsuarioEntity> listaPersonal = (List<UsuarioEntity>) request.getAttribute("personalempresa");
  List<AsignacionEntity> asignacionesEmpresa = (List<AsignacionEntity>) request.getAttribute("asignaciones");
  UsuarioEntity e = (UsuarioEntity) request.getAttribute("usuario");
%>
<html>
<head>
    <title>Personal | EMPRESA </title>
</head>
<body>
<h1>Listado de socios/autorizados</h1>

<div class="formulario">
  <form:form action="/empresa/filtrarNombre" method="post" modelAttribute="filtroNombre">
    Nombre:<form:input path="nombre" maxlength="20" size="20"></form:input>
    Apellidos:<form:input path="apellido" maxlength="20" size="20"></form:input>
    <br>
    <form:button>Buscar</form:button>
  </form:form>
  <br>
  <form:form action="/empresa/filtrarTipo" modelAttribute="filtroTipo" method="post">
    <form:select path="tipo">
      <form:option value="">--Seleccione--</form:option>
      <form:option value="socio">Socio</form:option>
      <form:option value="autorizado">Autorizado</form:option>
    </form:select>
    <br>
    <form:button>Filtrar</form:button>
  </form:form>
</div>

<div class="table-container">
  <table border="1">
    <tr>
      <th>ID</th>
      <th>Identificación</th>
      <th>Nombre</th>
      <th>Apellidos</th>
      <th>Email</th>
      <th>Contraseña</th>
      <th>Rol</th>
      <th>Subrol</th>
      <th>Dirección</th>
      <th>Teléfono</th>
      <th>Bloqueo</th>
    </tr>
    <%
      for(UsuarioEntity u : listaPersonal) {
    %>
    <tr>
      <td><%= u.getId() %></td>
      <td><%= u.getIdentificacion() %></td>
      <td><%= u.getNombre() %></td>
      <td><%= u.getApellido() %></td>
      <td><%= u.getEmail() %></td>
      <td><%= u.getContrasena() %></td>
      <td><%= u.getRol() %></td>
      <td><%= u.getSubrol() %></td>
      <td><%= u.getDireccion() %></td>
      <td><%= u.getTelefono() %></td>
      <td>
        <%
          if(u.getId()!=e.getId() && asignacionesEmpresa.contains(u.getId())){
        %>
        <a href="/empresa/bloquear?id=<%= u.getId() %>"><button>Bloquear</button></a>
        <%
          } else if (u.getId()!=e.getId()){
            out.println("Bloqueado");
          }
        %>
      </td>
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