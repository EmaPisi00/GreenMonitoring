<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.NotificaDAO" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.NotificaDAOImpl" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.NotificaBean" %>
<%@ page import="java.util.List" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%@ page import="java.util.ArrayList" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css" rel="stylesheet">
    <title>Bootstrap Example</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    <style>
    .cursore:hover {
        background-color: lightgray;
    }
     img {
         height: 25px;
         width: auto;
         object-fit: contain;
     }

</style>
    <%
        /* -- INIZIO AUTENTICAZIONE --*/
        UtenteBean seo = (UtenteBean) session.getAttribute("currentUserSession");
        NotificaDAO n = new NotificaDAOImpl();
        List<NotificaBean> listaNotifiche=null;
        if (seo instanceof AziendaBean) {
            listaNotifiche = n.retriveNotifichePerAzienda(seo.getEmail());
        } else if (seo instanceof DipendenteBean) {
            listaNotifiche = n.retriveNotifichePerDipendente(seo.getEmail());
        } else {
            response.sendRedirect("error.jsp");
        }

        int numNotifiche = listaNotifiche.size(); // Recupera il numero di notifiche dal database o da un'altra fonte
    %>
    <tiitle>Notifiche</tiitle>
</head>

<body class="p-3 m-0 border-0 bd-example bg-light">

<!-- bottone notifica Code -->
<button type="button" class="btn btn-primary position-relative" id="notificationButton">
    Inbox
    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger" id="notificationBadge">
    <%=numNotifiche%>
    <span class="visually-hidden">unread messages</span>
  </span>
</button>

<div class="toast-container">
    <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" id="notificationArea" style="display: none;">
    <% int i=0;
    List<NotificaBean> listaDaVisualizzare= new ArrayList<>();
    for (NotificaBean not: listaNotifiche) {
        listaDaVisualizzare.add(not);
    }
        for (NotificaBean notifica : listaDaVisualizzare ) {
            System.out.println(notifica);
            if(i<4) {

                out.print(
                        "" +
                                " <div class=\"toast-header mt-3\">" +
                                "      <img src=\"img/logo.png\" class=\"rounded me-2\" alt=\"...\">" +
                                "      <strong class=\"me-auto\">"+ notifica.getTipo()+"</strong>" +
                                "      <small class=\"text-muted\">" +notifica.getData() + "</small>" +
                                "      <button class=\"m-lg-1\"onclick='showModal(\""+notifica.getId() +"\",\""+ notifica.getTipo() + "\",\""+ notifica.getData() + "\",\""+notifica.getContenuto() + "\",\""+ notifica.getColtivazioneID() +"\")'>Leggi</button>" +
                                "    </div>"
                              );
                i++;
            }
    %>
    <%}
        out.print(
                "    <div class=\"d-flex cursore\" style=\'cursor: pointer;\' onclick=\'location.href=\"SezioneNotifiche.jsp\"\'>" +
                        "        <div class=\"toast-body\">" + "Tutti i messaggi" +
                        "        </div>" +
                        "</div>");

    %>
    </div>
</div>


<!-- Modal HTML -->
<!-- Modal HTML -->
<!-- Modal -->
<div class="modal fade" id="myModal">
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
                    <p id="notifica-contenuto"> </p>
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
                <button type="button" class="btn btn-danger" data-dismiss="myModal">Chiudi</button>
            </div>
        </div>
    </div>
</div>

<!-- End Example Code -->

<script>
    document.getElementById("notificationButton").addEventListener("click", function() {
        var notificationArea = document.getElementById("notificationArea");
        if (notificationArea.style.display === "none") {
            notificationArea.style.display = "block";
        } else {
            notificationArea.style.display = "none";
        }
    });
        function showModal(idNotifica,notifica, data, contenuto,coltivazione) {
        // Imposta il titolo della notifica
        document.getElementById("notifica-titolo").innerText = notifica;

        // Imposta il contenuto della notifica
        document.getElementById("notifica-contenuto").innerText = contenuto + "\nappartenente alla coltivazione ";

        //coltivazioneid
        document.getElementById("notifica-coltivazione").innerText = coltivazione;

        // Imposta la data della notifica
        document.getElementById("notifica-data").innerText = data ;

        // Mostra il modal
        $("#myModal").modal("show");

        // Nasconde l'area delle notifiche
        document.getElementById("notificationArea").style.display = "none";
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var numNotifiche = xhr.responseText;
                    document.getElementById("notificationBadge").innerHTML = numNotifiche;
                }
            };
            xhr.open("GET", "ServletNotifica?numNotifiche=true&idNotifica=" + idNotifica, true);
            xhr.send();

    }
    document.getElementById("coltivazioneBtn").addEventListener("click", function() {
        var coltivazioneId = document.getElementById("notifica-coltivazione").innerText;
        var url = "ServletColtivazioni?coltivazione=" + coltivazioneId;
        window.location.href = url;
    });
</script>

</body>
</html>


