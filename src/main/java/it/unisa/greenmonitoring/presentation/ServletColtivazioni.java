package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.ColtivazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager;
import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
import it.unisa.greenmonitoring.dataccess.dao.PiantaDAO;
import it.unisa.greenmonitoring.dataccess.dao.PiantaDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletColtivazioni", value = "/ServletColtivazioni")
public class ServletColtivazioni extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!(request.getSession().getAttribute("currentUserSession") instanceof UtenteBean)) {
            response.sendError(401);
        }
        if (request.getParameter("coltivazione") != null) {
            request.getSession().setAttribute("coltivazioneID", request.getParameter("coltivazione"));
            response.sendRedirect("Coltivazione.jsp");
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
        if (request.getParameter("moduloInserimentoColtivazione") != null) {
            if (!(request.getSession().getAttribute("currentUserSession") instanceof UtenteBean)) {
                response.sendError(401);
            } else {
                response.sendError(501);
                String utente = (String) request.getSession().getAttribute("currentUserSession");
                String nomepianta = request.getParameter("nomepianta");
                PiantaBean pb = new PiantaBean( utente,
                        nomepianta,
                        nomepianta,
                        "0", "9", "0", "9");
                PiantaManager pm = new PiantaManager();
                PiantaDAOImpl pdao = new PiantaDAOImpl();
                try {
                    pm.CreaPiantaManager(pb);
                    // -1 perchè sarebbe ottimo avere un retreive pianta nel manager, in modo da averne l'id.
                    int id = 0;
                    List<PiantaBean> ap = pdao.RetriveAllPianta();
                    for (int i = 0; i < ap.size(); i++) {
                        if (ap.get(i).getNome().equals(nomepianta)) {
                            if (ap.get(i).getAzienda() == null) {
                                id = ap.get(i).getId();
                                break;
                            }
                        }
                    }
                    ColtivazioneBean cb = new ColtivazioneBean( id, Integer.parseInt(request.getParameter("terreno")), Byte.parseByte("0"));
                    ColtivazioneManager cm = new ColtivazioneManager();
                    cm.avvioColtivazione(cb);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
