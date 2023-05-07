<%@ page import="org.taw.gestorbanco.entity.UsuarioEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: FERCA
  Date: 21/04/2023
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Aceptar Solicitud Alta</title>
    <link rel="stylesheet" type="text/css" href="/tabla.css">
    <link rel="stylesheet" type="text/css" href="/formularios.css">
</head>
<body>
  <h1>Cuestionario para abrir una nueva cuenta bancaria operativa</h1>
  <h2>Datos del cliente:</h2>
    <jsp:include page="cabeceraUsuario.jsp"></jsp:include>
  <br><br>
  <h2>Datos a rellenar:</h2>
  <form:form action="/gestoria/aceptarAlta" modelAttribute="cuentaNueva" method="post">
      <form:hidden path="user"></form:hidden>
      <form:hidden path="solId"></form:hidden>
      <div class="radio-buttons">
          <label>Tipo:</label>
          <form:radiobuttons path="tipo" items="${['Debito', 'Credito']}"></form:radiobuttons><br>
      </div>
      <div>
          <label>Saldo:</label>
          <form:input path="saldo" maxlength="10" size="10"/><br>
      </div>
      <div>
          <label>Moneda:</label>
          <form:select path="moneda" items="${divisas}" itemLabel="nombre" itemValue="id"/><br>
      </div>
      <button type="submit">Tramitar</button>
  </form:form>

</body>
</html>
