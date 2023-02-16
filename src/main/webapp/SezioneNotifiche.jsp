<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.lang.reflect.AnnotatedArrayType" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.NotificaDAOImpl" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.NotificaDAO" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %><%--
  Created by IntelliJ IDEA.
  User: Manuel
  Date: 23/01/2023
  Time: 16:40
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

    <title>Notifiche</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/TerreniJS.js"></script>
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</head>


<body>
<%
    /* -- INIZIO AUTENTICAZIONE --*/
    UtenteBean seo = (UtenteBean) session.getAttribute("currentUserSession");
    NotificaDAO n = new NotificaDAOImpl();
    if (!(seo instanceof AziendaBean)) {
        response.sendRedirect("error.jsp");
    }

%>
<%@ include file="/fragments/headerLoggedAzienda.html" %>

<% if(seo != null){%>

<%
    List<NotificaBean> listaNotifiche = n.retriveNotifichePerAzienda(seo.getEmail());
%>

<div class="container mt-5">
    <h2>Notifiche</h2>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Numero</th>
            <th>Azienda</th>
            <th>Coltivazione</th>
            <th>Tipo di Notifica</th>
            <th>Data e ora</th>
            <th>Visualizzazione Notifica</th>
        </tr>
        </thead>
        <tbody>
        <%
            int i=1;
            for (NotificaBean notifica : listaNotifiche) {
                out.print("<tr style=\"cursor: pointer;" +
                        (!notifica.getVisualizzazioneNotifica() ? "background-color: red;" : "") +
                        "\" onclick='showModal("+ notifica.getId() + ")' >" +
                        "<td>" + i + "</td>" +
                        "<td>" + notifica.getAziendaID() + "</td>" +
                        "<td>" + notifica.getColtivazioneID() + "</td>" +
                        "<td>" + notifica.getTipo() + "</td>" +
                        "<td>" + notifica.getData() + "</td>" +
                        "<td>" + notifica.getVisualizzazioneNotifica() + "</td>" +
                        "</tr>"
                );
                i++;
            }
        %>
        </tbody>
    </table>
</div>

<!-- Modal HTML -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Modal Title</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                Modal Body
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>

    function showModal(messaggio) {
        $("#myModal .modal-body").text("ID passato: " + messaggio);
        $("#myModal").modal("show");
        document.getElementById("notificationArea").style.display = "none";
    }

</script>
<%}%>
<%@include file="fragments/footer.html"%>
</body>
</html>