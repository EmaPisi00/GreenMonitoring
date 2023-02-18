package it.unisa.greenmonitoring.presentation;
import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.NotificaManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
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



@WebServlet(name = "ServletNotifica", value = "/ServletNotifica")
public class ServletNotifica extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UtenteBean utente = (UtenteBean) session.getAttribute("currentUserSession");
        if (request.getParameter("aggiorna") != null) {
            int idNotifica = Integer.parseInt(request.getParameter("idNotifica"));
            NotificaManager nm = new NotificaManager();
            if (utente instanceof AziendaBean) {
                nm.LeggiNotificaAziendaManager(idNotifica);
            } else {
                nm.LeggiNotificaDipendenteManager(idNotifica, utente.getEmail());
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/SezioneNotifiche.jsp");
            dispatcher.forward(request, response);
        }
        if ("true".equals(request.getParameter("numNotifiche"))) {
            if (utente instanceof AziendaBean || utente instanceof DipendenteBean) {

                NotificaManager nm = new NotificaManager();
                Integer id = Integer.valueOf(request.getParameter("idNotifica"));

                if (utente instanceof AziendaBean) {
                    nm.LeggiNotificaAziendaManager(id);
                } else {
                    nm.LeggiNotificaDipendenteManager(id, utente.getEmail());
                }
                int numNotifiche = nm.NotificheNonLette(utente.getEmail(), utente).size();
                System.out.println(numNotifiche + "numeroe notifiche");
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(String.valueOf(numNotifiche));
            }
        }
    }

    /**
     * Metodo post.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
