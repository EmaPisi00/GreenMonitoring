package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ArchiviaColtivazioneServlet", value = "/ArchiviaColtivazioneServlet")
public class ArchiviaColtivazioneServlet extends HttpServlet {
    /**
     * ColtivazioneManager.
     */
    private ColtivazioneManager coltivazioneManager = new ColtivazioneManager();
    /**
     * SensoreManager.
     */
    private SensoreManager sensoreManager = new SensoreManager();
    /**
     * prova.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Questa servlet archivia la coltivazione.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!(request.getSession().getAttribute("currentUserSession") instanceof AziendaBean)) {
            response.sendError(401);
        }
        AziendaBean aziendaBean = (AziendaBean) request.getSession().getAttribute("currentUserSession");
        int coltivazione = Integer.valueOf(request.getParameter("coltivazione"));
        ColtivazioneBean coltivazioneBean = coltivazioneManager.retrieveColtivazioneSingola(coltivazione);
        coltivazioneBean.setData_fine(new Date(System.currentTimeMillis()));
        coltivazioneBean.setStato_archiviazione((byte) 1);
        List<SensoreBean> sensoreBeanList = sensoreManager.visualizzaListaSensori(aziendaBean.getEmail());
        sensoreBeanList.stream()
                .filter(sensoreBean -> sensoreBean.getColtivazione().equals(coltivazione))
                .forEach(sensoreBean -> sensoreManager.cancellaAssociazioneSensore(sensoreBean));
        sensoreBeanList = sensoreManager.visualizzaListaSensori(aziendaBean.getEmail()).stream().filter(sensoreBean -> sensoreBean.getColtivazione().equals(coltivazione)).toList();
        coltivazioneManager.aggiornaColtivazione(coltivazioneBean);
        //System.out.println("[" + "\u001B[33m" + "ArchiviaColtivazioneServlet" + "\u001B[0m" + "] -- lista dei sensori ottenuta: " + (sensoreBeanList.size() > 0));
        response.sendRedirect("ListaColtivazioni.jsp");
    }
}
