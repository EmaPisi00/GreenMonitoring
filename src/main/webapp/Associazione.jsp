<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<!doctype html>
<html lang="en">
<head>
  <title>Associazione Dipendente-Azienda</title>
</head>
  <% UtenteBean user = (UtenteBean) session.getAttribute("currentUserSession");
    if (user instanceof AziendaBean && ((DipendenteBean)user).getAzienda() != null)  {
      //l'if controlla se l'utente è un dipendente e se è già associato ad un'azienda
      response.sendRedirect("Profile.jsp");
    } %>
  <body >
    <form method="post" action="AssociationServlet">
      <label for="codiceAzienda">Codice:</label>
      <input type="text" id="codiceAzienda" name="codiceAzienda">

      <input type="submit" value="Associa" name="Associa">
    </form>
  </body>
</html>