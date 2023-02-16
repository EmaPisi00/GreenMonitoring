
<!-- Jsp che permette di visualizzare le fisiopatie relative di ogni pianta-->
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">
    <title>Fisiopatie</title>

</head>
<body>
<%
    UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");

    if(u == null){
        response.sendRedirect("error.jsp");
    }else if (u instanceof DipendenteBean){
        response.sendRedirect("error.jsp");
    } else if(u instanceof AziendaBean){ %>
<%@include file="fragments/headerLoggedAzienda.html" %>

<%}%>

<%

    ColtivazioneManager coltivazioneManager = new ColtivazioneManager();
    ArrayList<FisiopatieBean> listaFisiopatie = coltivazioneManager.visualizzaFisiopatie();
    PiantaDAO piantaDAO = new PiantaDAOImpl();
    List<PiantaBean> listaPiante = piantaDAO.RetriveAllPiantaDefault();

%>

<div class="container py-5">
    <h3 class="display-3 py-4 text-center">Fisiopatie</h3>
    <div class="row justify-content-center">
        <div class="col-12 col-md-auto col-xl-8">
            <table class="table table-group-divider">
                <tr  style="font-family: 'Staatliches', cursive; font-size: 20px;">
                    <th scope="col">ID</th>
                    <th scope="col">ID Pianta</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Descrizione</th>
                    <th scope="col">Umidità Terra Min</th>
                    <th scope="col">Umidità Terra Max</th>
                    <th scope="col">Temperatura Min</th>
                    <th scope="col">Temperatura Max</th>
                    <th scope="col">Umidità Aria Min</th>
                    <th scope="col">Umidità Aria Max</th>
                </tr>
                <%
                    for (FisiopatieBean fisiopatia : listaFisiopatie) {
                        String nomePianta = "";
                        for (PiantaBean pianta : listaPiante) {
                            if (fisiopatia.getId_pianta() == pianta.getId()) {
                                nomePianta = pianta.getNome();
                                break;
                            }
                        }
                %>
                <tr>
                    <td><%= fisiopatia.getId() %>
                    </td>
                    <td><%= nomePianta %>
                    </td>
                    <td><%= fisiopatia.getNome() %>
                    </td>
                    <td><%= fisiopatia.getDescrizione() %>
                    </td>
                    <td><%= fisiopatia.getUmid_terr_min() %>
                    </td>
                    <td><%= fisiopatia.getUmid_terr_max() %>
                    </td>
                    <td><%= fisiopatia.getTemp_min() %>
                    </td>
                    <td><%= fisiopatia.getTemp_max() %>
                    </td>
                    <td><%= fisiopatia.getUmid_aria_min() %>
                    </td>
                    <td><%= fisiopatia.getUmid_aria_max() %>
                    </td>
                </tr>
                <% } %>
            </table>
        </div>
    </div>
</div>
<%@include file="fragments/footer.html"%>
</body>
</html>
