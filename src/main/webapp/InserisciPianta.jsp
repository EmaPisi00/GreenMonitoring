<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %><%--
  Created by IntelliJ IDEA.
  User: stefa
  Date: 22/01/2023
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<% UtenteBean u= (UtenteBean) session.getAttribute("currentUserSession");
    if (!(u instanceof AziendaBean))  { %>
<% response.sendRedirect("error.jsp"); %>
<% } %>
<body>
<form action="ServletPianta" method="post" enctype="multipart/form-data">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome"><br>
    <label for="descrizione">Descrizione:</label>
    <textarea id="descrizione" name="descrizione"></textarea><br>
    <label for="ph_min">pH minimo:</label>
    <input type="text" id="ph_min" name="ph_min"><br>
    <label for="ph_max">pH massimo:</label>
    <input type="text" id="ph_max" name="ph_max"><br>
    <label for="temperatura_min">Temperatura minima:</label>
    <input type="text" id="temperatura_min" name="temperatura_min"><br>
    <label for="temperatura_max">Temperatura massima:</label>
    <input type="text" id="temperatura_max" name="temperatura_max"><br>
    <label for="umidita_min">Umidità minima:</label>
    <input type="text" id="umidita_min" name="umidita_min"><br>
    <label for="umidita_max">Umidità massima:</label>
    <input type="text" id="umidita_max" name="umidita_max"><br>
    <label for="immagine">Immagine:</label>
    <input type="file" id="immagine" name="immagine"><br>
    <input type="hidden" id="azienda" name="azienda" value="<%=u.getEmail()%>">
    <input type="submit" value="inserisciPianta_submit" name="inserisciPianta_submit">
</form>
</body>
</html>
