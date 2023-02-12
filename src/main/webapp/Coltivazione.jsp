<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <style>
        @media screen and (max-width:768px) {
            .tohide { display: none; }
        }
    </style>
    <title>Coltivazione</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="canvas/canvasjs.min.js"></script>
    <script src="canvas/jquery.canvasjs.min.js"></script>
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

<%! String urlImmagine = new String("Foto coltivazione");
    List<ColtivazioneBean> list = null;
    List<SensoreBean> sList = null;
    List<PiantaBean> piantaBeanList = null;
    ColtivazioneManager cm = new ColtivazioneManager();
    PiantaManager pm = new PiantaManager();
    SensoreManager sm = new SensoreManager();
    TerrenoManager tm = new TerrenoManager();
    String nomePianta = new String();
    Integer coltivazioneID = 0;
    Double resultUmidita = 0d;
    Double resultTemperatura = 0d;
    Double resultPH = 0d;
    String colorPH = "green";
    String colorTemperatura = "green";
    String colorUmidità = "green";
    String descrizioneTerreno = new String("");
    PiantaBean temporaryPiantaBean = new PiantaBean();
    Integer idPianta = 0;
    TerrenoBean temporaryTerrenoBean = new TerrenoBean();
    ColtivazioneBean temporaryColtivazioneBean = new ColtivazioneBean();
%>
<%System.out.println("[Coltivazione.jsp] - superate le dichiarazioni: " + !(session.getAttribute("currentUserSession") instanceof UtenteBean)+"\n"+session.getAttribute("ColtivazioneID"));
%>

<% if (!(session.getAttribute("currentUserSession") instanceof UtenteBean) && session.getAttribute("coltivazioneID") == null) {
    response.sendError(401);
    } else {
        coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
        temporaryColtivazioneBean = cm.retrieveColtivazioneSingola(coltivazioneID);
        idPianta = temporaryColtivazioneBean.getPianta();
        temporaryPiantaBean = pm.ritornaPiantaManager(idPianta);
        nomePianta = temporaryPiantaBean.getNome();
        urlImmagine = temporaryPiantaBean.getImmagine();
        temporaryTerrenoBean = tm.restituisciTerrenoDaInt(temporaryColtivazioneBean.getTerreno());
        descrizioneTerreno = temporaryTerrenoBean.getNome();
        resultUmidita = cm.restituisciMisurazioniRecenti("umidità", coltivazioneID);
        resultPH = cm.restituisciMisurazioniRecenti("pH", coltivazioneID);
        resultTemperatura = cm.restituisciMisurazioniRecenti("Temperatura", coltivazioneID);
    } %>

<div class="bd">
        <!-- elenco dei tab accessibili -->
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Generale</button>
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
                <button class="nav-link" id="disabled-tab" data-bs-toggle="tab" data-bs-target="#disabled-tab-pane" type="button" role="tab" aria-controls="disabled-tab-pane4" aria-selected="false">Storico</button>
            </li>
        </ul>
    <div id="row">
        <div class="col-sm-6">
    <div id="generale">
        <div class="card" style="width: auto;">
            <div class="card-body">
                <div style="width: auto;" class="row">
                    <div class="col-sm-6">
                <h5>Info coltivazione</h5>
                <h7>Coltivazione di <%=nomePianta%></h7><br>
                <img src="<%=urlImmagine%>" alt="Foto coltivazione"/><br>
                <h7>Terreno: <%=descrizioneTerreno%></h7><br>
                <h7>Data inizio: <%=temporaryColtivazioneBean.getData_inizio()%></h7><br>
                    </div>
                    <div class="col-sm-6">
                <h5>Umidità media</h5>
                    <div style="width: 50%; height: 20px; background-color: #ddd;display: flex; align-items: center;">
                        <div style="width: <%=resultUmidita%>%; height: 20px; background-color: <%=colorUmidità%>;"></div><%out.print(resultUmidita+" %");%>
                        <div style="width: 10px; height: 10px; background-color: <%=colorUmidità%>; border-radius: 50%;"></div>
                    </div>
                <h5>Temperatura media</h5>
                <div style="width: 50%; height: 20px; background-color: #ddd;display: flex; align-items: center;">
                    <div style="width: <%=resultTemperatura%>%; height: 20px; background-color: <%=colorTemperatura%>;"></div>
                    <%=resultTemperatura%>&degC
                    <div style="width: 10px; height: 10px; background-color: <%=colorTemperatura%>; border-radius: 50%;"></div>
                </div>
                <h5>pH media</h5>
                <div style="width: 50%; height: 20px; background-color: #ddd;display: flex; align-items: center;">
                    <div style="width: <%=(resultPH/14)*100%>%; height: 20px; background-color: <%=colorPH%>;"></div>
                    <%=resultPH.intValue()%>
                    <div style="width: 10px; height: 10px; background-color: <%=colorPH%>; border-radius: 50%;"></div>
                </div>
                    </div>
                </div>
                <br>
                <button class="btn btn-danger" id="archivia-coltivazione">Archivia Coltivazione</button>
            </div>
        </div>
    </div>
        </div>
        <!-- contenuto dei tab -->
        <div class="col-sm-6">
    <div class="tab-content" id="myTabContent">
            <!-- Umidità -->
            <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <div style="width: auto;" class="row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                        <% cm = new ColtivazioneManager();
                                        List<MisurazioneSensoreBean> misurazioneSensoreBeans = cm.visualizzaMisurazioneOggiColtivazione(coltivazioneID, "umidità");
                                        for (int i = 0; i < misurazioneSensoreBeans.size() ;i++) {
                                            colorUmidità = "green";
                            /*
                            if (resultUmidita è lontano dal valore ottimale) {
                                colorUmidità = "red";
                            }
                            */%>
                        <h8>Sensore Umidità <%=misurazioneSensoreBeans.get(i).getSensore_id()%> : <%=misurazioneSensoreBeans.get(i).getValore()%>%</h8>
                        <%}%>
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <h5>Media</h5>
                        <div style="width: 50%; height: 20px; background-color: #ddd;display: flex; align-items: center;">
                            <div style="width: <%=resultUmidita%>%; height: 20px; background-color: <%=colorUmidità%>;"></div><%=resultUmidita%>%
                            <div style="width: 10px; height: 10px; background-color: <%=colorUmidità%>; border-radius: 50%;"></div>
                        </div>
                    </div>
                        </div>
                        <% out.print("<br>" +
                                // ho aggiunto questo form, poi te lo gestisci tu, basta che chiama servletSuggerimenti e c'è l'id della coltivazione
                                "<form action=\"ServletSuggerimenti\" method=\"get\">\n" +
                                "<input type=\"hidden\" name=\"coltivazione\" value=\"" + coltivazioneID + "\">" +
                                "<button type=\"submit\" class=\"btn btn-success\">Suggerimenti</button>"
                                + "</form>" +
                                //fino a qui
                                "</li></ul>");
                        %>
                    </div>
                    </div>
                </div>
            </div>
            <!-- pH -->
            <div class="tab-pane fade" id="contact-tab-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <div style="width: auto;" class="row">
                        <div class="col-sm-6 mb-3 mb-sm-0">
                                <% cm = new ColtivazioneManager();
                                    misurazioneSensoreBeans = cm.visualizzaMisurazioneOggiColtivazione(coltivazioneID, "pH");
                                    for (int i = 0; i < misurazioneSensoreBeans.size() ;i++) {
                                        colorPH = "green";
                            /*
                            if (resultPH è lontano dal valore ottimale) {
                                colorPH = "red";
                            }
                            */%>
                                <h8>Sensore pH <%=misurazioneSensoreBeans.get(i).getSensore_id()%> : <%=misurazioneSensoreBeans.get(i).getValore()%></h8>
                                <%}%>
                            </div>
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <h5>Media</h5>
                        <div style="width: 50%; height: 20px; background-color: #ddd;display: flex; align-items: center;">
                            <div style="width: <%=(resultPH/14)*100%>%; height: 20px; background-color: <%=colorPH%>;"></div><%=resultPH.intValue()%>
                            <div style="width: 10px; height: 10px; background-color: <%=colorPH%>; border-radius: 50%;"></div>
                        </div>
                    </div>
                        </div>
                    </div>
                </div>
                </div>
            <!-- Temperatura -->
            <div class="tab-pane fade" id="contact-tab-pane2" role="tabpanel" aria-labelledby="contact-tab2" tabindex="0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <div style="width: auto;" class="row">
                        <div class="col-sm-6 mb-3">
                                <% cm = new ColtivazioneManager();
                                    misurazioneSensoreBeans = cm.visualizzaMisurazioneOggiColtivazione(coltivazioneID, "Temperatura");
                                    for (int i = 0; i < misurazioneSensoreBeans.size() ;i++) {
                                        colorTemperatura = "green";
                            /*
                            if (resultTemperatura è lontano dal valore ottimale) {
                                colorTemperatura = "red";
                            }
                            */%>
                                <h8>Sensore temperatura <%=misurazioneSensoreBeans.get(i).getSensore_id()%> : <%=misurazioneSensoreBeans.get(i).getValore()%>&degC</h8>
                                <%}%>
                            </div>
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <h5>Media</h5>
                        <div style="width: 50%; height: 20px; background-color: #ddd;display: flex; align-items: center;">
                            <div style="width: <%=resultTemperatura%>%; height: 20px; background-color: <%=resultTemperatura%>;"></div><%=resultTemperatura%>&degC
                            <div style="width: 10px; height: 10px; background-color: <%=colorTemperatura%>; border-radius: 50%;"></div>
                        </div>
                    </div>
                        </div>
                    </div>
                </div>
                </div>
            <!-- Fisiopatie -->
            <div class="tab-pane fade" id="contact-tab-pane3" role="tabpanel" aria-labelledby="contact-tab3" tabindex="0">
                    <div class="card" style="width: auto;">
                        <div class="card-body">
                            <h5>Nessuna fisiopatia al momento</h5>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Storico -->
            <div class="tab-pane fade" id="contact-tab-pane3" role="tabpanel" aria-labelledby="contact-tab4" tabindex="0">>
                <h5 class="card-title">Rilevamenti per periodo</h5>
                <form method="ServletColtivazioni" action="post">
                    <input id="periodo-inizio" type="date" max="<%=new java.sql.Date(System.currentTimeMillis())%>" name="data_inizio_periodo" required>
                    <input id="periodo-fine" type="date" max="<%=new java.sql.Date(System.currentTimeMillis())%>" name="data_fine_periodo" required>
                    <select required>
                        <option name="tipoSensore" value="umidità">Umidità</option>
                        <option name="tipoSensore" value="pH">pH</option>
                        <option name="tipoSensore" value="temperatura">Temperatura</option>
                    </select>
                    <button id="rilevamentiPerPeriodo" type="button" class="btn btn-success">Mostra per questi periodi</button>
                </form>
                <script type="text/javascript">
                    $("#rilevamentiPerPeriodo").onclick = function() {
                        var inputInizio = document.getElementById("periodo-inizio").value;
                        var inputFine = document.getElementById("periodo-inizio").value;
                        if (inputInizio != null && inputFine != null) {
                            var xhr = new XMLHttpRequest();
                            xhr.open("POST", "ServletColtivazioni", true);
                            xhr.onreadystatechange = function () {
                                if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                                    //Costruisco il grafico
                                    window.onload = function () {
                                        var chart = new CanvasJS.Chart("chartContainer", JSON.parse(xhr.responseText));
                                        chart.render();
                                    }
                                };xhr.send();
                            }
                        }
                    }
                </script>
            </div>
        </div>
        <!-- lista dei sensori e form rimuovi sensore -->
        <div class="col-sm-6">
            <div class="card" style="width: auto;">
                <div class="card-body">
                    <h5 class="card-title">Sensori</h5>
                    <%  Object sa = session.getAttribute("currentUserSession");
                        cm = new ColtivazioneManager();
                        out.print("<ul class=\"list-group\">\n");
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
                                out.print("<li class=\"list-group-item\" name=\"sensoreDaRimuovere\" value=\"" + sList.get(i).getId() + "\">Sensore " + sList.get(i).getTipo() + " " + sList.get(i).getIdM());
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
</div>
<%@include file="fragments/footer.html"%>
</body>
</html>
