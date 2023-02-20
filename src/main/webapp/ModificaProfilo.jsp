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
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

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

    <style>
        .btn-green {
            color: rgb(255, 255, 255);
            background-color: darkseagreen;
            border: 2px solid rgb(255, 255, 255);
            border-radius: 10px;
            font-size: 26px;
            font-family: 'Anton', sans-serif;
        }
        .btn-green:hover{
            color: darkseagreen;
            background-color: rgb(255, 255, 255);
        }
        .btn-reed {
            color: rgb(255, 255, 255);
            background-color: #ec084c;
            border: 2px solid rgb(255, 255, 255);
            border-radius: 10px;
            font-size: 26px;
            font-family: 'Anton', sans-serif;
        }
        .btn-reed:hover{
            color: #ec084c;;
            background-color: rgb(255, 255, 255);
        }
    </style>
</head>
<title>Modifica Profilo</title>
<% UtenteBean user= (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if (user == null)  {
    response.sendRedirect("error.jsp");
} else if(user instanceof AziendaBean){ %>
<%@ include file="/fragments/headerLoggedAzienda.html" %>
<%} else if (user instanceof DipendenteBean){ %>
<%@ include file="/fragments/headerLoggedDipendente.html" %>
   <% }%>
<body>
<% if (request.getAttribute("conferma")!=null) {%>
<div class="alert alert-success">
    <h3>Confermato</h3>
    <p><%=request.getAttribute("descrizione")%></p>
</div>
<%}%>
<% if (request.getAttribute("errore")!=null) {%>
<div class="alert alert-danger">
    <h3>Errore</h3>
    <p><%=request.getAttribute("descrizione")%></p>
</div>
<%}%>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="img/logo.png"><span class="font-weight-bold">GreenMonitoring</span></div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Modifica Profilo</h4>
                </div>
                <form id="GestioneProfilo" action="ModificaProfiloServlet" method="post">
                    <div class="row mt-2">
                        <label class="text-center">Email</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control border-2"   id="email" name="email"  placeholder="null">
                            <label class="px-3" for="email"><%= user.getEmail() %></label>
                        </div>

                        <label class="text-center">Password</label>
                        <div class="form-floating mb-3">
                            <input type="tex" class="form-control border-2" id="password" name="password" placeholder="null">
                            <label class="px-3" for="password"> <%= user.getPassword()%> </label>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <label class="text-center">Telefono</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control border-2"   id="telefono" name="telefono"  placeholder="null">
                            <label class="px-3" for="telefono"><%= user.getTelefono() %></label>
                        </div>
                        <label class="text-center">Citta'</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control border-2"  id="citta" name="citta"  placeholder="null">
                            <label class="px-3" for="citta"><%= user.getCitta() %></label>
                        </div>
                        <label class="text-center">Provincia</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control border-2" id="provincia" name="provincia"  placeholder="null">
                            <label class="px-3" for="provincia"><%= user.getProvincia() %></label>
                        </div>
                        <label class="text-center" >Indirizzo</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control border-2"  id="indirizzo" name="indirizzo"  placeholder="null">
                            <label class="px-3" for="indirizzo"><%= user.getIndirizzo() %></label>
                        </div>
                    </div>

                        <%  if (user instanceof DipendenteBean)  {%>
                    <div class="row">
                        <label class="text-center">Nome</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control border-2" id="nome" name="nome"  placeholder="null">
                            <label  class="px-3" for="nome"><%= ((DipendenteBean) user).getNome() %></label>
                        </div>
                        <label class="text-center">Cognome</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control border-2"  id="cognome" name="cognome"  placeholder="null">
                            <label class="px-3" for="cognome"><%= ((DipendenteBean) user).getCognome() %></label>
                        </div>
                            <input type="hidden" class="form-control"  id="Nome_aziendaDipendente" name="Nome_aziendaDipendente" value="<%= ((DipendenteBean) user).getAzienda() %>"  placeholder="null">
                    </div>
                        <% } else if (user instanceof AziendaBean) { %>
                    <div class="row">
                        <label class="text-center">Nome Azienda</label>
                        <div class="form-floating col mb-3 ">
                            <input type="text" class="form-control border-2" id="Nome_azienda" name="Nome_azienda"  placeholder="null">
                            <label class="px-3" for="Nome_azienda"><%= ((AziendaBean) user).getNome_azienda()  %></label>
                        </div>
                        <label class="text-center">Partita Iva</label>
                        <div class="form-floating col mb-3 ">
                            <input type="text" class="form-control border-2"  id="partita_iva" name="partita_iva"  placeholder="null">
                            <label class="px-3" for="partita_iva"><%= ((AziendaBean) user).getPartita_iva() %></label>
                        </div>
                    </div>
                        <% } %>
                    <button class="btn-green" type="submit" id="save-profile-btn">Save Profile</button>
            </div>
            </form>
        </div>
    </div>
</div>
</div>

<!--MODAL-->
<div class="modal" id="confirm-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Conferma operazione</h5>
            </div>
            <div class="modal-body">
                Sei sicuro di voler salvare il profilo?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-reed" onclick="closeModal()">Annulla</button>
                <button type="button" class="btn-green" id="confirm-save-btn">Conferma</button>
            </div>
        </div>
    </div>
</div>


<script>
    $(document).ready(function() {
        $('#save-profile-btn').click(function(e) {
            e.preventDefault(); // previene l'invio del form
            $('#confirm-modal').modal('show'); // mostra il modal di conferma
        });

        $('#confirm-save-btn').click(function() {
            $('#confirm-modal').modal('hide'); // nasconde il modal di conferma
            $('#GestioneProfilo').submit(); // invia i dati al server
        });
    });

    function closeModal() {
        $("#confirm-modal").modal("hide");
    }

</script>
<%@include file="fragments/footer.html"%>
</body>
</html>
