<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <!-- Import font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@1,500&family=Quicksand&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">


    <title>Registrazione Dipendente</title>

</head>
<body>

<%@include file="fragments/headerLogin.html" %>

<%

    if (session.getAttribute("currentUserSession") != null) {
        //l'if controlla se l'utente è già loggato quando tenta di arrivare alla registrazione
        response.sendRedirect("ListaColtivazioni.jsp");
    }

%>
<div class="container py-5 h100">
    <div class="row justify-content-center align-items-center h-100" style="width: 100%;">
        <h3 class="display-3 text-center py-5">Registrazione Dipendente</h3>
        <div class="card text-black"
             style="border-radius: 1rem; border: 6px solid green; font-size:  22px;">

            <form class="row g-3 " style="font-family: 'Lora', serif; " action="ServletDipendente" method="get">

                <div class="col-md-6 ">
                    <div class="form-outline form-white mb-4">
                        <label for="inputNome" class="form-label">Nome</label>
                        <input type="text" class="form-control border-secondary"  id="inputNome" placeholder="emanuele"
                               name="inputNome" required=""/>
                    </div>
                </div>
                <div class="col-md-6 ">
                    <div class="form-outline form-white mb-4">
                        <label for="inputCognome" class="form-label">Cognome</label>
                        <input type="text" class="form-control border-secondary" id="inputCognome"
                               placeholder="Pisaturo" name="inputCognome" required=""/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputEmail" class="form-label">Email</label>
                        <input type="email" class="form-control border-secondary" id="inputEmail" placeholder="emanuele@x.com"
                               name="inputEmail" required=""/>
                    </div>
                </div>
                <div class="col-md-6">
                    <label for="inputEmail" class="form-label">Conferma Email</label>
                    <input type="email" class="form-control border-secondary" id="confermaInputEmail" placeholder="emanuele@x.com"
                           name="confermaInputEmail" required=""/>
                </div>
                <div class="col-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputPassword" class="form-label">Password</label>
                        <input type="password" class="form-control border-secondary" id="inputPassword" placeholder="*****"
                               name="inputPassword" required=""/>
                    </div>
                </div>
                <div class="col-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputPassword" class="form-label">Conferma Password</label>
                        <input type="password" class="form-control border-secondary" id="confermaInputPassword" placeholder="*****"
                               name="confermaInputPassword" required=""/>
                    </div>
                </div>
                <div class="col-md-6">
                    <label for="inputProvincia" class="form-label">Provincia</label>
                    <input type="text" class="form-control border-secondary" id="inputProvincia" placeholder="Napoli" required=""
                           name="inputProvincia"/>
                </div>
                <div class="col-md-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputTelefono" class="form-label">Telefono</label>
                        <input type="text" class="form-control border-secondary" id="inputTelefono" placeholder="3457257849"
                               required="" name="inputTelefono"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputIndirizzo" class="form-label">Indirizzo</label>
                        <input
                                type="text"
                                class="form-control border-secondary"
                                id="inputIndirizzo"
                                placeholder="Via Roma 13"
                                required=""
                                name="inputIndirizzo"
                        /></div>
                </div>
                <div class="col-md-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputCitta" class="form-label">Citta</label>
                        <input
                                type="text"
                                class="form-control border-secondary"
                                id="inputCitta"
                                placeholder="Salerno"
                                required=""
                                name="inputCitta"
                        />
                    </div>
                </div>
                <div class="col-12" style="text-align: center; margin-bottom: 16px;">
                    <input type="submit" class="btn btn-outline-success btn-lg px-5" value="Registrazione"/>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="fragments/footer.html" %>
</body>
</html>
