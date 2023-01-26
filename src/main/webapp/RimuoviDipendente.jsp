<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO" %>
<head>
  <!-- Import Bootstrap -->
  <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

  <!-- Import css -->
  <link rel="stylesheet" href="css/footer.css">
  <link rel="stylesheet" href="css/headerLogin.css">

</head>

<%
  UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
  if (u instanceof AziendaBean) { %>
<%@include file="fragments/footer.html"%>
<%
    DipendenteDAO d = new DipendenteDAOImpl();
    List<DipendenteBean> dipendenti = d.retrieveAllByCode(u.getEmail());

%>
  <html>
  <head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

  </head>
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
  
  <%@include file="fragments/footer.html"%>
  </body>
  </html>
