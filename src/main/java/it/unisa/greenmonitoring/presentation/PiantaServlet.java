package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager;
import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "PiantaServlet", value = "/PiantaServlet")
@MultipartConfig
public class PiantaServlet extends HttpServlet {
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
        if (request.getParameter("inserisciPianta_submit") != null) {
            HttpSession session = request.getSession();
            UtenteBean utente = (UtenteBean) session.getAttribute("currentUserSession");
            String azienda = utente.getEmail();
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            Float ph_min;
            Float ph_max;
            Float temperatura_min;
            Float temperatura_max;
            Float umidita_min;
            Float umidita_max;
            Part immagine = request.getPart("immagine");
            InputStream oo = immagine.getInputStream();
            byte[] imageData = oo.readAllBytes();
            oo.close();

            //errore formato ph_min
            try {
                 ph_min = Float.parseFloat(request.getParameter("ph_min"));
            } catch (NumberFormatException e) {
                System.out.println("1");
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", "errore ph_min formato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            //errore formato ph_max
            try {
                ph_max = Float.parseFloat(request.getParameter("ph_max"));
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "2");
                request.setAttribute("descrizione", "errore ph_max formato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            //errore formato temp_min
            try {
                temperatura_min = Float.parseFloat(request.getParameter("temperatura_min"));
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "3");
                request.setAttribute("descrizione", "errore temperatura_min formato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            //errore formato temp_max
            try {
                temperatura_max = Float.parseFloat(request.getParameter("temperatura_max"));
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "4");
                request.setAttribute("descrizione", "errore temperatura_max formato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            //errore formato umidita_min
            try {
               umidita_min = Float.parseFloat(request.getParameter("umidita_min"));
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "5");
                request.setAttribute("descrizione", "errore umidita_min formato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
                return;
            }
            //errore formato umidita_max
            try {
                umidita_max = Float.parseFloat(request.getParameter("umidita_max"));
            } catch (NumberFormatException e) {
                request.setAttribute("errore", "6");
                request.setAttribute("descrizione", "errore umidita_max formato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
                return;
            }

            PiantaBean pianta = new PiantaBean(azienda, nome, descrizione, ph_min, ph_max, temperatura_min, temperatura_max);
            pianta.setUmidita_min(umidita_min);
            pianta.setUmidita_max(umidita_max);
            pianta.setImmagine(imageData);

            //errori
            if (!pianta.getNome().matches("^[a-zA-Z0-9 ]{3,30}")) {
                request.setAttribute("errore", "7");
                request.setAttribute("descrizione", "errore nome");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
            } else if (pianta.getPh_min() <= 1 || pianta.getPh_min() >= 14 || pianta.getPh_max() <= 1 || pianta.getPh_max() >= 14) {
                request.setAttribute("errore", "8");
                request.setAttribute("descrizione", "errore valori range ph");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
            } else if (pianta.getTemperatura_min() < 10 || pianta.getTemperatura_min() > 100 || pianta.getTemperatura_max() < 10 || pianta.getTemperatura_max() > 100) {
                request.setAttribute("errore", "9");
                request.setAttribute("descrizione", "errore valori range temperatura");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
            } else if (pianta.getUmidita_min() < 1 || pianta.getUmidita_min() > 100 || pianta.getUmidita_max() < 1 || pianta.getUmidita_max() > 100) {
                 request.setAttribute("errore", "10");
                 request.setAttribute("descrizione", "errore valori range umidità");
                 RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                 dispatcher.forward(request, response);
            } else if (!pianta.getDescrizione().matches("^[a-zA-Z0-9 ]{1,255}")) {
                request.setAttribute("errore", "11");
                request.setAttribute("descrizione", "errore lunghezza descrizione");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
            } else if (immagine.getSize() > 3145728L || immagine.getSize() <= 0) {
                request.setAttribute("errore", "12");
                request.setAttribute("descrizione", "errore dimensione immagine");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
            } else if (!immagine.getContentType().contains("image")) {
                request.setAttribute("errore", "13");
                request.setAttribute("descrizione", "errore tipo immagine");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
            } else {
                if (pm.inserisciPianta(pianta) == null) {
                    // se l'inserimento nel db non è andato a buon fine.
                    request.setAttribute("errore", "14");
                    request.setAttribute("descrizione", "errore nell'inserimento nel DB oppure il nome della pianta già esiste per questa azienda");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/InserisciPianta.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("conferma", "15");
                    request.setAttribute("descrizione", "Inserimento avvenuto con successo");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/Piante.jsp");
                    dispatcher.forward(request, response);
                }
            }
        }
    }
}
