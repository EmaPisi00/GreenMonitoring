<%--
  Created by IntelliJ IDEA.
  User: franc
  Date: 24/01/2023
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.SensoreDAOImpl" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.SensoreBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">
    <title>Lista Sensori</title>
</head>
<body>

<% UtenteBean u= (UtenteBean) session.getAttribute("currentUserSession");

    if(u == null){
        response.sendRedirect("error.jsp");

    }else if (!(u instanceof AziendaBean))  {
 response.sendRedirect("error.jsp");
 } else{  %>
<%@include file="fragments/headerLoggedAzienda.html"%>
<%
    }%>

<%if (u!= null) {%>

<div class="container py-5" style="width: 100%; height: 100%; ">
    <div class="row ">
        <div class="col-12 text-center">
            <h3 class="display-3 text-center">Lista Sensori</h3>
        </div>
        <div class="col-14 py-5">
            <table  class="table table-group-divider" style="font-size: 30px;">
                <thead>
                <tr style="font-family: 'Staatliches', cursive; font-size: 20px;">
                    <th  scope="col">ID</th>
                    <th  scope="col">Tipo</th>
                    <th  scope="col">ID Mosquitto</th>
                    <th  scope="col">Azienda</th>
                </tr>
                </thead>
                <% SensoreDAOImpl sensoreDAO = new SensoreDAOImpl();
                    List<SensoreBean> sensori = sensoreDAO.retrieveAllByAzienda(u.getEmail());
                    for (SensoreBean sensore : sensori) { %>
                <tr class="justify-content-center" >
                    <td ><%= sensore.getId() %>
                    </td>
                    <td><%= sensore.getTipo() %>
                    </td>
                    <td><%= sensore.getIdM() %>
                    </td>
                    <td><%= sensore.getAzienda() %>
                    </td>
                </tr>
                <% } %>
            </table>
        </div>
        <div class="container py-2">
            <div class="row justify-content-center">
                <div class="col-3">
                    <button onclick="location.href='InserisciSensore.jsp'" type="button"
                            class="btn btn-outline-success btn-lg px-5" data-toggle="Modal"
                            data-target="#exampleModalCenter">
                        Inserisci Sensore
                    </button>
                </div>
            </div>
        </div>

    </div>
</div>

<%}%>

<%@include file="fragments/footer.html" %>
</body>
</html>
