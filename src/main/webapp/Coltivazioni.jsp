<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.lang.reflect.AnnotatedArrayType" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.autenticazione.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %><%--
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
    <!-- <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet"> -->
    <!-- link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet"> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
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
                    } else if ( ! (session.getAttribute("currentUserSession") instanceof UtenteBean)) {
                        response.sendError(401);
                    }
                    /* -- PASSATI I TEST, IL CONTAINER APRE IL RESTO DELLA PAGINA -- */
                    else {
                        List<ColtivazioneBean> list = null;

                        if ( (session.getAttribute("currentUserSession") instanceof DipendenteBean)) {
                            DipendenteBean a = (DipendenteBean) sa;
                            ColtivazioneManager cm = new ColtivazioneManager();
                            list = cm.visualizzaStatoColtivazioni( a.getAzienda() );
                        } else {
                            AziendaBean a = (AziendaBean) sa;
                            ColtivazioneManager cm = new ColtivazioneManager();
                            list = cm.visualizzaStatoColtivazioni( a.getEmail() );
                        }

                        out.print("<ul class=\"list-group\">");
                        for (ColtivazioneBean cb : list) {
                            System.out.println("sono nel for");
                            if (cb.getStato_archiviazione() == 0){
                                System.out.println("archiviata");
                                out.print("<li class=\"list-group-item disabled\">" +
                                         "Coltivazione " +  cb.getId() +
                                        "<br>Terreno associato: " + cb.getTerreno() +
                                        "   (Archiviata)</li>"
                                );
                            } else {
                                System.out.println("non archiviata");
                                out.print("<li class=\"list-group-item \">" +
                                        "Coltivazione " +  "pianta"+cb.getPianta() +
                                        "<br>id terreno associato: " + cb.getTerreno() +
                                        "</li>"
                                );
                            }

                        }
                        if ((session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                        out.print("</ul>");
                        out.print("        </div>\n" +
                                "    </div>\n" +
                                "    <!-- Fine coltivazioni -->");
                        out.print("    <!-- Inserisci coltivazione -->\n" +
                                "    <div class=\"card\" style=\"width: 30rem;\">\n" +
                                "        <div class=\"card-body\">\n" +
                                "<h5 class=\"card-title\">Modulo inserimento coltivazione</h5>" +
                                "            <form action=\"\" method=\"post\" id=\"aggiungi_coltivazione\">\n" +
                                "                <label>Inserire l'id (#) del terreno a cui associare la coltivazione</label><br>\n" +
                                "                <input type=\"text\" name=\"terreno\" required><br>\n" +
                                "                <label>Inserire il nome della pianta da inserire</label><br>\n" +
                                "                <input type=\"text\" class=\"disabled\" required><br>\n" +
                                "                <label>Inserire il codice del sensore da inserire e selezionare il tipo di sensore</label><br>\n" +
                                "                <input type=\"text\" class=\"disabled\" required>\n" +
                                "                <select name=\"sensore\" id=\"sensore_tipo\">\n" +
                                "                    <option value=\"pH\">Temperatura</option>\n" +
                                "                    <option value=\"temperatura\">pH</option>\n" +
                                "                    <option value=\"umidità\">Umidità</option>\n" +
                                "                </select><br><br>\n" +
                                "                <button type=\"submit\" class=\"btn btn-primary\">\n" +
                                "                    Aggiungi coltivazione\n" +
                                "                </button>\n" +
                                "            </form>\n" +
                                "        </div>\n" +
                                "    </div>\n" +
                                "    <!-- Fine inserisci coltivazione --> </div>");
                        }
                    }
                %>
</body>
</html>
