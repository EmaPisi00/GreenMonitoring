<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO" %>
<html>
<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>


    <style>
        @media screen and (max-width: 768px) {
            .tohide {
                display: none;
            }
        }
    </style>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/RimuoviDipendente.js"></script>
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>


    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

        <title>Rimuovi Dipendente</title>
</head>

<%
    UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
    if (u instanceof DipendenteBean) {
        response.sendRedirect("error.jsp");
    } else if (u instanceof AziendaBean) { %>
<%@include file="fragments/headerLoggedAzienda.html" %>
<%
    DipendenteDAO d = new DipendenteDAOImpl();
    List<DipendenteBean> dipendenti = d.retrieveAllByCode(u.getEmail());
%>


<body>

<div class="container py-5" style="width: 100%; height: 100%; ">
    <div class="row">
        <div class="col-12 text-center">
            <h3 class="display-3 text-center">Dipendenti</h3>
        </div>
        <div class="col-14 py-5 " style="font-size: 20px;">
            <table class="table table-group-divider">
                <thead>
                <tr style="font-family: 'Staatliches', cursive; font-size: 25px;">
                    <th scope="col">ID</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Cognome</th>
                    <th scope="col">Rimozione</th>
                </tr>
                </thead>
                <% String salvaEmail = "";
                    for (DipendenteBean dipendente : dipendenti) { %>
                <tr class="justify-content-center">
                    <td><%= dipendente.getEmail() %>
                    </td>
                    <td><%= dipendente.getNome() %>
                    </td>
                    <td><%= dipendente.getCognome() %>
                    </td>
                    <td><%salvaEmail = dipendente.getEmail();%>
                        <img src="img/delete.png" width="35px" style="cursor: pointer" data-bs-toggle="modal"
                             data-bs-target="#exampleModal"></td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
</div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Sei sicuro di voler effettuare la rimozione?
            </div>
            <div class="modal-footer">
                <button id="closeModal" class="btn btn-secondary" data-dismiss="modal">Esci</button>
                <button id="summit" class="btn btn-outline-danger"><a style="text-decoration: none; "
                                                                      href="RimuoviAssociazioneServlet?action=delete&email=<%= salvaEmail%>">Conferma</a>
                </button>
            </div>
        </div>
    </div>
</div>

<% } %>


<%@include file="fragments/footer.html" %>
</body>

</html>
