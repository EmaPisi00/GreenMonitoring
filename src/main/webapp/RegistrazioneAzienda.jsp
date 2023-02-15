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
    <link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@1,500&family=Quicksand&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">


    <title>Registrazione Azienda</title>
</head>
<body >


<%@include file="/fragments/headerLogin.html"%>
<%

    if (session.getAttribute("currentUserSession") != null) {
        //l'if controlla se l'utente è già loggato quando tenta di arrivare alla registrazione
        response.sendRedirect("ListaColtivazioni.jsp");
    }

%>

<div class="container py-4 h100">
    <div class="row justify-content-center align-items-center h-100" style="width: 100%;">
        <div class="card text-black" style="border-radius: 1rem; border: 6px solid green; font-size:  22px; background-color: rgb(208, 213, 218);">
            <legend style="font-size: 32px; text-align:center; color: black; font-family: 'Lobster', cursive;">Registrazione Azienda</legend>
            <form class="row g-3 " action="ServletAzienda" style="font-family: 'Lora', serif;" method="get">

                <div class="col-md-6  " novalidate="">
                    <div class="form-outline form-white mb-4">
                        <label for="inputEmail" class="form-label">Email</label>
                        <input type="email" class="form-control" id="inputEmail" placeholder="emanuele@x.com"
                               name="inputEmail" required="" />
                    </div>
                </div>
                <div class="col-md-6 " novalidate="">
                    <div class="form-outline form-white mb-4">
                        <label for="inputEmail" class="form-label">Conferma Email</label>
                        <input type="email" class="form-control" id="confermaInputEmail"
                               placeholder="emanuele@x.com" name="confermaInputEmail" required="" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputPassword" class="form-label">Password</label>
                        <input type="password" class="form-control" id="inputPassword" placeholder="*****"
                               name="inputPassword" required="" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputPassword" class="form-label">Conferma Password</label>
                        <input type="password" class="form-control" id="confermaInputPassword" placeholder="*****"
                               max="15" min="8" name="confermaInputPassword" required="" />
                    </div>
                </div>
                <div class="col-12">
                    <div class="form-outline form-white mb-4">
                        <label for="inputIndirizzo" class="form-label">Indirizzo</label>
                        <input type="text" class="form-control" id="inputIndirizzo" placeholder="Via Roma 13"
                               required="" name="inputIndirizzo" />
                    </div>
                </div>
                <div class="col-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputCitta" class="form-label">Citta</label>
                        <input type="text" class="form-control" id="inputCitta" placeholder="Salerno" required=""
                               name="inputCitta" />
                    </div>
                </div>
                <div class="col-md-6">
                    <label for="inputProvincia" class="form-label">Provincia</label>
                    <input type="text" class="form-control" id="inputProvincia" placeholder="Napoli" required=""
                           name="inputProvincia" />
                </div>
                <div class="col-md-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputTelefono" class="form-label">Telefono</label>
                        <input type="text" class="form-control" id="inputTelefono" placeholder="3457257849"
                               required="" name="inputTelefono" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputPartitaIva" class="form-label">Partita Iva</label>
                        <input type="text" class="form-control" id="inputPartitaIva" placeholder="111111111111"
                               max="11" required="" name="inputPartitaIva" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-outline form-white mb-4">
                        <label for="inputAzienda" class="form-label">Nome Azienda</label>
                        <input type="text" class="form-control" id="inputAzienda" placeholder="Montenegro"
                               required="" name="inputAzienda" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-outline form-white mb-4">
                        <label for="codiceAzienda" class="form-label">Codice Azienda</label>
                        <input type="text" class="form-control" id="codiceAzienda" placeholder="Montenegro"
                               required="" name="codiceAzienda" />
                    </div>
                </div>
                <div class="col-12" style="text-align: center; margin-bottom: 16px;">
                    <input type="submit" class="btn btn-outline-success btn-lg px-5" value="Registrazione" />

                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/fragments/footer.html"%>

</body>
</html>
