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
<%@ include file="/fragments/headerLogged.html" %>
<%}%>
<form method="post" action="LoginServlet">
    <div class="container" style="width: 100px; height: auto">
    <label for="email">Username:</label>
    <input type="text" id="email" name="email">

    <label for="password">Password:</label>
    <input type="password" id="password" name="password">

    <input type="submit" value="Login" name="Login">
    </div>
</form>


<%@include file="/fragments/footer.html"%>
</body>
</html>
