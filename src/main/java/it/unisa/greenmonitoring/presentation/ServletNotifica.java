package it.unisa.greenmonitoring.presentation;
import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.NotificaManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "ServletNotifica", value = "/ServletNotifica")
public class ServletNotifica extends HttpServlet {
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

        System.out.println("asdasd");
        //leggo la notifica
        Integer idNotifica = Integer.valueOf(request.getParameter("id"));

        NotificaManager nm = new NotificaManager();
        nm.LeggiNotificaAziendaManager(idNotifica);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"status\":\"success\"}");
        out.flush();

    }
}
