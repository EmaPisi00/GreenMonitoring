package it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio;

import it.unisa.greenmonitoring.dataccess.beans.*;
import it.unisa.greenmonitoring.dataccess.dao.*;

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
     * @param notifica
     */
    public void inserisciNotificaManager(NotificaBean notifica) {
        nd.aggiungiNotifica(notifica);
    }

    /**
     *
     * @param id
     */
    public void LeggiNotificaAziendaManager(int id) {
        nd.updateLetturaNotifica(id);
    }
}
