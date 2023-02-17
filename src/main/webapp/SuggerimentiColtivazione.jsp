<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
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
<%@include file="fragments/headerLoggedAzienda.html" %>
<%MeteoApiAdapter meteoApi = new OpenMeteoApiAdapterImpl();
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

<table>
    <tr>
        <th>Informazioni sul tempo</th>
    </tr>
    <%  DatiMeteoBean meteo = meteoApi.getTomorrowRain(terrenoBean.getLatitudine(), terrenoBean.getLongitudine());{ %>
    <tr>
        <td>Temperatura minima: <%= meteo.getTemperatura_min() %>°<br>
            Temperatura massima: <%= meteo.getTemperatura_max() %>°<br>
            Millimetri di pioggia: <%= meteo.getRain() %><br>
    </tr>
</table>
<table>
    <tr>
        <%
            long weather_code = meteo.getWeather_code();
            if (weather_code < 56 && (misurazione > umidMax || (umidMax - misurazione)<= 5)) {
                out.println("Domani non pioverà però è consigliato disattivare l'irrigazione in quanto " +
                        "l'umidità della pianta è quasi ai suoi limiti.");
               %> <img src="img\NonIrrigare.jpg" alt="NonIrrigare"> <%
            }else if (weather_code < 56 && (umidMax - misurazione) >= (umidMax - umidMin)/2) {
                out.println("Domani non pioverà ed è consigliato di attivare l'irrigazione in quanto " +
                        "il terreno risulta alquanto secca e ha bisogno di idratazione.");
            } else if (weather_code == 56 || weather_code == 57 && (umidMax - misurazione) >= (umidMax - umidMin)/2) {
                out.println("Domani ci sarà una leggera pioggia, è consigliato non irrigare.");
            } else if (weather_code == 56 || weather_code == 57 && (umidMax - misurazione) <= (umidMax - umidMin)/2) {
                out.println("Domani ci sarà una leggera pioggia, però è consigliato irrigare in quanto " +
                        "il terrento è asciutto.");
            } else if (weather_code == 61 || weather_code == 63 || weather_code == 65) {
                out.println("Domani ci sarà una forte pioggia, è consigliato disattivare l'irrigazione.");
            }
        %>
    </tr>
</table>
<% } %>


<%@include file="fragments/footer.html"%>
</body>
</html>
