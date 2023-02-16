<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %><%--
  Created by IntelliJ IDEA.
  User: emanu
  Date: 23/01/2023
  Time: 17:56
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

    <!-- Import Font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@1,500&family=Quicksand&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">


    <title>Login</title>
</head>
<body >

<% UtenteBean u= (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if (u == null)  { %>
<%@include file="/fragments/headerLogin.html" %>
<%} else{
    request.getSession(false).invalidate();
}%>


<div class="container py-5">
    <div class="row justify-content-center align-items-center" style="width: 100%;">
        <h3 class="display-3 text-center py-4 ">Login</h3>
        <div class="card text-black text-center"
             style="border-radius: 1rem; border: 6px solid green; font-size:  28px; ">
           <form class="row justify-content-center  py-1" action="ServletLogin" method="post">

                <div class="col-7 justify-content-center" style="font-family: 'Lora', serif; ">
                    <div class="form-outline form-white mb-4">
                        <label for="inputEmail" class="form-label">Email</label>
                        <input type="email" style="background-color: rgb(208, 213, 218);" class="form-control border-secondary" id="inputEmail" placeholder="emanuele@gmail.com" name="email"
                               required="" />
                    </div>
                </div>
                <div class="col-7 justify-content-center" style="font-family: 'Lora', serif; ">
                    <div class="form-outline form-white mb-4">
                        <label for="inputPassword" class="form-label">Password</label>
                        <input type="password" style="background-color: rgb(208, 213, 218);" class="form-control border-secondary" id="inputPassword" placeholder="******" name="password"
                               required="" />
                    </div>
                </div>
                <div class="col-12" style="text-align: center; margin-bottom: 16px; font-family: 'Lora', serif; ">
                    <input type="submit" class="btn btn-outline-success btn-lg px-5" style="font-size: 25px;" value="Login" />
                </div>
            </form>
        </div>
    </div>
</div>







</body>

</html>
<%@include file="/fragments/footer.html"%>
</body>
</html>
