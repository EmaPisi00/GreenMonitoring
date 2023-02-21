<!-- Questa Jsp permette ad un dipendente loggato di potersi associare ad un azienda tramite un codice.-->

<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.DipendenteBean" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet"  href="css/headerLogin.css" type="text/css">

    <style>
        @media screen and (max-width: 768px) {
            .tohide {
                display: none;
            }
        }
    </style>
    <title>Associazione Dipendente-Azienda</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/Associazione.js"></script>
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <title>Associazione Azienda</title>
</head>

<body class="assotiationBody">
<% UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
    if (u == null) {
        response.sendRedirect("error.jsp");
    } else if (u instanceof AziendaBean) {
        response.sendRedirect("error.jsp");
    } else if (((DipendenteBean) u).getAzienda() != null) {
        //l'if controlla se l'utente è un dipendente e se è già associato ad un'azienda
        response.sendRedirect("Profile.jsp");
    }
%>
<%@ include file="fragments/headerLoggedDipendenteNonAssociato.html"%>


    <div class="loginContainer">
        <div id="formLogin">
            <form method="post" action="AssociazioneServlet">
                <div class="formValidation">
                    <label for="codiceAzienda" class="form-label">Codice Azienda:</label>
                    <input type="text" class="textInputStyle" id="codiceAzienda" placeholder="AA22ss33"
                           name="codiceAzienda" required/>
                </div>
                <%
                    String errorMessage = (String) request.getAttribute("descrizione");
                    if (errorMessage != null) {
                %>
                <div id="errorPopup" class="errorPopup">
                    <h6 id="errorText" class="error"><%= errorMessage %>
                    </h6 id="errorText">
                </div>
                <%
                    }
                %>
                <br>
                <!-- Button trigger modal -->
                <button id="showModal" type="submit" disabled="disabled" class="btn btn-outline-success btn-lg px-3" data-toggle="Modal"
                        data-target="#exampleModalCenter">
                    Associa
                </button>
                <br>
                <!-- Modal -->
                <div id=Modal class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-body">
                                Nome Azienda:
                                <h5 id="nomeAzienda"></h5> <br>
                                Indirizzo Azienda:
                                <h5 id="indirizzoAzienda"></h5> <br>
                                Provincia Azienda:
                                <h5 id="provinciaAzienda"></h5> <br>
                                Cliccare su "Conferma" per associarsi oppure su "Esci" per annullare l'operazione.
                            </div>
                            <div class="modal-footer">
                                <button id="closeModal" type="button" class="btn btn-secondary" data-dismiss="modal">Esci</button>
                                <button id="summit" type="submit" class="btn btn-primary">Conferma</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <br>
        </div>
    </div>


<script type="text/javascript">
    $(document).ready(function(){

        $("#codiceAzienda").blur(function(){

            var codice =$("#codiceAzienda").val();

            if(codice.length >= 1)
            {
                $.ajax({
                    url:"CheckAzienda",
                    type:"post",
                    data:"codiceAzienda="+codice,
                    dataType:"text",
                    success:function(data)
                    {
                        var object = JSON.parse(data);
                        if(object["success"] === 1) {
                            $("#nomeAzienda").html(object["nome"]);
                            $("#indirizzoAzienda").html(object["indirizzo"]);
                            $("#provinciaAzienda").html(object["provincia"]);
                            $("#showModal").removeAttr("disabled");
                            $("#showModal").attr("type","button")
                        }
                        else
                        {
                            $("#showModal").removeAttr("disabled");
                            $("#showModal").attr("type", "submit");
                        }
                    }
                });
            }
            else {
                $("#showModal").attr("type", "submit");
            }
        });

        // aggiungi il listener del pulsante "Associa"
        $("#showModal").on("click", function(){
            if($("#showModal").attr("type") === "button") // controlla se il pulsante è abilitato
            {
                $("#exampleModalCenter").modal("show");
            }
        });

    });
</script>


<%@include file="fragments/footer.html" %>
</body>
</html>