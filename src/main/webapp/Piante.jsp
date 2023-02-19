<%@ page import="java.util.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %><%--
  Created by IntelliJ IDEA.
  User: Manuel
  Date: 23/01/2023
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

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
        .imgPianta {
            height: 70px;
            width: auto;
            object-fit: contain;
        }
        .btn-green {
            color: rgb(255, 255, 255);
            background-color: darkseagreen;
            border: 2px solid rgb(255, 255, 255);
            border-radius: 10px;
            font-size: 26px;
            font-family: 'Anton', sans-serif;
        }
        .btn-green:hover{
            color: darkseagreen;
            background-color: rgb(255, 255, 255);
        }
        .btn-reed {
            color: rgb(255, 255, 255);
            background-color: #ec084c;
            border: 2px solid rgb(255, 255, 255);
            border-radius: 10px;
            font-size: 26px;
            font-family: 'Anton', sans-serif;
        }
        .btn-reed:hover{
            color: #ec084c;;
            background-color: rgb(255, 255, 255);
        }

    </style>
    <title>Piante</title>
</head>
<body>
<%
    UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
    System.out.println(request.getAttribute("errore"));
    System.out.println(request.getAttribute("descrizione"));
    if (!(u instanceof AziendaBean)) {
        response.sendRedirect("error.jsp");
        return;
    }%>
<%@include file="fragments/headerLoggedAzienda.html"%>

<% if (request.getAttribute("conferma")!=null) {%>
<div class="alert alert-success">
    <h3>Confermato</h3>
    <p><%=request.getAttribute("descrizione")%></p>
</div>
<%}%>
<% if (request.getAttribute("errore")!=null) {%>
<div class="alert alert-danger">
    <h3>Errore</h3>
    <p><%=request.getAttribute("descrizione")%></p>
</div>
<%}%>

<div class="bd py-5 ">
    <h3 class="display-3 text-center">Piante</h3>
    <form id="visualizza_piante" action="RimuoviPiantaServlet" method="post">
        <div class="container py-5">
            <div class="row">
                <div class="col-14 py-5">
                    <table class="table table-group-divider">
                        <thead>
                        <tr  style="font-family: 'Staatliches', cursive; font-size: 20px;">
                            <th scope="col"></th>
                            <th scope="col">#</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Immagine</th>
                            <th scope="col">Descrizione</th>
                            <th scope="col">Ph minimo</th>
                            <th scope="col">Ph massimo</th>
                            <th scope="col">Temperatura minima</th>
                            <th scope="col">Temperatura massima</th>
                            <th scope="col">Umidità minima</th>
                            <th scope="col">Umidità massima</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody class="text-center align-middle">
                        <%
                            /* -- INIZIO AUTENTICAZIONE --*/
                            String email = u.getEmail();
                            int idPianta = 0;

                            PiantaManager p = new PiantaManager();
                            List<PiantaBean> list = p.ListaPianteManager(email);
                            int i = 1;
                            if (list.size() != 0) {
                                Iterator it = list.iterator();
                                while (it.hasNext()) {
                                    PiantaBean pb = (PiantaBean) it.next();
                                    String immagine;
                                    try {
                                        immagine = new String(Base64.getEncoder().encode(pb.getImmagine()));
                                    } catch (NullPointerException e) {
                                       immagine = null;
                                    }
                                    out.print("<tr>" +
                                            "<td>");
                                    out.print("</td>" +
                                            "<td>" + i + "</td>" +
                                            "<td>" + pb.getNome() + "</td>" +
                                            "<td><img class=\"imgPianta\" id=\"immagine\" src=\"data:image/jpeg;base64," + immagine + "\">" +
                                            "<td><div class=\"overflow-auto\" style=\"max-width: 260px; max-height: 100px;\" >" + pb.getDescrizione() + "</div></td>" +
                                            "<td>" + pb.getPh_min() + "</td>" +
                                            "<td>" + pb.getPh_max() + "</td>" +
                                            "<td>" + pb.getTemperatura_min() + "</td>" +
                                            "<td>" + pb.getTemperatura_max() + "</td>" +
                                            "<td>" + pb.getUmidita_min() + "</td>" +
                                            "<td>" + pb.getUmidita_max() + "</td>");
                                            if (pb.getAzienda()!=null) {
                                                idPianta = pb.getId();
                                    out.print(
                                            "<td><button class=\"btn btn-danger mx-2 my-2\" type=\"button\" value=\"" + pb.getId() + "\" onclick='showModal(\""+ pb.getId() +"\")'>Rimuovi</button>" +
                                            " <button type=\"submit\" value=\"" + pb.getId() + "\"class=\"btn btn-primary mx-2\" name=\"modificaRange_submit\">Modifica</button></td>" +
                                            "</tr>"
                                    );} else {
                                                out.print("<td><i class=\"bi bi-x-octagon-fill\"></i> <br>" +

                                                        "</td>");
                                            }
                                    i++;
                                }
                            }
                        %>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </form>
    <div class="container py-2">
        <div class="row justify-content-center">
            <div class="col-3">
                <button onclick="location.href='InserisciPianta.jsp'" type="button"
                        class="btn btn-outline-success btn-lg px-5" data-toggle="Modal"
                        data-target="#exampleModalCenter">
                    Aggiungi Pianta
                </button>
            </div>
        </div>
    </div>
</div><!-- End bd -->

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Sei sicuro di voler effettuare la rimozione ?
            </div>
            <div class="modal-footer">
                <button id="closeModal" class="btn-reed" data-bs-dismiss="modal" onclick="closeModal()">No</button>
                <form id="rimuoviPianta" action="RimuoviPiantaServlet" method="post">
                    <input type="hidden" name="idPianta" value="">
                    <button id="summit" name="Rimuovi" class="btn-green">Conferma</button>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function showModal(id) {
        // Mostra il modal
        $("#exampleModal").modal("show");
        $("#rimuoviPianta input[name=idPianta]").val(id);

    }

    $(document).ready(function() {

        $("#alrt").hide();

        $("#summit").click(function(){
            $("#rimuoviPianta").submit();
        });

    });

    function closeModal() {
        $("#exampleModal").modal("hide");
    }

</script>
<%@include file="fragments/footer.html" %>
</body>
</html>
