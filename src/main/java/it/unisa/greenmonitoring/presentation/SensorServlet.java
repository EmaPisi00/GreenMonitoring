package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "SensorServlet", value = "/SensorServlet")
@MultipartConfig
public class SensorServlet extends HttpServlet {

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
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        int id_mosquitto = Integer.parseInt(request.getParameter("id_mosquitto"));
        UtenteBean u = (UtenteBean) request.getSession().getAttribute("currentUserSession");
        AziendaBean azienda = (AziendaBean) u;
        SensoreBean sns = new SensoreBean();
        sns.setTipo(tipo);
        sns.setAzienda(azienda.getEmail());
        sns.setIdM(id_mosquitto);
        try {
            SensoreManager.createSensore(sns);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("GestioneSensori.jsp");
    }

}

