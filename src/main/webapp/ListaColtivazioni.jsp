<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.lang.reflect.AnnotatedArrayType" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %><%--
  Created by IntelliJ IDEA.
  User: Nicola
  Date: 16/01/2023
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

    <title>Coltivazioni</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <!-- <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet"> -->
    <!-- link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet"> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<%@include file="fragments/headerLogged.html"%>

<div class="bd">
    <legend style="text-align:center;">Coltivazioni</legend>

    <div id="alrt" class="alert alert-warning fade show" role="alert" hidden>
        <i class="bi bi-exclamation-triangle me-1"> Selezionare almeno un sensore.</i>
    </div>

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
                                if (cb.getStato_archiviazione() == 0) {
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
                                "<h5 class=\"card-title\">Modulo inserimento coltivazione</h5>" +
                                "            <form action=\"ServletColtivazioni\" method=\"post\" id=\"aggiungi_coltivazione\">\n" +
                                "                <input type=\"hidden\" name=\"moduloInserimentoColtivazione\" required><br>\n" +
                                "                <label>Scegliere il terreno di cui avviare una coltivazione</label><br>");
                                TerrenoManager tm = new TerrenoManager();
                                List<TerrenoBean> tbList = tm.visualizzaListaTerreni(ab.getEmail());
                                ColtivazioneManager cm = new ColtivazioneManager();
                                List<ColtivazioneBean> cList = cm.visualizzaStatoColtivazioni(ab.getEmail());
                                List<Integer> ids = new ArrayList<>();

                                if (cList != null || cList.size() > 0) {
                                    out.print("<select type=\"text\" name=\"terreno\" required><br>\n");
                                    for (int i = 0; i < cList.size(); i++) {
                                        ids.add(cList.get(i).getTerreno());
                                    }

                                    if (tbList != null && tbList.size() > 0) {
                                        for (int i = 0; i < tbList.size(); i++) {
                                            if (!ids.contains(tbList.get(i).getId())) {
                                                out.print("<option value=" + tbList.get(i).getId() + ">"+ "<img src=\""+ tm.retrieveTerreno(String.valueOf(tbList.get(i).getId())).getImmagine() +"\" alt=\"immagine di terreno"+ tbList.get(i).getImmagine() +"\">" +"id: "+ tbList.get(i).getId() + "</option>");
                                            }
                                        }
                                    } else {
                                        out.print("<label>Non ci sono terreni.</label>");
                                    }
                                    out.print("</select><br>");
                                } else {
                                    for (int i = 0; i < ids.size(); i++) {
                                        out.print("<option value=" + ids.get(i) + ">"+ "<img src=\""+ tm.retrieveTerreno(String.valueOf(ids.get(i))).getImmagine() +"\" alt=\"immagine di terreno"+ ids.get(i)+"\">"+ "id: "+ ids.get(i) +"</option>");
                                    }
                                }
                                out.print("<label>Scegliere la pianta di cui avviare una coltivazione</label><br>");

                                cList = cm.visualizzaStatoColtivazioni(ab.getEmail());
                                PiantaManager pm = new PiantaManager();
                                List<PiantaBean> pList = pm.ListaPianteManager(ab.getEmail());
                                if (pList == null) {
                                    out.print("<h7>Non ci sono piante.</h7>");
                                } else {
                                    out.print("<select type=\"text\" name=\"nomepianta\" required><br>\n");
                                    for (int i = 0; i < cList.size(); i++) {
                                        ids.add(cList.get(i).getPianta());
                                    }
                                    for (int i = 0; i < pList.size(); i++) {
                                        if (!(ids.contains(pList.get(i).getId()))) {
                                            out.print("<option value=\"" + pList.get(i).getId() + "\"> "+ pList.get(i).getNome() + "</option>");
                                        }
                                    }
                                    out.print("</select><br>");
                                }

                            //INSERIMENTO DEI SENSORI
                            out.print("<label>Scegliere i sensori da associare alla coltivazione</label><br>" +
                                    "<label>pH</label><br>");
                            SensoreManager sm = new SensoreManager();
                            List<SensoreBean> sbList = sm.visualizzaListaSensori(ab.getEmail());
                            if (sbList == null || sbList.size() == 0) {
                                out.print("<h7>Non ci sono sensori.</h7>");
                            } else {
                                out.print("<select type=\"text\" name=\"sensorePh\" required><br>\n");
                                for (int i = 0; i < sbList.size(); i++) {
                                /*if (sbList.get(i).getColtivazione() == null && sbList.get(i).getTipo().toLowerCase().equals("ph")) {
                                    out.print("<option value=\"" + pList.get(i).getId() + "\"></option>");
                                    out.print("<input type="checkbox" id="chk" name="pH" value="true"><br>");
                                }*/
                                }
                                out.print("                </select><br>");

                                out.print("<label>Temperatura</label><br>");
                                out.print("                <select type=\"text\" name=\"sensoreTemperatura\" required><br>\n");

                                for (int i = 0; i < sbList.size(); i++) {
                                /*if (sbList.get(i).getColtivazione() == null && sbList.get(i).getTipo().toLowerCase().equals("temperatura")) {
                                    out.print("<option value=\"" + pList.get(i).getId() + "\"></option>");
                                    out.print("<input type="checkbox" id="chk" name="temperatura" value="true"><br>")
                                }*/
                                }
                                out.print("                </select><br>");


                                out.print("<label>Umidità</label><br>");
                                out.print("                <select type=\"text\" name=\"sensoreUmidita\" required><br>\n");

                                for (int i = 0; i < sbList.size(); i++) {
                                /*if (sbList.get(i).getColtivazione() == null && (sbList.get(i).getTipo().toLowerCase().contains("umidit"))) {
                                    out.print("<option value=\"" + pList.get(i).getId() + "\"></option>");
                                    out.print("<input type="checkbox" id="chk" name="umidita" value="true"><br>");
                                }*/
                                }
                                out.print("                </select><br>");


                                out.print("<br><br><button type=\"button\" class=\"btn btn-primary\" onclick=\"$(\"#aggiungi_coltivazione\").click(function() {\n" +
                                        "            if ($('#chk:checked').length == 0) {\n" +
                                        "                $(\"#alrt\").fadeIn();\n" +
                                        "                return false;\n" +
                                        "            }\n" +
                                        "            else {\n" +
                                        "                $(\"#alrt\").fadeOut();\n" +
                                        "            }\n" +
                                        "        });\">\n" +
                                        "                    Aggiungi coltivazione\n" +
                                        "                </button>\n" +
                                        "            </form>\n" +
                                        "        </div>\n" +
                                        "    </div>\n" +
                                        "    <!-- Fine inserisci coltivazione --> </div>");
                            }
                        }
                    }
                %>
            </div>
            <%@include file="fragments/footer.html"%>
</body>
</html>

