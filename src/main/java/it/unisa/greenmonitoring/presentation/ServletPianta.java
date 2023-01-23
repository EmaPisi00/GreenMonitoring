package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager;
import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

import java.util.HashMap;

import java.util.Map;

@WebServlet(name = "ServletPianta", value = "/ServletPianta")
@MultipartConfig
public class ServletPianta extends HttpServlet {
    /**
     * Object that provides the methods to manage the Pianta.
     */
    private PiantaManager pm = new PiantaManager();
    /**
     * Method that handle the GET requests.
     * @param request the request from the client.
     * @param response the response from the server.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Method that handle the POST requests.
     * @param request the request from the client.
     * @param response the response from the server.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("inserisciPianta_submit") != null) {
            gestioneErrori(inserisciPiantaServlet(request, response), request, response);
        }
        response.sendRedirect("index.jsp");

    }

    private HashMap<String, String> inserisciPiantaServlet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        UtenteBean utente = (UtenteBean) session.getAttribute("currentUserSession");
        String azienda = utente.getEmail();
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String ph_min = request.getParameter("ph_min");
        String ph_max = request.getParameter("ph_max");
        String temperatura_min = request.getParameter("temperatura_min");
        String temperatura_max = request.getParameter("temperatura_max");
        String umidita_min = request.getParameter("umidita_min");
        String umidita_max = request.getParameter("umidita_max");
        System.out.println(azienda + nome + descrizione + ph_max + ph_min + temperatura_max + temperatura_min + umidita_max + umidita_min);
        Part immagine = request.getPart("immagine");
        String fileName = null;

        if (immagine.getSize() > 0) {
        fileName = Paths.get(immagine.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = immagine.getInputStream();
        // Salva l'immagine su disco
        String path = "C:\\Users\\stefa\\IdeaProjects\\GreenMonitoring\\src\\main\\webapp\\img\\" + fileName;
        Files.copy(fileContent, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        fileContent.close();
        }

        PiantaBean pianta = new PiantaBean(azienda, nome, descrizione, ph_min, ph_max, temperatura_min, temperatura_max);
        pianta.setUmidita_min(umidita_min);
        pianta.setUmidita_max(umidita_max);
        pianta.setImmagine(fileName);
        try {
            return pm.CreaPiantaManager(pianta);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void gestioneErrori(HashMap<String, String> errore, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (errore != null) {
            for (Map.Entry<String, String> entry : errore.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
                request.setAttribute(entry.getKey(), entry.getValue());
            }
        }
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/InserisciTerreno.jsp");
            dispatcher.forward(request, response);



        }
    }
