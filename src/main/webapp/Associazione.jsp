<!-- Questa Jsp permette ad un dipendente loggato di potersi associare ad un azienda tramite un codice.-->

<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <style>
        @media screen and (max-width: 768px) {
            .tohide {
                display: none;
            }
        }
    </style>
    <title>Associazione Dipendente-Azienda</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/Associazione.js"></script>
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <title>Associazione Azienda</title>
</head>

<body>
<% UtenteBean user = (UtenteBean) session.getAttribute("currentUserSession");
    if (user == null) {
        response.sendRedirect("error.jsp");
    } else if (user instanceof AziendaBean) {
        response.sendRedirect("error.jsp");
    } else if (((DipendenteBean) user).getAzienda() != null) {
        //l'if controlla se l'utente è un dipendente e se è già associato ad un'azienda
        response.sendRedirect("Profile.jsp");
    } else if (((DipendenteBean) user).getAzienda() == null) { %>
<%@include file="fragments/headerLoggedDipendenteNonAssociato.html" %>

<%} %>


<div class="container py-5 border-1">
    <div class="row justify-content-center">
        <div class="col-5 py-5">
            <legend style="font-size: 40px; text-align:center; color: black; ">
                Associazione Azienda
            </legend>
            <form class="row g-4 justify-content-center  py-3" action="ServletAssociazione" id="associa_dipendente"
                  method="post">
                <div class="col-6" style="font-family: 'Lora', serif; ">
                    <div class="form-outline form-white mb-4">
                        <label for="codiceAzienda" class="form-label">Codice Azienda:</label>
                        <input type="text" class="form-control" id="codiceAzienda" placeholder="12345"
                               name="codiceAzienda"
                               required=""/>
                    </div>
                </div>

                <!-- Button trigger modal -->
                <button id="showModal" type="button" class="btn btn-outline-success btn-lg px-3" data-toggle="Modal"
                        data-target="#exampleModalCenter">
                    Associa
                </button>
            </form>

        </div>
    </div>
</div>
<!-- Modal -->
<div id=Modal class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Sei sicuro di voler effettuare l'associazione?
            </div>
            <div class="modal-footer">
                <button id="closeModal" type="button" class="btn btn-secondary" data-dismiss="modal">Esci</button>
                <button id="summit" type="button" class="btn btn-primary">Conferma</button>
            </div>
        </div>
    </div>
</div>

<%@include file="fragments/footer.html" %>
</body>
</html>