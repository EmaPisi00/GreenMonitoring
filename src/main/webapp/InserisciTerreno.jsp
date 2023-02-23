<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.TerrenoBean" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <title>Inserisci Terreno</title>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link href="/img/favicon.png" rel="icon">
    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        #image-preview {
            height: 70px;
            width: auto;
            object-fit: contain;
        }
        .input-info-wrapper {
            display: flex;
            align-items: center;
        }

        .info-wrapper {
            position: relative;
            margin-left: -10px;
        }

        .info-btn {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 50px;
            height: 50px;
            border: none;
            background-color: transparent;
            color: #333;
            font-size: 1.5rem;
            cursor: pointer;
        }

        .info-btn:hover {
            color: #ff0044;
        }

        .info-text {
            position: absolute;
            top: 50%;
            left: calc(100% + 10px);
            transform: translateY(-50%);
            background-color: #ff0044;
            color: #fff;
            font-size: 1rem;
            padding: 10px;
            border-radius: 5px;
            opacity: 0;
            visibility: hidden;
            transition: opacity 0.2s ease-in-out, visibility 0.2s ease-in-out;
            width: 250px;
        }

        .info-btn:hover + .info-text,
        .info-text:hover {
            opacity: 1;
            visibility: visible;
        }

    </style>
</head>


<% UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
    String errore = (String) request.getAttribute("erroriTerrenoBean");
    if (u == null) {
        response.sendRedirect("error.jsp");
    } else if (!(u instanceof AziendaBean)) { %>
<% response.sendRedirect("error.jsp"); %>
<% } else { %>
<%@include file="fragments/headerLoggedAzienda.jsp" %>
<%}%>

<body>
<% if (request.getAttribute("conferma") != null) {%>
<div class="alert alert-success">
    <h3>Confermato</h3>
    <p><%=request.getAttribute("descrizione")%>
    </p>
</div>
<%}%>
<% if (request.getAttribute("errore") != null) {%>
<div class="alert alert-danger">
    <h3>Errore</h3>
    <p><%=request.getAttribute("descrizione")%> <i class="bi bi-info-circle"></i>
    </p>
</div>
<%}%>


<% if (errore != null) { %>
<div class="text-danger"><%= errore%>
</div>
<% } %>

<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col d-flex justify-content-center">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Inserisci Terreno</h4>
                </div>
                <form id="inserisciTerreno_submit" action="TerrenoServlet" method="post" enctype="multipart/form-data">
                    <input type="text" id="azienda" name="azienda" value="<%=u.getEmail()%>" style="display: none">

                    <div class="mb-3">
                        <label class="text-center">Nome terreno</label>
                        <div class="input-info-wrapper">
                            <input type="text" class="form-control border-2" id="nome" name="nome" required placeholder="">
                            <div class="info-wrapper">
                                <button class="info-btn btn">
                                    <i class="bi bi-info-circle"></i>
                                </button>
                                <span class="info-text">Il nome deve contenere almeno 3 caratteri e massimo 30</span>
                            </div>
                        </div>
                    </div>


                    <div class="mb-3">
                        <label class="text-center">Latitudine</label>
                        <div class="input-info-wrapper">
                            <input type="text" class="form-control border-2" id="latitudine" name="latitudine" required placeholder="">
                            <div class="info-wrapper">
                                <button class="info-btn btn">
                                    <i class="bi bi-info-circle"></i>
                                </button>
                                <span class="info-text">Inserire un numero compreso tra 1 e 90</span>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="text-center">Longitudine</label>
                        <div class="input-info-wrapper">
                            <input type="text" class="form-control border-2" id="longitudine" name="longitudine" required placeholder="">
                            <div class="info-wrapper">
                                <button class="info-btn btn">
                                    <i class="bi bi-info-circle"></i>
                                </button>
                                <span class="info-text">Inserire un numero compreso tra 1 e 180</span>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="text-center">Superficie</label>
                        <div class="input-info-wrapper">
                            <input type="text" class="form-control border-2" id="superfice" name="superfice" required placeholder="">
                            <div class="info-wrapper">
                                <button class="info-btn btn">
                                    <i class="bi bi-info-circle"></i>
                                </button>
                                <span class="info-text">Inserire un numero</span>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="text-center">Immagine</label>
                        <div class="input-info-wrapper">
                            <label for="file-input" class="btn btn-primary bg-white">
                                <img id="image-preview" src="img/card-image.svg" alt="Select Image">
                            </label>
                            <input id="file-input" name="immagine" accept=".png, .jpg, .jpeg" type="file" style="display:none;">
                            <div class="info-wrapper">
                                <button class="info-btn btn">
                                    <i class="bi bi-info-circle"></i>
                                </button>
                                <span class="info-text">Inserire un file immagine non superiore a 3Mb</span>
                            </div>
                        </div>
                    </div>

                    <div class="container py-3">
                        <div class="row justify-content-center">
                            <div>
                                <input type="submit" class="btn btn-outline-success btn-lg px-3" required value="Inserisci"
                                       name="inserisciTerreno_submit">
                            </div>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>




<script>
    const fileInput = document.getElementById("file-input");
    const imagePreview = document.getElementById("image-preview");

    fileInput.addEventListener("change", (event) => {
        const file = event.target.files[0];
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
            imagePreview.src = reader.result;
        };
    });
</script>

<%@include file="/fragments/footer.html" %>

</body>
</html>

