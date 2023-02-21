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
    <script>
        window.onload = function (){
            var items = document.getElementsByClassName("list-group-item");
            if (items.length > 3){
                document.getElementById("overFlow").style.overflow = "scroll";
            }

        }
    </script>
    <style>
        #umiditaChartCanvas {
            width: 500px;
            height: 200px;
        }
        #phCanvas{
            width: 500px;
            height: 200px;
        }
        #TemperaturaCanvas{
            width: 500px;
            height: 200px;
        }
        @media screen and (max-width: 768px) {
            .tohide {
                display: none;
            }
            #umiditaChartCanvas{
                width: 300px;
                height: 200px;
            }
            #phCanvas{
                width: 300px;
                height: 200px;
            }
            #TemperaturaCanvas{
                width: 300px;
                height: 200px;
            }
            #overFlow{
                width: 300px;
                height: 180px;
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
        #overFlow{
            width: 500px;
            height: 180px;
            overflow: scroll;
        }
        .nav-item{
            height: 20%;
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
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">
</head>
<title>Coltivazione</title>
<body>


<%@include file="fragments/headerLoggedAzienda.html" %>

<%! byte[] Immagine = null;
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
    response.sendRedirect("error.jsp");
} else if (session.getAttribute("coltivazioneID") != null) {
    coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
    temporaryColtivazioneBean = cm.retrieveColtivazioneSingola(coltivazioneID);
    idPianta = temporaryColtivazioneBean.getPianta();
    temporaryPiantaBean = pm.visualizzaPianta(idPianta);
    nomePianta = temporaryPiantaBean.getNome();
    Immagine = temporaryPiantaBean.getImmagine();
    temporaryTerrenoBean = tm.restituisciTerrenoDaInt(temporaryColtivazioneBean.getTerreno());
    descrizioneTerreno = temporaryTerrenoBean.getNome();
    resultUmidita = cm.restituisciMisurazioniRecenti("umidita", coltivazioneID);
    resultPH = cm.restituisciMisurazioniRecenti("pH", coltivazioneID);
    resultTemperatura = cm.restituisciMisurazioniRecenti("Temperatura", coltivazioneID);
} else {
    response.sendError(404);
}%>
<div class="container" style="margin-bottom: 9%">
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
                <img id="immagine" src="data:image/jpeg;base64,<%=Immagine%>" alt="Foto coltivazione">
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
                <form id="archiviaColtivazione" method="POST" action="ArchiviaColtivazioneServlet">
                    <input type="hidden" name="coltivazione" value="<%=coltivazioneID%>">
                <button type="submit" class="btn btn-danger" id="archivia-coltivazione">Archivia Coltivazione</button>
                </form>
        </div>
        <br>
        <!-- lista dei sensori e form rimuovi sensore -->
        <div class="col-sm-6">
            <div class="card" style="width: auto;">
                <h5 class="card-title" style="text-emphasis: center;">Sensori</h5>
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
                        out.print("<div id=\"overFlow\" style=\"width: auto;\">");
                        for (int i = 0; i < sList.size(); i++) {
                            out.print("<form id=\"rimuoviSensore\" action=\"RimuoviAssociazioneSensoreServlet\" method=\"post\" style=\"margin: 1px\">");
                            out.print("<li class=\"list-group-item\" name=\"sensoreDaRimuovere\" value=\"" + sList.get(i).getId() + "\">Sensore " + sList.get(i).getTipo() + " " + sList.get(i).getIdM());
                            out.print("<input type=\"hidden\" name=\"sensoreDaRimuovere\" value=\"" + sList.get(i).getId() + "\">");
                            if ((session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                                out.print("<a></a><button type=\"submit\" class=\"btn btn-link\">Rimuovi</button>");
                                out.print("</form>");
                            }
                        }
                        out.print("</div>");
                        out.print("</ul>");
                    }
                %>
                <%if ((session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                    out.print("<button type=\"button\" class=\"btn btn-light\" onclick=\"window.location.href=\'./InserisciSensore.jsp\'\">Aggiungi sensore +</button>");
                }%>
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
        <%  List<MisurazioneSensoreBean> misurazioneSensoreBeansUmidita = cm.visualizzaMisurazioneColtivazione(coltivazioneID, "umidita");
            if (misurazioneSensoreBeansUmidita.size() != 0) { %>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="umidità-tab" data-bs-toggle="tab" data-bs-target="#umidità-tab-pane"
                    type="button" role="tab" aria-controls="umidità-tab-pane" aria-selected="false" onclick="findUmiditaChart()">Dati Umidità
            </button>
        </li>
        <% } %>
        <%  List<MisurazioneSensoreBean> misurazioneSensoreBeansPh = cm.visualizzaMisurazioneColtivazione(coltivazioneID, "pH");
            if (misurazioneSensoreBeansPh.size() != 0) { %>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="ph-tab" data-bs-toggle="tab" data-bs-target="#ph-tab-pane" type="button"
                    role="tab" aria-controls="ph-tab-pane" aria-selected="false" onclick="findPhChart()">Dati ph
            </button>
        </li>
        <% } %>
        <%  List<MisurazioneSensoreBean> misurazioneSensoreBeansTemperatura = cm.visualizzaMisurazioneColtivazione(coltivazioneID, "Temperatura");
            if (misurazioneSensoreBeansTemperatura.size() !=  0) {%>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="temperatura-tab" data-bs-toggle="tab" data-bs-target="#temperatura-tab-pane"
                    type="button" role="tab" aria-controls="temperatura-tab-pane" aria-selected="false" onclick="findTemperaturaChart()">Dati Temperatura
            </button>
        </li>
        <% } %>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="fisiopatie-tab" data-bs-toggle="tab" data-bs-target="#fisiopatie-tab-pane"
                    type="button" role="tab" aria-controls="fisiopatie-tab-pane" aria-selected="false">Fisiopatie
            </button>
        </li>
        <%if (misurazioneSensoreBeansUmidita.size() != 0 || misurazioneSensoreBeansPh.size() != 0 || misurazioneSensoreBeansTemperatura.size() != 0) {%>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="storico-tab" data-bs-toggle="tab" data-bs-target="#storico-tab-pane"
                    type="button" role="tab" aria-controls="storico-tab-pane" aria-selected="false">Storico
            </button>
        </li>
        <%}%>
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
                        <%if (misurazioneSensoreBeansUmidita.size() == 0 && misurazioneSensoreBeansPh.size() == 0 && misurazioneSensoreBeansTemperatura.size() == 0) {%>
                        <h5>Non ci sono misurazioni</h5>
                        <%}%>
                        <%if (misurazioneSensoreBeansUmidita.size() != 0) {%>
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
                        <%}%>
                        <%if (misurazioneSensoreBeansTemperatura.size() != 0) {%>
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
                        <%}%>
                        <%if (misurazioneSensoreBeansPh.size() != 0) {%>
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
                        <%}%>
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
                            <div id="umiditaChartCanvas">
                                <script type="text/javascript">
                                    function findUmiditaChart() {
                                        var coltivazioneID = <%=coltivazioneID%>;
                                        var tipoSensore = "umidita"
                                            var xhr = new XMLHttpRequest();
                                            xhr.open("POST", "StoricoServlet");
                                            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                            xhr.send("today=today"+"&coltivazioneID="+coltivazioneID+"&tipoSensore="+tipoSensore);
                                            xhr.onreadystatechange = function () {
                                                if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                                                    var chartData = JSON.parse(xhr.responseText);
                                                    var chart1 = new CanvasJS.Chart("umiditaChartCanvas", chartData);
                                                    chart1.render();
                                                } else if (xhr.status === 500){
                                                    var element = document.getElementsByClassName("container");
                                                    element.innerHTML;
                                                }
                                            }
                                    }
                                </script>
                            </div>
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
                        <% cm = new ColtivazioneManager();
                            for (int i = 0; i < misurazioneSensoreBeansUmidita.size(); i++) {
                                colorUmidità = "green";

                                if (resultUmidita >= temporaryPiantaBean.getUmidita_max() || resultUmidita <= temporaryPiantaBean.getUmidita_min()) {
                                    colorUmidità = "red";
                                }
                                %>
                        <h8>Sensore Umidità <%=misurazioneSensoreBeansUmidita.get(i).getSensore_id()%>
                            : <%=misurazioneSensoreBeansUmidita.get(i).getValore()%>%
                        </h8><br>
                        <%}%>
                    </div>
                <% out.print("<br>" +
                        // ho aggiunto questo form, poi te lo gestisci tu, basta che chiama servletSuggerimenti e c'è l'id della coltivazione
                        "<form action=\"SuggerimentiServlet\" method=\"get\">\n" +
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
    <div class="tab-pane fade" id="ph-tab-pane" role="tabpanel" aria-labelledby="ph-tab-pane" tabindex="0">
        <div class="card" style="width: auto;">
            <div class="card-body">
                <div style="width: auto;" class="row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                    <div id="phCanvas">
                        <script type="text/javascript">
                            function findPhChart() {
                                var coltivazioneID = <%=coltivazioneID%>;
                                var tipoSensore = "pH"
                                    var xhr = new XMLHttpRequest();
                                    xhr.open("POST", "StoricoServlet");
                                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                    xhr.send("today=today"+"&coltivazioneID="+coltivazioneID+"&tipoSensore="+tipoSensore);
                                    xhr.onreadystatechange = function () {
                                        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                                            var chartData = JSON.parse(xhr.responseText);
                                            var chart2 = new CanvasJS.Chart("phCanvas", chartData);
                                            chart2.render();
                                        } else if (xhr.status === 500){
                                            var element = document.getElementsByClassName("container");
                                            element.innerHTML;
                                        }
                                    }
                            }
                        </script>
                    </div>
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
                        <% for (int i = 0; i < misurazioneSensoreBeansPh.size(); i++) {
                                colorPH = "green";

                            if (resultPH >= temporaryPiantaBean.getPh_max() || resultPH <= temporaryPiantaBean.getPh_min()) {
                                colorPH = "red";
                            }
                            %>
                        <h8>Sensore pH <%=misurazioneSensoreBeansPh.get(i).getSensore_id()%>
                            : <%=misurazioneSensoreBeansPh.get(i).getValore()%>
                        </h8><br>
                        <%}%>
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
                        <div id="TemperaturaCanvas">
                            <script type="text/javascript">
                                function findTemperaturaChart() {
                                    var coltivazioneID = <%=coltivazioneID%>;
                                    var tipoSensore = "Temperatura"
                                        var xhr = new XMLHttpRequest();
                                        xhr.open("POST", "StoricoServlet");
                                        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                        xhr.send("today=today"+"&coltivazioneID="+coltivazioneID+"&tipoSensore="+tipoSensore);
                                        xhr.onreadystatechange = function () {
                                            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                                                var chartData = JSON.parse(xhr.responseText);
                                                var chart3 = new CanvasJS.Chart("TemperaturaCanvas", chartData);
                                                chart3.render();
                                            } else if (xhr.status === 500){
                                                var element = document.getElementsByClassName("container");
                                                element.innerHTML;
                                            }
                                        }
                                }
                            </script>
                    </div>
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
                        <%  for (int i = 0; i < misurazioneSensoreBeansTemperatura.size(); i++) {
                                colorTemperatura = "green";

                                if (resultTemperatura >= temporaryPiantaBean.getTemperatura_max() || resultTemperatura <= temporaryPiantaBean.getTemperatura_min()) {
                                colorTemperatura = "red";
                            }
                        %>
                        <h8>Sensore temperatura <%=misurazioneSensoreBeansTemperatura.get(i).getSensore_id()%>
                            : <%=misurazioneSensoreBeansTemperatura.get(i).getValore()%>&degC
                        </h8><br>
                        <%}%>
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
                    <% for (FisiopatieBean fisiopatia : listaFisiopatie) { %>
                    <div style="display:flex; flex-direction: column;">
                        <div style="display:flex; ">
                            <div style="width:22%; font-weight:bold;">Pianta: </div><div> <%= nomePianta %></div>
                        </div>
                        <div style="display:flex; ">
                            <div style="width:22%; font-weight:bold;">Nome: </div><div> <%= fisiopatia.getNome() %></div>
                        </div>
                        <div style="display:flex; ">
                            <div style="width:22%; font-weight:bold;">Umidità Terra Min: </div><div> <%= fisiopatia.getUmid_terr_min() %></div>
                        </div>
                        <div style="display:flex; ">
                            <div style="width:22%; font-weight:bold;">Umidità Terra Max: </div><div> <%= fisiopatia.getUmid_terr_max() %></div>
                        </div>
                        <div style="display:flex; ">
                            <div style="width:22%; font-weight:bold;">Temperatura Min: </div><div> <%= fisiopatia.getTemp_min() %>°</div>
                        </div>
                        <div style="display:flex; ">
                            <div style="width:22%; font-weight:bold;">Temperatura Max: </div><div> <%= fisiopatia.getTemp_max() %>°</div>
                        </div>
                        <% if (resultTemperatura != null) { %>
                        <div style="display:flex; ">
                            <div style="width:22%; font-weight:bold;">Temperatura attuale: </div><div style="font-weight:bold;"> <%= resultTemperatura %>°</div>
                        </div>
                        <% } %>
                        <div style="display:flex; ">
                            <div style="width:22%; font-weight:bold;">Umidità Aria Min: </div><div> <%= fisiopatia.getUmid_aria_min() %></div>
                        </div>
                        <div style="display:flex; ">
                            <div style="width:22%; font-weight:bold;">Umidità Aria Max: </div><div> <%= fisiopatia.getUmid_aria_max() %></div>
                        </div>
                        <% if (resultUmidita != null ) { %>
                        <div style="display:flex; ">
                            <div style="width:22%; font-weight:bold;">Umidità aria attuale: </div><div style="font-weight:bold;"> <%= resultUmidita %>°</div>
                        </div>
                        <% } %>
                    </div>
                    <!-- <% if (resultUmidita != null ) { %>
                        <div style="display:flex;">
                            <div style="width:15%;">Umidità terreno attuale</div>
                            <div style="width:15%;"><%= resultUmidita %></div>
                            <div style="width:15%;"><%= fisiopatia.getUmid_terr_min() %></div>
                            <div style="width:15%;"><%= fisiopatia.getUmid_terr_max() %></div>
                        </div>
                        <% } %> -->


                    <div>
                        <div style="font-weight:bold;">Descrizione:</div>
                        <div><%= fisiopatia.getDescrizione() %></div>
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
        <form method="ColtivazioniServlet" action="post">
            <input id="periodo-inizio" type="date" max="<%=new java.sql.Date(System.currentTimeMillis())%>"
                   name="data_inizio_periodo" required>
            <input id="periodo-fine" type="date" max="<%=new java.sql.Date(System.currentTimeMillis())%>"
                   name="data_fine_periodo" required>
            <select id="selectSensore" required>
                <option name="tipoSensore" value="umidita">Umidità</option>
                <option name="tipoSensore" value="pH">pH</option>
                <option name="tipoSensore" value="temperatura">Temperatura</option>
            </select>
            <button id="rilevamentiPerPeriodo" type="button" class="btn btn-success" onclick="loadJsonAndPrintChart()" style="height: auto; width: auto;">Mostra per questi periodi</button>
        </form>
        <div id="rilevamentiPerPeriodoCanvas" style="height: auto; width: auto;"></div>
        <script type="text/javascript">
            function loadJsonAndPrintChart() {
                var coltivazioneID = <%=coltivazioneID%>;
                var inputInizio = document.getElementById("periodo-inizio").value;
                var inputFine = document.getElementById("periodo-fine").value;
                var tipoSensore = document.getElementById("selectSensore").value;
                if (inputInizio != null && inputFine != null) {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "StoricoServlet");
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhr.send("data_inizio_periodo="+ inputInizio +"&data_fine_periodo="+ inputFine +"&coltivazioneID="+coltivazioneID+"&tipoSensore="+tipoSensore);
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                            var chartData = JSON.parse(xhr.responseText);
                            var chart = new CanvasJS.Chart("rilevamentiPerPeriodoCanvas", chartData);
                            chart.render();
                        } else if (xhr.status === 500){
                            var element = document.getElementsByClassName("container");
                            element.innerHTML;
                        }
                    }
                }
            }
        </script>
        <script src="canvas/canvasjs.min.js"></script>
    </div>
</div>
</div>
    <!-- Fine coltivazioni -->

    <%@include file="fragments/footer.html" %>
</body>
</html>