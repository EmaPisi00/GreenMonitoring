package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "RimuoviTerrenoServlet", value = "/RimuoviTerrenoServlet")
public class RimuoviTerrenoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!(request.getSession().getAttribute("currentUserSession") instanceof UtenteBean)) {
            response.sendError(401);
        }

        String action = request.getParameter("action");
        TerrenoManager terrenoManager = new TerrenoManager();
        HttpSession session = request.getSession();
        UtenteBean user = (UtenteBean) session.getAttribute("currentUserSession");


        if (user instanceof AziendaBean) {
            if (action != null && action.equalsIgnoreCase("delete")) {

                int idTerreno = Integer.parseInt(request.getParameter("id"));
                terrenoManager.rimuoviTerreno(idTerreno, user.getEmail());
                response.sendRedirect("Terreni.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
