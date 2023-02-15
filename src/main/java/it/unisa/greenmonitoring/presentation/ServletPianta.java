package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@WebServlet(name = "ServletPianta", value = "/ServletPianta")
@MultipartConfig
public class ServletPianta extends HttpServlet {
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
            String ph_min = request.getParameter("ph_min");
            String ph_max = request.getParameter("ph_max");
            String temperatura_min = request.getParameter("temperatura_min");
            String temperatura_max = request.getParameter("temperatura_max");
            String umidita_min = request.getParameter("umidita_min");
            String umidita_max = request.getParameter("umidita_max");
            System.out.println(azienda + nome + descrizione + ph_max + ph_min + temperatura_max + temperatura_min + umidita_max + umidita_min);
            Part immagine = request.getPart("immagine");
            String fileName = null;

            String contextPath = request.getServletContext().getRealPath("/");
            Path currentPath = Paths.get(contextPath);
            Path parentPath = currentPath.getParent();

            if (immagine.getSize() > 0) {
                fileName = Paths.get(immagine.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                InputStream fileContent = immagine.getInputStream();
                // Salva l'immagine su disco
                String path = parentPath + "/immagini/piante/" + fileName;
                Files.copy(fileContent, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
                fileContent.close();
            }

            PiantaBean pianta = new PiantaBean(azienda, nome, descrizione, ph_min, ph_max, temperatura_min, temperatura_max);
            pianta.setUmidita_min(umidita_min);
            pianta.setUmidita_max(umidita_max);
            pianta.setImmagine(fileName);

            PiantaBean errori = pm.inserisciPianta(pianta);
            if (errori == null) {
                request.setAttribute("erroriPiantaBean", "errore nell inserimento dei dati pianta");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/InserisciPianta.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Piante.jsp");
                dispatcher.forward(request, response);
            }
        } else if (request.getParameter("rimuoviPianta_submit") != null) {
            HttpSession session = request.getSession();
            AziendaBean utente = (AziendaBean) session.getAttribute("currentUserSession");
            System.out.println("***** id" + request.getParameter("rimuoviPianta_submit"));
            int idPianta = Integer.parseInt(request.getParameter("rimuoviPianta_submit"));
            pm.rimuoviPiantaManager(idPianta, utente.getEmail());
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Piante.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("modificaRange_submit") != null) {
            int idPianta = Integer.parseInt(request.getParameter("modificaRange_submit"));
            System.out.println("pianta id= " + idPianta);
            PiantaBean pianta = pm.visualizzaPianta(idPianta);
            request.setAttribute("piantaModificare", pianta);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ModificaRangePianta.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("modificaPianta") != null) {
            HttpSession session = request.getSession();
            PiantaBean pianta = (PiantaBean) session.getAttribute("pianta");
            PiantaBean copiaPianta = new PiantaBean();
            String ph_min = request.getParameter("ph_min").isEmpty() ? pianta.getPh_min() : request.getParameter("ph_min");
            String ph_max = request.getParameter("ph_max").isEmpty() ? pianta.getPh_max() : request.getParameter("ph_max");
            String temperatura_min = request.getParameter("temperatura_min").isEmpty() ? pianta.getTemperatura_min() : request.getParameter("temperatura_min");
            String temperatura_max = request.getParameter("temperatura_max").isEmpty() ? pianta.getTemperatura_max() : request.getParameter("temperatura_max");
            String umidita_min = request.getParameter("umidita_min").isEmpty() ? pianta.getUmidita_min() : request.getParameter("umidita_min");
            String umidita_max = request.getParameter("umidita_max").isEmpty() ? pianta.getUmidita_max() : request.getParameter("umidita_max");
            copiaPianta.setId(pianta.getId());
            copiaPianta.setImmagine(pianta.getImmagine());
            copiaPianta.setNome(pianta.getNome());
            copiaPianta.setDescrizione(pianta.getDescrizione());
            copiaPianta.setAzienda(pianta.getAzienda());
            copiaPianta.setTemperatura_max(temperatura_max);
            copiaPianta.setTemperatura_min(temperatura_min);
            copiaPianta.setUmidita_min(umidita_min);
            copiaPianta.setUmidita_max(umidita_max);
            copiaPianta.setPh_max(ph_max);
            copiaPianta.setPh_min(ph_min);
            System.out.println("copia = " + copiaPianta + "\npiantanormale=" + pianta);
            if (pm.aggiornaPianta(copiaPianta) == null) {
                System.out.println("nno");
                request.setAttribute("erroreDatiPianta", "dati inseriti errati");
                request.setAttribute("piantaModificare", pianta);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ModificaRangePianta.jsp");
                dispatcher.forward(request, response);
            } else {
                System.out.println("va tuttto bene");
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/Piante.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
    }
