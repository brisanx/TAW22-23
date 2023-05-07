<%@ page import="org.taw.gestorbanco.dto.UsuarioDTO" %>
<%@ page import="org.taw.gestorbanco.dto.CuentaBancariaDTO" %><%--
  Created by IntelliJ IDEA.
  User: Mike
  Date: 02/05/2023
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    UsuarioDTO user = (UsuarioDTO) request.getAttribute("user");
 %>
<head>
    <title>Bienvenido <%=user.getNombre()%></title>
    <style>
        td{
            text-align: center;
        }
        #principal{
            height: 100%;
        }
        table{
            width: 100%;
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
        #exit{
            width: 140px;
            height: 60px;
            background-color: #a90202;
            border: none;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            border-radius: 15px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        #exit:hover {
            background-color: #c25151;
        }
    </style>
</head>
<body>
<table id="principal">
    <tr>
        <td><a href="/cajero/modificarDatos"><button>Modificar mis datos</button></a></td>
        <td><h1>Cuenta de <%=user.getNombre()%> <%=user.getApellido()%></h1></td>
        <td><a href="/cajero/sacarDinero"><button>Sacar dinero</button></a></td>
    </tr>
    <tr>
        <td><a href="/cajero/listarOP"><button>Ver listado de operaciones</button></a></td>
        <td><jsp:include page="cabeceraCajero.jsp"/></td>
        <td><a href="/cajero/transferenciaCajero"><button>Hacer transferencia</button></a></td>
    </tr>
    <tr>
        <td></td>
        <td><a href="/cajero/logout"><button id="exit">Cerrar sesión</button></a></td>
        <td></td>
    </tr>
</table>

</body>
</html>
