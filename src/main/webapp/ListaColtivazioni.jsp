<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.lang.reflect.AnnotatedArrayType" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="java.sql.Date" %><%--
  Created by IntelliJ IDEA.
  User: Nicola
  Date: 16/01/2023
  Time: 16:40
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

    <title>Coltivazioni</title>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <script src="bootstrap-5.2.3-dist/js/ListaColtivazioni.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <!-- <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet"> -->
    <!-- link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet"> -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<% UtenteBean u= (UtenteBean) request.getSession().getAttribute("currentUserSession");
    if (u instanceof DipendenteBean)  { %>
<%@include file="/fragments/headerLoggedDipendente.html" %>
<%} else if(u instanceof  AziendaBean){ %>
<%@ include file="/fragments/headerLoggedAzienda.html" %>
<%} else { %>
<%@include file="fragments/headerLogin.html"%>
<% }%>


<div class="bd">
    <legend style="text-align:center;">Coltivazioni</legend>
    <!-- Coltivazioni -->
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Coltivazioni</h5>
            <% /* -- INIZIO AUTENTICAZIONE -- */
                Object sa = session.getAttribute("currentUserSession");
                if (sa == null) {
                    response.sendError(401);
                } else if (!(session.getAttribute("currentUserSession") instanceof UtenteBean)) {
                    response.sendError(401);
                }
                /* -- PASSATI I TEST, IL CONTAINER APRE IL RESTO DELLA PAGINA -- */
                else {
                    List<ColtivazioneBean> list = null;
                    if ((session.getAttribute("currentUserSession") instanceof DipendenteBean)) {
                        DipendenteBean a = (DipendenteBean) sa;
                        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> qui <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                        if(a.getAzienda() == null) {
                            response.sendRedirect("error.jsp");
                        }
                        ColtivazioneManager cm = new ColtivazioneManager();
                        list = cm.visualizzaStatoColtivazioni(a.getAzienda());
                    } else {
                        AziendaBean a = (AziendaBean) sa;
                        ColtivazioneManager cm = new ColtivazioneManager();
                        list = cm.visualizzaStatoColtivazioni(a.getEmail());
                    }
                    if (list.size() == 0) { %>
            <h7>Non ci sono coltivazioni.</h7>
            <% } else { TerrenoManager terrenoManager = new TerrenoManager();
            %>
            <ul class="list-group">
                <% for (ColtivazioneBean cb : list) {
                    if (cb.getStato_archiviazione() == 1) { %>
                <li class="list-group-item disabled">
                    Coltivazione <%=cb.getId()%><br>Terreno: <%=terrenoManager.restituisciTerrenoDaInt(cb.getTerreno()).getNome()%>
                    <br>(Archiviata)
                </li>
                <% } else {
                %>
                <li class="list-group-item ">Coltivazione <%=cb.getId()%>
                    <br>Terreno: <%=terrenoManager.restituisciTerrenoDaInt(cb.getTerreno()).getNome()%>
                    <form action="ServletColtivazioni" method="get">
                        <input type="hidden" name="coltivazione" value="<%=cb.getId()%>">
                        <button type="submit" class="btn btn-success">Visualizza stato</button>
                    </form>
                </li>
                <% }
                }
                } %>
            </ul>
        </div>
    </div>
    <!-- Fine coltivazioni -->
<% }
%>
    <button type="submit" class="btn" onclick="location.href='AggiungiColtivazione.jsp'">Aggiungi Coltivazione</button>
</div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>