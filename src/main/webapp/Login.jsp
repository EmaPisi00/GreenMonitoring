<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/login.css" type="text/css">
    <link rel="stylesheet" href="css/headerLogin.css" type="text/css">
    <link rel="stylesheet" href="css/footer.css" type="text/css">

    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <!-- Import Font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@1,500&family=Quicksand&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">


</head>
<% UtenteBean u = (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if (u != null) {
        request.getSession(false).invalidate();
    }%>
<%@ include file="fragments/headerLogin.html" %>

<body class="loginBody ">

<div class="container w-50 py-5" style="margin-bottom: 15%">
    <div class="row justify-content-center">
        <form method="post" action="LoginServlet"
              style="border-radius: 1rem; border: 2px solid green; font-size:  22px; ">
            <div class="row justify-content-center">
                <div class="col-6 text-center">
                    <div class="mb-4 text-center">
                        <label class="form-label text-center">E-mail</label>
                        <input class="form-control " type="text" placeholder='Inserisci email' name="email"
                               id="email"/>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-6 text-center">
                    <div class="mb-4 text-center">
                        <label class="form-label text-center">Password</label>
                        <input class="form-control" type="password" placeholder='********' name="password"
                               id="password"/>
                    </div>
                </div>
            </div>
            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
            %>
            <div id="errorPopup" class="errorPopup">
                <h6 id="errorText" class="error"><%= errorMessage %>
                </h6 id="errorText">
            </div>
            <%
                }
            %>

            <div>
                <div style="display:flex;flex-direction: column;align-items: center;  margin-bottom: 20px">
                    <input type="submit" name="signin" class="btn btn-outline-success btn-lg px-5" value="Accedi"/>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="fragments/footer.html" %>

<script>
    // Mostra il popup di errore se necessario
    var errorPopup = document.getElementById("errorPopup");
    if (errorPopup) {
        errorPopup.style.display = "block";
    }
</script>
</body>
</html>