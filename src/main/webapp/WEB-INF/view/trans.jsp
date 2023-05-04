<%@ page import="org.taw.gestorbanco.entity.CuentaBancariaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: albas
  Date: 01/05/2023
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CuentaBancariaEntity corigen = (CuentaBancariaEntity) request.getAttribute("cuentaorigen");
%>
<html>
<head>
    <title>Transferencia</title>
</head>
<body>
   <form:form action="/transhecha" method="post" modelAttribute="op">
        <form:hidden path="id"/>
       <form:hidden path="cuentaBancariaByIdCuentaOrigen" value="<%=corigen.getId()%>"/>
        Cantidad: <form:input path="cantidad"/>
        Cuenta: <form:input path="cuentaBancariaByIdCuentaDestino"/>
       <form:button>Realizar</form:button>
   </form:form>
</body>
</html>
