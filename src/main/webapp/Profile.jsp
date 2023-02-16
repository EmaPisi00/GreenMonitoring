<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.AziendaDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.UtenteManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 17/01/2023
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>

<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

</head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<% UtenteBean user= (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if (user == null)  {
        response.sendRedirect("error.jsp");
    } else if(user instanceof AziendaBean){ %>
<%@ include file="/fragments/headerLoggedAzienda.html" %>
<%} else if (user instanceof DipendenteBean){ %>
<%@ include file="/fragments/headerLoggedDipendente.html" %>

<% }%>
<html>
<body>

<h1>Dettagli utente attualmente in sessione</h1>

<table>
    <tr>
        <td>Email:</td>
        <td><%= user.getEmail() %></td>
    </tr>
    <tr>
        <td>Password:</td>
        <td><%= user.getPassword() %></td>
    </tr>
    <tr>
        <td>Telefono:</td>
        <td><%= user.getTelefono() %></td>
    </tr>
    <tr>
        <td>Città:</td>
        <td><%= user.getCitta() %></td>
    </tr>
    <tr>
        <td>Provincia:</td>
        <td><%= user.getProvincia() %></td>
    </tr>
    <tr>
        <td>Indirizzo:</td>
        <td><%= user.getIndirizzo() %></td>
    </tr>

    <%  if (user instanceof DipendenteBean)  {
        AziendaDAO aziendaDAO = new AziendaDAOImpl();
        AziendaBean azienda = aziendaDAO.retrieveForKey(((DipendenteBean) user).getAzienda());
    %>

    <tr>
        <td>Nome:</td>
        <td><%= ((DipendenteBean) user).getNome() %></td>
    </tr>
    <tr>
        <td>Cognome:</td>
        <td><%= ((DipendenteBean) user).getCognome() %></td>
    </tr>
    <tr>
        <td>Azienda:</td>
        <td><%= azienda.getNome_azienda() %></td>
    </tr>
    <% } else if (user instanceof AziendaBean) { %>
    <tr>
        <td>Nome Azienda:</td>
        <td><%= ((AziendaBean) user).getNome_azienda() %></td>
    </tr>
    <tr>
        <td>Partita IVA:</td>
        <td><%= ((AziendaBean) user).getPartita_iva() %></td>
    </tr>
    <% } %>
</table>

<button onclick="location.href='ModificaProfilo.jsp'">Modifica profilo</button>


<%@include file="fragments/footer.html"%>

</body>
</html>


