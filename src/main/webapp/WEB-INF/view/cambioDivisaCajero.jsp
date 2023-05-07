
<%@ page import="org.taw.gestorbanco.dto.UsuarioDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 05/05/2023
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Cambiar divisa</title>
  <style>
    table{
      width: 100%;
      text-align: center;
      font-size: 18px;
    }
    button {
      width: 200px;
      height: 110px;
      background-color: #5b5b5b;
      border: none;
      color: #fff;
      font-size: 18px;
      font-weight: bold;
      border-radius: 6px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    button:hover {
      background-color: #939393;
    }
  </style>
</head>
<body>
<h1 style="text-align: center">Cambiar divisa</h1>
<table>
  <tr>
    <td>
      <form:form action="/cajero/confirmaCambioDivisa" method="post" modelAttribute="cuenta"><br>
      <form:hidden path="moneda"/>
      <form:hidden path="saldo"/>
      Cuenta seleccionada: <form:input path="id" readonly="true" size="3"/><br><br>
      Nueva divisa: <form:select path="divisaByDivisaId" items="${divisas}" itemLabel="nombre" itemValue="id"/><br><br>
    </td>
    <td><form:button>Realizar</form:button></td>
    </form:form>
  </tr>
</table>


</body>
</html>