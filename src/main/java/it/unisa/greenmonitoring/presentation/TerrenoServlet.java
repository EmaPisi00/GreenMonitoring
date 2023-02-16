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
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet(name = "ServletTerreno", value = "/ServletTerreno")
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
            byte[] imageData = immagine.getInputStream().readAllBytes();
            Float latitudine, longitudine, superficie;
            System.out.println("*****" + immagine.getSize());
            try {
                latitudine = Float.parseFloat(request.getParameter("latitudine"));
            } catch (NumberFormatException num) {
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", "errore latitudine formato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                longitudine = Float.parseFloat(request.getParameter("longitudine"));
            } catch (NumberFormatException num) {
                request.setAttribute("errore", "2");
                request.setAttribute("descrizione", "errore longitudine formato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                superficie = Float.parseFloat(request.getParameter("superficie"));
            } catch (NumberFormatException num) {
                request.setAttribute("errore", "3");
                request.setAttribute("descrizione", "errore superficie");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
                return;
            }
            TerrenoBean terreno = new TerrenoBean(nome, latitudine, longitudine, superficie, imageData, azienda);

            if (!terreno.getNome().matches("^[a-zA-Z0-9 ]{3,30}")) {
                request.setAttribute("errore", "4");
                request.setAttribute("descrizione", "errore nome");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
            } else if (terreno.getLatitudine() < 0 || terreno.getLatitudine() > 90) {
                request.setAttribute("errore", "5");
                request.setAttribute("descrizione", "errore latitudine");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
            } else if (terreno.getLongitudine() < 0 || terreno.getLongitudine() > 180) {
                request.setAttribute("errore", "6");
                request.setAttribute("descrizione", "errore longitudine");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);

            } else if (immagine.getSize() > 3145728L || immagine.getSize() <= 0) {
                System.out.println("***** SBAGLIATAAAA" + immagine.getSize());
                request.setAttribute("errore", "7");
                request.setAttribute("descrizione", "errore dimensione immagine");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
            } else if (!immagine.getContentType().contains("image")) {
                request.setAttribute("errore", "8");
                request.setAttribute("descrizione", "errore tipo immagine");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                dispatcher.forward(request, response);
            } else {

                if (tm.inserisciTerreno(terreno)!=null) {
                    request.setAttribute("errore", "10");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Terreni.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("errore", "9");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciTerreno.jsp");
                    dispatcher.forward(request, response);
                }
            }
        } else if (request.getParameter("inserisciTerreno_submit") == null) {
            Enumeration<String> parameters = request.getParameterNames();
            TerrenoBean temporaryTerrenoBean = new TerrenoBean();
            boolean result = false;
            while (parameters.hasMoreElements()) {
                int id = Integer.parseInt(request.getParameter(parameters.nextElement()));
                result = tm.rimuoviTerreno(id);
                if (!result) {
                    try {
                        temporaryTerrenoBean = tm.restituisciTerrenoDaInt(id);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    request.getSession().setAttribute("terrenoOccupato", " Il terreno " + temporaryTerrenoBean.getNome() + " è impegnato con una coltivazione.");
                    break;
                }
            }
            response.sendRedirect("Terreni.jsp");
        }
       //response.sendRedirect("index.jsp");
    }
}
