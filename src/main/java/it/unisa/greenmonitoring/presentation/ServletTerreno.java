package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager;
import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletTerreno", value = "/ServletTerreno")
public class ServletTerreno extends HttpServlet {
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

        String azienda = request.getParameter("azienda");
        String immagine = request.getParameter("immagine");
        Float latitudine = Float.parseFloat(request.getParameter("latitudine"));
        Float longitudine = Float.parseFloat(request.getParameter("longitudine"));
        String superfice = request.getParameter("superfice");
        TerrenoBean terreno = new TerrenoBean(latitudine, longitudine, superfice, immagine, azienda);
        try {
            tm.createTerreno(terreno);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("index.jsp");
    }
}
