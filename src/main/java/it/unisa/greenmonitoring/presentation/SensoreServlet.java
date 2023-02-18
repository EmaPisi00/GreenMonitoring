package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "SensoreServlet", value = "/SensoreServlet")
@MultipartConfig
public class SensoreServlet extends HttpServlet {

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
        SensoreBean sns = new SensoreBean();
        SensoreManager sensoreManager = new SensoreManager();
        if (request.getParameter("RegistraSensore") != null) {
            String tipo = request.getParameter("tipo");
            String id_mosquitto = request.getParameter("id_mosquitto");
            UtenteBean u = (UtenteBean) request.getSession().getAttribute("currentUserSession");
            AziendaBean azienda = (AziendaBean) u;
            sns.setTipo(tipo);
            sns.setAzienda(azienda.getEmail());
            sns.setIdM(id_mosquitto);
            try {
                sensoreManager.creaSensore(sns);
                request.setAttribute("success", "1");
                request.setAttribute("successMessage", "Sensore inserito con successo.");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InserisciSensore.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("success", "2");
                request.setAttribute("successMessage", "Sensore non inserito.");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/InserisciSensore.jsp");
                throw new RuntimeException(e);
            }

        } else if (request.getParameter("AssociaSensore") != null) {
            int coltivazione = Integer.parseInt(request.getParameter("id_coltivazione"));
            int id_sensore = Integer.parseInt(request.getParameter("id_sensore"));
            try {
                sns = sensoreManager.visualizzaSensore(id_sensore);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sensoreManager.aggiungiAssociazioneSensore(coltivazione, sns);
            request.setAttribute("success", "1");
            request.setAttribute("successMessage", "Sensore associato con successo.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListaSensori.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("CancellaAssociazioneSensore") != null) {
            int id_sensore = Integer.parseInt(request.getParameter("id_sensore"));
            try {
                sns = sensoreManager.visualizzaSensore(id_sensore);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sensoreManager.cancellaAssociazioneSensore(sns);
            request.setAttribute("success", "2");
            request.setAttribute("successMessage", "Sensore rimosso con successo.");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListaSensori.jsp");
            dispatcher.forward(request, response);
        }
    }

}

