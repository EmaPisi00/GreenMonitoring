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
    <div class="row">
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Home</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Dati Umidità</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Dati Ph</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="contact-tab2" data-bs-toggle="tab" data-bs-target="#contact-tab-pane2" type="button" role="tab" aria-controls="contact-tab-pane2" aria-selected="false">Dati Temperatura</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="contact-tab3" data-bs-toggle="tab" data-bs-target="#contact-tab-pane3" type="button" role="tab" aria-controls="contact-tab-pane3" aria-selected="false">Fisiopatie</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="disabled-tab" data-bs-toggle="tab" data-bs-target="#disabled-tab-pane" type="button" role="tab" aria-controls="disabled-tab-pane" aria-selected="false" disabled>Archivia Coltivazione</button>
        </li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <h5 class="card-title">Info coltivazione</h5>
                        <%
                            /* -- INIZIO AUTENTICAZIONE -- */
                            Object sa = session.getAttribute("currentUserSession");
                            int[] valoriMisurati = {0, 0, 0};
                            ColtivazioneBean cb = null;;
                            if (sa == null) {
                                response.sendError(401);
                            } else if (!(session.getAttribute("currentUserSession") instanceof UtenteBean)) {
                                response.sendError(401);
                            }
                            /* -- PASSATI I TEST, IL CONTAINER APRE IL RESTO DELLA PAGINA -- */
                            else {
                                String urlImmagine = new String("Foto coltivazione");
                                List<ColtivazioneBean> list = null;
                                List<SensoreBean> sList = null;
                                List<PiantaBean> piantaBeanList = null;
                                ColtivazioneManager cm = new ColtivazioneManager();
                                PiantaManager pm = new PiantaManager();
                                SensoreManager sm = new SensoreManager();
                                String nomePianta = new String();
                                Integer coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
                                if ((session.getAttribute("currentUserSession") instanceof DipendenteBean)) {
                                    DipendenteBean a = (DipendenteBean) sa;
                                    cb = cm.visualizzaStatoColtivazioni(a.getAzienda()).stream().filter(o -> o.getId() == coltivazioneID).findFirst().orElse(null);
                                    sList = sm.visualizzaListaSensori(a.getAzienda()).stream().filter(o -> o.getColtivazione() == coltivazioneID).toList();
                                    list = cm.visualizzaStatoColtivazioni(a.getAzienda());
                                    piantaBeanList = pm.ListaPianteManager(a.getAzienda());
                                    for (int i = 0; i < piantaBeanList.size(); i++) {
                                        for (int j = 0; j < list.size(); j++) {
                                            if (piantaBeanList.get(i).getId().equals(list.get(j).getPianta())) {
                                                nomePianta = piantaBeanList.get(i).getNome();
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    AziendaBean a = (AziendaBean) sa;
                                    cb = cm.visualizzaStatoColtivazioni(a.getEmail()).stream().filter(o -> o.getId() == coltivazioneID).findFirst().orElse(null);
                                    list = cm.visualizzaStatoColtivazioni(a.getEmail());
                                    sList = sm.visualizzaListaSensori(a.getEmail()).stream().filter(o -> o.getColtivazione() == coltivazioneID).toList();
                                    piantaBeanList = pm.ListaPianteManager(a.getEmail());
                                    for (int i = 0; i < piantaBeanList.size(); i++) {
                                        for (int j = 0; j < list.size(); j++) {
                                            if (piantaBeanList.get(i).getId().equals(list.get(j).getPianta())) {
                                                nomePianta = piantaBeanList.get(i).getNome();
                                                urlImmagine = piantaBeanList.get(i).getImmagine();
                                                break;
                                            }
                                        }
                                    }
                                }
                                valoriMisurati = cm.visualizzaMediaSensori((String) session.getAttribute("coltivazioneID"));
                                out.print("<ul><li class=\"list-group-item \">" +
                                        "Coltivazione di " + nomePianta + "<br>" +
                                        "<img src=\"" + urlImmagine + "\" alt=\"Foto coltivazione\">" + "<br>" +
                                        "<h7>media pH</h7> " + valoriMisurati[0] + "<br>" +
                                        "<h7>media temperatura</h7> " + valoriMisurati[1] + "<br>" +
                                        "<h7>media umidità</h7> " + valoriMisurati[2] + "<br>" +


                                        // ho aggiunto questo form, poi te lo gestisci tu, basta che chiama servletSuggerimenti e c'è l'id della coltivazione
                                        "<form action=\"ServletSuggerimenti\" method=\"get\">\n" +
                                        "<input type=\"hidden\" name=\"coltivazione\" value=\"" + coltivazioneID + "\">" +
                                        "<button type=\"submit\" class=\"btn btn-success\" >Suggerimenti</button>"
                                        + "</form>" +

                                        //fino a qui
                                        "</li></ul>"
                                );
                            %>
                    </div>
                </div>
            </div>
        </div>

        <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <h5 class="card-title">Misurazioni più recenti</h5>
                        <%  //System.out.println("\u001B[31m" + "if statement" + "\u001B[0m" + "[" + "\u001B[32m" + "Coltivazione.jsp" + "\u001B[0m" + "]" + " ColtivazioneBean is " + cb.toString());
                            System.out.println("[" + "\u001B[32m" + "Coltivazione.jsp" + "\u001B[0m" + "]" + " ColtivazioneBean is " + cb.toString());
                            ArrayList<MisurazioneSensoreBean> misurazioneSensoreBeanArrayList = cb.getListaMisurazioni();
                            List<Date> date = misurazioneSensoreBeanArrayList.stream().map(o -> (Date) o.getData()).toList();
                            out.print("<ul>");
                            for (int i = date.size() - 1; i < date.size() - 5; i--) { //parto dal fondo così sicuro prendo le misurazioni più recenti.
                                if (misurazioneSensoreBeanArrayList.get(i).getTipo().toLowerCase().contains("umidit")) {
                                    if (misurazioneSensoreBeanArrayList.get(i).getData().after(misurazioneSensoreBeanArrayList.get(i - 1).getData())) {
                                        if (misurazioneSensoreBeanArrayList.get(i).getOra().after(misurazioneSensoreBeanArrayList.get(i - 1).getData())) {
                                            out.print("<li class=\"list-group-item \"> Misurazione in data : " + misurazioneSensoreBeanArrayList.get(i).getData() +
                                                    "risultato : " + misurazioneSensoreBeanArrayList.get(i).getValore() + "</li>");
                                        } else {
                                            out.print("<li class=\"list-group-item \"> Misurazione in data : " + misurazioneSensoreBeanArrayList.get(i - 1).getData() +
                                                    "risultato : " + misurazioneSensoreBeanArrayList.get(i - 1).getValore() + "</li>");
                                        }
                                    }
                                }
                            }
                            out.print("</ul>");
                        %>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="contact-tab-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="0">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <h5 class="card-title">Misurazioni più recenti</h5>
            <%  //System.out.println("\u001B[31m" + "if statement" + "\u001B[0m" + "[" + "\u001B[32m" + "Coltivazione.jsp" + "\u001B[0m" + "]" + " ColtivazioneBean is " + cb.toString());
                System.out.println("[" + "\u001B[32m" + "Coltivazione.jsp" + "\u001B[0m" + "]" + " ColtivazioneBean is " + cb.toString());
                misurazioneSensoreBeanArrayList = cb.getListaMisurazioni();
                date = misurazioneSensoreBeanArrayList.stream().map(o -> (Date) o.getData()).toList();
                out.print("<ul>");
                for (int i = date.size() - 1; i < date.size() - 5; i--) { //parto dal fondo così sicuro prendo le misurazioni più recenti.
                    if (misurazioneSensoreBeanArrayList.get(i).getTipo().toLowerCase().contains("ph")) {
                        if (misurazioneSensoreBeanArrayList.get(i).getData().after(misurazioneSensoreBeanArrayList.get(i - 1).getData())) {
                            if (misurazioneSensoreBeanArrayList.get(i).getOra().after(misurazioneSensoreBeanArrayList.get(i - 1).getData())) {
                                out.print("<li class=\"list-group-item \"> Misurazione in data : " + misurazioneSensoreBeanArrayList.get(i).getData() +
                                        "risultato : " + misurazioneSensoreBeanArrayList.get(i).getValore() + "</li>");
                            } else {
                                out.print("<li class=\"list-group-item \"> Misurazione in data : " + misurazioneSensoreBeanArrayList.get(i - 1).getData() +
                                        "risultato : " + misurazioneSensoreBeanArrayList.get(i - 1).getValore() + "</li>");
                            }
                        }
                    }
                }
                out.print("</ul>");
            %>
                    </div>
                </div>
            </div>
        </div>

        <div class="tab-pane fade" id="contact-tab-pane2" role="tabpanel" aria-labelledby="contact-tab2" tabindex="0">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <h5 class="card-title">Misurazioni più recenti</h5>
            <%  //System.out.println("\u001B[31m" + "if statement" + "\u001B[0m" + "[" + "\u001B[32m" + "Coltivazione.jsp" + "\u001B[0m" + "]" + " ColtivazioneBean is " + cb.toString());
            System.out.println("[" + "\u001B[32m" + "Coltivazione.jsp" + "\u001B[0m" + "]" + " ColtivazioneBean is " + cb.toString());
            misurazioneSensoreBeanArrayList = cb.getListaMisurazioni();
            date = misurazioneSensoreBeanArrayList.stream().map(o -> (Date) o.getData()).toList();
            out.print("<ul>");
            for (int i = date.size() - 1; i < date.size() - 5; i--) { //parto dal fondo così sicuro prendo le misurazioni più recenti.
                if (misurazioneSensoreBeanArrayList.get(i).getTipo().toLowerCase().contains("temp")) {
                    if (misurazioneSensoreBeanArrayList.get(i).getData().after(misurazioneSensoreBeanArrayList.get(i - 1).getData())) {
                        if (misurazioneSensoreBeanArrayList.get(i).getOra().after(misurazioneSensoreBeanArrayList.get(i - 1).getData())) {
                            out.print("<li class=\"list-group-item \"> Misurazione in data : " + misurazioneSensoreBeanArrayList.get(i).getData() +
                                    "risultato : " + misurazioneSensoreBeanArrayList.get(i).getValore() + "</li>");
                        } else {
                            out.print("<li class=\"list-group-item \"> Misurazione in data : " + misurazioneSensoreBeanArrayList.get(i - 1).getData() +
                                    "risultato : " + misurazioneSensoreBeanArrayList.get(i - 1).getValore() + "</li>");
                        }
                    }
                }
            }
            out.print("</ul>");
        %>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="contact-tab-pane3" role="tabpanel" aria-labelledby="contact-tab3" tabindex="0">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <h5>Nessuna fisiopatia al momento</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>

        <div class="col-lg-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Sunto delle misurazioni (media)</h5>
                    <div id="chartContainer" style="height: 370px; width: 100%;"></div>
                    <script type="text/javascript">
                        window.onload = function () {

                            var chart = new CanvasJS.Chart("chartContainer", {
                                theme: "light1", // "light2", "dark1", "dark2"
                                animationEnabled: false, // change to true
                                title:{
                                    text: ""
                                },
                                data: [
                                    {
                                        // Change type to "bar", "area", "spline", "pie",etc.
                                        type: "pie",
                                        dataPoints: [
                                            { label: "pH",  y: <%=valoriMisurati[0]%>  },
                                            { label: "Temperatura", y:  <%=valoriMisurati[1]%>  },
                                            { label: "Umidità", y: <%=valoriMisurati[2]%>  }
                                        ]
                                    }
                                ]
                            });
                            chart.render();

                        }
                    </script>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="card" style="width: 30rem;">
                <div class="card-body">
                    <h5 class="card-title">Sensori</h5>

                    <%  cm = new ColtivazioneManager();
                        out.print("<ul class=\"list-group\">\n");
                        list = null;
                        sList = null;
                        sm = new SensoreManager();
                        if ((session.getAttribute("currentUserSession") instanceof DipendenteBean)) {
                            DipendenteBean a = (DipendenteBean) sa;
                            sList = sm.visualizzaListaSensori(a.getAzienda()).stream().filter(o -> o.getColtivazione() == coltivazioneID).toList();
                            list = cm.visualizzaStatoColtivazioni(a.getAzienda());
                        } else if (session.getAttribute("currentUserSession") instanceof AziendaBean) {
                            AziendaBean a = (AziendaBean) sa;
                            list = cm.visualizzaStatoColtivazioni(a.getEmail());
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
                    }%>
                    <!-- Fine coltivazioni -->
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html"%>
</body>


</html>
