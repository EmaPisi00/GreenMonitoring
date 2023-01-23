<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.TerrenoBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="java.lang.reflect.AnnotatedArrayType" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.autenticazione.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean" %><%--
  Created by IntelliJ IDEA.
  User: Nicola
  Date: 16/01/2023
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Coltivazioni</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="bd">
    <legend style="text-align:center;">Coltivazioni</legend>
</div>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Coltivazioni</h5>
                    <!-- List group with custom content -->
                    <ul class="list-group list-group-numbered">
                <%
                    /* -- INIZIO AUTENTICAZIONE -- */
                    Object seo = session.getAttribute("currentUserSession");
                    if (seo == null) {
                        response.sendError(401);
                    } else if ( ! (session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                        response.sendError(401);
                    }
                    /* -- PASSATI I TEST, IL CONTAINER APRE IL RESTO DELLA PAGINA -- */
                    else {
                        AziendaBean a = (AziendaBean) seo;

                        ColtivazioneManager cm = new ColtivazioneManager();
                        List<ColtivazioneBean> list = cm.visualizzaStatoColtivazioni( a.getEmail() );

                        for (ColtivazioneBean cb : list) {
                            if (cb.getStato_archiviazione() == 0){
                                out.print("<li class=\"list-group-item d-flex justify-content-between align-items-start disabled\">" +
                                        "<div class=\"ms-2 me-auto\">\n" +
                                        "<div class=\"fw-bold\">Coltivazione " +  cb.getId() + "</div>"+
                                        cb.getTerreno() +
                                        "<br>(Archiviata)</div>"+
                                        "</li>"
                                );
                            } else {
                                out.print("<li class=\"list-group-item d-flex justify-content-between align-items-start\">" +
                                        "<div class=\"ms-2 me-auto\">\n" +
                                        "<div class=\"fw-bold\">Coltivazione " + cb.getId() + "</div>"+
                                        cb.getTerreno() +
                                        "</div>"+
                                        "</li>"
                                );
                            }

                        }
                    }
                %>
                    </ul><!-- End with custom content -->
        </div>
    </div>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <form action="" method="post" id="aggiungi_coltivazione">
                <label>Inserire l'id (#) del terreno a cui associare la coltivazione</label><br>
                <input type="text" name="terreno" required><br>
                <label>Inserire il nome della pianta da inserire</label><br>
                <input type="text" class="disabled" ><br><br>
                <button onclick="$('#aggiungi_coltivazione').submit()" type="button" class="btn btn-primary">
                    Aggiungi coltivazione
                </button>
            </form>
        </div>
    </div>
</body>
</html>
