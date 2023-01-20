<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.AziendaDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %><%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 17/01/2023
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% UtenteBean user= (UtenteBean) session.getAttribute("currentUserSession");
    if (user == null)  { %>
    <% response.sendRedirect("error.jsp"); } %>
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
            <td><%= ((DipendenteBean) user).getAzienda() %></td>
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
    <button id="registratiButton" onclick="javascript:location.href='${pageContext.request.contextPath}/EditProfile.jsp'">Modifica profilo</button></p>
</body>
</html>
