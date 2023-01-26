<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.TerrenoBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Random" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="java.lang.reflect.AnnotatedArrayType" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.PiantaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %><%--
  Created by IntelliJ IDEA.
  User: Manuel
  Date: 23/01/2023
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista Piante</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/TerreniJS.js"></script>
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        img {
            height: 70px;
            width: auto;
            object-fit: contain;
        }
    </style>
</head>
<body>
<div class="bd">
    <legend style="text-align:center;">Piante</legend>
<form id="visualizza_piante" action="ServletPianta" method="post">
    <div class="card">
        <div class="card-body">
        <table class="table">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">#</th>
                <th scope="col">Nome</th>
                <th scope="col">Descrizione</th>
                <th scope="col">Ph minimo</th>
                <th scope="col">Ph massimo</th>
                <th scope="col">Temperatura minima</th>
                <th scope="col">Temperatura massima</th>
                <th scope="col">Umidità minima</th>
                <th scope="col">Umidità massima</th>
                <th scope="col">Immagine</th>
            </tr>
            </thead>
            <tbody>
                <%
                    /* -- INIZIO AUTENTICAZIONE --*/
                    Object seo = session.getAttribute("currentUserSession");
                    String email= null;

                    if (seo == null) {
                        response.sendError(401);
                    }

                    if(seo instanceof AziendaBean) {
                        email = ((AziendaBean) seo).getEmail();
                    }
                    else if(seo instanceof DipendenteBean) {
                        email = ((DipendenteBean) seo).getAzienda();
                    }



                        PiantaManager p = new PiantaManager();
                        List<PiantaBean> list = p.ListaPianteManager(email);

                        for (PiantaBean pb : list) {
                            out.print("<tr>" +
                                    "<td>" +
                                    "</td>" +
                                    "<td>" + pb.getId() + "</td>" +
                                    "<td>" + pb.getNome() + "</td>" +
                                    "<td><div class=\"overflow-auto\" style=\"max-width: 260px; max-height: 100px;\" >" + pb.getDescrizione() +"</div></td>" +
                                    "<td>" + pb.getPh_min() + "</td>" +
                                    "<td>" + pb.getPh_max() + "</td>" +
                                    "<td>" + pb.getTemperatura_min() + "</td>" +
                                    "<td>" + pb.getTemperatura_max() + "</td>" +
                                    "<td>" + pb.getUmidita_min() + "</td>" +
                                    "<td>" + pb.getUmidita_max() + "</td>" +
                                    "<td> <img src=\"" + "img/" + pb.getImmagine() + "\" alt=\"Descrizione immagine\"></td>" +
                                    "</tr>"
                            );
                    }
                %>
            </tbody>
        </table>
        </div>
    </div>
</form>
</div><!-- End bd -->

</body>
</html>
