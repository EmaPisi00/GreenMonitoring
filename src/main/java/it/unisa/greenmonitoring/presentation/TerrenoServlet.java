package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager;
import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "TerrenoServlet", value = "/TerrenoServlet")
@MultipartConfig
public class TerrenoServlet extends HttpServlet {
    /**
     * Object that provides the methods to manage the Terreno.
     */
    private TerrenoManager tm = new TerrenoManager();
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
        if (request.getParameter("inserisciTerreno_submit") != null) {
            //setta i parametri
            String nome = request.getParameter("nome");
            String azienda = request.getParameter("azienda");
            Part immagine = request.getPart("immagine");
            InputStream oo = immagine.getInputStream();
            byte[] imageData = oo.readAllBytes();
            oo.close();

            Float latitudine;
            Float longitudine;
            Float superficie;
            try {
                latitudine = Float.parseFloat(request.getParameter("latitudine"));
            } catch (NumberFormatException num) {
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", "Errore nel formato della latitudine. Controlla l'info");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                longitudine = Float.parseFloat(request.getParameter("longitudine"));
            } catch (NumberFormatException num) {
                request.setAttribute("errore", "2");
                request.setAttribute("descrizione", "Errore nel formato della longitudine. Controlla l'info");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                superficie = Float.parseFloat(request.getParameter("superficie"));
            } catch (NumberFormatException num) {
                request.setAttribute("errore", "3");
                request.setAttribute("descrizione", "Errore nel formato della superficie. Controlla l'info");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
                return;
            }
            TerrenoBean terreno = new TerrenoBean(nome, latitudine, longitudine, superficie, imageData, azienda);
            System.out.println(terreno);
            if (!terreno.getNome().matches("^[a-zA-Z0-9 ]{3,30}")) {
                request.setAttribute("errore", "4");
                request.setAttribute("descrizione", "Errore nel nome. Controlla l'info.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
            } else if (terreno.getLatitudine() < 0 || terreno.getLatitudine() > 90) {
                request.setAttribute("errore", "5");
                request.setAttribute("descrizione", "Errore nei valori insriti in latitudine. Controlla l'info");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
            } else if (terreno.getLongitudine() < 0 || terreno.getLongitudine() > 180) {
                request.setAttribute("errore", "6");
                request.setAttribute("descrizione", "Errore nei valori insriti in longitudine. Controlla l'info");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);

            } else if (immagine.getSize() > 3145728L || immagine.getSize() <= 0) {
                request.setAttribute("errore", "7");
                request.setAttribute("descrizione", "Errore nella dimensione dell'immagine");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
            } else if (!immagine.getContentType().contains("image")) {
                request.setAttribute("errore", "8");
                request.setAttribute("descrizione", "Errore nel formato dell'immagine. Controlla l'info");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
            } else {
                if (tm.inserisciTerreno(terreno) != null) {
                    request.setAttribute("conferma", "10");
                    request.setAttribute("descrizione", "Inserimento avvenuto con successo");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Terreni.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("errore", "9");
                    request.setAttribute("descrizione", "Errore nel salvataggio dei dati.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                    dispatcher.forward(request, response);
                }
            }
        }
       //response.sendRedirect("index.jsp");
    }
}
