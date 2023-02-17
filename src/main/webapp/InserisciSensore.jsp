<%@ page import="it.unisa.greenmonitoring.dataccess.beans.SensoreBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<!doctype html>
<html lang="en">
<head>
  <!-- Import Bootstrap -->
  <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

  <!-- Import css -->
  <link rel="stylesheet" href="css/footer.css">
  <link rel="stylesheet" href="css/headerLogin.css">


  <meta charset="utf-8">
  <link rel="icon" type="image/x-icon" href="img/favicon.png">

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
  <title>Inserisci Sensore</title>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

  <style>
    table {
      margin: 0 auto;
      border-collapse: collapse;
    }

    table td {
      padding: 5px;
    }

    table td:first-child {
      text-align: right;
      font-weight: bold;
      width: 120px;
    }

    table select, table input {
      width: 100%;
    }

    table input[type="submit"] {
      margin-top: 10px;
      width: auto;
    }
  </style>

</head>

<% UtenteBean u= (UtenteBean) session.getAttribute("currentUserSession");
  if(u == null){
    response.sendRedirect("error.jsp");
  } else if (!(u instanceof AziendaBean))  { %>
  <% response.sendRedirect("error.jsp"); %>
<% } else{  %>
<%@include file="fragments/headerLoggedAzienda.html"%>
<%}%>

  <body >
  <form method="post" action="ServletSensore">
    <div class="container py-5">
      <h5 class="display-3 text-center">Inserisci Un Sensore</h5>
      <div class="row justify-content-center">
        <div class="col-3">
    <table class="table ">
      <tr>
        <td>Tipo sensore:</td>
        <td>
          <select id="tipo" name="tipo">
            <option value="Umidità">Umidita'</option>
            <option value="Temperatura">Temperatura</option>
            <option value="PH">PH</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>ID Mosquitto:</td>
        <td><input type="text" id="id_mosquitto" name="id_mosquitto"></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" class="btn btn-outline-success btn-lg px-3" value="Registra Sensore" name="RegistraSensore"></td>
      </tr>
    </table>
        </div>
      </div>
    </div>
  </form>

  <%@include file="/fragments/footer.html"%>


  </body>
</html>
