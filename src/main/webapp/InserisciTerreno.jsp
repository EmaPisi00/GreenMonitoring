<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
  <title>InserisciTerreno</title>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>


</head>

<% UtenteBean u= (UtenteBean) session.getAttribute("currentUserSession");
  if (!(u instanceof AziendaBean))  { %>
  <% response.sendRedirect("error.jsp"); %>
<% } %>
<body >

<form id="inserisci_terreno" action="ServletTerreno" method="post" enctype="multipart/form-data" class="row g-3 needs-validation" novalidate>


  <input type="text" id="azienda" name="azienda" value="<%=u.getEmail()%>"  style="display: none"><br><br>

  <div class="form-group">
    <label for="latitudine">latitudine:</label>
    <input type="text" class="form-control<%= request.getAttribute("erroreLatitudine") != null ? " is-invalid" : "" %>" id="latitudine" name="latitudine" placeholder="Inserisci latitudine">
    <% if(request.getAttribute("erroreLatitudine") != null) { %>
    <div class="invalid-feedback">
      <%= request.getAttribute("erroreLatitudine") %>
    </div>
    <% } %>
  </div>

  <br>

  <div class="col-md-4">
    <label for="longitudine" class="form-label" >longitudine:</label>
    <input type="text" class="form-control<%= request.getAttribute("erroreLongitudine") != null ? " is-invalid" : "" %>" id="longitudine" name="longitudine" placeholder="Inserisci longitudine">
    <% if(request.getAttribute("erroreLongitudine") != null) { %>
    <div class="invalid-feedback">
      <%= request.getAttribute("erroreLongitudine") %>
    </div>
    <% } %>
  </div>
  <br>


  <div class="col-md-4">
    <label for="superfice" class="form-label" >superfice:</label>
    <input type="text" class="form-control<%= request.getAttribute("erroreSuperfice") != null ? " is-invalid" : "" %>" id="superfice" name="superfice" placeholder="Inserisci superfice">
    <% if(request.getAttribute("erroreSuperfice") != null) { %>
    <div class="invalid-feedback">
      <%= request.getAttribute("erroreSuperfice") %>
    </div>
    <% } %>
  </div>
  <br>

  <label for="immagine">immagine:</label>
  <input type="file" id="immagine" name="immagine"  accept=".png, .jpg, .jpeg"><br><br>

  <div class="col-12">
    <button class="btn btn-primary">
    <input type="submit" value="inserisciTerreno_submit" name="inserisciTerreno_submit">
    </button>
  </div>
</form>
<script>
  (function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
            .forEach(function (form) {
              form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                  event.preventDefault()
                  event.stopPropagation()
                }

                form.classList.add('was-validated')
              }, false)
            })
  })()
</script>
</body>
</html>
