<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.AziendaDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %><%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 17/01/2023
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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

<% UtenteBean user= (UtenteBean) session.getAttribute("currentUserSession");
    System.out.println(user.getClass());
    if (user == null)  {
        response.sendRedirect("error.jsp");
    }%>

<body>
<div class="container">
    <h1>Modifica profilo</h1>
    <form id="GestioneProfilo" action="ServletGestioneProfilo" method="post">
        <div class="row">

            <label>email</label>
            <div class="form-floating mb-3 ">
                <input type="text" class="form-control"   id="email" name="email"  placeholder="null">
                <label for="email"><%= user.getEmail() %></label>
            </div>

            <label>password</label>
            <div class="form-floating mb-3">
                <input type="tex" class="form-control" id="password" name="password" placeholder="null">
                <label for="password"> <%= user.getPassword()%> </label>
            </div>
        </div>
        <div class="row">
            <label>telefono</label>
            <div class="form-floating mb-3 ">
                <input type="text" class="form-control"   id="telefono" name="telefono"  placeholder="null">
                <label for="telefono"><%= user.getTelefono() %></label>
            </div>
            <label>citta</label>
            <div class="form-floating mb-3 ">
                <input type="text" class="form-control"  id="citta" name="citta"  placeholder="null">
                <label for="citta"><%= user.getCitta() %></label>
            </div>
        </div>
        <div class="row">
            <label>provincia</label>
            <div class="form-floating mb-3 ">
                <input type="text" class="form-control" id="provincia" name="provincia"  placeholder="null">
                <label for="provincia"><%= user.getProvincia() %></label>
            </div>
            <label>indirizzo</label>
            <div class="form-floating mb-3 ">
                <input type="text" class="form-control"  id="indirizzo" name="indirizzo"  placeholder="null">
                <label for="indirizzo"><%= user.getIndirizzo() %></label>
            </div>
        </div>

        <%  if (user instanceof DipendenteBean)  {
        %>
        <div class="row">
            <label>nome</label>
            <div class="form-floating mb-3 ">
                <input type="text" class="form-control" id="nome" name="nome"  placeholder="null">
                <label for="nome"><%= ((DipendenteBean) user).getNome() %></label>
            </div>
            <label>cognome</label>
            <div class="form-floating mb-3 ">
                <input type="text" class="form-control"  id="cognome" name="cognome"  placeholder="null">
                <label for="cognome"><%= ((DipendenteBean) user).getCognome() %></label>
            </div>
            <label>Nome_aziendaDipendente</label>
            <div class="form-floating mb-3 ">
                <input type="text" class="form-control"   id="Nome_aziendaDipendente" name="Nome_aziendaDipendente"  placeholder="null">
                <label for="Nome_aziendaDipendente"><%= ((DipendenteBean) user).getAzienda() %></label>
            </div>

        </div>


        <% } else if (user instanceof AziendaBean) { %>
        <div class="row">
            <label>Nome_azienda</label>
            <div class="form-floating col mb-3 ">
                <input type="text" class="form-control" id="Nome_azienda" name="Nome_azienda"  placeholder="null">
                <label for="Nome_azienda"><%= ((AziendaBean) user).getNome_azienda()  %></label>
            </div>
            <label>partita_iva</label>
            <div class="form-floating col mb-3 ">
                <input type="text" class="form-control"  id="partita_iva" name="partita_iva"  placeholder="null">
                <label for="partita_iva"><%= ((AziendaBean) user).getPartita_iva() %></label>
            </div>
        </div>
        <% } %>
        <button  type="submit"  id="myBtn" >Submit</button>
    </form>
</div>

</body>
</html>