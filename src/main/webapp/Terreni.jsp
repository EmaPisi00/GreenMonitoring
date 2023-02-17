<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.TerrenoBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.AziendaBean" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.UtenteBean" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: Nicola
  Date: 16/01/2023
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <style>
        @media screen and (max-width: 768px) {
            .tohide {
                display: none;
            }
        }
    </style>
    <title>Terreni</title>
    <script src="./jquery/jquery-3.6.3.min.js"></script>
    <link rel="icon" type="image/x-icon" href="img/favicon.png">

    <link href="bootstrap-5.2.3-dist/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/TerreniJS.js"></script>
    <link href="bootstrap-5.2.3-dist/css/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import Bootstrap -->
    <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

    <!-- Import css -->
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/headerLogin.css">

    <style>
        #immagine {
            height: 70px;
            width: auto;
            object-fit: contain;
        }
    </style>
    <title>Terreni</title>
</head>
<body>

<%
    UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession");
    if(u == null){
        response.sendRedirect("error.jsp");
    }
    if (u instanceof AziendaBean) { %>
<%@include file="fragments/headerLoggedAzienda.html" %>

<% if (u != null) {%>
<div class="bd">
    <div id="alrt" class="alert alert-warning fade show" role="alert">
        <i class="bi bi-exclamation-triangle me-1">Selezionare almeno un terreno.</i>
    </div>
    <%if (session.getAttribute("terrenoOccupato") != null) {%>
    <div id="alrtTerreno" class="alert alert-warning fade show" role="alert">
        <i class="bi bi-exclamation-triangle me-1"><%=session.getAttribute("terrenoOccupato")%>
        </i>
    </div>
    <%
            session.removeAttribute("terrenoOccupato");
        }
        }
    %>

    <div class="container py-5">
        <div class="row">
            <div class="col-12 text-center">
                <h3 class="display-3 text-center">Terreni Presenti</h3>
            </div>
            <div class="col-14 py-5">
                <table class="table table-group-divider">
                    <thead>
                    <tr style="font-family: 'Staatliches', cursive; font-size: 25px;">
                        <th scope="col">nome</th>
                        <th scope="col" class="tohide">Immagine</th>
                        <th scope="col">Latitudine</th>
                        <th scope="col">Longitudine</th>
                        <th scope="col">Superficie</th>
                        <th scope="col">Rimuovi</th>
                    </tr>
                    </thead>

                    <%
                        TerrenoBean terrenoBean = new TerrenoBean();
                        int idTerreno = 0;
                        /* -- INIZIO AUTENTICAZIONE -- */
                        Object seo = session.getAttribute("currentUserSession");
                        if (seo == null) {
                            response.sendError(401);
                        } else if (!(session.getAttribute("currentUserSession") instanceof AziendaBean)) {
                            response.sendError(401);
                        }
                        /* -- PASSATI I TEST, APRE IL RESTO DELLA PAGINA--*/
                        else {
                            AziendaBean a = (AziendaBean) seo;

                            TerrenoManager t = new TerrenoManager();
                            List<TerrenoBean> list = t.visualizzaListaTerreni(a.getEmail());
                            int i = 0;
                            ColtivazioneManager cm = new ColtivazioneManager();
                            List<ColtivazioneBean> clist = cm.visualizzaStatoColtivazioni(a.getEmail());
                            List<Integer> ids = new ArrayList<>();

                            if (list.size() != 0) {
                                Iterator it = list.iterator();
                                while (it.hasNext()) {
                                    terrenoBean = (TerrenoBean) it.next();
                                    String immagine;
                                    try {
                                        immagine = new String(Base64.getEncoder().encode(terrenoBean.getImmagine()));
                                    } catch (NullPointerException e) {
                                        immagine = null;
                                    }
                    %>

                    <tr class="justify-content-center">
                        <td><%= terrenoBean.getNome()%>
                        </td>
                        <td><img id="immagine" src="data:image/jpeg;base64,<%=immagine%>">
                        </td>
                        <td><%= terrenoBean.getLatitudine()%>
                        </td>
                        <td><%= terrenoBean.getLongitudine()%>
                        </td>
                        <td><%= terrenoBean.getSuperficie()%>
                        </td>
                        <td><p style="display: none"><%= idTerreno = terrenoBean.getId()%>
                        </p> <img src="img/delete.png" width="35px" style="cursor: pointer" data-bs-toggle="modal"
                                  data-bs-target="#exampleModal"></td>
                    </tr>

                    <% }
                    }
                    }
                    %>

                </table>
            </div>
        </div>
    </div>

    <div class="container py-2">
        <div class="row justify-content-center">
            <div class="col-3">
                <button onclick="location.href='InserisciTerreno.jsp'" type="button"
                        class="btn btn-outline-success btn-lg px-5" data-toggle="Modal"
                        data-target="#exampleModalCenter">
                    Aggiungi terreno
                </button>
            </div>
        </div>
    </div>

</div><!-- End bd -->


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                Sei sicuro di voler effettuare la rimozione?
            </div>
            <div class="modal-footer">
                <button id="closeModal" class="btn btn-secondary" data-dismiss="modal">No</button>
                <button id="summit" class="btn btn btn-outline-danger"><a style="text-decoration: none; "
                                                                          href="ServletRemoveTerreno?action=delete&id=<%= idTerreno%>">Conferma</a>
                </button>
            </div>
        </div>
    </div>
</div>

<%}%>
<%@include file="fragments/footer.html" %>
</body>
</html>