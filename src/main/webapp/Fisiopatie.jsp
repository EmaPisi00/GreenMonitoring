<%@ page import="java.util.List" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.beans.*" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager" %>
<%@ page import="it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager" %>
<%@ page import="it.unisa.greenmonitoring.dataccess.dao.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <!-- Import Bootstrap -->
  <link href="bootstrap-5.2.3-dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="bootstrap-5.2.3-dist/js/bootstrap.bundle.min.js"></script>

  <!-- Import css -->
  <link rel="stylesheet" href="css/footer.css">
  <link rel="stylesheet" href="css/headerLogin.css">
  <title>Fisiopatie</title>
  <style>
    table {
      width: 100%;
      border-collapse: collapse;
      background-color: #d3fdd3;
    }

    th {
      text-align: left;
      padding: 8px;
      background-color: #339933;
      border: 1px solid #000000;
      color: #fff;
    }

    td {
      text-align: left;
      padding: 8px;
      border: 1px solid #000000;
      color: #000;
    }

    tr:nth-child(even) {
      background-color: #90EE90;
    }
  </style>
</head>
<body>
<%
  UtenteBean u = (UtenteBean) session.getAttribute("currentUserSession"); %>
<%@include file="fragments/headerLoggedAzienda.html" %>
<%
  if (u == null) {
    response.sendRedirect("Login.jsp");
  }
  FisiopatieDAO fisiopatieDAO = new FisiopatieDAOImpl();
  PiantaDAO piantaDAO = new PiantaDAOImpl();
  ArrayList<FisiopatieBean> listaFisiopatie = fisiopatieDAO.retrieveAll();
  List<PiantaBean> listaPiante = piantaDAO.RetriveAllPiantaDefault();

%>

<table>
  <tr>
    <th>ID</th>
    <th>ID Pianta</th>
    <th>Nome</th>
    <th>Descrizione</th>
    <th>Umidità Terra Min</th>
    <th>Umidità Terra Max</th>
    <th>Temperatura Min</th>
    <th>Temperatura Max</th>
    <th>Umidità Aria Min</th>
    <th>Umidità Aria Max</th>
  </tr>
  <%
    for (FisiopatieBean fisiopatia : listaFisiopatie) {
      String nomePianta = "";
      for (PiantaBean pianta : listaPiante) {
        if (fisiopatia.getId_pianta() == pianta.getId()) {
          nomePianta = pianta.getNome();
          break;
        }
      }
  %>
  <tr>
    <td><%= fisiopatia.getId() %></td>
    <td><%= nomePianta %></td>
    <td><%= fisiopatia.getNome() %></td>
    <td><%= fisiopatia.getDescrizione() %></td>
    <td><%= fisiopatia.getUmid_terr_min() %></td>
    <td><%= fisiopatia.getUmid_terr_max() %></td>
    <td><%= fisiopatia.getTemp_min() %></td>
    <td><%= fisiopatia.getTemp_max() %></td>
    <td><%= fisiopatia.getUmid_aria_min() %></td>
    <td><%= fisiopatia.getUmid_aria_max() %></td>
  </tr>
  <% } %>
</table>
</body>
</html>
