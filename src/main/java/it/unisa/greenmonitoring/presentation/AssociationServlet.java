package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAO;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AssociationServlet", value = "/AssociationServlet")
public class AssociationServlet extends HttpServlet {

    /** Non lo so, l'ha messa intellij sta roba sotto.
    */
    public AssociationServlet() throws SQLException {
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

        String codiceAzienda = request.getParameter("codiceAzienda");
        HttpSession session = request.getSession();
        UtenteBean user = (UtenteBean) session.getAttribute("currentUserSession");

        if (user instanceof DipendenteBean && ((DipendenteBean) user).getAzienda() == null) {
            AziendaDAO aziendaDAO = null;
            try {
                aziendaDAO = new AziendaDAOImpl();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            AziendaBean azienda;
            try {
                azienda = aziendaDAO.retrieveByCode(codiceAzienda);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (azienda != null) {
                DipendenteBean dipendente = (DipendenteBean) user;
                dipendente.setAzienda(azienda.getEmail());
                DipendenteDAO dipendenteDAO = null;
                try {
                    dipendenteDAO = new DipendenteDAOImpl();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    dipendenteDAO.doUpdate(dipendente);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect("Profile.jsp");
            } else {
                response.sendRedirect("Associazione.jsp");
            }
        } else {
            response.sendRedirect("Associazione.jsp");
        }
    }

}
