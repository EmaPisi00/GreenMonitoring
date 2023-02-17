package it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio;

import it.unisa.greenmonitoring.dataccess.beans.*;
import it.unisa.greenmonitoring.dataccess.dao.*;

import java.util.List;

public class NotificaManager {
    /**
     * nd.
     */
   private NotificaDAO nd;

    /**
     * qualcosa.
     */
    public NotificaManager() {
        nd = new NotificaDAOImpl();
    }

    /**
     *
     * @param id
     */
    public void LeggiNotificaAziendaManager(int id) {
        nd.updateLetturaNotificaAzienda(id);
    }

    /**
     *
     * @param id
     * @param email
     */
    public void LeggiNotificaDipendenteManager(int id, String email) {
        nd.updateLetturaNotificaDipendente(id, email);
    }
    /**
     *
     * @param aziendaId
     * @return List
     */
    public List<NotificaBean> NotificheNonLette(String aziendaId) {
        List<NotificaBean> listaDaVisualizzare = null;
        List<NotificaBean> listaNotifiche = nd.retriveNotifichePerAzienda(aziendaId);
        for (NotificaBean not: listaNotifiche) {
            listaDaVisualizzare.add(not);
        }
        System.out.println("lunghezza*****" + listaDaVisualizzare.size());
        return listaDaVisualizzare;
    }
}
