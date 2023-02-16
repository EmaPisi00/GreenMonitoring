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

<title>Lista Coltivazioni</title>
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
            <% } else {
            %>
            <ul class="list-group">
                <% for (ColtivazioneBean cb : list) {
                    if (cb.getStato_archiviazione() == 1) { %>
                <li class="list-group-item disabled">
                    Coltivazione <%=cb.getId()%><br>Terreno associato: <%=cb.getTerreno()%>
                    <br>(Archiviata)
                </li>
                <% } else {
                %>
                <li class="list-group-item ">Coltivazione <%=cb.getId()%>
                    <br>id terreno associato: <%=cb.getTerreno()%>
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
    <%
        /* Stampa il form per inserire la coltivazione solo se ad accedere alla pagina è un'azienda */
        if ((session.getAttribute("currentUserSession") instanceof AziendaBean)) {
            AziendaBean ab = (AziendaBean) session.getAttribute("currentUserSession");
    %> <!-- Inserisci coltivazione -->
    <div class="card" style="width: 30rem;">
        <div class="card-body">
            <h5 class="card-title">Modulo inserimento coltivazione</h5>
            <% if (session.getAttribute("errore") != null) {
            %>
            <div id="alert" class="alert alert-warning alert-dismissible fade show" role="alert">
                <i class="bi bi-exclamation-triangle me-1"><%=session.getAttribute("errore")%>
                </i>
            </div>
            <%
                    session.removeAttribute("errore");
                }
            %>
            <div id="alrt" class="alert alert-warning fade show" role="alert">
                <i class="bi bi-exclamation-triangle me-1"> Selezionare almeno un sensore.</i></div>
            <form action="ServletColtivazioni" method="post" id="aggiungi_coltivazione">
                <input type="hidden" name="moduloInserimentoColtivazione" required><br>
                <label>Scegliere il terreno di cui avviare una coltivazione</label><br>
                <% //Se servletColtivazione invia un errore viene stampato un alert
                    TerrenoManager tm = new TerrenoManager();
                    List<TerrenoBean> tbList = tm.visualizzaListaTerreni(ab.getEmail());
                    ColtivazioneManager cm = new ColtivazioneManager();
                    List<ColtivazioneBean> cList = cm.visualizzaStatoColtivazioni(ab.getEmail());
                    List<Integer> ids = new ArrayList<>();
                    if (cList != null || cList.size() > 0) {
                %>
                <select type="text" name="terreno" required><br>
                    <%
                        for (int i = 0; i < cList.size(); i++) {
                            ids.add(cList.get(i).getTerreno());
                        }
                        if (tbList != null && tbList.size() > 0) {
                            for (int i = 0; i < tbList.size(); i++) {
                                if (!ids.contains(tbList.get(i).getId())) {
                    %>
                    <option value=" <%=tbList.get(i).getId()%>"> nome: <%=tbList.get(i).getNome()%>
                    </option>
                    <% }
                    }%>
                    <% } else { %>
                    <label>Non ci sono terreni.</label>
                    <% } %>
                </select><br>
                <% } else {
                    for (int i = 0; i < ids.size(); i++) {
                %>
                <option value="<%=ids.get(i)%>">nome: <%=tm.restituisciTerrenoDaInt(ids.get(i)).getNome()%>
                </option>
                <% }
                } %>
                <label>Scegliere la pianta di cui avviare una coltivazione</label><br>
                <% cList = cm.visualizzaStatoColtivazioni(ab.getEmail());
                    PiantaManager pm = new PiantaManager();
                    List<PiantaBean> pList = pm.ListaPianteManager(ab.getEmail());
                    if (pList == null || pList.size() == 0) {

                %>
                <h7>Non ci sono piante.</h7>
                <%
                } else {
                %>
                <select type=\"text\" name=\"nomepianta\" required><br>\n");
                    <% for (int i = 0; i < pList.size(); i++) { %>
                    <option value="<%=pList.get(i).getId()%>"><%=pList.get(i).getNome()%>
                    </option>
                    <% } %>
                </select><br>
                <% } %>
                <!-- INSERIMENTO DEI SENSORI -->
                <label>Scegliere i sensori da associare alla coltivazione</label><br>
                <label>pH</label><br>
                <%
                    SensoreManager sm = new SensoreManager();
                    List<SensoreBean> sbList = sm.visualizzaListaSensori(ab.getEmail());
                    if (sbList == null || sbList.size() == 0) {

                %>
                <h7>Non ci sono sensori.</h7>
                <%
                } else {
                    for (int i = 0; i < sbList.size(); i++) {
                        if (sbList.get(i).getColtivazione() == 0 && sbList.get(i).getTipo().toLowerCase().equals("ph")) {
                %>
                <input type="checkbox" id="chk" name="sensorePh" value=" + sbList.get(i).getId() + "> Codice
                sensore: <%=sbList.get(i).getId()%><br>
                <% }
                } %>
                <label>Temperatura</label><br>
                <% for (int i = 0; i < sbList.size(); i++) {
                    if (sbList.get(i).getColtivazione() == 0 && sbList.get(i).getTipo().toLowerCase().equals("temperatura")) {
                %>
                <input type="checkbox" id="chk" name="sensoreTemperatura" value="<%=sbList.get(i).getId()%>"> Codice
                sensore: <%=sbList.get(i).getId()%><br>
                <% }
                } %>
                <label>Umidità</label><br>
                <%
                    for (int i = 0; i < sbList.size(); i++) {
                        if (sbList.get(i).getColtivazione() == 0 && (sbList.get(i).getTipo().toLowerCase().contains("umidit"))) {

                %>
                <input type="checkbox" id="chk" name="sensoreUmidita" value="<%=sbList.get(i).getId()%>"> Codice
                sensore: <%=sbList.get(i).getId()%><br>
                <%
                        }
                    }
                    java.sql.Date todayDate = new java.sql.Date(System.currentTimeMillis());
                %>
                <label>Inserire la data di inizio della coltivazione</label><br>
                <input type="date" id="dataInizio" name="datainizio" max="<%=todayDate%>" required><br><br>
                <button type="button" id="summit" class="btn btn-primary">Aggiungi coltivazione</button>
            </form>
        </div>
    </div>
    <!-- Fine inserisci coltivazione --> </div>
<% }
}
}
%>
</div>
</div>
<%@include file="fragments/footer.html" %>
</body>
</html>