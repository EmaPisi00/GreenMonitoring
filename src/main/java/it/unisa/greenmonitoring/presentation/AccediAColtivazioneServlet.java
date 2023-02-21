package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccediAColtivazioneServlet", value = "/AccediAColtivazioneServlet")
public class AccediAColtivazioneServlet extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        if (!(request.getSession().getAttribute("currentUserSession") instanceof UtenteBean)) {
            response.sendError(401);
        }
        if (request.getParameter("coltivazione") != null) {
            request.getSession().setAttribute("coltivazioneID", request.getParameter("coltivazione"));
            response.sendRedirect("Coltivazione.jsp");
        }
    }
}
