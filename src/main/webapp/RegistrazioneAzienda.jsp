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

        .form-label {

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


<fieldset>
    <div class="bd">
        <legend style="text-align:center;">Registrazione Azienda</legend>


        <form class="row g-3">

            <div class="col-md-6">
                <label for="inputEmail" class="form-label">Email</label>
                <input type="email" class="form-control" id="inputEmail" placeholder="emanuele@x.com"/>
            </div>
            <div class="col-md-6">
                <label for="inputEmail" class="form-label">Conferma Email</label>
                <input type="email" class="form-control" id="confermaInputEmail" placeholder="emanuele@x.com"/>
            </div>
            <div class="col-md-12">
                <label for="inputPassword" class="form-label">Password</label>
                <input type="password" class="form-control" id="inputPassword" placeholder="*****"/>
            </div>
            <div class="col-12">
                <label for="inputIndirizzo" class="form-label">Indirizzo</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputIndirizzo"
                        placeholder="Via Roma 13"
                />
            </div>
            <div class="col-12">
                <label for="inputCitta" class="form-label">Citta</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputCitta"
                        placeholder="Salerno"
                />
            </div>
            <div class="col-md-6">
                <label for="inputTelefono" class="form-label">Telefono</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputTelefono"
                        placeholder="via Roma"
                />
            </div>
            <div class="col-md-6">
                <label for="inputProvincia" class="form-label">Provincia</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputProvincia"
                        placeholder="Napoli"
                />
            </div>
            <div class="col-md-6">
                <label for="inputAzienda" class="form-label">Nome Azienda</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputAzienda"
                        placeholder="Montenegro"
                />
            </div>
            <div class="col-md-6">
                <label for="inputPartitaIva" class="form-label">Partita Iva</label>
                <input
                        type="text"
                        class="form-control"
                        id="inputPartitaIva"
                        placeholder="111111111111"
                        max="11"
                />
            </div>
            <br>
        </form>
    </div>
</fieldset>

</div>
<div class="col-12" style="text-align: center; ">
    <button type="submit" style="font-size: 20px; padding: 1%;" class="btn btn-primary">Registrazione
    </button>
</div>


<!-- End Example Code -->
</body>
</html>
