<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet"  href="css/login.css" type="text/css">
    <link rel="stylesheet"  href="css/headerLogin.css" type="text/css">
    <link rel="stylesheet"  href="css/footer.css" type="text/css">

    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <!-- Import Font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@1,500&family=Quicksand&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">


</head>
<% UtenteBean u= (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if (u != null)  {
        request.getSession(false).invalidate();
    }%>
<%@ include file="fragments/headerLogin.html"%>
    <body class="loginBody">
    <div class="loginContainer">
        <div id="formLogin">
            <form method="post" action="LoginServlet">
                <div class="formValidation">
                    <br>Email<br>
                    <input class="textInputStyle" type="text" placeholder='Inserisci email' name="email" id="email"/>
                </div>
                <div class="formValidation">
                    <br>Password<br>
                    <input class="textInputStyle" type="password" placeholder='********' name="password" id="password"/>
                </div>
                <%
                    String errorMessage = (String)request.getAttribute("errorMessage");
                    if(errorMessage != null) {
                %>
                <div id="errorPopup" class="errorPopup">
                    <p id="errorText" class="error"><%= errorMessage %></p>
                </div>
                <%
                    }
                %>
                <input type="submit" name="signin" id="loginButton" value="Accedi" />
            </form>
        </div>
    </div>

    <%@ include file="fragments/footer.html"%>

    <script>
        // Mostra il popup di errore se necessario
        var errorPopup = document.getElementById("errorPopup");
        if (errorPopup) {
            errorPopup.style.display = "block";
        }
    </script>
    </body>
</html>