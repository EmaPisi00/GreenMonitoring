<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Date" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


    <title>Coltivazione</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <script src="./canvas/canvasjs.min.js" >
    </script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

</head>
<body>

<%@include file="fragments/headerLoggedAzienda.html"%>

<div class="bd">
    <legend style="text-align:center;">Coltivazioni</legend>
    <!-- Coltivazioni -->
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Coltivazioni</h5>

                <%
                    /* -- INIZIO AUTENTICAZIONE -- */
                    Object sa = session.getAttribute("currentUserSession");
                    if (sa == null) {
                        response.sendError(401);
                    } else if (!(session.getAttribute("currentUserSession") instanceof UtenteBean)) {
                        response.sendError(401);
                    }
                    /* -- PASSATI I TEST, IL CONTAINER APRE IL RESTO DELLA PAGINA -- */
                    else {
                        List<ColtivazioneBean> list = null;

                        if ((session.getAttribute("currentUserSession") instanceof DipendenteBean)) {
                            DipendenteBean a = (DipendenteBean) sa;
                            ColtivazioneManager cm = new ColtivazioneManager();
                            list = cm.visualizzaStatoColtivazioni(a.getAzienda());
                        } else {
                            AziendaBean a = (AziendaBean) sa;
                            ColtivazioneManager cm = new ColtivazioneManager();
                            list = cm.visualizzaStatoColtivazioni(a.getEmail());
                        }
                        if (list.size() == 0){
                            out.print("<h7>Non ci sono coltivazioni.</h7>");
                        } else {
                            out.print("<ul class=\"list-group\">");
                            for (ColtivazioneBean cb : list) {
                                if (cb.getStato_archiviazione() == 1) {
                                    out.print("<li class=\"list-group-item disabled\">" +
                                            "Coltivazione " + cb.getId() +
                                            "<br>Terreno associato: " + cb.getTerreno() +
                                            "   (Archiviata)</li>"
                                    );
                                } else {
                                    out.print("<li class=\"list-group-item \">" +
                                            "Coltivazione " + cb.getId() +
                                            "<br>id terreno associato: " + cb.getTerreno() +
                                            "<form action=\"ServletColtivazioni\" method=\"get\">\n" +
                                            "<input type=\"hidden\" name=\"coltivazione\" value=\"" + cb.getId() + "\">" +
                                            "<button type=\"submit\" class=\"btn btn-success\" >Visualizza stato</button>"
                                            + "</form>" +
                                            "</li>"
                                    );
                                }

                            }
                        }
                        /* Stampa il form per inserire la coltivazione solo se ad accedere alla pagina è un'azienda */
                        if ((session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                        AziendaBean ab = (AziendaBean) session.getAttribute("currentUserSession");
                        out.print("</ul>");
                        out.print("        </div>\n" +
                                "    </div>\n" +
                                "    <!-- Fine coltivazioni -->");
                        out.print("    <!-- Inserisci coltivazione -->\n" +
                                "    <div class=\"card\" style=\"width: 30rem;\">\n" +
                                "        <div class=\"card-body\">\n" +
                                "<h5 class=\"card-title\">Modulo inserimento coltivazione</h5>");
                        if (session.getAttribute("errore") != null){
                                out.print("<div id=\"alert\" class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">" +
                                        "<i class=\"bi bi-exclamation-triangle me-1\"> " + session.getAttribute("errore") + "</i>" +
                                        "</div>");
                                session.removeAttribute("errore");
                        }
                        out.print("<div id=\"alrt\" class=\"alert alert-warning fade show\" role=\"alert\">" +
                                "   <i class=\"bi bi-exclamation-triangle me-1\"> Selezionare almeno un sensore.</i>" +
                                "</div>");
                            out.print("          <form action=\"ServletColtivazioni\" method=\"post\" id=\"aggiungi_coltivazione\">\n" +
                                "                <input type=\"hidden\" name=\"moduloInserimentoColtivazione\" required><br>\n" +
                                "                <label>Scegliere il terreno di cui avviare una coltivazione</label><br>");

                                }%>
                            </div>

                            <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
                                <% if (session.getAttribute("currentUserSession") instanceof UtenteBean) {
                                    ColtivazioneManager cm = new ColtivazioneManager();
                                    UtenteBean ub = (UtenteBean) session.getAttribute("currentUserSession");
                                    ColtivazioneBean cb = cm.retrieveColtivazioneSingola((int) session.getAttribute("coltivazioneID"));
                                    ArrayList<MisurazioneSensoreBean> misurazioneSensoreBeanArrayList = cb.getListaMisurazioni();
                                    List<Date> date = misurazioneSensoreBeanArrayList.stream().map(o -> (Date) o.getData()).toList();
                                    out.print("<ul>");
                                    for (int i = date.size()-1; i < date.size()-5; i--) { //parto dal fondo così sicuro prendo le misurazioni più recenti.
                                        if (misurazioneSensoreBeanArrayList.get(i).getTipo().toLowerCase().contains("temp")) {
                                            if (misurazioneSensoreBeanArrayList.get(i).getData().after(misurazioneSensoreBeanArrayList.get(i-1).getData())) {
                                                if (misurazioneSensoreBeanArrayList.get(i).getOra().after(misurazioneSensoreBeanArrayList.get(i-1).getData())) {
                                                    out.print("<li class=\"list-group-item \"> Misurazione in data : " + misurazioneSensoreBeanArrayList.get(i).getData() +
                                                            "risultato : " + misurazioneSensoreBeanArrayList.get(i).getValore() + "</li>");
                                                } else {
                                                    out.print("<li class=\"list-group-item \"> Misurazione in data : " + misurazioneSensoreBeanArrayList.get(i-1).getData() +
                                                            "risultato : " + misurazioneSensoreBeanArrayList.get(i-1).getValore() + "</li>");
                                                }
                                            }
                                        }
                                    }
                                    out.print("</ul>");

                                }%>
                            </div>
                            <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">Nessuna fisiopatia</div>
                        </div>
                    </div>
                    <%  out.print("<ul class=\"list-group\">\n");
                        List<ColtivazioneBean> list = null;
                        List<SensoreBean> sList = null;
                        ColtivazioneManager cm = new ColtivazioneManager();
                        SensoreManager sm = new SensoreManager();
                        if ((session.getAttribute("currentUserSession") instanceof DipendenteBean)) {
                            DipendenteBean a = (DipendenteBean) sa;
                            Integer coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
                            sList = sm.visualizzaListaSensori(a.getAzienda()).stream().filter(o -> o.getColtivazione() == coltivazioneID).toList();
                            list = cm.visualizzaStatoColtivazioni(a.getAzienda());
                        } else if (session.getAttribute("currentUserSession") instanceof AziendaBean) {
                            AziendaBean a = (AziendaBean) sa;
                            list = cm.visualizzaStatoColtivazioni(a.getEmail());
                            Integer coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
                            sList = sm.visualizzaListaSensori(a.getEmail()).stream().filter(o -> o.getColtivazione() == coltivazioneID).toList();
                        }
                        if (sList != null) {
                            for (int i = 0; i < sList.size(); i++) {
                                out.print("<form id=\"rimuoviSensore\" action=\"ServletColtivazioni\" method=\"post\">");
                                out.print("<li class=\"list-group-item\" name=\"sensoreDaRimuovere\" value=\"" + sList.get(i).getId() + "\">Sensore " + sList.get(i).getTipo() + " " + sList.get(i).getId());
                                out.print("<input type=\"hidden\" name=\"sensoreDaRimuovere\" value=\"" + sList.get(i).getId() + "\">");
                                if ((session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                                    out.print("<a></a><button type=\"submit\" class=\"btn btn-link\">Rimuovi</button>");
                                    out.print("</form><br>");
                                }
                            }
                            if ((session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                                out.print("<button type=\"button\" class=\"btn btn-light\" onclick=\"window.location.href=\'./InserisciSensore.jsp\'\">Aggiungi sensore +</button>");
                            }
                            out.print("</ul>");
                        }
                    %>
                    <!-- Fine coltivazioni -->
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html"%>
</body>


</html>