package it.unisa.greenmonitoring.presentation;
import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet(name = "RimuoviPiantaServlet", value = "/RimuoviPiantaServlet")
@MultipartConfig
public class RimuoviPiantaServlet extends HttpServlet {
    /**
     * Object that provides the methods to manage the Pianta.
     */
    private PiantaManager pm = new PiantaManager();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("idPianta"));
        if (request.getParameter("Rimuovi") != null) {
            System.out.println("sono in rimuovi");
            HttpSession session = request.getSession();
            AziendaBean utente = (AziendaBean) session.getAttribute("currentUserSession");
            int idPianta = Integer.parseInt(request.getParameter("idPianta"));
            if (!pm.rimuoviPiantaManager(idPianta, utente.getEmail())) {
                System.out.println("sono impegnata in coltivazione");
                request.setAttribute("errore", "0");
                request.setAttribute("descrizione", "Pianta, impegnata in una coltivazione");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Piante.jsp");
                dispatcher.forward(request, response);

            } else {
                System.out.println("conferma rimozione");
                request.setAttribute("conferma", "1");
                request.setAttribute("descrizione", "La pianta è stata rimossa con successo.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Piante.jsp");
                dispatcher.forward(request, response);

            }
        } else if (request.getParameter("modificaRange_submit") != null) {
            int idPianta = Integer.parseInt(request.getParameter("modificaRange_submit"));
            System.out.println("pianta id= " + idPianta);
            PiantaBean pianta = pm.visualizzaPianta(idPianta);
            request.getSession().setAttribute("piantaModificare", pianta);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("ModificaPianta") != null) {
            System.out.println("sono nella request modificaPianta");
            System.out.println("sono in modifica");
            HttpSession session = request.getSession();
            PiantaBean pianta = (PiantaBean) session.getAttribute("pianta");
            Float ph_min = null;
            Float ph_max = null;
            Float temperatura_min = null;
            Float temperatura_max = null;
            Float umidita_min = null;
            Float umidita_max = null;
            try {
                ph_min = request.getParameter("ph_min").isEmpty() ? pianta.getPh_min() : Float.valueOf(request.getParameter("ph_min"));
            } catch (Exception e) {
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", "errore ph_min formato");
                request.setAttribute("piantaModificare", pianta);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                ph_max = request.getParameter("ph_max").isEmpty() ? pianta.getPh_max() : Float.valueOf(request.getParameter("ph_max"));
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "2");
                request.setAttribute("descrizione", "errore ph_max formato");
                request.setAttribute("piantaModificare", pianta);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                temperatura_min = request.getParameter("temperatura_min").isEmpty() ? pianta.getTemperatura_min() : Float.valueOf(request.getParameter("temperatura_min"));
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "3");
                request.setAttribute("descrizione", "errore ph_max formato");
                request.setAttribute("piantaModificare", pianta);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                temperatura_max = request.getParameter("temperatura_max").isEmpty() ? pianta.getTemperatura_max() : Float.valueOf(request.getParameter("temperatura_max"));
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "4");
                request.setAttribute("descrizione", "errore ph_max formato");
                request.setAttribute("piantaModificare", pianta);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                umidita_min = request.getParameter("umidita_min").isEmpty() ? pianta.getUmidita_min() : Float.valueOf(request.getParameter("umidita_min"));
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "5");
                request.setAttribute("descrizione", "errore ph_max formato");
                request.setAttribute("piantaModificare", pianta);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                umidita_max = request.getParameter("umidita_max").isEmpty() ? pianta.getUmidita_max() : Float.valueOf(request.getParameter("umidita_max"));
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "6");
                request.setAttribute("descrizione", "errore ph_max formato");
                request.setAttribute("piantaModificare", pianta);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            PiantaBean copiaPianta = new PiantaBean();
            copiaPianta.setId(pianta.getId());
            copiaPianta.setTemperatura_min(temperatura_min);
            copiaPianta.setTemperatura_max(temperatura_max);
            copiaPianta.setUmidita_min(umidita_min);
            copiaPianta.setUmidita_max(umidita_max);
            copiaPianta.setPh_min(ph_min);
            copiaPianta.setPh_max(ph_max);

        if (copiaPianta.getPh_min() <= 1 || copiaPianta.getPh_min() >= 14 || copiaPianta.getPh_max() <= 1 || copiaPianta.getPh_max() >= 14) {
            request.setAttribute("errore", "7");
            request.setAttribute("descrizione", "errore valori range ph");
            request.setAttribute("piantaModificare", pianta);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
            dispatcher.forward(request, response);
        } else if (copiaPianta.getTemperatura_min() < 10 || copiaPianta.getTemperatura_min() > 100 || copiaPianta.getTemperatura_max() < 10 || copiaPianta.getTemperatura_max() > 100) {
            request.setAttribute("errore", "8");
            request.setAttribute("descrizione", "errore valori range temperatura");
            request.setAttribute("piantaModificare", pianta);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
            dispatcher.forward(request, response);
        } else if (copiaPianta.getUmidita_min() < 1 || copiaPianta.getUmidita_min() > 100 || copiaPianta.getUmidita_max() < 1 || copiaPianta.getUmidita_max() > 100) {
            request.setAttribute("errore", "9");
            request.setAttribute("descrizione", "errore valori range umidità");
            request.setAttribute("piantaModificare", pianta);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
            dispatcher.forward(request, response);
        }
            if (pm.aggiornaPianta(copiaPianta) == null) {
                request.setAttribute("errore", "10 ");
                request.setAttribute("descrizione", "Errore! Valore massimo minore di Valore minimo");
                request.setAttribute("piantaModificare", pianta);
                request.setAttribute("piantaModificare", pianta);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ModificaRangePianta.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("conferma", "11");
                request.setAttribute("descrizione", "Modifiche effettuate con successo");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Piante.jsp");
                dispatcher.forward(request, response);
            }
        }

    }
}
