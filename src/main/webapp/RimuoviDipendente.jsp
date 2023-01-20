<%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 19/01/2023
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl" %>



<%
  UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
  if (u instanceof AziendaBean) {
    AziendaBean azienda = (AziendaBean) u;
    DipendenteDAOImpl d = new DipendenteDAOImpl();
    List<DipendenteBean> dipendenti = d.retrieveAllForKey(u.getEmail());

%>
  <html>
  <body>
  <h1>Gestione dipendenti</h1>
  <form action="ServletDipendente" method="post">
    <table>
      <tr>
        <th>Seleziona</th>
        <th>ID</th>
        <th>Nome</th>
        <th>Cognome</th>
      </tr>
      <% for (DipendenteBean dipendente : dipendenti) { %>
      <tr>
        <td><input type="checkbox" name="idDipendente" value="<%= dipendente.getEmail() %>"></td>
        <td><%= dipendente.getEmail() %></td>
        <td><%= dipendente.getNome() %></td>
        <td><%= dipendente.getCognome() %></td>
      </tr>
      <% }   %>
    </table>
    <input type="submit" value="Rimuovi associazione azienda">
  </form>
  <% } else {
    response.sendRedirect("error.jsp");
  }  %>
  </body>
  </html>
