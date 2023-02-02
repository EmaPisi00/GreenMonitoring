<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO" %>
<html>
<head>
  <!-- Import Bootstrap -->
  <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

  <!-- Import css -->
  <link rel="stylesheet" href="css/footer.css">
  <link rel="stylesheet" href="css/headerLogin.css">

  <style>
    @media screen and (max-width:768px) {
      .tohide { display: none; }
    }
  </style>
  <script src="./jquery/jquery-3.6.3.min.js"></script>
  <link href="/img/favicon.png" rel="icon">
  <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
  <script src="bootstrap-5.2.3-dist/js/RimuoviDipendente.js"></script>
  <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>


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
<%@include file="fragments/headerLoggedAzienda.html"%>
<%
  DipendenteDAO d = new DipendenteDAOImpl();
  List<DipendenteBean> dipendenti = d.retrieveAllByCode(u.getEmail());
%>


<body>
<h1>Gestione dipendenti</h1>
<form action="ServletDipendente" method="post" id="rimuovi_dipendente">
  <table>
    <tr>
      <th>Seleziona</th>
      <th>ID</th>
      <th>Nome</th>
      <th>Cognome</th>
    </tr>
    <% for (DipendenteBean dipendente : dipendenti) { %>
    <tr>
      <td><input id="chk" type="checkbox" name="idDipendente" value="<%= dipendente.getEmail() %>"></td>
      <td><%= dipendente.getEmail() %></td>
      <td><%= dipendente.getNome() %></td>
      <td><%= dipendente.getCognome() %></td>
    </tr>
    <% }   %>
  </table>
  <!-- Button trigger modal -->
  <button id="showModal" type="button" class="btn btn-primary" data-toggle="Modal" data-target="#exampleModalCenter">
    Rimuovi
  </button>
</form>

<!-- Modal -->
<div id=Modal class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-body">
        Sei sicuro di voler effettuare la rimozione?
      </div>
      <div class="modal-footer">
        <button id="closeModal" type="button" class="btn btn-secondary" data-dismiss="modal">Esci</button>
        <button id="summit" type="button" class="btn btn-primary">Conferma</button>
      </div>
    </div>
  </div>
</div>
<%@include file="fragments/footer.html"%>
</body>
<% } %>
</html>
