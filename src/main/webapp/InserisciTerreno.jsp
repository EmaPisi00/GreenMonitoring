        <%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.TerrenoBean" %>
<!doctype html>
<html lang="en">
<head>
  <!-- Import Bootstrap -->
  <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

  <!-- Import css -->
  <link rel="stylesheet" href="css/footer.css">
  <link rel="stylesheet" href="css/headerLogin.css">

  <title>Inserisci terreno</title>
  <script src="./jquery/jquery-3.6.3.min.js"></script>
  <link href="/img/favicon.png" rel="icon">
  <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
  <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<% UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
  TerrenoBean t= (TerrenoBean) request.getAttribute("erroriTerrenoBean");;
  if (!(u instanceof AziendaBean)) { %>
<% response.sendRedirect("error.jsp"); %>
<% } else {
%>
<%@include file="fragments/headerLogged.html"%>
<%}%>
<body >
<br>
<div class="container">
  <div class="row justify-content-center">
    <div class="card col-md-6">
      <div class="card-body">
        <form id="inserisci_terreno" action="ServletTerreno" method="post" enctype="multipart/form-data"
          class="needs-validation" >

          <input type="text" id="azienda" name="azienda" value="<%=u.getEmail()%>"  style="display: none">
          <% if (t != null && t.getId() == null) { %>
          <div class="text-danger"> esiste già un terreno con questa latitudine e longitudine </div>
          <% } %>

          <div>
            <% if (t != null && t.getLatitudine() == null) { %>
            <div class="text-danger text-center">
              Errore latitudine
            </div>
            <% } %>
            <label for="latitudine"  class="text-left-label">latitudine</label>
            <input type="text" class="form-control" id="latitudine" name="latitudine" placeholder="Inserisci latitudine" required>
          </div>


          <div>
          <% if (t != null && t.getLongitudine() == null) { %>
            <div class="text-danger" >Errore longitudine</div>
          <% } %>
            <label for="longitudine" class="form-label" >longitudine:</label>
            <input type="text" class="form-control" id="longitudine" name="longitudine" placeholder="Inserisci longitudine" required>
          </div>


          <div>
          <% if (t != null && t.getSuperficie() == null) { %>
            <div class="text-danger">Errore superfice</div>
          <% } %>
            <label for="superfice" class="form-label" >superfice:</label>
            <input type="text" class="form-control" id="superfice" name="superfice" required>
          </div>


          <label for="immagine">immagine:</label>
          <input type="file" id="immagine" name="immagine"  accept=".png, .jpg, .jpeg"><br><br>

          <div>
            <button class="btn btn-primary">
              <input type="submit" value="inserisciTerreno_submit" name="inserisciTerreno_submit">
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>


<%@include file="/fragments/footer.html"%>

</body>
</html>

