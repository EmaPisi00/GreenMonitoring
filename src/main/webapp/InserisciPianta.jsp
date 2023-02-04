<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%--
  Created by IntelliJ IDEA.
  User: stefa
  Date: 22/01/2023
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <title>Title</title>
</head>

<body>

<% UtenteBean u= (UtenteBean) session.getAttribute("currentUserSession");
    String errori = (String)request.getAttribute("erroriPiantaBean");
    if (!(u instanceof AziendaBean))  { %>
<% response.sendRedirect("error.jsp"); %>
<% } else{  %>

<%@ include file="/fragments/headerLoggedAzienda.html" %>
<%}%>

<form action="ServletPianta" method="post" enctype="multipart/form-data">

    <%
        if (errori != null) { %>
    <div class="text-danger"> <%= errori%> </div>
    <% } %>

    </div>
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required><br>
    <label for="descrizione">Descrizione:</label>
    <textarea id="descrizione" name="descrizione"></textarea><br>
    <label for="ph_min">pH minimo:</label>
    <input type="text" id="ph_min" name="ph_min" required><br>
    <label for="ph_max">pH massimo:</label>
    <input type="text" id="ph_max" name="ph_max" required><br>
    <label for="temperatura_min">Temperatura minima:</label>
    <input type="text" id="temperatura_min" name="temperatura_min" required><br>
    <label for="temperatura_max">Temperatura massima:</label>
    <input type="text" id="temperatura_max" name="temperatura_max" required><br>
    <label for="umidita_min">Umidità minima:</label>
    <input type="text" id="umidita_min" name="umidita_min" required><br>
    <label for="umidita_max">Umidità massima:</label>
    <input type="text" id="umidita_max" name="umidita_max" required><br>
    <label for="immagine">Immagine:</label>
    <input type="file" id="immagine" name="immagine" ><br>
    <input type="hidden" id="azienda" name="azienda" value="<%=u.getEmail()%>">
    <input type="submit" value="inserisciPianta_submit" name="inserisciPianta_submit">
</form>
<form action="Piante.jsp">
    <input type="submit" value="bottone">
</form>

<%@include file="/fragments/footer.html" %>

</body>
</html>
