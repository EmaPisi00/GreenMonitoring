<!doctype html>
<html lang="en">
<head>
    <style>
        .bg {
            background-color: green;
        }

        .form-complete {
            width: 30%;
            padding-top: 200px;
            margin: 0 auto;
        }

        .button {
            width: 27%;
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

        .p{
            text-align: center;
            padding: 10px;
        }

        .p div{
            margin:6px;
            padding: 10px;
            max-width: 200px;
            display: inline-block;
            text-align: left;
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

<div class="p">
    <div class="register" > <form action="RegistrazioneAzienda.jsp"><input class="btn btn-primary" type="submit" value="Registra Azienda"></form></div>


    <div class="dip" ><form action="Dipendente.jsp"><input  class="btn btn-primary" type="submit" value="Registra Dipendente"></form></div>

</div>
</body>
</html>