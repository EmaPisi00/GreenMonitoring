<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.TerrenoBean" %><%--
  Created by IntelliJ IDEA.
  User: Nicola
  Date: 16/01/2023
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Terreni</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/TerreniJS.js"></script>
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<form id="rimuovi_terreno">
    <div class="card">
        <div class="card-body">
        <h5 class="card-title">Terreni</h5>
        <table class="table">
            <thead>
            <tr>
                <th scoper="col"></th>
                <th scope="col">#</th>
                <th scope="col">Latitudine</th>
                <th scope="col">Longitudine</th>
                <th scope="col">Immagine</th>
                <th scope="col">Superficie</th>
            </tr>
            </thead>
            <tbody>
                <%
                    TerrenoManager t = new TerrenoManager();
                    List<TerrenoBean> list = t.visualizzaListaTerreni();
                    int i = 0;
                    for (TerrenoBean tb : list) {
                        System.out.print("<tr>" +
                                "<td>"+
                                "<input id=\"terreno"+i+"\" type=\"checkbox\" value=\""+ tb.getId() +"+\"></input>" +
                                "</td>"+
                                "<td>" + tb.getId() + "</td>" +
                                "<td>" + tb.getLatitudine()+ "</td>" +
                                "<td>" + tb.getLongitudine() + "</td>" +
                                "<td>" + tb.getImmagine() + "</td>" +
                                "<td>" + tb.getSuperficie() + "</td>" + "</tr>"
                        );

                    }
                %>
            </tbody>
        </table>
        </div>
    </div>
</form>
<!-- Button trigger modal -->
<button id="showModal" type="button" class="btn btn-primary" data-toggle="Modal" data-target="#exampleModalCenter">
    Rimuovi terreni
</button>

<!-- Modal -->
<div id=Modal class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">

            <div class="modal-body">
                Sei sicuro di voler effettuare la rimozione?
            </div>
            <div class="modal-footer">
                <button id="closeModal" type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                <button id="summit" type="button" class="btn btn-primary">Sì</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
