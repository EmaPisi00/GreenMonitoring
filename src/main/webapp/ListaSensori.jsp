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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">
    <title>Lista Sensori</title>
</head>
<body>
<%
    UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
    if (u instanceof AziendaBean) { %>
            <%@include file="fragments/headerLogged.html"%>
      <%  SensoreDAOImpl sensoreDAO = new SensoreDAOImpl();
        List<SensoreBean> sensori = sensoreDAO.retrieveAllByAzienda(u.getEmail());
%>
<table>
    <tr>
        <th>ID</th>
        <th>Tipo</th>
        <th>ID Mosquitto</th>
        <th>Azienda</th>
    </tr>
    <% for (SensoreBean sensore : sensori) { %>
    <tr>
        <td><%= sensore.getId() %></td>
        <td><%= sensore.getTipo() %></td>
        <td><%= sensore.getIdM() %></td>
        <td><%= sensore.getAzienda() %></td>
    </tr>
    <% } %>
</table>
<a href="InserisciSensore.jsp" class="button">Inserisci sensore</a>
<% } else { %>
<p>Accesso negato, non sei autorizzato a visualizzare questa pagina</p>
<% } %>
</body>
</html>
