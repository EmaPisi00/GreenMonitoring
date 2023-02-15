package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.NotificaBean;

import java.util.List;

public interface NotificaDAO {
    /**
     * crep una notifica da inserire nl de.
     * @param notifica
     */
    void aggiungiNotifica(NotificaBean notifica);

    /**
     *
     * @param azienda
     * @return List
     */
    List<NotificaBean> retriveNotifichePerAzienda(String azienda);

    /**
     *
     * @param azienda
     * @return List
     */
    List<NotificaBean> retriveNotifichePerAziendaDaVisualizzare(String azienda);

    /**
     *
     * @param id
     */
    void updateLetturaNotifica(int id);
}
