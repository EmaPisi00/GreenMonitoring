package it.unisa.greenmonitoring.presentation;

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
                String nomepianta = request.getParameter("nomepianta");
                String sensoreid = request.getParameter("sensore");
                int terreno = Integer.parseInt(request.getParameter("terreno"));
                PiantaDAOImpl pdao = new PiantaDAOImpl();
                try {
                    int id = 0; //questo id serve per trovare l'id della prima occorrenza, senza azienda di una certa pianta

                    //qui usa il dao ma quando sarà implementata la funzione utilizzerà il manager.
                    List<PiantaBean> ap = pdao.RetriveAllPiantaDefault();
                    for (int i = 0; i < ap.size(); i++) {
                        if (ap.get(i).getNome().equals(nomepianta)) {
                            System.out.println("ServletColtivazioni: nome della pianta " + ap.get(i).getNome());
                            if (ap.get(i).getAzienda() == null) {
                                id = ap.get(i).getId();
                                break;
                            }
                        }
                    }
                    System.out.println("ServletColtivazioni: id della pianta: " + id);
                    ColtivazioneBean cb = new ColtivazioneBean(id, terreno, Byte.parseByte("0"));
                    ColtivazioneManager cm = new ColtivazioneManager();
                    cm.avvioColtivazione(cb);
                    /* Prendo l'id della coltivazione appena generata */
                    int id_nuovaColtivazione = 0;
                    List<ColtivazioneBean> listColtivazioni = cm.visualizzaColtivazioniAvviate(utente);
                    for (int i = 0; i < listColtivazioni.size(); i++) {
                        if (listColtivazioni.get(i).getData_inizio().equals(cb.getData_inizio()) && listColtivazioni.get(i).getTerreno() == terreno) {
                            id_nuovaColtivazione = listColtivazioni.get(i).getId();
                        }
                    }
                    /* Procedo ad associare la coltivazione al sensore*/

                    //qui usa il dao ma quando sarà implementata la funzione utilizzerà il manager.
                    SensoreManager sm = new SensoreManager();
                    SensoreDAOImpl sdao = new SensoreDAOImpl();
                    SensoreBean sb = sm.retrieveSensore(Integer.parseInt(sensoreid)); //cerco il sensore in base all'id passato dal form.
                    sb.setColtivazione(id_nuovaColtivazione); //ora il sensore è associato a quella coltivazione
                    sdao.update(sb.getId(), sb);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
