<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
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
PiantaBean pianta = (PiantaBean) request.getAttribute("piantaModificare");
String errori = (String)request.getAttribute("erroreDatiPianta");
session.setAttribute("pianta",pianta);
    if (!(u instanceof AziendaBean))  { %>
<% response.sendRedirect("error.jsp"); %>
<% } else{%>

<%@ include file="/fragments/headerLoggedAzienda.html" %>
<%}%>

<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Modifica Pianta</h4>
                </div>

                <div class="col-md-3 border-right"></div>
                <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                    <img class="rounded-circle mt-5" width="150px" src="<%= request.getContextPath() %>/../immagini/piante/<%= pianta.getImmagine() %>" alt="Descrizione immagine">
                    <span class="font-weight-bold">GreenMonitoring</span>
                </div>
                <% if (errori != null) { %>
                <div class="text-danger"> <%= errori%> </div>
                <% } %>
                <form id="ModificaRange" action="ServletPianta" method="post">
                    <input type="text" id="idPianta" name="idPianta" value="<%= pianta.getId()%>"  style="display: none">
                    <div class="row mt-2">
                        <label>Azienda</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"  readonly id="azienda" name="azienda" value="<%= pianta.getAzienda() %>"  placeholder="null">
                        </div>

                        <label>nome</label>
                        <div class="form-floating mb-3">
                            <input type="tex" class="form-control" id="nome" readonly value="<%= pianta.getNome()%>" name="nome" placeholder="null">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <label>descrizione</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"  value="<%=pianta.getDescrizione() %>"  readonly id="descrizione" name="descrizione"  placeholder="null">
                        </div>
                        <label>ph_min</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"  id="ph_min" name="ph_min"  placeholder="null">
                            <label for="ph_min"><%= pianta.getPh_min() %></label>
                        </div>
                        <label>ph_max</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control" id="ph_max" name="ph_max"  placeholder="null">
                            <label for="ph_max"><%= pianta.getPh_max() %></label>
                        </div>
                        <label>temperatura_min</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"  id="temperatura_min" name="temperatura_min"  placeholder="null">
                            <label for="temperatura_min"><%= pianta.getTemperatura_min()%></label>
                        </div>
                    </div>

                    <div class="row">
                        <label>temperatura_max</label>
                        <div class="form-floating col mb-3 ">
                            <input type="text" class="form-control" id="temperatura_max" name="temperatura_max"  placeholder="null">
                            <label for="temperatura_max"><%= pianta.getTemperatura_max()  %></label>
                        </div>
                        <label>umidita_min</label>
                        <div class="form-floating col mb-3 ">
                            <input type="text" class="form-control"  id="umidita_min" name="umidita_min"  placeholder="null">
                            <label for="umidita_min"><%= pianta.getUmidita_min()%></label>
                        </div>
                    </div>
                    <div class="row">
                        <label>umidita_max</label>
                        <div class="form-floating col mb-3 ">
                            <input type="text" class="form-control" id="umidita_max" name="umidita_max"  placeholder="null">
                            <label for="umidita_max"><%= pianta.getUmidita_max()  %></label>
                        </div>
                    </div>


                    <div class="mt-5 text-center">
                        <button class="btn btn-primary profile-button" value="modificaPianta" name="modificaPianta" type="submit">Save modifica</button>
                    </div>

            </form>
        </div>
    </div>
</div>
</div>


<%@include file="/fragments/footer.html"%>
</body>
</html>
