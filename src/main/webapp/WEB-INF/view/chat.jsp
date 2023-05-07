

<%@ page import="org.taw.gestorbanco.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.taw.gestorbanco.entity.MensajeEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<MensajeEntity> mensajes = (List<MensajeEntity>) request.getAttribute("mensajes");
%>
<html>
<head>
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            height: 300px;
        }
        .container {
            max-width: 800px;
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
        #outerDivWrapper, #outerDiv {
            height: 100%;
            margin: 0em;
            text-align:left;
        }

        #scrollableContent {
            height: 100%;
            margin: 0em;
            overflow-y: auto;
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

    <div id="outerDivWrapper">
        <div id="outerDiv">
            <div id="scrollableContent">
                <%
                    for (MensajeEntity conv: mensajes) {
                %>

                <%= conv.getFecha() %>
                <%= conv.getSender() %>
                <%= conv.getTexto() %> <br>

                <%
                    }
                %>
            </div>
        </div>
    </div>
    <form action="/home/user/conversacion/chat/insertar" method="post">
        <input type="text" name="texto"> <button type="submit">Enviar</button>
    </form>
    <form action="/home/user/conversacion" method="get">
        <button type="submit">Volver a gestión conversaciones</button>
    </form>
</div>
</body>
</html>
