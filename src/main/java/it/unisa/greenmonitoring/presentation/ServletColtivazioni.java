package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                /*
                PiantaManager pm = new PiantaManager();
                PiantaBean pb = new PiantaBean( (String) request.getSession().getAttribute("currentUserSession"),
                        request.getParameter("nomepianta"),
                        request.getParameter("nomepianta"),
                        0, 9, 0, 9);
                try {
                    pm.CreaPiantaManager(pb);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //-1 perchè sarebbe ottimo avere un retreive pianta nel manager, in modo da averne l'id.
                ColtivazioneBean cb = new ColtivazioneBean(-1, Integer.parseInt(request.getParameter("terreno")), Byte.parseByte("0"));
                ColtivazioneManager cm = new ColtivazioneManager();
                cm.avvioColtivazione(cb);*/
            }
        }
    }
}
