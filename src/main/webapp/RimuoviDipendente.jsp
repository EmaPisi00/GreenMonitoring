<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO" %>


<%
  UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
  if (u instanceof AziendaBean) {
    DipendenteDAO d = new DipendenteDAOImpl();
    List<DipendenteBean> dipendenti = d.retrieveAllByCode(u.getEmail());

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
    //mettere il popup della conferma rimozione con Modal (su bootstrap)
  }  %>
  </body>
  </html>
