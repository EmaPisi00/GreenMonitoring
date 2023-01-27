<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.lang.reflect.AnnotatedArrayType" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.MisurazioneSensoreManager" %><%--
  Created by IntelliJ IDEA.
  User: Nicola
  Date: 16/01/2023
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Coltivazione</title>
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
<body>
<%  UtenteBean ut = (UtenteBean) session.getAttribute("currentUserSession");
    if (!(ut instanceof AziendaBean) || ut == null)  { %>
<% response.sendRedirect("error.jsp"); %>
<% } else{  %>
<%@include file="fragments/headerLogged.html"%>
<%}%>
<div class="bd">
    <legend style="text-align:center;"></legend>
    <!-- Coltivazioni -->
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Info coltivazione</h5>

            <%
                /* -- INIZIO AUTENTICAZIONE -- */
                Object sa = session.getAttribute("currentUserSession");
                if (sa == null) {
                    response.sendError(401);
                } else if ( ! (session.getAttribute("currentUserSession") instanceof UtenteBean)) {
                    response.sendError(401);
                }
                /* -- PASSATI I TEST, IL CONTAINER APRE IL RESTO DELLA PAGINA -- */
                else {
                    List<ColtivazioneBean> list = null;

                    if ( (session.getAttribute("currentUserSession") instanceof DipendenteBean)) {
                        DipendenteBean a = (DipendenteBean) sa;
                        ColtivazioneManager cm = new ColtivazioneManager();
                        list = cm.visualizzaStatoColtivazioni( a.getAzienda() );
                    } else {
                        AziendaBean a = (AziendaBean) sa;
                        ColtivazioneManager cm = new ColtivazioneManager();
                        list = cm.visualizzaStatoColtivazioni( a.getEmail() );
                    }
                    MisurazioneSensoreManager msm = new MisurazioneSensoreManager();
                    int[] valoriMisurati = msm.visualizzaMediaSensori((String) session.getAttribute("coltivazioneID"));
                            out.print("<li class=\"list-group-item \">" +
                                    "Coltivazione "+ session.getAttribute("coltivazioneID") + "<br>" +
                                    "<h7>media pH</h7> " + valoriMisurati[0] + "<br>" +
                                    "<h7>media temperatura</h7> " + valoriMisurati[1] + "<br>" +
                                    "<h7>media umidità</h7> " + valoriMisurati[2] + "<br>" +
                                    "</li>"
                            );
                            session.removeAttribute("coltivazioneID");
                    }
            %>
        </div>
    </div>
    <!-- Fine coltivazioni -->
</div>
</body>
</html>
