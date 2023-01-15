<!doctype html>
<html lang="en">
<head>
    <style>
        .bg {
            background-color: green;
        }

        .form-complete {
            width: 310px;
            padding-top: 80px;
            margin: 0 auto;
        }

        .form-control {
            width: 90%;
        }

        .button {
            width: 100px;
            padding-top: 20px;
            display: block;
            margin: 0 auto;
        }

        .rounded {
            width: 5%;
            height: 8%;
            background: green;
        }


        a:link {
            text-decoration: none;
            align-content: center;
        }
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <title>Bootstrap Example</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>


<body class="bg">

<img src="img/Logo.png" class="rounded mx-auto d-block" alt="...">

<div class="form-complete">
    <div class="form-floating mb-3">
        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
        <label for="floatingInput">Email address</label>
    </div>
    <div class="form-floating">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
        <label for="floatingPassword">Password</label>
    </div>
    <div class="button">
        <input class="btn btn-primary" type="reset" value="Login">
        <div>
        </div>
    </div>
</div>

<a href="RegistrazioneAzienda.jsp">Se non hai ancora un account, puoi registrarti qui se devi registrare
    un'azienda</a><br><br>
<a href="RegistrazioneDipendente.jsp">Se non hai ancora un account, puoi registrarti qui se devi registrare un
    dipendente</a>
</body>
</html>