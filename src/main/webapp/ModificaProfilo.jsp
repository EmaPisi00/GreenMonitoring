<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %><%--
  Created by IntelliJ IDEA.
  User: stefa
  Date: 21/01/2023
  Time: 18:30
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
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <link href="bootstrap-5.2.3-dist/css/testCSS.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<% UtenteBean user= (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if (user == null)  { %>
<%@include file="/fragments/headerLogin.html" %>
<%} else{ %>
<%@ include file="/fragments/headerLoggedAzienda.html" %>
<%}%>
<body>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="C:\Users\stefa\IdeaProjects\GreenMonitoring\src\main\webapp\img\Logo.png"><span class="font-weight-bold">GreenMonitoring</span></div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Modifica Profilo</h4>
                </div>
                <form id="GestioneProfilo" action="ServletGestioneProfilo" method="post">
                    <div class="row mt-2">
                        <label>email</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"   id="email" name="email"  placeholder="null">
                            <label for="email"><%= user.getEmail() %></label>
                        </div>

                        <label>password</label>
                        <div class="form-floating mb-3">
                            <input type="tex" class="form-control" id="password" name="password" placeholder="null">
                            <label for="password"> <%= user.getPassword()%> </label>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <label>telefono</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"   id="telefono" name="telefono"  placeholder="null">
                            <label for="telefono"><%= user.getTelefono() %></label>
                        </div>
                        <label>citta</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"  id="citta" name="citta"  placeholder="null">
                            <label for="citta"><%= user.getCitta() %></label>
                        </div>
                        <label>provincia</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control" id="provincia" name="provincia"  placeholder="null">
                            <label for="provincia"><%= user.getProvincia() %></label>
                        </div>
                        <label>indirizzo</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"  id="indirizzo" name="indirizzo"  placeholder="null">
                            <label for="indirizzo"><%= user.getIndirizzo() %></label>
                        </div>
                    </div>

                        <%  if (user instanceof DipendenteBean)  {%>
                    <div class="row">
                        <label>nome</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control" id="nome" name="nome"  placeholder="null">
                            <label for="nome"><%= ((DipendenteBean) user).getNome() %></label>
                        </div>
                        <label>cognome</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"  id="cognome" name="cognome"  placeholder="null">
                            <label for="cognome"><%= ((DipendenteBean) user).getCognome() %></label>
                        </div>
                        <label>Nome_aziendaDipendente</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"   id="Nome_aziendaDipendente" name="Nome_aziendaDipendente"  placeholder="null">
                            <label for="Nome_aziendaDipendente"><%= ((DipendenteBean) user).getAzienda() %></label>
                        </div>
                    </div>
                        <% } else if (user instanceof AziendaBean) { %>
                    <div class="row">
                        <label>Nome_azienda</label>
                        <div class="form-floating col mb-3 ">
                            <input type="text" class="form-control" id="Nome_azienda" name="Nome_azienda"  placeholder="null">
                            <label for="Nome_azienda"><%= ((AziendaBean) user).getNome_azienda()  %></label>
                        </div>
                        <label>partita_iva</label>
                        <div class="form-floating col mb-3 ">
                            <input type="text" class="form-control"  id="partita_iva" name="partita_iva"  placeholder="null">
                            <label for="partita_iva"><%= ((AziendaBean) user).getPartita_iva() %></label>
                        </div>
                    </div>
                        <% } %>
                    <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="submit">Save Profile</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
</div>


<%@include file="fragments/footer.html"%>
</body>
</html>
