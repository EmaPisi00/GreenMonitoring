<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.AziendaDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.UtenteManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl" %><%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 17/01/2023
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>

<head>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">
    <title>Priflo</title>
</head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<% UtenteBean user= (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if (user == null)  {
        response.sendRedirect("error.jsp");
    } else if(user instanceof AziendaBean){ %>
<%@ include file="/fragments/headerLoggedAzienda.html" %>
<%} else if (user instanceof DipendenteBean){ %>
<%@ include file="/fragments/headerLoggedDipendente.html" %>

<% }%>
<html>
<body>
<% if (request.getAttribute("conferma")!=null) {%>
<div class="alert alert-success">
    <h3>Confermato</h3>
    <p><%=request.getAttribute("descrizione")%></p>
</div>
<%}%>
<% if (request.getAttribute("errore")!=null) {%>
<div class="alert alert-danger">
    <h3>Errore</h3>
    <p><%=request.getAttribute("descrizione")%></p>
</div>
<%}%>


<% if( user instanceof AziendaBean) {%>

<div class="container py-5 w-100 h-100" style="margin-top: 50px">
    <div class="row justify-content-center">
        <div class="col-12 py-3">
                <h5 class=" text-center">Benvenuto <%= ((AziendaBean)user).getNome_azienda() %> nella tua area personale</h5>
        </div>
        <div class="col-12">
            <table class="table table-responsive-lg">
                <tr style="font-family: 'Staatliches', cursive; font-size: 20px;">
                    <th>Email</th>
                    <th>Password</th>
                    <th>Telefono</th>
                    <th>Città</th>
                    <th>Provincia</th>
                    <th>Indirizzo</th>
                </tr>
                <tr>
                    <td><%= user.getEmail()%> </td>
                    <td>*****</td>
                    <td><%= user.getTelefono()%></td>
                    <td><%= user.getCitta()%></td>
                    <td><%= user.getProvincia()%></td>
                    <td><%= user.getIndirizzo()%></td>
                </tr>
            </table>
        </div>
    </div>
</div>

<%}%>

    <%  if (user instanceof DipendenteBean)  {
        AziendaDAO aziendaDAO = new AziendaDAOImpl();
        AziendaBean azienda = aziendaDAO.retrieveForKey(((DipendenteBean) user).getAzienda());
    %>

<div class="container py-5 w-100 h-100" style="margin-top: 50px">
    <div class="row justify-content-center">
        <div class="col-12 py-3">
            <h5 class=" text-center">Benvenuto <%= ((DipendenteBean)user).getNome() %> nella tua area personale</h5>
        </div>
        <div class="col-12">
            <table class="table table-responsive-lg">
                <tr style="font-family: 'Staatliches', cursive; font-size: 20px;">
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>Azienda</th>
                    <th>Nome Azienda</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Telefono</th>
                    <th>Città</th>
                    <th>Provincia</th>
                    <th>Indirizzo</th>
                </tr>
                <tr>
                    <td><%= ((DipendenteBean) user).getNome()%></td>
                    <td><%= ((DipendenteBean) user).getCognome() %></td>
                    <td><%= azienda.getNome_azienda() %></td>
                    <td><%= ((DipendenteBean) user).getAzienda() %></td>
                    <td><%= user.getEmail()%> </td>
                    <td>*****</td>
                    <td><%= user.getTelefono()%></td>
                    <td><%= user.getCitta()%></td>
                    <td><%= user.getProvincia()%></td>
                    <td><%= user.getIndirizzo()%></td>
                </tr>
            </table>
        </div>
    </div>
</div>

    <% } %>
<div class="container py-2">
    <div class="row justify-content-center">
        <div class="col-3">
            <button onclick="location.href='ModificaProfilo.jsp.jsp'" type="button"
                    class="btn btn-outline-success btn-lg px-5" data-toggle="Modal"
                    data-target="#exampleModalCenter">
                Modifica Profilo
            </button>
        </div>
    </div>
</div>

<%@include file="fragments/footer.html"%>

</body>
</html>


