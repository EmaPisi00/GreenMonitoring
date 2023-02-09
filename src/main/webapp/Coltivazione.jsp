<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Date" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionesensore.MisurazioneSensoreManager" %>
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
    <div class="row" style="width: 99%">
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
            <button class="nav-link" id="disabled-tab" data-bs-toggle="tab" data-bs-target="#disabled-tab-pane" type="button" role="tab" aria-controls="disabled-tab-pane" aria-selected="false" disabled>Archivia Coltivazione</button>
        </li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <h5 class="card-title">Info coltivazione</h5>
                        <%  /* -- INIZIO AUTENTICAZIONE -- */
                            Object sa = session.getAttribute("currentUserSession");
                            System.out.println("[Coltivazione.jsp] - Sono in coltivazionejsp - ");
                            ColtivazioneBean cb = null;
                            if (sa == null) {
                                response.sendError(401);
                            } else if (!(session.getAttribute("currentUserSession") instanceof UtenteBean)) {
                                response.sendError(401);
                            }
                            /* -- PASSATI I TEST, IL CONTAINER APRE IL RESTO DELLA PAGINA -- */
                            else {
                                System.out.println("[Coltivazione.jsp] - Superati i controlli - ");
                                String urlImmagine = new String("Foto coltivazione");
                                List<ColtivazioneBean> list = null;
                                List<SensoreBean> sList = null;
                                List<PiantaBean> piantaBeanList = null;
                                ColtivazioneManager cm = new ColtivazioneManager();
                                PiantaManager pm = new PiantaManager();
                                SensoreManager sm = new SensoreManager();
                                String nomePianta = new String();
                                String IDazienda = new String();
                                Integer coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
                                if ((session.getAttribute("currentUserSession") instanceof DipendenteBean)) {
                                    DipendenteBean a = (DipendenteBean) sa;
                                    IDazienda = a.getAzienda();
                                    cb = cm.visualizzaStatoColtivazioni(a.getAzienda()).stream().filter(o -> o.getId() == coltivazioneID).findFirst().orElse(null);
                                    System.out.println("[Coltivazione.jsp] - ColtivazioneBean è - " +cb);
                                    sList = sm.visualizzaListaSensori(a.getAzienda()).stream().filter(o -> o.getColtivazione() == coltivazioneID).toList();
                                    System.out.println("[Coltivazione.jsp] - SensoreList (sList) è - " +sList);
                                    list = cm.visualizzaStatoColtivazioni(a.getAzienda());
                                    System.out.println("[Coltivazione.jsp] - statoColtivazioni (list) è - " +list);
                                    piantaBeanList = pm.ListaPianteManager(a.getAzienda());
                                    System.out.println("[Coltivazione.jsp] - piantaBeanList è - " +piantaBeanList);
                                    for (int i = 0; i < piantaBeanList.size(); i++) {
                                        for (int j = 0; j < list.size(); j++) {
                                            if (piantaBeanList.get(i).getId().equals(list.get(j).getPianta())) {
                                                nomePianta = piantaBeanList.get(i).getNome();
                                                break;
                                            }
                                        }
                                    }
                                    System.out.println("[Coltivazione.jsp] - superati i due for - ");
                                    System.out.println("[Coltivazione.jsp] - nomePianta è - " +nomePianta);
                                } else {
                                    AziendaBean a = (AziendaBean) sa;
                                    IDazienda = a.getEmail();
                                    cb = cm.visualizzaStatoColtivazioni(a.getEmail()).stream().filter(o -> o.getId() == coltivazioneID).findFirst().orElse(null);
                                    System.out.println("[Coltivazione.jsp] - ColtivazioneBean è - " +cb);
                                    list = cm.visualizzaStatoColtivazioni(a.getEmail());
                                    sList = sm.visualizzaListaSensori(a.getEmail()).stream().filter(o -> o.getColtivazione() == coltivazioneID).toList();
                                    System.out.println("[Coltivazione.jsp] - SensoreList (sList) è - " +sList);
                                    piantaBeanList = pm.ListaPianteManager(a.getEmail());
                                    System.out.println("[Coltivazione.jsp] - piantaBeanList è - " +piantaBeanList);
                                    for (int i = 0; i < piantaBeanList.size(); i++) {
                                        for (int j = 0; j < list.size(); j++) {
                                            if (piantaBeanList.get(i).getId().equals(list.get(j).getPianta())) {
                                                nomePianta = piantaBeanList.get(i).getNome();
                                                urlImmagine = piantaBeanList.get(i).getImmagine();
                                                break;
                                            }
                                        }
                                    }
                                    System.out.println("[Coltivazione.jsp] - superati i due for - ");
                                    System.out.println("[Coltivazione.jsp] - nomePianta è - " + nomePianta);
                                }
                                out.print("<ul><li class=\"list-group-item \">" +
                                        "Coltivazione di " + nomePianta + "<br>" +
                                        "<img src=\"" + urlImmagine + "\" alt=\"Foto coltivazione\">");
                                ColtivazioneManager coltivazionemanager = new ColtivazioneManager();
                            Double resultUmidita = coltivazionemanager.restituisciMisurazioniRecenti("umidità", coltivazioneID);
                            Double resultTemperatura = coltivazionemanager.restituisciMisurazioniRecenti("temperatura", coltivazioneID);
                            Double resultPH = coltivazionemanager.restituisciMisurazioniRecenti("pH", coltivazioneID);
                            String colorPH = "blue";
                            String colorTemperatura = "blue";
                            String colorUmidità = "blue";
                            /*
                            if (resultUmidita è lontano dal valore ottimale) {
                                colorUmidità = "red";
                            } else if (resultTemperatura è lontano dal valore ottimale) {
                                colorTemperatura = "red";
                            } else if (resultPH è lontano dal valore ottimale) {
                                colorPH = "red";
                            }
                            */}
                        %>
                        <h5>Umidità media</h5>
                        <div style="width: 50%; height: 20px; background-color: #ddd;display: flex; align-items: center;">
                            <div style="width: <%=resultUmidita%>%; height: 20px; background-color: <%=colorUmidità%>;"></div>
                            System.out.println("["+ "\u001B31 Coltivazione.jsp"+ "\u001B00]" - restultUmidità - " +resultUmidita +" - colorUmidita - " +colorUmidità);
                            <%=resultUmidita%>%
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
                            <div style="width: <%if (resultPH.equals(0)) { Integer baseCasePH = 0; out.print(baseCasePH); } else { out.print((resultPH * 10) + 10); }%>%; height: 20px; background-color: <%=colorPH%>;"></div>
                            <%if (resultPH.equals(0)) { Integer baseCasePH = 0; out.print(baseCasePH); } else { out.print((resultPH * 10) + 10); }%>
                            <div style="width: 10px; height: 10px; background-color: <%=colorPH%>; border-radius: 50%;"></div>
                        </div>
                        <% out.print("<br>" +
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
                        <div>
                            <% coltivazionemanager = new ColtivazioneManager();
                                    List<MisurazioneSensoreBean> misurazioneSensoreBeans = coltivazionemanager.visualizzaMisurazioneOggiColtivazione(coltivazioneID, "umidità");
                                    for (int i = 0; i < misurazioneSensoreBeans.size() ;i++) {
                                            out.print("<li class=\"list-group-item\"> Sensore umidità" + misurazioneSensoreBeans.get(i).getId() + ": " + misurazioneSensoreBeans.get(i).getValore() + "</li>");
                                    }
                                    %>
                        </div>
                        <div style="width: 50%; height: 20px; background-color: #ddd;display: flex; align-items: center;">
                            <div style="width: <%=resultUmidita%>%; height: 20px; background-color: <%=colorUmidità%>;"></div>
                            <%=resultUmidita%>%
                            <div style="width: 10px; height: 10px; background-color: <%=colorUmidità%>; border-radius: 50%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="contact-tab-pane" role="tabpanel" aria-labelledby="contact-tab" tabindex="0">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <div>
                            <% coltivazionemanager = new ColtivazioneManager();
                                misurazioneSensoreBeans = coltivazionemanager.visualizzaMisurazioneOggiColtivazione(coltivazioneID, "pH");
                                    for (int i = 0; i < misurazioneSensoreBeans.size() ;i++) {
                                            out.print("<li class=\"list-group-item\"> Sensore umidità" + misurazioneSensoreBeans.get(i).getId() + ": " + misurazioneSensoreBeans.get(i).getValore() + "</li>");
                                    }
                                    %>
                        </div>
                        <div style="width: 50%; height: 20px; background-color: #ddd;display: flex; align-items: center;">
                            <div style="width: <%if (resultPH.equals(0)) { Integer baseCasePH = 0; out.print(baseCasePH); } else { out.print((resultPH * 10) + 10); }%>%; height: 20px; background-color: <%=colorPH%>;"></div>
                            <%if (resultPH.equals(0)) { Integer baseCasePH = 0; out.print(baseCasePH); } else { out.print((resultPH * 10) - 40); }%>
                            <div style="width: 10px; height: 10px; background-color: <%=colorPH%>; border-radius: 50%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="tab-pane fade" id="contact-tab-pane2" role="tabpanel" aria-labelledby="contact-tab2" tabindex="0">
            <div class="col-sm-6 mb-3 mb-sm-0">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <div>
                            <% coltivazionemanager = new ColtivazioneManager();
                                    misurazioneSensoreBeans = coltivazionemanager.visualizzaMisurazioneOggiColtivazione(coltivazioneID, "temperatura");
                                    for (int i = 0; i < misurazioneSensoreBeans.size() ;i++) {
                                        out.print("<li class=\"list-group-item\"> Sensore temperatura" + misurazioneSensoreBeans.get(i).getId() + ": " + misurazioneSensoreBeans.get(i).getValore() + "</li>");
                                    }
                                    %>
                        </div>
                        <div style="width: 50%; height: 20px; background-color: #ddd;display: flex; align-items: center;">
                            <div style="width: <%=resultTemperatura%>%; height: 20px; background-color: <%=colorTemperatura%>;"></div>
                            <%=resultTemperatura%>"\00b0"C

                            <div style="width: 10px; height: 10px; background-color: <%=colorTemperatura%>; border-radius: 50%;"></div>
                        </div>
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
                                    }
                                    ;xhr.send();
                                }
                            }
                        }
                    </script>
                </div>
            </div>
        </div>

        <div class="col-sm-6">
            <div class="card" style="width: auto;">
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
