<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<!doctype html>
<html lang="en">
<head>
  <!-- Import Bootstrap -->
  <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

  <!-- Import css -->
  <link rel="stylesheet" href="css/footer.css">
  <link rel="stylesheet" href="css/headerLogin.css">
  <title>Associazione Dipendente-Azienda</title>
</head>
  <% UtenteBean user = (UtenteBean) session.getAttribute("currentUserSession");
    if (user instanceof AziendaBean && ((DipendenteBean)user).getAzienda() != null)  {
      //l'if controlla se l'utente è un dipendente e se è già associato ad un'azienda
      response.sendRedirect("Profile.jsp");
    } %>
  <body >


<div class="container">
    <form method="post" action="AssociationServlet">
      <label for="codiceAzienda">Codice:</label>
      <input type="text" id="codiceAzienda" name="codiceAzienda">

      <input type="submit" value="Associa" name="Associa">
    </form>
</div>
  
  <%@include file="fragments/footer.html"%>
  </body>
</html>