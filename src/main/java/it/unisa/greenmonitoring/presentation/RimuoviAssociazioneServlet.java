package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.UtenteManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RimuoviAssociazioneServlet", value = "/RimuoviAssociazioneServlet")
public class RimuoviAssociazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getParameter("action");
        UtenteManager utenteManager = new UtenteManager();
        HttpSession session = request.getSession();
        UtenteBean user = (UtenteBean) session.getAttribute("currentUserSession");


        if (user instanceof AziendaBean) {
            if (action != null && action.equalsIgnoreCase("delete")) {

                String email = request.getParameter("email");
                try {
                    utenteManager.rimuoviAssociazioneDipendente(email);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                response.sendRedirect("RimuoviDipendente.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
