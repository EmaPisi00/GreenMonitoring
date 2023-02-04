<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
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
        <div class="col-sm-6 mb-3 mb-sm-0">
    <div class="card" style="width: 30rem;">
        <div class="card-body">
            <h5 class="card-title">Info coltivazione</h5>

            <%
                /* -- INIZIO AUTENTICAZIONE -- */
                Object sa = session.getAttribute("currentUserSession");
                int[] valoriMisurati = {0, 0, 0};
                if (sa == null) {
                    response.sendError(401);
                } else if ( ! (session.getAttribute("currentUserSession") instanceof UtenteBean)) {
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
                    if ((session.getAttribute("currentUserSession") instanceof DipendenteBean)) {
                        DipendenteBean a = (DipendenteBean) sa;
                        Integer coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
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
                        list = cm.visualizzaStatoColtivazioni(a.getEmail());
                        Integer coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
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
                            "<img src=\"" + urlImmagine +"\" alt=\"Foto coltivazione\">"  + "<br>" +
                            "<h7>media pH</h7> " + valoriMisurati[0] + "<br>" +
                            "<h7>media temperatura</h7> " + valoriMisurati[1] + "<br>" +
                            "<h7>media umidità</h7> " + valoriMisurati[2] + "<br>" +
                            "</li></ul>"
                    );
                }%>
        </div>
    </div>
            <button type="button" class="btn btn-light" href="./SuggerimentiColtivazione">Suggerimenti</button>
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
