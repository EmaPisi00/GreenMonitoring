<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.FisiopatieDAO" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.FisiopatieDAOImpl" %>
<%@ page import="java.util.ArrayList" %>
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
        @media screen and (max-width: 768px) {
            .tohide {
                display: none;
            }
        }

        .value-bar {
            display: flex;
            align-items: center;
            height: 20px;
            background-color: lightgray;
            border-radius: 10px;
            padding: 5px;
        }

        .mesured-value {
            height: 100%;
            width: 50%;
            background-color: green;
            border-radius: 10px 0 0 10px;
        }

        .value-status {
            width: 10px;
            height: 10px;
            border-radius: 50%;
            margin-left: 10px;
        }


        .value-label {
            margin-left: 10px;
            font-size: 14px;
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
<%@include file="fragments/headerLoggedAzienda.html" %>

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
<% if (!(session.getAttribute("currentUserSession") instanceof UtenteBean)) {
    response.sendError(401);
} else if (session.getAttribute("coltivazioneID") != null) {
    coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
    temporaryColtivazioneBean = cm.retrieveColtivazioneSingola(coltivazioneID);
    idPianta = temporaryColtivazioneBean.getPianta();
    temporaryPiantaBean = pm.visualizzaPianta(idPianta);
    nomePianta = temporaryPiantaBean.getNome();
    urlImmagine = temporaryPiantaBean.getImmagine();
    temporaryTerrenoBean = tm.restituisciTerrenoDaInt(temporaryColtivazioneBean.getTerreno());
    descrizioneTerreno = temporaryTerrenoBean.getNome();
    resultUmidita = cm.restituisciMisurazioniRecenti("umidita", coltivazioneID);
    resultPH = cm.restituisciMisurazioniRecenti("pH", coltivazioneID);
    resultTemperatura = cm.restituisciMisurazioniRecenti("Temperatura", coltivazioneID);
} else {
    response.sendError(404);
}%>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="row text-center">
                <h5>Info coltivazione</h5>
            </div>
            <div class="row row-cols-2">
                <div class="col">
                    <h7>Coltivazione:</h7>
                </div>
                <div class="col">
                    <h7><%=nomePianta%>
                    </h7>
                </div>
            </div>
            <div class="row">
                <img src="<%=urlImmagine%>" alt="Foto coltivazione"/>
            </div>
            <div class="row row-cols-2">
                <div class="col">
                    <h7>Terreno:</h7>
                </div>
                <div class="col">
                    <h7><%=descrizioneTerreno%>
                    </h7>
                </div>
            </div>
            <div class="row row-cols-2">
                <div class="col">
                    <h7>Data inizio:</h7>
                </div>
                <div class="col d-flex align-items-center">
                    <h7><%=temporaryColtivazioneBean.getData_inizio()%>
                    </h7>
                </div>
            </div>
        </div>
        <div class="col d-flex align-items-center">
            <h7>
                <button class="btn btn-danger" id="archivia-coltivazione" disabled>Archivia Coltivazione</button>
            </h7>
        </div>
        <div class="col d-flex align-items-center">
            <div id="fullChartCanvas" style="width: 500px; height: 200px ">
                <script type="text/javascript">
                    window.onload =  function() {
                        var coltivazioneID = <%=coltivazioneID%>;
                            console.log("sono nell'if");
                            var xhr = new XMLHttpRequest();
                            console.log("xhr inizializzato");
                            xhr.open("POST", "ServletColtivazioni");
                            console.log("open fatta");
                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            console.log("requestHeader settato");
                            xhr.send("coltivazioneID="+coltivazioneID+"&today=today");
                            console.log("inviata la richiesta");
                            xhr.onreadystatechange = function () {
                                console.log("sono dentro la funzione");
                                if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                                    console.log("lo status è 200");
                                    var fullchartData = JSON.parse(xhr.responseText);
                                    console.log(fullchartData);
                                    var chart2 = new CanvasJS.Chart("fullChartCanvas", fullchartData);
                                    chart2.render();
                                } else if (xhr.status === 500){
                                    var div = document.getElementById("fullChartCanvas");
                                    div.innerHTML = xhr.responseText;
                                }
                            }
                    }
                </script>
            </div>
        </div>
    </div>
</div>
<div class="bd mt-5">
    <!-- elenco dei tab accessibili -->
    <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="generale-tab" data-bs-toggle="tab" data-bs-target="#generale-tab-pane"
                    type="button" role="tab" aria-controls="generale-tab-pane" aria-selected="true">Generale
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="umidità-tab" data-bs-toggle="tab" data-bs-target="#umidità-tab-pane"
                    type="button" role="tab" aria-controls="umidità-tab-pane" aria-selected="false">Dati Umidità
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="ph-tab" data-bs-toggle="tab" data-bs-target="#ph-tab-pane" type="button"
                    role="tab" aria-controls="ph-tab-pane" aria-selected="false">Dati ph
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="temperatura-tab" data-bs-toggle="tab" data-bs-target="#temperatura-tab-pane"
                    type="button" role="tab" aria-controls="temperatura-tab-pane" aria-selected="false">Dati Temperatura
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="fisiopatie-tab" data-bs-toggle="tab" data-bs-target="#fisiopatie-tab-pane"
                    type="button" role="tab" aria-controls="fisiopatie-tab-pane" aria-selected="false">Fisiopatie
            </button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="storico-tab" data-bs-toggle="tab" data-bs-target="#storico-tab-pane"
                    type="button" role="tab" aria-controls="storico-tab-pane" aria-selected="false">Storico
            </button>
        </li>
    </ul>
</div>
<!-- contenuto dei tab -->
<div class="tab-content" id="myTabContent">
    <!-- Generale -->
    <div class="tab-pane fade show active" id="generale-tab-pane" role="tabpanel" aria-labelledby="generale-tab-pane"
         tabindex="0">
        <div class="card" style="width: auto;">
            <div class="card-body">
                <div style="width: auto;" class="row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <!--Media umidità -->
                            <h5>Umidità media</h5>
                            <div class="row">
                                <div class="col value-bar">
                                    <div class="mesured-value" style="width: <%=resultUmidita%>%"></div>
                                </div>
                                <div class="col-3 value-label"><%=resultUmidita%>%</div>
                                <div class="col-1">
                                    <div class="value-status" style="background-color: <%=colorUmidità%>;"></div>
                                </div>
                            </div>
                        <!--Media temperatura -->
                        <h5>Temperatura media</h5>
                        <div class="row">
                            <div class="col value-bar">
                                <div class="mesured-value" style="width: <%=resultTemperatura%>%"></div>
                            </div>
                            <div class="col-3 value-label"><%=resultTemperatura%>&degC</div>
                            <div class="col-1">
                                <div class="value-status" style="background-color: <%=colorTemperatura%>;"></div>
                            </div>
                        </div>
                        <!--Media ph -->
                        <h5>pH media</h5>
                        <div class="row">
                            <div class="col value-bar">
                                <div class="mesured-value" style="width: <%=(resultPH/14)*100%>%"></div>
                            </div>
                            <div class="col-3 value-label"><%=resultPH.intValue()%>
                            </div>
                            <div class="col-1">
                                <div class="value-status" style="background-color: <%=colorPH%>;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Umidità -->
    <div class="tab-pane fade" id="umidità-tab-pane" role="tabpanel" aria-labelledby="umidità-tab-pane" tabindex="0">
        <div class="card" style="width: auto;">
            <div class="card-body">
                <div style="width: auto;" class="row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <% cm = new ColtivazioneManager();
                            List<MisurazioneSensoreBean> misurazioneSensoreBeans = cm.visualizzaMisurazioneColtivazione(coltivazioneID, "umidita");
                            for (int i = 0; i < misurazioneSensoreBeans.size(); i++) {
                                colorUmidità = "green";
                            /*
                            if (resultUmidita è lontano dal valore ottimale) {
                                colorUmidità = "red";
                            }
                            */%>
                        <h8>Sensore Umidità <%=misurazioneSensoreBeans.get(i).getSensore_id()%>
                            : <%=misurazioneSensoreBeans.get(i).getValore()%>%
                        </h8><br>
                        <%}%>
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <h5>Media</h5>
                        <div class="row">
                            <div class="col value-bar">
                                <div class="mesured-value" style="width: <%=resultUmidita%>%"></div>
                            </div>
                            <div class="col-3 value-label"><%=resultUmidita%>&degC</div>
                            <div class="col-1">
                                <div class="value-status" style="background-color: <%=colorUmidità%>;"></div>
                            </div>
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
    <!-- pH -->
    <div class="tab-pane fade" id="ph-tab-pane" role="tabpanel" aria-labelledby="ph-tab-pane" tabindex="0">
        <div class="card" style="width: auto;">
            <div class="card-body">
                <div style="width: auto;" class="row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <% cm = new ColtivazioneManager();
                            misurazioneSensoreBeans = cm.visualizzaMisurazioneColtivazione(coltivazioneID, "pH");
                            for (int i = 0; i < misurazioneSensoreBeans.size(); i++) {
                                colorPH = "green";
                            /*
                            if (resultPH è lontano dal valore ottimale) {
                                colorPH = "red";
                            }
                            */%>
                        <h8>Sensore pH <%=misurazioneSensoreBeans.get(i).getSensore_id()%>
                            : <%=misurazioneSensoreBeans.get(i).getValore()%>
                        </h8><br>
                        <%}%>
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <h5>Media</h5>
                        <div class="row">
                            <div class="col value-bar">
                                <div class="mesured-value" style="width: <%=(resultPH/14)*100%>%"></div>
                            </div>
                            <div class="col-3 value-label"><%=resultPH%>
                            </div>
                            <div class="col-1">
                                <div class="value-status" style="background-color: <%=colorPH%>;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Temperatura -->
    <div class="tab-pane fade" id="temperatura-tab-pane" role="tabpanel" aria-labelledby="temperatura-tab-pane"
         tabindex="0">
        <div class="card" style="width: auto;">
            <div class="card-body">
                <div style="width: auto;" class="row">
                    <div class="col-sm-6 mb-3">
                        <% cm = new ColtivazioneManager();
                            misurazioneSensoreBeans = cm.visualizzaMisurazioneColtivazione(coltivazioneID, "Temperatura");

                            for (int i = 0; i < misurazioneSensoreBeans.size(); i++) {
                                colorTemperatura = "green";
                            /*
                            if (resultTemperatura è lontano dal valore ottimale) {
                                colorTemperatura = "red";
                            }
                            */%>
                        <h8>Sensore temperatura <%=misurazioneSensoreBeans.get(i).getSensore_id()%>
                            : <%=misurazioneSensoreBeans.get(i).getValore()%>&degC
                        </h8><br>
                        <%}%>
                    </div>
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <h5>Media</h5>
                        <div class="row">
                            <div class="col value-bar">
                                <div class="mesured-value" style="width: <%=resultTemperatura%>%"></div>
                            </div>
                            <div class="col-3 value-label"><%=resultTemperatura%>&degC</div>
                            <div class="col-1">
                                <div class="value-status" style="background-color: <%=colorTemperatura%>;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Fisiopatie -->
    <div class="tab-pane fade" id="fisiopatie-tab-pane" role="tabpanel" aria-labelledby="fisiopatie-tab"
         tabindex="0">
        <div class="card" style="width: auto;">
            <div class="card-body">
                <%
                    ArrayList<FisiopatieBean> listaFisiopatie = cm.visualizzaFisiopatiePerPianta(idPianta);
                    if (listaFisiopatie.size() == 0) {
                        out.print("Nessuna fisiopatia presente per questa pianta.");
                    } else {
                %>

                <div>
                    <div style="display:flex;">
                        <div style="width:5%;">ID</div>
                        <div style="width:10%;">Pianta</div>
                        <div style="width:10%;">Nome</div>
                        <div style="width:15%;">Umidità Terra Min</div>
                        <div style="width:15%;">Umidità Terra Max</div>
                        <div style="width:15%;">Temperatura Min</div>
                        <div style="width:15%;">Temperatura Max</div>
                        <div style="width:15%;">Umidità Aria Min</div>
                        <div style="width:15%;">Umidità Aria Max</div>
                    </div>
                    <%
                        for (FisiopatieBean fisiopatia : listaFisiopatie) {

                    %>
                    <div style="display:flex;">
                        <div style="width:5%;"><%= fisiopatia.getId() %>
                        </div>
                        <div style="width:10%;"><%= nomePianta %>
                        </div>
                        <div style="width:10%;"><%= fisiopatia.getNome() %>
                        </div>
                        <div style="width:15%;"><%= fisiopatia.getUmid_terr_min() %>
                        </div>
                        <div style="width:15%;"><%= fisiopatia.getUmid_terr_max() %>
                        </div>
                        <div style="width:15%;"><%= fisiopatia.getTemp_min() %>
                        </div>
                        <div style="width:15%;"><%= fisiopatia.getTemp_max() %>
                        </div>
                        <div style="width:15%;"><%= fisiopatia.getUmid_aria_min() %>
                        </div>
                        <div style="width:15%;"><%= fisiopatia.getUmid_aria_max()%>
                        </div>
                    </div>
                    <div div style="border: 1px solid black; padding: 10px;">
                        <strong>Descrizione:</strong><br>
                        <%= fisiopatia.getDescrizione() %>
                    </div>
                    <% }
                    }
                    %>
                </div>
            </div>
        </div>
    </div>
    <!-- Storico -->
    <div class="tab-pane fade" id="storico-tab-pane" role="tabpanel" aria-labelledby="storico-tab-pane" tabindex="0">
        <h5 class="card-title">Rilevamenti per periodo</h5>
        <form method="ServletColtivazioni" action="post">
            <input id="periodo-inizio" type="date" max="<%=new java.sql.Date(System.currentTimeMillis())%>"
                   name="data_inizio_periodo" required>
            <input id="periodo-fine" type="date" max="<%=new java.sql.Date(System.currentTimeMillis())%>"
                   name="data_fine_periodo" required>
            <select id="selectSensore" required>
                <option name="tipoSensore" value="umidita">Umidità</option>
                <option name="tipoSensore" value="pH">pH</option>
                <option name="tipoSensore" value="temperatura">Temperatura</option>
            </select>
            <button id="rilevamentiPerPeriodo" type="button" class="btn btn-success" onclick="loadJsonAndPrintChart()">Mostra per questi periodi</button>
        </form>
        <div id="rilevamentiPerPeriodoCanvas" style="height: 370px; width: 500px;"></div>
        <script type="text/javascript">
            function loadJsonAndPrintChart() {
                var coltivazioneID = <%=coltivazioneID%>;
                console.log(coltivazioneID);
                var inputInizio = document.getElementById("periodo-inizio").value;
                console.log(inputInizio);
                var inputFine = document.getElementById("periodo-fine").value;
                console.log(inputFine);
                var tipoSensore = document.getElementById("selectSensore").value;
                console.log(tipoSensore);
                if (inputInizio != null && inputFine != null) {
                    console.log("sono nell'if");
                    var xhr = new XMLHttpRequest();
                    console.log("xhr inizializzato");
                    xhr.open("POST", "ServletColtivazioni");
                    console.log("open fatta");
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    console.log("requestHeader settato");
                    xhr.send("data_inizio_periodo="+ inputInizio +"&data_fine_periodo="+ inputFine +"&coltivazioneID="+coltivazioneID+"&tipoSensore="+tipoSensore);
                    console.log("data_inizio_periodo="+ inputInizio +"&data_fine_periodo="+ inputFine +"&coltivazioneID="+coltivazioneID+"&tipoSensore="+tipoSensore);
                    console.log("inviata la richiesta");
                    xhr.onreadystatechange = function () {
                        console.log("sono dentro la funzione");
                        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                            console.log("lo status è 200");
                            var chartData = JSON.parse(xhr.responseText);
                            console.log(chartData);
                            var chart = new CanvasJS.Chart("rilevamentiPerPeriodoCanvas", chartData);
                            chart.render();
                        } else if (xhr.status === 500) {
                            console.log(xhr.responseText);
                        }
                    }
                }
            }
        </script>
        <script src="canvas/canvasjs.min.js"></script>
    </div>
    <!-- lista dei sensori e form rimuovi sensore -->
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="card" style="width: auto;">
                    <h5 class="card-title">Sensori</h5>
                    <% Object sa = session.getAttribute("currentUserSession");
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
                    </div>
                </div>
                <!-- Fine coltivazioni -->
            </div>
        </div>


        <%@include file="fragments/footer.html" %>
</body>
</html>