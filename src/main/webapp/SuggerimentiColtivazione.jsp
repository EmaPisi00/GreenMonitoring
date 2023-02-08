<%@ page import="it.unisa.greenmonitoring.dataccess.dao.SensoreDAOImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.OpenMeteoApiAdapterImpl" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.MeteoApiAdapter" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession"); %>
<%@include file="fragments/headerLoggedAzienda.html" %>
<%MeteoApiAdapter meteoApi = new OpenMeteoApiAdapterImpl();
    ColtivazioneManager cm = new ColtivazioneManager();
    TerrenoManager tm = new TerrenoManager();
    Integer coltivazioneID = Integer.parseInt((String) session.getAttribute("coltivazioneID"));
    ColtivazioneBean coltivazioneBean = cm.retrieveColtivazioneSingola(coltivazioneID);
    TerrenoBean terrenoBean = tm.restituisciTerrenoDaInt(coltivazioneBean.getTerreno());

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
            Codice meteo: <%= meteo.getWeather_code() %></td>
    </tr>
</table>
<table>
    <tr>
        <%
            long weather_code = meteo.getWeather_code();
            if (weather_code < 56) {
                out.println("Domani non pioverà, ti consigliamo di irrigare il terreno domani.");
            } else if (weather_code == 56 || weather_code == 57) {
                out.println("Domani ci sarà poca pioggia, ti suggeriamo di non irrigare.");
            } else if (weather_code == 61 || weather_code == 63 || weather_code == 65) {
                out.println("Domani fa o temporal, statt a cas.");
            }
        %>
    </tr>
</table>
<% } %>


<%@include file="fragments/footer.html"%>
</body>
</html>
