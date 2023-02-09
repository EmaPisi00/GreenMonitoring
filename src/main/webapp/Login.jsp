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


    <title>Title</title>
</head>
<body>

<% UtenteBean u= (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if (!(u instanceof AziendaBean) )  { %>
<%@include file="/fragments/headerLogin.html" %>
<%} else{ %>
<%@ include file="/fragments/headerLoggedAzienda.html" %>
<%}%>
<!DOCTYPE html>
<html lang="en">

<head>

    <!-- Import Bootstrap -->
    <link href="/bootstrap/bootstrap-5.2.3-dist/bootstrap-5.2.3-dist/css/bootstrap.css" rel="stylesheet">
    <script src="/bootstrap/bootstrap-5.2.3-dist/bootstrap-5.2.3-dist/js/bootstrap.bundle.js"></script>

    <!-- Import css -->


    <link rel="stylesheet" href="/css/login.css">

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-6 text-center">

                        <div class="mb-md-5 mt-md-4 pb-5">

                            <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                            <p class="text-white-50 mb-5">Inserisci email e Password!</p>
                            <form method="post" action="ServletLogin">

                            <div class="form-outline form-white mb-4">
                                <input type="email" id="typeEmailX" name="email" class="form-control form-control-lg" />
                                <label class="form-label" for="typeEmailX">Email</label>
                            </div>

                            <div class="form-outline form-white mb-4">
                                <input type="password" name="password" id="typePasswordX" class="form-control form-control-lg" />
                                <label class="form-label" for="typePasswordX">Password</label>
                            </div>

                            <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Password Dimenticata?</a></p>


                                <input type="submit" class="btn btn-outline-light btn-lg px-5" value="Login"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>



</body>

</html>
<%@include file="/fragments/footer.html"%>
</body>
</html>
