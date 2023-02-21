package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager;
import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.TerrenoManager;
import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "ColtivazioniServlet", value = "/ColtivazioniServlet")
public class ColtivazioniServlet extends HttpServlet {
    /**
     * Sensore manager.
     */
    private SensoreManager sensoreManager = new SensoreManager();
    /**
     * Pianta manager.
     */
    private PiantaManager piantaManager = new PiantaManager();
    /**
     * Terreno manager.
     */
    private TerrenoManager terrenoManager = new TerrenoManager();
    /**
     * Coltivazione manager.
     */
    private ColtivazioneManager coltivazioneManager = new ColtivazioneManager();
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
        } else if (request.getParameter("moduloInserimentoColtivazione") != null) {
            Date date = new java.sql.Date(System.currentTimeMillis());
            AziendaBean aziendaBean = (AziendaBean) ((UtenteBean) request.getSession().getAttribute("currentUserSession"));
            String utente = aziendaBean.getEmail();
            String[] sensorePh = request.getParameterValues("sensorePh"); //id
            String[] sensoreTemperatura = request.getParameterValues("sensoreTemperatura"); //id
            String[] sensoreUmidita = request.getParameterValues("sensoreUmidita"); //id
            ArrayList<String> sensori = new ArrayList<>();

            int idPianta;
            int idterreno;
            Date dataInizio;
            try {
                idPianta = Integer.parseInt(request.getParameter("nomepianta"));
            } catch (NumberFormatException | NullPointerException numberFormatException) {
                request.setAttribute("errore", "1");
                request.setAttribute("descrizione", " Formato id pianta errato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaColtivazioni.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                idterreno = Integer.parseInt(request.getParameter("terreno"));
            } catch (NumberFormatException | NullPointerException numberFormatException) {
                request.setAttribute("errore", "2");
                request.setAttribute("descrizione", " Formato terreno errato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaColtivazioni.jsp");
                dispatcher.forward(request, response);
                return;
            }
            try {
                dataInizio = Date.valueOf(request.getParameter("datainizio"));
            } catch (IllegalArgumentException | NullPointerException numberFormatException) {
                request.setAttribute("errore", "3");
                request.setAttribute("descrizione", " Formato data errato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            }
            if (piantaManager.visualizzaPianta(idPianta) == null) {
                request.setAttribute("errore", "4");
                request.setAttribute("descrizione", " Pianta inesistente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (terrenoManager.restituisciTerrenoDaInt(idterreno) == null) {
                request.setAttribute("errore", "5");
                request.setAttribute("descrizione", " Terreno inesistente");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            } else if (dataInizio.after(new Date(System.currentTimeMillis()))) {
                request.setAttribute("errore", "6");
                request.setAttribute("descrizione", " Data non valida");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            } else if ((sensorePh == null || sensorePh.length == 0) && (sensoreTemperatura == null || sensoreTemperatura.length == 0) && (sensoreUmidita == null || sensoreUmidita.length == 0)) {
                request.setAttribute("errore", "7");
                request.setAttribute("descrizione", " Nessun sensore selezionato");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AggiungiColtivazione.jsp");
                dispatcher.forward(request, response);
                return;
            } else {
                if (sensorePh != null) {
                    Collections.addAll(sensori, sensorePh);
                }
                if (sensoreTemperatura != null) {
                    Collections.addAll(sensori, sensoreTemperatura);
                }
                if (sensoreUmidita != null) {
                    Collections.addAll(sensori, sensoreUmidita);
                }
            }
            ColtivazioneBean cb = new ColtivazioneBean();
            cb.setPianta(idPianta);
            cb.setStato_archiviazione(Byte.parseByte("0"));
            cb.setTerreno(idterreno);
            cb.setData_inizio(dataInizio);
            try {
                ArrayList<SensoreBean> listaSensoriBean = new ArrayList<>();
                for (String id_sensore : sensori) {
                    listaSensoriBean.add(sensoreManager.visualizzaSensore(Integer.parseInt(id_sensore)));
                }
                if (coltivazioneManager.avvioColtivazione(cb, utente, listaSensoriBean) == null) {
                    request.setAttribute("errore", "8");
                    request.setAttribute("descrizione", " Errore nel salvataggio della coltivazione");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaColtivazioni.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("errore", "9");
                    request.setAttribute("descrizione", " Coltivazione avviata. ");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaColtivazioni.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
