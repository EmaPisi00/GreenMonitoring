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


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("sono in rimuoviterreno");
        if (!(request.getSession().getAttribute("currentUserSession") instanceof AziendaBean)) {
            response.sendError(401);
        }

        String action = request.getParameter("action");
        TerrenoManager terrenoManager = new TerrenoManager();
        HttpSession session = request.getSession();
        UtenteBean user = (UtenteBean) session.getAttribute("currentUserSession");


        if (user instanceof AziendaBean) {
            System.out.println("sono in rimuoviterreno");
            int idTerreno = Integer.parseInt(request.getParameter("id"));
            if (terrenoManager.rimuoviTerreno(idTerreno, user.getEmail())) {
                request.setAttribute("conferma", "1");
                request.setAttribute("descrizione", "Terreno rimosso con successo");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Terreni.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", "Terreno occupato in una coltivazione attiva");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Terreni.jsp");
                dispatcher.forward(request, response);
            }


        } else {
            response.sendRedirect("error.jsp");
        }


    }
}
