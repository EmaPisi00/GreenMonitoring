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

  <style>
    /* Stile per la finestra modale */
    #confirmModal {
      text-align: center;
    }
    #confirmModal .modal-dialog {
      display: inline-block;
      text-align: left;
      vertical-align: middle;
    }
  </style>

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
<!-- Modal di bootstrap per mostrare messaggio di successo o errore -->
<div class="modal" id="modal-message">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
      </div>
    </div>
  </div>
</div>
<% if (request.getAttribute("success") != null) { %>
<script>
  $(document).ready(function() {
    $("#modal-message .modal-title").text("Operazione completata");
    $("#modal-message .modal-body").text("La rimozione dell'associazione dipendente è stata completata con successo.");
    $("#modal-message").modal("show");
  });
</script>
<% } else if (request.getAttribute("error") != null) { %>
<script>
  $(document).ready(function() {
    $("#modal-message .modal-title").text("Errore");
    $("#modal-message .modal-body").text("Si è verificato un errore nella rimozione dell'associazione dipendente. Riprova più tardi.");
    $("#modal-message").modal("show");
  });
</script>
<% } }%>

<%@include file="fragments/footer.html"%>
</body>
</html>
<script type="text/javascript">
  function openConfirmModal() {
    $("#confirmModal").modal("show");
  }

  // Chiusura della finestra modale
  function closeConfirmModal() {
    $("#confirmModal").modal("hide");
  }

  // Rimozione del dipendente selezionato
  function removeEmployee() {
    // chiamare il codice per rimuovere il dipendente dal database
    // mostrare un messaggio di successo o di errore
    // chiudere la finestra modale
  }
</script>