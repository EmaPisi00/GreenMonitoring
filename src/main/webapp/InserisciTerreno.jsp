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


<body >


<form id="inserisci_terreno" action="ServletTerreno" method="post" enctype="multipart/form-data" class="row g-3 needs-validation" novalidate>

  <%
    String error = (String) request.getAttribute("erroreTerreno");
    %>
  <label for="azienda">Azienda:</label>
  <input type="text" id="azienda" name="azienda"><br><br>


  <%
    if (error != null) {
  %>
  <div class="text-danger text-sm">
    <%=error%>
  </div>
<% } %>
  <div class="col-md-4">
    <label for="latitudine" class="form-label" >latitudine:</label>
    <input type="number" max="90" min="0" id="latitudine" name="latitudine" required>
    <div class="valid-feedback">Ok!</div>
    <div class="invalid-feedback">
      Il valore deve essere compreso tra 0 e 90
    </div>

  </div>
  <br>

  <div class="col-md-4">
    <label for="longitudine" class="form-label" >longitudine:</label>
    <input type="number" max="180" min="0" id="longitudine" name="longitudine" required>
    <div class="valid-feedback">Ok!
    </div>
    <div class="invalid-feedback">
      Il valore deve essere compreso tra 0 e 180
    </div>
  </div>
  <br>


  <label for="superfice">superfice:</label>
  <input type="text" id="superfice" name="superfice"><br><br>

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
