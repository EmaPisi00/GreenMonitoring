package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.sql.SQLException;
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
                java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                if (request.getParameter("nomepianta") == null || request.getParameter("terreno") == null) {
                    String errore = "L'operazione non è stata eseguita: La pianta o il terreno sono vuoti.";
                    request.getSession().setAttribute("errore", errore);
                    response.sendRedirect("ListaColtivazioni.jsp");
                } else if (java.sql.Date.valueOf(request.getParameter("datainizio")).after(date)) {
                    String errore = "La data non è corretta.";
                    request.getSession().setAttribute("errore", errore);
                } else {
                    AziendaBean aziendaBean = (AziendaBean) ((UtenteBean) request.getSession().getAttribute("currentUserSession"));
                    String utente = aziendaBean.getEmail();
                    String nomepianta = request.getParameter("nomepianta"); //id
                    String sensorePh = request.getParameter("sensorePh"); //id
                    String sensoreTemperatura = request.getParameter("sensoreTemperatura"); //id
                    String sensoreUmidita = request.getParameter("sensoreUmidita"); //id
                    String dataInizio = request.getParameter("datainizio");
                    java.sql.Date dataInizioDate = java.sql.Date.valueOf(dataInizio);
                    int terreno = Integer.parseInt(request.getParameter("terreno")); //id
                    ColtivazioneBean cb = new ColtivazioneBean();
                    cb.setPianta(Integer.valueOf(nomepianta));
                    cb.setStato_archiviazione(Byte.parseByte("0"));
                    cb.setTerreno(terreno);
                    SensoreManager sm = new SensoreManager();
                    List<SensoreBean> slist = sm.visualizzaListaSensori(aziendaBean.getEmail());
                    for (int i = 0; i < slist.size(); i++) {
                        SensoreBean s = null;
                        if (slist.get(i).getId() == Integer.valueOf(sensorePh) || slist.get(i).getId() == Integer.valueOf(sensorePh) || slist.get(i).getId() == Integer.valueOf(sensoreTemperatura)) {
                            s = slist.get(i);
                            cb.getListaSensori().add(s);
                        }
                    }
                    ColtivazioneManager cm = new ColtivazioneManager();
                    cb.setData_inizio(dataInizioDate);
                    try {
                        cm.avvioColtivazione(cb, utente);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    List<ColtivazioneBean> cblist = cm.visualizzaColtivazioniAvviate(aziendaBean.getEmail());
                    Integer id_coltivazione = 0;
                    for (int i = 0; i < cblist.size(); i++) {
                        if (cblist.get(i).getTerreno().equals(terreno)) {
                            id_coltivazione = cblist.get(i).getTerreno();
                            break;
                        }
                    }
                    SensoreBean s = new SensoreBean();
                    /*
                    for (int i = 0; i < slist.size(); i++) {
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
                    }
                request.getSession().setAttribute("coltivazioneID", String.valueOf(id_coltivazione));
                response.sendRedirect("Coltivazione.jsp");*/
                }
            }
        } else if (request.getParameter("sensoreDaRimuovere") != null) {
            String sensoreDaRimuovere = request.getParameter("sensoreDaRimuovere");
            System.out.println(sensoreDaRimuovere);
            /*AziendaBean aziendaBean = (AziendaBean) ((UtenteBean) request.getSession().getAttribute("currentUserSession"));
            SensoreManager sm = new SensoreManager();
            List<SensoreBean> sensoreBeanList = sm.visualizzaListaSensori(aziendaBean.getEmail());
            for (int i = 0; i < sensoreBeanList.size(); i++) {
                if (sensoreBeanList.get(i).getId() == Integer.valueOf(sensoreDaRimuovere)) {
                    try {
                        sm.cancellaSensore(sensoreBeanList.get(i));
                        break;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            } */

        }
    }
}
