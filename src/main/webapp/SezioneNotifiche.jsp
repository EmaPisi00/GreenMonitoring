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
    <%
        /* -- INIZIO AUTENTICAZIONE --*/
        UtenteBean seo = (UtenteBean) session.getAttribute("currentUserSession");
        NotificaDAO n = new NotificaDAOImpl();
        List<NotificaBean> listaNotifiche = null;
        if (seo instanceof AziendaBean) {%>
    <%@include file="/fragments/headerLoggedAzienda.jsp" %>
           <% listaNotifiche = n.retriveNotifichePerAzienda(seo.getEmail());%>
        <%} else if (seo instanceof DipendenteBean) {%>
    <%@include file="/fragments/headerLoggedDipendente.jsp" %>
            <%listaNotifiche = n.retriveNotifichePerDipendente(seo.getEmail());
        } else {
            response.sendRedirect("error.jsp");
        }
            %>

</head>
<body>
<div class="container mt-5">
    <h5 class="display-3 text-center">Sezione Notifiche</h5>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Numero</th>
            <th>Coltivazione</th>
            <th>Tipo di Notifica</th>
            <th>Data e ora</th>
        </tr>
        </thead>
        <tbody>
        <%
            int i=1;
            for (NotificaBean notifica : listaNotifiche) {
                out.print("<tr style=\"cursor: pointer;" +
                        (!notifica.getVisualizzazioneNotifica() ? "background-color: red;" : "") +
                        "\" onclick='showModal(\""+notifica.getId() +"\",\""+ notifica.getTipo() + "\",\""+ notifica.getData() + "\",\""+notifica.getContenuto() + "\",\""+ notifica.getColtivazioneID() +"\")'>" +
                        "<td>" + i + "</td>" +
                        "<td>" + notifica.getColtivazioneID() + "</td>" +
                        "<td>" + notifica.getTipo() + "</td>" +
                        "<td>" + notifica.getData() + "</td>" +
                        "</tr>"
                );
                i++;
            }
        %>
        </tbody>
    </table>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" >
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Dettagli notifica</h4>
            </div>
            <!-- Modal body -->
            <div class="card text-center modal-body">
                <div class="card-header">
                    <h5><span id="notifica-titolo"></span></h5>
                </div>
                <div class="card-body">
                    <p><span id="notifica-contenuto"></span></p>
                    <button type="button" class="btn btn-primary" id="coltivazioneBtn" value="">
                        <span id="notifica-coltivazione"></span>
                    </button>

                </div>
                <div class="card-footer text-muted">
                    <p>Data: <span id="notifica-data"></span></p>
                </div>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger"  onclick="closeModal()">Chiudi</button>
            </div>
        </div>
    </div>
</div>

<!-- End Example Code -->

<script>
    var idN;
    function showModal(idNotifica,notifica, data, contenuto,coltivazione) {

        idN= idNotifica
        // Imposta il titolo della notifica
        document.getElementById("notifica-titolo").innerText = notifica;

        // Imposta il contenuto della notifica
        document.getElementById("notifica-contenuto").innerText = contenuto;

        //coltivazioneid
        document.getElementById("notifica-coltivazione").innerText = coltivazione;

        // Imposta la data della notifica
        document.getElementById("notifica-data").innerText = data ;

        // Mostra il modal
        $("#myModal").modal("show");

        // Nasconde l'area delle notifiche
        document.getElementById("notificationArea").style.display = "none";

    }
    document.getElementById("coltivazioneBtn").addEventListener("click", function() {
        var coltivazioneId = document.getElementById("notifica-coltivazione").innerText;
        var url = "ColtivazioniServlet?coltivazione=" + coltivazioneId;
        window.location.href = url;
    });

    window.onbeforeunload = function () {
        // Salva la posizione dello scroll nella memoria di sessione
        var scrollPosition = window.scrollY;
        sessionStorage.setItem('scrollPosition', scrollPosition);
    }

    window.onload = function () {
        // Ottiene la posizione dello scroll salvata nella memoria di sessione
        var scrollPosition = sessionStorage.getItem('scrollPosition');

        // Se c'è una posizione salvata, reimposta la posizione dello scroll
        if (scrollPosition !== null) {
            window.scrollBy(0, scrollPosition);
            // Cancella la posizione salvata dalla memoria di sessione
            sessionStorage.removeItem('scrollPosition');
        }
    }

    function closeModal() {
        $("#myModal").modal("hide");
        var url = "NotificaServlet?aggiorna=true&idNotifica=" + idN;
        window.location.href = url;


    }
</script>
<%@include file="fragments/footer.html"%>
</body>
</html>