package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.NotificaBean;

import java.util.List;

public interface NotificaDAO {
    /**
     * crep una notifica da inserire nl de.
     * @param notifica
     * @param dipendenti
     */
    void aggiungiNotifica(NotificaBean notifica, List<String> dipendenti);

    /**
     *
     * @param azienda
     * @return List
     */
    List<NotificaBean> retriveNotifichePerAzienda(String azienda);

    /**
     *
     * @param id
     */
    void updateLetturaNotificaAzienda(int id);

    /**
     *
     * @param id
     * @param idDipendente
     */
    void updateLetturaNotificaDipendente(int id, String idDipendente);

    /**
     *
     * @param emailDipendente
     * @return List
     */
    List<NotificaBean> retriveNotifichePerDipendente(String emailDipendente);
}
