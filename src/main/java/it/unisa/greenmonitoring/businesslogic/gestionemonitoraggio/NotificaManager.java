package it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio;

import it.unisa.greenmonitoring.dataccess.beans.*;
import it.unisa.greenmonitoring.dataccess.dao.*;

import java.util.ArrayList;
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
     * @param utente
     * @return List
     */
    public List<NotificaBean> NotificheNonLette(String aziendaId, UtenteBean utente) {
        List<NotificaBean> listaDaVisualizzare = new ArrayList<>();
        List<NotificaBean> listaNotifiche = new ArrayList<>();
        if (utente instanceof AziendaBean) {
            listaNotifiche = nd.retriveNotifichePerAzienda(aziendaId);
        } else {
            listaNotifiche = nd.retriveNotifichePerDipendente(aziendaId);
        }
        for (NotificaBean not: listaNotifiche) {
            if (!not.getVisualizzazioneNotifica()) {
                listaDaVisualizzare.add(not);
            }

        }
        System.out.println("lunghezza*****" + listaDaVisualizzare.size());
        return listaDaVisualizzare;
    }
}
