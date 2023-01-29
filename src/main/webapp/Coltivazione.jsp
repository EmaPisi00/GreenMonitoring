<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Coltivazione</title>
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
<%  UtenteBean ut = (UtenteBean) session.getAttribute("currentUserSession");
    if (!(ut instanceof AziendaBean) || ut == null)  { %>
<% response.sendRedirect("error.jsp"); %>
<% } else{  %>
<%@include file="fragments/headerLogged.html"%>
<%}%>
<div class="bd">
    <legend style="text-align:center;"></legend>
    <!-- Coltivazioni -->
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Info coltivazione</h5>

            <%
                /* -- INIZIO AUTENTICAZIONE -- */
                Object sa = session.getAttribute("currentUserSession");
                if (sa == null) {
                    response.sendError(401);
                } else if ( ! (session.getAttribute("currentUserSession") instanceof UtenteBean)) {
                    response.sendError(401);
                }
                /* -- PASSATI I TEST, IL CONTAINER APRE IL RESTO DELLA PAGINA -- */
                else {
                    List<ColtivazioneBean> list = null;
                    List<SensoreBean> sList = null;
                    ColtivazioneManager cm = new ColtivazioneManager();
                    SensoreManager sm = new SensoreManager();
                    if ((session.getAttribute("currentUserSession") instanceof DipendenteBean)) {
                        DipendenteBean a = (DipendenteBean) sa;
                        Integer coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
                        sList = sm.visualizzaListaSensori(a.getAzienda()).stream().filter(o -> o.getColtivazione() == coltivazioneID).toList();
                        list = cm.visualizzaStatoColtivazioni(a.getAzienda());
                    } else {
                        AziendaBean a = (AziendaBean) sa;
                        list = cm.visualizzaStatoColtivazioni(a.getEmail());
                        Integer coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
                        sList = sm.visualizzaListaSensori(a.getEmail()).stream().filter(o -> o.getColtivazione() == coltivazioneID).toList();
                    }
                    int[] valoriMisurati = cm.visualizzaMediaSensori((String) session.getAttribute("coltivazioneID"));
                    out.print("<ul><li class=\"list-group-item \">" +
                            "Coltivazione " + session.getAttribute("coltivazioneID") + "<br>" +
                            "<h7>media pH</h7> " + valoriMisurati[0] + "<br>" +
                            "<h7>media temperatura</h7> " + valoriMisurati[1] + "<br>" +
                            "<h7>media umidità</h7> " + valoriMisurati[2] + "<br>" +
                            "</li></ul>"
                    );
                    //session.removeAttribute("coltivazioneID");

                    out.print("<ul class=\"list-group\">\n");
                    if ((session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                        out.print("<form id=\"rimuoviSensore\" action=\"ServletColtivazione\" method=\"post\">");
                    }

                    out.print("<li class=\"list-group-item-success\" aria-current=\"true\">Sensori</li>\n");
                    if (sList != null) {
                        for (int i = 0; i < sList.size(); i++) {
                            out.print("<li class=\"list-group-item\" name=\"sensoreDaRimuovere\" value=\"" + sList.get(i).getId() + "\">Sensore " + sList.get(i).getTipo() + " " + sList.get(i).getId());
                            if ((session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                                out.print("<a></a><button type=\"button\" class=\"btn btn-link\" onclick=\"$(#rimuoviSensore).submit()\">Rimuovi</button>");
                            }
                        }
                        if ((session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                            out.print("</form><br>");
                            out.print("<button type=\"button\" class=\"btn btn-light\" href=\"./InserisciSensore\">Aggiungi sensore +</button>");
                        }
                        out.print("</ul>");
                    }
                }
            %>
        </div>
    </div>
    <!-- Fine coltivazioni -->
</div>
</body>
</html>
