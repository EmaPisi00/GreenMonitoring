package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.UtenteManager;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AssociazioneServlet", value = "/AssociazioneServlet")
public class AssociazioneServlet extends HttpServlet {

    /** Non lo so, l'ha messa intellij sta roba sotto.
    */
    public AssociazioneServlet() throws SQLException {
    }


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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //
        UtenteManager utenteManager = new UtenteManager();
        String codiceAzienda = request.getParameter("codiceAzienda");
        HttpSession session = request.getSession();
        UtenteBean user = (UtenteBean) session.getAttribute("currentUserSession");

        if (user instanceof DipendenteBean) {
            if (((DipendenteBean) user).getAzienda() != null) { //controlla se l'utente è già associato
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Associazione.jsp");
                dispatcher.forward(request, response);
            } else if (!(codiceAzienda.matches("^\\w{8}$"))) { //stringa di 8 char, es: ASDdd234
                request.setAttribute("errore", "2");
                request.setAttribute("descrizione", "descrizione...");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Associazione.jsp");
                dispatcher.forward(request, response);
            } else {
                try {
                    if (utenteManager.associazioneDipendente((DipendenteBean) user, codiceAzienda)) {
                        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Profile.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        request.setAttribute("errore", "3");
                        request.setAttribute("descrizione", "non combacia");
                        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Associazione.jsp");
                        dispatcher.forward(request, response);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }

        } else {
            response.sendRedirect("Profile.jsp");
        }
    }
}
