<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.AdapterMeteo.MeteoApiAdapter" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.AdapterMeteo.OpenMeteoApiAdapterImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">
    <title>Suggerimenti Coltivazione</title>
</head>
<body>
<%
    UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
    if (u == null) {
        response.sendRedirect("error.jsp");
    }
%>
<%@include file="fragments/headerLoggedAzienda.jsp" %>
<%
    MisurazioneSensoreDAO misurazioneSensoreDAO = new MisurazioneSensoreDAOImpl();
    PiantaManager piantaManager = new PiantaManager();
    ColtivazioneManager cm = new ColtivazioneManager();
    TerrenoManager tm = new TerrenoManager();
    Integer coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
    ColtivazioneBean coltivazioneBean = cm.retrieveColtivazioneSingola(coltivazioneID);
    TerrenoBean terrenoBean = tm.restituisciTerrenoDaInt(coltivazioneBean.getTerreno());
    Double misurazione = misurazioneSensoreDAO.retrieveMostRecentMesurement("umidità", coltivazioneID);
    PiantaBean piantaBean = piantaManager.visualizzaPianta(coltivazioneBean.getPianta());
    Double umidMax = Double.valueOf(piantaBean.getUmidita_max());
    Double umidMin = Double.valueOf(piantaBean.getUmidita_min());
%>


<div class="container py-5">
    <div class=" text-center">
        <h5 class="display-3" style="color: black">Suggerimenti</h5>
    </div>
</div>


<div class="container py-2">
    <div class="col-12 text-center">
        <p style="font-size: 35px;">Informazioni sul tempo di domani per la coltivazione sul terreno
            " <%= terrenoBean.getNome()%> "</p>
    </div>
</div>
<div class="text-center"
     style="font-size: 30px; font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif ; line-height: 100px;">
    <% DatiMeteoBean meteo = cm.visualizzaDatiMeteo(terrenoBean.getLatitudine(), terrenoBean.getLongitudine());
        { %>
    <div class="col-12">
        Temperatura Minima:     <%= meteo.getTemperatura_min() %>
    </div>
    <div class="col-12">
        Temperatura Max:        <%= meteo.getTemperatura_max()%>
    </div>
    <div class="col-12">
        Millimetri di Pioggia:      <%= meteo.getRain()%>
    </div>
</div>

<div class="container h-100">
    <div class="container w-50 text-center py-5">
        <%
            long weather_code = meteo.getWeather_code();

            if (weather_code < 56 && (misurazione > umidMax || (umidMax - misurazione) <= 5)) { %>
        <div class="row">
            <div class="col-5">
                <img src="img/sun.png" style="margin-bottom: -50%;" width="50%">
            </div>

            <div class="col-5">
                <img src="img/NonIrrigare.jpg" style="margin-bottom: -50%;" width="50%">
            </div>
        </div>
        <div class="text-center" style="margin-top:144px">
            <p style="color:black">Domani non pioverà però è consigliato disattivare l'irrigazione in quanto l'umidità della pianta è quasi ai suoi limiti.</p>
        </div>

        <% } else if (weather_code < 56 && (umidMax - misurazione) >= (umidMax - umidMin) / 2) { %>
        <div class="row">
            <div class="col-5">
                <img src="img/sun.png" style="margin-bottom: -50%;" width="50%">
            </div>
            <div class="col-5">
                <img src="img/Irrigare.png" style="margin-bottom: -50%;" width="50%">
            </div>
        </div>
        <div class=" text-center" style="margin-top:144px">
            <p style="color:black">Domani non pioverà ed è consigliato di attivare l'irrigazione in quanto il terreno risulta alquanto secca e ha bisogno di idratazione.</p>
        </div>
        <% } else if (weather_code == 56 || weather_code == 57 && (umidMax - misurazione) >= (umidMax - umidMin) / 2) { %>
        <div class="row">
            <div class="col-5">
                <img src="img/Pioggia.jpg" style="margin-bottom: -50%;" width="50%">
            </div>
            <div class="col-5">
                <img src="img/NonIrrigare.jpg" style="margin-bottom: -50%;" width="50%">
            </div>
        </div>
        <div class="text-center" style="margin-top:144px">
            <p style="color:black">Domani ci sarà una leggera pioggia, è consigliato non irrigare.</p>
        </div>
        <% } else if (weather_code == 56 || weather_code == 57 && (umidMax - misurazione) <= (umidMax - umidMin) / 2) { %>
        <div class="row">
            <div class="col-5">
                <img src="img/Pioggia.jpg" style="margin-bottom: -50%;" width="50%">
            </div>
            <div class="col-5">
                <img src="img/Irrigare.png" style="margin-bottom: -50%;" width="50%">
            </div>
        </div>
        <div class=" text-center" style="margin-top:144px">
            <p style="color:black">Domani ci sarà una leggera pioggia, però è consigliato irrigare in quanto il terrento è asciutto.</p>
        </div>
        <% } else if (weather_code == 61 || weather_code == 63 || weather_code == 65) { %>
        <div class="row">
            <div class="col-5">
                <img src="img/Temporale.jpg" style="margin-bottom: -50%;" width="50%">
            </div>

            <div class="col-5">
                <img src="img/NonIrrigare.jpg" style="margin-bottom: -50%;" width="50%">
            </div>
        </div>
        <div class=" text-center" style="margin-top:144px">
            <p style="color:black">Domani ci sarà una forte pioggia, è consigliato disattivare l'irrigazione.</p>
        </div>
        <% } %>
        <% } %>
    </div>
</div>

<%@include file="fragments/footer.html" %>

</body>
</html>
