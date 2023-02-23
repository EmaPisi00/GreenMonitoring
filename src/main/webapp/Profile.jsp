<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.AziendaDAO" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl" %>


<head>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">
    <title>Profilo</title>

    <!--Altro-->
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<% UtenteBean user = (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if(user == null){ %>
<%@include file="fragments/headerLogin.html"%>
<% } else if (user instanceof DipendenteBean && ((DipendenteBean) user).getAzienda()!= null ) { %>
<%@include file="/fragments/headerLoggedDipendente.jsp" %>
<%} else if(user instanceof DipendenteBean && ((DipendenteBean) user).getAzienda() == null) {%>
<%@include file="fragments/headerLoggedDipendenteNonAssociato.html"%>
<% } else if (user instanceof AziendaBean) { %>
<%@ include file="/fragments/headerLoggedAzienda.jsp" %>
<%}%>

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



    <% if (user instanceof AziendaBean) {%>
        <div class="container w-50 py-5" style="margin-top: 20px; margin-bottom: 10%">
    <% } else if (user instanceof DipendenteBean) {%>
        <div class="container w-50 py-5" style="margin-top: 20px; margin-bottom: 3%">
    <% } %>
    <div>
        <div class="col-12 py-3">
            <% if (user instanceof AziendaBean) {%>
                <h5 class=" display-5 text-center">Benvenuto <%= ((AziendaBean)user).getNome_azienda() %> nella tua area personale</h5>
            <% } else if (user instanceof DipendenteBean) {%>
            <h5 class=" display-5 text-center">Benvenuto <%= ((DipendenteBean)user).getNome() %> nella tua area personale</h5>
            <% } %>
        </div>
        <div class="col-12">
            <table class="table table-responsive-lg" style="font-family: 'Staatliches', cursive; font-size: 20px; text-align:left">
                <%  if (user instanceof DipendenteBean)  {
                    AziendaDAO aziendaDAO = new AziendaDAOImpl();
                    AziendaBean aziendaBean = aziendaDAO.retrieveForKey(((DipendenteBean) user).getAzienda());

                %>
                <tr>
                    <td> Nome: <%= ((DipendenteBean) user).getNome()%> </td>
                </tr>
                <tr>
                    <td> Cognome: <%= ((DipendenteBean) user).getCognome() %> </td>
                </tr>
                <tr>
                    <td> Nome Azienda: <%= aziendaBean.getNome_azienda() %> </td>
                </tr>
                <% } %>
                <tr>
                    <td> Email: <%= user.getEmail()%> </td>
                </tr>
                <tr>
                    <td> Telefono: <%= user.getTelefono()%> </td>
                </tr>
                <tr>
                    <td> Città: <%= user.getCitta()%> </td>
                </tr>
                <tr>
                    <td> Provincia: <%= user.getProvincia()%> </td>
                </tr>
                <tr>
                    <td> Indirizzo: <%= user.getIndirizzo()%> </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="container py-2">
        <div class="row justify-content-center">
            <div style="text-align: center">
                <button onclick="location.href='ModificaProfilo.jsp'" type="button"
                        class="btn btn-outline-success btn-lg px-5" data-toggle="Modal"
                        data-target="#exampleModalCenter">
                    Modifica Profilo
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="fragments/footer.html"%>

<script>
    function myFunction() {
        var x = document.getElementById("myInput");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }
</script>

</body>
</html>


