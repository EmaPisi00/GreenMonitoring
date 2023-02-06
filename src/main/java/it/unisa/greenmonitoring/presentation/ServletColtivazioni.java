package it.unisa.greenmonitoring.presentation;

import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
                    String[] sensorePh = request.getParameterValues("sensorePh"); //id
                    System.out.println(sensorePh);
                    String[] sensoreTemperatura = request.getParameterValues("sensoreTemperatura"); //id
                    String[] sensoreUmidita = request.getParameterValues("sensoreUmidita"); //id
                    ArrayList<String> sensori = new ArrayList<>();
                    Collections.addAll(sensori, sensorePh);
                    Collections.addAll(sensori, sensoreTemperatura);
                    Collections.addAll(sensori, sensoreUmidita);
                    String dataInizio = request.getParameter("datainizio");
                    java.sql.Date dataInizioDate = java.sql.Date.valueOf(dataInizio);
                    int terreno = Integer.parseInt(request.getParameter("terreno")); //id
                    System.out.println("l'id del terreno nella servlet " + terreno);
                    ColtivazioneBean cb = new ColtivazioneBean();
                    cb.setPianta(Integer.valueOf(nomepianta));
                    cb.setStato_archiviazione(Byte.parseByte("0"));
                    cb.setTerreno(terreno);
                    SensoreManager sensoreManager = new SensoreManager();
                    ColtivazioneManager cm = new ColtivazioneManager();
                    cb.setData_inizio(dataInizioDate);
                    try {
                        cm.avvioColtivazione(cb, utente, sensori);

                        System.out.println("[ServletColtivazioni] - id_azienda is " + aziendaBean.getEmail());

                        ArrayList<ColtivazioneBean> coltivazioneBeans = cm.visualizzaStatoColtivazioni(aziendaBean.getEmail());
                                //visualizzaColtivazioniAvviate(aziendaBean.getEmail());

                        System.out.println("[ServletColtivazioni] - coltivazioneBeans is " + coltivazioneBeans.toString());

                        Integer id_coltivazione = coltivazioneBeans.get(coltivazioneBeans.size() - 1).getId();

                        System.out.println("[ServletColtivazioni] - id_coltivazione is " + id_coltivazione);

                        /*for (int i = 0; i < coltivazioneBeans.size(); i++) {
                            if (coltivazioneBeans.get(i).getTerreno().equals(cb.getTerreno())) {
                                id_coltivazione = coltivazioneBeans.get(i).getId();

                                System.out.println("[ServletColtivazioni] - id_coltivazione is " + id_coltivazione);

                                break;
                            }
                        }*/
                        cb.setId(id_coltivazione);
                        SensoreBean sensoreBean;
                        for (String sensore : sensorePh) {
                            sensoreBean = sensoreManager.retrieveSensore(Integer.parseInt(sensore));
                            sensoreManager.aggiungiAssociazioneSensore(cb.getId(), sensoreBean);
                        }
                        for (String sensore : sensoreTemperatura) {
                            sensoreBean = sensoreManager.retrieveSensore(Integer.parseInt(sensore));
                            sensoreManager.aggiungiAssociazioneSensore(cb.getId(), sensoreBean);
                        }
                        for (String sensore : sensoreUmidita) {
                            sensoreBean = sensoreManager.retrieveSensore(Integer.parseInt(sensore));
                            sensoreManager.aggiungiAssociazioneSensore(cb.getId(), sensoreBean);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    /*

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
