<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
  <title>InserisciTerreno</title>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>


<body >


<form id="rimuovi_terreno" action="ServletTerreno" method="post" enctype="multipart/form-data" >
  <label for="azienda">Azienda:</label>
  <input type="text" id="azienda" name="azienda"><br><br>

  <label for="latitudine">latitudine:</label>
  <input type="text" id="latitudine" name="latitudine"><br><br>

  <label for="longitudine">longitudine:</label>
  <input type="text" id="longitudine" name="longitudine"><br><br>

  <label for="superfice">superfice:</label>
  <input type="text" id="superfice" name="superfice"><br><br>

  <label for="immagine">immagine:</label>
  <input type="file" id="immagine" name="immagine"  accept=".png, .jpg, .jpeg"><br><br>

  <input type="submit" value="inserisciTerreno_submit" name="inserisciTerreno_submit">

</form>
</body>
</html>

