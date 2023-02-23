<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.*" %>



<!-- Link per l'importo del font  -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Tangerine">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@600&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@1,500&family=Quicksand&display=swap"
      rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">


<link href="/bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="/bootstrap-5.2.3-dist/js/bootstrap.bundle.js"></script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="/css/navbar.css">
<link rel="stylesheet" href="/css/headerLogin.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
    .cursore:hover {
        background-color: lightgray;
    }
    #imgNotifica {
        height: 25px;
        width: auto;
        object-fit: contain;
    }


    #menu__toggle {
        opacity: 0;
    }
    #menu__toggle:checked + .menu__btn > span {
        transform: rotate(45deg);
    }
    #menu__toggle:checked + .menu__btn > span::before {
        top: 0;
        transform: rotate(0deg);
    }
    #menu__toggle:checked + .menu__btn > span::after {
        top: 0;
        transform: rotate(90deg);
    }
    #menu__toggle:checked ~ .menu__box {
        left: 0 !important;
    }
    .menu__btn {
        position: absolute;
        top: 20px;
        left: 20px;
        width: 26px;
        height: 26px;
        cursor: pointer;
        z-index: 101;
    }
    .menu__btn > span,
    .menu__btn > span::before,
    .menu__btn > span::after {
        display: block;
        position: absolute;
        width: 100%;
        height: 2px;
        background-color: #000000;
        transition-duration: .25s;
    }
    .menu__btn > span::before {
        content: '';
        top: -8px;
    }
    .menu__btn > span::after {
        content: '';
        top: 8px;
    }
    .menu__box {
        line-height: 60px;
        text-align: center;
        z-index: 100;
        display: block;
        position: fixed;
        top: 0;
        left: -100%;
        width: 300px;
        height: 100%;
        margin: 0;
        padding: 80px 0;
        list-style: none;
        background: linear-gradient(to bottom,rgb(221, 203, 203) , rgb(72, 140, 59));
        box-shadow: 2px 2px 6px rgba(0, 0, 0, .4);
        transition-duration: .25s;
    }
    .menu__item {
        display: block;
        padding: 12px 24px;
        color: #333;
        font-family: 'Roboto', sans-serif;
        font-size: 20px;
        font-weight: 600;
        text-decoration: none;
        transition-duration: .25s;
    }
    .menu__item:hover {
        background-color: #CFD8DC;
    }


</style>
<%
    /* -- INIZIO AUTENTICAZIONE --*/
    UtenteBean seoNotifica = (UtenteBean) session.getAttribute("currentUserSession");
    NotificaDAO nH = new NotificaDAOImpl();
    List<NotificaBean> listaNotificheH= new ArrayList<>();
    if (seoNotifica instanceof AziendaBean) {
        listaNotificheH = nH.retriveNotifichePerAzienda(seoNotifica.getEmail());
    } else if (seoNotifica instanceof DipendenteBean) {
        listaNotificheH = nH.retriveNotifichePerDipendente(seoNotifica.getEmail());
    } else {
        response.sendRedirect("error.jsp");
    }
    List<NotificaBean> listaDaVisualizzareNotificaH= new ArrayList<>();
    for (NotificaBean notH: listaNotificheH) {
        if(!notH.getVisualizzazioneNotifica()) {
            listaDaVisualizzareNotificaH.add(notH);
        }
    }

    int numNotifiche = listaDaVisualizzareNotificaH.size(); // Recupera il numero di notifiche dal database o da un'altra fonte
%>

<!-- Header -->
<header>

    <!-- Logo e titolo -->
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-12 col-xs-12" style="margin-top: 20px;">
                <!-- Titolo -->
                <div class="title" >
                    <h1 style="font-family: 'Anton', sans-serif;" class="display-4">Green Monitoring</h1>
                </div>
            </div>
            <div class="col-1 col-md-1 col-xs-12" >
            <!-- Logo -->
            <div style="position: absolute;" class="logo" >
                <a href="index.jsp"><img src="img/zoomed_lg2.png" style="   left: 50px; display: block; position: absolute; margin-top: 20px;" class="log_img img-fluid rounded-top  " alt=""></a>
            </div>
        </div>
            <!-- Profilo e Logout -->
            <div class="log_reg col-lg-4 py-2" style=" position: absolute; margin-top: 28px;">

                <% if (!request.getRequestURI().endsWith("SezioneNotifiche.jsp")) { %>


                <!--Notifica-->
                <div class="btn1">
                    <form action="Profile.jsp"><input class="log" type="button" value="Notifica" id="notificationButtonH">
                        <span class="translate-middle badge rounded-pill bg-danger" id="notificationBadgeH">
                            <%=numNotifiche%>
                            <span class="visually-hidden">unread messages</span>
                        </span>
                    </form>
                </div>
                <!--Notifica-->

                <% } %>

                <div class="btn1">
                    <form action="Profile.jsp"><input class="log" type="submit" value="Profilo"></form>

                </div>

                <div class="btn1">
                    <form action="LogoutServlet"><input class="log" type="submit" value="Logout"></form>
                </div>


            </div>
        </div>
    </div>



    <div class="toast-container" style="position: absolute; top: 120; right: 0;">
        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" id="notificationAreaH" style="display: none;">
            <%
                int indice=0;
                for (NotificaBean notificaH : listaDaVisualizzareNotificaH ) {
                    if(indice<4) {
                        out.print(
                                "" +
                                        " <div id=\"rigaH" + indice + "\" class=\"toast-header bg-danger bg-opacity-50\">" +
                                        "      <img id=\"imgNotifica\" src=\"img/logo.png\" class=\"rounded me-2\" alt=\"...\">" +
                                        "      <strong class=\"me-auto\">"+ notificaH.getTipo()+"</strong>" +
                                        "      <small class=\"text-muted\">" +notificaH.getData() + "</small>" +
                                        "      <button class=\"btn btn-outline-success m-lg-1\"onclick='showModalH(\""+ indice +"\",\""+notificaH.getId() +"\",\""+ notificaH.getTipo() + "\",\""+ notificaH.getData() + "\",\""+notificaH.getContenuto() + "\",\""+ notificaH.getColtivazioneID() +"\")'>Leggi</button>" +
                                        "    </div>"
                        );
                        indice++;
                    }
            %>
            <%}
                out.print(
                        "    <div class=\"d-flex cursore\" style=\'cursor: pointer;\' onclick=\'location.href=\"SezioneNotifiche.jsp\"\'>" +
                                "        <div class=\"toast-body\">" + "Tutti i messaggi" +
                                "        </div>" +
                                "</div>");

            %>
        </div>
    </div>



    <!-- Modal HTML -->
    <!-- Modal HTML -->
    <!-- Modal -->
    <div class="modal fade" id="myModalH">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Dettagli notifica</h4>
                </div>
                <!-- Modal body -->
                <div class="card text-center modal-body">
                    <div class="card-header">
                        <h5><span id="notifica-titoloH"></span></h5>
                    </div>
                    <div class="card-body">
                        <p id="notifica-contenutoH"> </p>
                        <button type="button" class="btn btn-primary" id="coltivazioneBtnH" value="">
                            <span id="notifica-coltivazioneH"></span>
                        </button>

                    </div>
                    <div class="card-footer text-muted">
                        <p>Data: <span id="notifica-dataH"></span></p>
                    </div>
                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger"  onclick="closeModalH()">Chiudi</button>
                </div>
            </div>
        </div>
    </div>

    <!-- End Example Code -->




    <script>
        document.getElementById("notificationButtonH").addEventListener("click", function() {
            var notificationArea = document.getElementById("notificationAreaH");
            if (notificationArea.style.display === "none") {
                notificationArea.style.display = "block";
            } else {
                notificationArea.style.display = "none";
            }
        });
        function showModalH(indice,idNotifica,notifica, data, contenuto,coltivazione) {
            var notificaRow = document.getElementById("rigaH"+indice);
            if (notificaRow) {
                notificaRow.classList.remove('bg-danger');
            }
            // Imposta il titolo della notifica
            document.getElementById("notifica-titoloH").innerText = notifica;

            // Imposta il contenuto della notifica
            document.getElementById("notifica-contenutoH").innerText = contenuto + "\nappartenente alla coltivazione ";

            //coltivazioneid
            document.getElementById("notifica-coltivazioneH").innerText = coltivazione;

            // Imposta la data della notifica
            document.getElementById("notifica-dataH").innerText = data ;

            // Mostra il modal
            $("#myModalH").modal("show");

            // Nasconde l'area delle notifiche
            document.getElementById("notificationAreaH").style.display = "none";
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var numNotifiche = xhr.responseText;
                    document.getElementById("notificationBadgeH").innerHTML = numNotifiche;
                }
            };
            xhr.open("GET", "NotificaServlet?numNotifiche=true&idNotifica=" + idNotifica, true);
            xhr.send();

        }
        document.getElementById("coltivazioneBtnH").addEventListener("click", function() {
            var coltivazioneId = document.getElementById("notifica-coltivazioneH").innerText;
            var url = "AccediAColtivazioneServlet?coltivazione=" + coltivazioneId;
            window.location.href = url;
        });

        function closeModalH() {
            $("#myModalH").modal("hide");
        }
    </script>

 <!--   <div class="col-lg-6">

        <nav class="navbar" style="top:0px; position: absolute;">
            <div class="container" >
                <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
                        data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasNavbar"
                     aria-labelledby="offcanvasNavbarLabel">
                    <div style="background: linear-gradient(to bottom,rgb(221, 203, 203) , rgb(221,203,203))"
                         class="offcanvas-header">
                        <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"
                                aria-label="Close"></button>
                    </div>
                    <div class="offcanvas-body" >
                        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="Notifiche.jsp">Notifiche</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link active" aria-current="page"
                                   href="RimuoviDipendente.jsp">Dipendente</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="Terreni.jsp">Terreni</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="Piante.jsp">Piante</a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link active" aria-current="page"
                                   href="ListaColtivazioni.jsp">Coltivazioni</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page"
                                   href="ListaSensori.jsp">Sensori</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    </div>
    </div>
-->


    <div class="hamburger-menu">
        <input id="menu__toggle" type="checkbox" />
        <label class="menu__btn" for="menu__toggle">
            <span></span>
        </label>
        <ul class="menu__box">
            <li><a class="menu__item" href="index.jsp">Home</a></li>
            <li><a class="menu__item" href="RimuoviDipendente.jsp">Dipendente</a></li>
            <li><a class="menu__item" href="Terreni.jsp">Terreni</a></li>
            <li><a class="menu__item" href="Piante.jsp">Piante</a></li>
            <li><a class="menu__item" href="ListaColtivazioni.jsp">Coltivazioni</a></li>
            <li><a class="menu__item" href="ListaSensori.jsp">Sensori</a></li>
        </ul>
    </div>


</header>

