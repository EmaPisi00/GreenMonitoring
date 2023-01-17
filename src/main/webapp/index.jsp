<!doctype html>
<html lang="en">
<head>
    <style>
        .bg {
            background-color: green;
        }

        .form-complete {
            width: 310px;
            padding-top: 200px;
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

        .rounded
        {
            width: 8%;
            height: 12%;
        }


        a:link {
            text-decoration: none;
            margin-bottom: 0;
        }
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <title>HomePage</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</head>


<body >

<a href="index.jsp"><img src="img/lg.png" class="rounded float-start" alt="..."></a>


<form method="post" action="LoginServlet">
    <label for="email">Username:</label>
    <input type="text" id="email" name="email">

    <label for="password">Password:</label>
    <input type="password" id="password" name="password">

    <input type="submit" value="Login" name="Login">
</form>

<a href="RegistrazioneAzienda.jsp">Se non hai ancora un account, puoi registrarti qui se devi registrare
    un'azienda</a><br><br>
<a href="Dipendente.jsp">Se non hai ancora un account, puoi registrarti qui se devi registrare un
    dipendente</a>
</body>
</html>