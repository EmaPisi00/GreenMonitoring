<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="java.io.DataInput" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
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

    <title>Inserisci Pianta</title>
</head>

<body>

<% UtenteBean user = (UtenteBean) session.getAttribute("currentUserSession");
    if (user == null) {
        response.sendRedirect("error.jsp");
    } else if (user instanceof DipendenteBean) {
        response.sendRedirect("error.jsp");
    } else if (user instanceof AziendaBean) { %>
<%@include file="fragments/headerLoggedAzienda.html" %>

<%} %>


<% if (user != null) {%>
<form action="ServletPianta" method="post" enctype="multipart/form-data">

    <%
        String errori = (String) request.getAttribute("erroriPiantaBean");
        if (errori != null) { %>
    <div class="text-danger"><%= errori%>
    </div>
    <% } %>

    </div>
    <div class="container py-5">

        <div class="row ">


            <div class="col-12 ">
                <h3 class="display-3 text-center">Inserisci una Pianta</h3>
            </div>
        </div>
        <div class="card  text-black" style="border-radius: 1rem; border: 6px solid green; font-size:  22px; ">
            <div class="row justify-content-center " style="font-family: 'Staatliches', cursive; font-size: 20px; text-align: center">
                <div class="col-md-10">
                    <div class="form-outline form-white mb-4">
                        <label class="form-label" for="nome">Nome:</label>
                        <input style=" background-color: rgb(208, 213, 218);" type="text" id="nome" class="form-control border-secondary" name="nome" required><br>
                    </div>
                </div>
                <div class="col-md-10">
                    <div class="form-outline form-white mb-4">
                        <label class="form-label" for="descrizione">Descrizione:</label>
                        <textarea style=" background-color: rgb(208, 213, 218);" id="descrizione" class="form-control border-secondary " name="descrizione"></textarea><br>
                    </div>
                </div>

                <div class="col-md-5">
                    <div class="form-outline form-white mb-4">
                        <label class="form-label" for="ph_min">pH minimo:</label>
                        <input style=" background-color: rgb(208, 213, 218);" type="text" class="form-control border-secondary" id="ph_min" name="ph_min" required><br>
                    </div>
                </div>

                <div class="col-md-5">
                    <div class="form-outline form-white mb-4">
                        <label class="form-label" for="ph_max">pH massimo:</label>
                        <input style=" background-color: rgb(208, 213, 218);" type="text" class="form-control border-secondary" id="ph_max" name="ph_max" required><br>
                    </div>
                </div>

                <div class="col-md-5">
                    <div class="form-outline form-white mb-4">
                        <label class="form-label" for="temperatura_min">Temperatura minima:</label>
                        <input style=" background-color: rgb(208, 213, 218);" type="text" class="form-control border-secondary" id="temperatura_min" name="temperatura_min" required><br>
                    </div>
                </div>

                <div class="col-md-5">
                    <div class="form-outline form-white mb-4">
                        <label class="form-label" for="temperatura_max">Temperatura massima:</label>
                        <input style=" background-color: rgb(208, 213, 218);" type="text" class="form-control border-secondary" id="temperatura_max" name="temperatura_max" required><br>
                    </div>
                </div>

                <div class="col-md-5">
                    <div class="form-outline form-white mb-4">
                        <label class="form-label" for="umidita_min">Umidità minima:</label>
                        <input  style=" background-color: rgb(208, 213, 218); "type="text" class="form-control border-secondary" id="umidita_min" name="umidita_min" required><br>
                    </div>
                </div>

                <div class="col-md-5">
                    <div class="form-outline form-white mb-4">
                        <label class="form-label" for="umidita_max">Umidità massima:</label>
                        <input style=" background-color: rgb(208, 213, 218);" type="text" class="form-control border-secondary" id="umidita_max" name="umidita_max" required><br>
                    </div>
                </div>
                <div class="col-md-3 justify-content-center">
                    <div class="form-outline form-white mb-4">
                        <label class="form-label" for="immagine">Immagine:</label>
                        <input  type="file" class="form-control" id="immagine" name="immagine"><br>
                    </div>
                </div>

            <input type="hidden" id="azienda" name="azienda" value="<%=user.getEmail()%>">

            </div>
            <div class="row justify-content-center">
                <div class="col-2 py-5">
                    <input type="submit" value="Inserisci Pianta" class="btn btn-outline-success btn-lg px-5" name="inserisciPianta_submit">
                </div>
            </div>

        </div>
    </div>
</form>

<form action="Piante.jsp">
    <input type="submit" value="bottone">
</form>

<%}%>
<%@include file="/fragments/footer.html" %>

</body>
</html>
