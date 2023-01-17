<!DOCTYPE html>
<html lang="en">
<head>

    <style>
        fieldset {

        }

        legend {
            font-size: 160%;
            color: black;
        }

        input {
            margin: 5px;
        }

        .bd {
            font-size: 120%;
            line-height: 1.5;
            position: relative;
            margin: 30px;
        }

        .rounded
        {
            width: 8%;
            height: 12%;
        }

    </style>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"
    />
    <link
            href="https://getbootstrap.com/docs/5.3/assets/css/docs.css"
            rel="stylesheet"
    />
    <title>Registrazione Azienda</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="p-3 m-0 border-0 bd-example">


<a href="index.jsp"><img src="img/lg.png" class="rounded float-start" alt="..."></a>


<fieldset>
    <div class="bd">
        <legend style="text-align:center;">Registrazione Dipendente</legend>


        <form class="row g-3 "  action="ServletDipendente" method="get">

            <div class="col-md-6">
                <label for="inputNome" class="form-label">Nome </label>
                <input
                        type="text"
                        class="form-control"
                        id="inputNome"
                        placeholder="Montenegro"
                        required=""
                        name="inputNome"
                />
            </div>
            <div class="col-md-6">
                <label for="inputCognome" class="form-label">Cognome</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputCognome"
                        placeholder="Montenegro"
                        required=""
                        name="inputCognome"
                />
            </div>
            <div class="col-md-6 needs-validation was-validated"  novalidate="" >
                <label for="inputEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="inputEmail" placeholder="emanuele@x.com" name="inputEmail" required=""/>
            </div>
            <div class="col-md-6 needs-validation was-validated"  novalidate="">
                <label for="inputEmail" class="form-label">Conferma Email</label>
                <input type="email" class="form-control" id="confermaInputEmail" placeholder="emanuele@x.com" name="confermaInputEmail" required=""/>
            </div>
            <div class="col-md-6">
                <label for="inputPassword" class="form-label">Password</label>
                <input type="password" class="form-control" id="inputPassword" placeholder="*****" name="inputPassword" required=""/>

            </div>
            <div class="col-md-6">
                <label for="inputPassword" class="form-label">Password</label>
                <input type="password" class="form-control" id="confermaInputPassword" placeholder="*****" name="confermaInputPassword" required=""/>
            </div>
            <div class="col-12">
                <label for="inputIndirizzo" class="form-label">Indirizzo</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputIndirizzo"
                        placeholder="Via Roma 13"
                        required=""
                        name="inputIndirizzo"
                />
            </div>
            <div class="col-6">
                <label for="inputCitta" class="form-label">Citta</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputCitta"
                        placeholder="Salerno"
                        required=""
                        name="inputCitta"
                />
            </div>
            <div class="col-md-6">
                <label for="inputProvincia" class="form-label">Provincia</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputProvincia"
                        placeholder="Napoli"
                        required=""
                        name="inputProvincia"
                />
            </div>
            <div class="col-md-6">
                <label for="inputTelefono" class="form-label">Telefono</label>
                <input
                        type="tel"
                        class="form-control"
                        id="inputTelefono"
                        placeholder="3342890437"
                        required=""
                        name="inputTelefono"
                />
            </div>

            <div class="col-md-6">
                <label for="inputAzienda" class="form-label">Nome Azienda</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputAzienda"
                        placeholder="***-***"
                        name="inputAzienda"
                        required
                />
            </div>
            <div class="col-12" style="text-align: center; ">
                <input type="submit" class="btn btn-primary" value="Registrazione" />
            </div>
            <br>
        </form>
    </div>
</fieldset>


</div>


</body>
</html>
