<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Link per l'importo del font  -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Tangerine">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100&display=swap" rel="stylesheet">

    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/headerHomePage.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomePage</title>
</head>

<body>
<%@include file="/fragments/headerHomePage.html" %>

<!-- Contenitore principale -->
<div class="container-fluid">
    <div style="margin-top: 30px;" class="Divisione"></div>
    <div class="text-center">
        <img src="img/prova.jpg" style="margin-top: 20px;" class="img-fluid" alt="...">
    </div>
    <!-- Chi Siamo -->
    <div class="ChiSiamo">
        <a name="ChiSiamo">
            <h1 class="display-3">Chi Siamo</h1>
        </a>
        <span class="chiSiamo">La Cia-Agricoltori Italiani intende supportare le piccole e medie aziende agricole in
        modo da
        beneficiarne la
        crescita e la competitività sul mercato, attraverso tecnologie come l’agricoltura di precisione. Punta su
        un’agricoltura sostenibile con attenzione a qualità, sicurezza, tutela e valorizzazione dell’ambiente,
        agricoltura
        biologica, energie alternative. La Cia-Agricoltori Italiani guarda l’agricoltura del prossimo futuro con uno
        slancio
        particolare verso l’innovazione e sempre maggiore sostenibilità.​</span>
    </div>
    <div style="margin-top: 20px;" class="Divisione"></div>
    <div class="text-center">
        <img src="img/obiettivi.jpg" style="margin-top: 20px;" class="img-fluid" alt="...">
    </div>
    <div>
        <!-- Obiettivi -->
        <a name="Obiettivi">
            <h1 class="display-3">I Nostri Obiettivi</h1>
        </a>
        <div class="container">
            <div class="row align-items-start">
                <div class="col-lg-6">
                    <div class="nomiObiettivi"><a>Supporto piccole-medie Aziende</a></div>
                    <p style="margin-top: 20px;">Il sistema supporta le attività delle piccole e medie aziende agricole,
                        migliorando la gestione delle risorse e favorendo un’agricoltura più sostenibile.​</p>
                </div>
                <div class="col-lg-6">
                    <div class="nomiObiettivi">
                        <a>Controllo della qualità della salute</a>
                    </div>
                    <p style="margin-top: 20px;">Il sistema rileva se le condizioni ambientali possono nuocere alla
                        crescita
                        della pianta o favorire lo sviluppo di fisiopatie.​​</p>
                </div>
                <div class="col-lg-6">
                    <div class="nomiObiettivi">
                        <a>Monitoraggio delle Coltivazioni</a>
                    </div>
                    <p style="margin-top: 20px;">Il sistema monitora l’andamento delle coltivazioni attraverso la
                        consultazione di dati specifici raccolti da sensori in tempo reale.​ </p>
                </div>
                <div class="col-lg-6">
                    <div class="nomiObiettivi">
                        <a>Supporto e suggerimenti all'Irrigazione</a>
                    </div>
                    <p style="margin-top: 20px;">Il sistema offre la possibilità di migliorare l’attività di irrigazione
                        fornendo dei suggerimenti alle aziende.​</p>
                </div>
            </div>
        </div>
    </div>
    <div style="margin-top: 20px;" class="Divisione"></div>
    <div>
        <!-- Contatti -->
        <a name="Contatti">
            <h1 class="display-3">Contatti</h1>
        </a>
        <!-- Footer -->
        <footer class="text-center text-lg-start text-muted">

            <section class="">
                <div class="container text-center text-md-start mt-5">
                    <div class="row mt-3">

                        <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                            <h6 class="text-uppercase fw-bold mb-4">
                                <i class="fas fa-gem me-3"></i>GreenMonitoring
                            </h6>
                            <p>
                                BOOOHH
                            </p>
                        </div>
                        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase fw-bold mb-4">
                                Altri link
                            </h6>
                            <p>
                                <a href="#!" class="text-reset">Altro</a>
                            </p>
                            <p>
                                <a href="#!" class="text-reset">Help</a>
                            </p>
                        </div>

                        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                            <!-- Links -->
                            <h6 class="text-uppercase fw-bold mb-4">Contact</h6>
                            <p><i class="fas fa-home me-3"></i> Italy Salerno, SA </p>
                            <p>
                                <i class="fas fa-envelope me-3"></i>
                                info@example.com
                            </p>
                            <p><i class="fas fa-phone me-3"></i> + 39 334 567 3488</p>
                            <p><i class="fas fa-print me-3"></i> + 39 312 200 3924</p>
                        </div>
                    </div>

                </div>
            </section>
            <!-- Copyright -->
            <div class="text-center p-4">
                © 2021 Copyright: GreenMonitoring
            </div>
        </footer>
        <!-- Footer -->

    </div>
</div>

</div>


<div>
    <a href="#Login"><img src="img/frecciaSu.png" style="margin-top: -20%; margin-right: 5%;" class="rounded float-end"
                          alt="..."></a>

</div>

</body>

</html>