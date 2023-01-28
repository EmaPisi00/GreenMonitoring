package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionecoltivazione.PiantaManager;
import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.*;
import it.unisa.greenmonitoring.dataccess.dao.PiantaDAOImpl;
import it.unisa.greenmonitoring.dataccess.dao.SensoreDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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
                AziendaBean aziendaBean = (AziendaBean) ((UtenteBean) request.getSession().getAttribute("currentUserSession"));
                String utente = aziendaBean.getEmail();
                String nomepianta = request.getParameter("nomepianta"); //id
                String sensorePh = request.getParameter("sensorePh"); //id
                String sensoreTemperatura = request.getParameter("sensoreTemperatura"); //id
                String sensoreUmidita = request.getParameter("sensoreUmidita"); //id
                int terreno = Integer.parseInt(request.getParameter("terreno")); //id
                ColtivazioneBean cb = new ColtivazioneBean(Integer.valueOf(nomepianta), terreno, Byte.parseByte("0"));
                ColtivazioneManager cm = new ColtivazioneManager();
                cm.avvioColtivazione(cb);

                List<ColtivazioneBean> cblist = cm.visualizzaColtivazioniAvviate(aziendaBean.getEmail());
                int id_coltivazione = 0;
                for (int i = 0; i < cblist.size(); i++) {
                    if (cblist.get(i).getTerreno().equals(terreno)) {
                        id_coltivazione = cblist.get(i).getTerreno();
                        break;
                    }
                }

                SensoreManager sm = new SensoreManager();
                List<SensoreBean> slist = sm.visualizzaListaSensori(aziendaBean.getEmail());
                SensoreBean s = new SensoreBean();
             /*   for (int i = 0; i < slist.size(); i++) {
                    if (slist.get(i).getId().equals(sensorePh)) {
                        s = slist.get(i);
                        s.setColtivazione(id_coltivazione);
                        sm.updateSensore(s);
                    } else if (slist.get(i).getId().equals(sensoreTemperatura)) {
                        s = slist.get(i);
                        s.setColtivazione(id_coltivazione);
                        sm.updateSensore(s);
                    } else if (slist.get(i).getId().equals(sensoreUmidita)) {
                        s = slist.get(i);
                        s.setColtivazione(id_coltivazione);
                        sm.updateSensore(s);
                    }
                }     */
            }
        }
    }
}
