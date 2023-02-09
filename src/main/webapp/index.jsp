<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<%--
  Created by IntelliJ IDEA.
  User: emanu
  Date: 25/01/2023
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <title>Title</title>
</head>
<body>

<% UtenteBean u= (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if (u instanceof DipendenteBean)  { %>
<script type="text/javascript">
    window.onload = function() {
        alert('Login effettuato')
    }
</script>
<%@include file="/fragments/headerLoggedDipendente.html" %>
<%} else if(u instanceof  AziendaBean){ %>
<script type="text/javascript">
    window.onload = function() {
        alert('Login effettuato (ps: da migliorare )')
    }
</script>
<%@ include file="/fragments/headerLoggedAzienda.html" %>
<%} else { %>
<%@include file="fragments/headerLogin.html"%>
<% }%>


<!-- Div principale -->
<div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
    </div>
    <!-- Div Immagini -->
    <div class="carousel-inner">
        <div class="carousel-item active" data-bs-interval="10000">
            <img src="img/slide1.jpg" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <h5>Monitora le tue coltivazioni</h5>
                <p>Registrati come azienda e gestisci le tue coltivazioni.</p>
            </div>
        </div>
        <div class="carousel-item" data-bs-interval="10000">
            <img src="img/slide2.jpg" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <h5>Aggiungi una pianta personalizzata</h5>
                <p>Registrandoti avrai la possibilità di aggiungere una pianta personalizzata scegliendo ogni minimo
                    particolare.</p>
            </div>
        </div>
        <div class="carousel-item" data-bs-interval="10000">
            <img src="img/slide3.jpg" class="d-block w-100" alt="...">
            <div class="carousel-caption d-none d-md-block">
                <h5>Salute e cura delle coltivazioni</h5>
                <p>Attraverso il nostro sistema potrai monitorare in ogni momento della giornata le tue coltivazioni
                    e in caso di problemi riceverai subito una notifica.</p>
            </div>
        </div>
    </div>
    <!-- Button che permettono lo scorrere delle immagini-->
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>


<%@include file="/fragments/footer.html" %>

</body>
</html>
