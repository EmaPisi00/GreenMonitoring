<%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 24/01/2023
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.SensoreDAOImpl" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.SensoreBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.OpenMeteoApiAdapterImpl" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DatiMeteoBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.MeteoApiAdapter" %>
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
      <% MeteoApiAdapter meteoApi = new OpenMeteoApiAdapterImpl(); %>
<table>
    <tr>
        <th>Temperatura minima</th>
        <th>Temperatura massima</th>
        <th>Pioggia</th>
        <th>Codice Meteo</th>
    </tr>
    <%  DatiMeteoBean meteo = meteoApi.getTomorrowRain(40.7389,14.6005);{ %>
    <tr>
        <td><%= meteo.getTemperatura_min() %></td>
        <td><%= meteo.getTemperatura_max() %></td>
        <td><%= meteo.getRain() %></td>
        <td><%= meteo.getWeather_code() %></td>
    </tr>
    <% } %>
</table>

<%@include file="fragments/footer.html"%>
</body>
</html>
