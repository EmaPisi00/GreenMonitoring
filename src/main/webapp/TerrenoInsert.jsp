<%--
  Created by IntelliJ IDEA.
  User: stefa
  Date: 16/01/2023
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>InserisciTerreno</title>
</head>
<body>
<form method="POST" action="ServletTerreno" enctype="multipart/form-data">
    <label for="azienda">Azienda:</label>
    <input type="text" id="azienda" name="azienda"><br><br>

    <label for="immagine">immagine:</label>
    <input type="file" id="immagine" name="immagine"  accept=".png, .jpg, .jpeg"><br><br>

    <label for="latitudine">latitudine:</label>
    <input type="text" id="latitudine" name="latitudine"><br><br>

    <label for="longitudine">longitudine:</label>
    <input type="text" id="longitudine" name="longitudine"><br><br>

    <label for="superfice">superfice:</label>
    <input type="text" id="superfice" name="superfice"><br><br>


    <input type="submit" value="inserisciTerreno_submit" name="inserisciTerreno_submit">
</form>
</body>
</html>
