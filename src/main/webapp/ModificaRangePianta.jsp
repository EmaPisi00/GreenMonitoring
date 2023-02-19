        <%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="java.util.Base64" %>
<%--
  Created by IntelliJ IDEA.
  User: stefa
  Date: 22/01/2023
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
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

    <title>Modifica Range Pianta</title>
    <style>
        img {
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
</head>

<body>

<% UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
    PiantaBean pianta = (PiantaBean) session.getAttribute("piantaModificare");
    String errori = (String) request.getAttribute("errore");
    session.setAttribute("pianta", pianta);
    String immagine = null;
    if (!(u instanceof AziendaBean)) { %>
<% response.sendRedirect("error.jsp"); %>
<% } else {
    immagine = null;
    try {
        immagine = new String(Base64.getEncoder().encode(pianta.getImmagine()));
    } catch (NullPointerException e) {
        immagine = null;
    }


%>

<%@ include file="/fragments/headerLoggedAzienda.html" %>
<%}%>

<% if (errori !=null) {%>
<div class="alert alert-danger">
    <h3>Errore</h3>
    <p><%=request.getAttribute("descrizione")%></p>
</div>
<%}%>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">

        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Modifica Pianta</h4>
                </div>
                <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                    <img id="immagine" src="data:image/jpeg;base64,<%=immagine%>">
                    <span class="font-weight-bold"><%=pianta.getNome()%></span>
                </div>

                <form id="ModificaRange" name="ModificaRange" action="RimuoviPiantaServlet" method="post">
                    <input type="hidden" id="ModificaPianta" name="ModificaPianta" value="1">
                    <div class="row mt-2">
                        <label class="text-center">ph_min</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"   id="ph_min" name="ph_min"  placeholder="null">
                            <label for="ph_min"><%=pianta.getPh_min() %></label>
                        </div>

                        <label class="text-center">ph_max</label>
                        <div class="form-floating mb-3">
                            <input type="tex" class="form-control" id="ph_max" name="ph_max" placeholder="null">
                            <label for="ph_max"> <%=pianta.getPh_max()%> </label>
                        </div>
                    </div>

                    <div class="row mt-3">
                        <label class="text-center">temperatura_min</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"   id="temperatura_min" name="temperatura_min"  placeholder="null">
                            <label for="temperatura_min"><%=pianta.getTemperatura_min() %></label>
                        </div>

                        <label class="text-center">temperatura_max'</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"  id="temperatura_max" name="temperatura_max"  placeholder="null">
                            <label for="temperatura_max"><%=pianta.getTemperatura_max() %></label>
                        </div>

                        <label class="text-center">umidita_min</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control" id="umidita_min" name="umidita_min"  placeholder="null">
                            <label for="umidita_min"><%=pianta.getUmidita_min() %></label>
                        </div>

                        <label class="text-center">>umidita_max</label>
                        <div class="form-floating mb-3 ">
                            <input type="text" class="form-control"  id="umidita_max" name="umidita_max"  placeholder="null">
                            <label for="umidita_max"><%=pianta.getUmidita_max()%></label>
                        </div>
                    </div>

                    <button class="btn-green" type="submit" id="save-profile-btn">Save Profile</button>

                </form>
            </div>
        </div>
    </div>
</div>

<!--MODAL-->
<div class="modal" id="confirm-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Conferma operazione</h5>
            </div>
            <div class="modal-body">
                Sei sicuro di voler salvare il profilo?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn-reed" onclick="closeModal()">Annulla</button>
                <button type="button" class="btn-green" id="confirm-save-btn">Conferma</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#save-profile-btn').click(function(e) {
            e.preventDefault(); // previene l'invio del form
            $('#confirm-modal').modal('show'); // mostra il modal di conferma
        });

        $('#confirm-save-btn').click(function() {
            $('#confirm-modal').modal('hide'); // nasconde il modal di conferma
            $('#ModificaRange').submit(); // invia i dati al server
        });
    });

    function closeModal() {
        $("#confirm-modal").modal("hide");
    }

</script>


<%@include file="/fragments/footer.html"%>
</body>
</html>
