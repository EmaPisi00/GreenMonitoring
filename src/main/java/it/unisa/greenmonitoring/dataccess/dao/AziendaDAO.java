package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;


import java.sql.SQLException;
import java.util.List;

/**
 * Classe che implementa il DAO della registrazione di un'azienda.
 */
public interface AziendaDAO {

    /**
     * Metodo create che permette di salvare i dati nel DB.
     * @param registrazioneAziendaBean
     * @throws SQLException
     */
    void create(AziendaBean registrazioneAziendaBean) throws SQLException;

    /**
     * Metodo retrieve che permette di ricercare tutti i dati di tutte le aziende.
     * @return List<AziendaBean>
     * @throws SQLException
     */
    List<AziendaBean> retrieveAll() throws SQLException;

    /**
     * Metodo retrieve che permette di ricercare tutte le aziende a partire da un ID.
     * @param email
     * @return List<AziendaBean>
     * @throws SQLException
     */
    List<AziendaBean> retrieveForKey(String email) throws SQLException;

    /**
     * Metodo retrieve che permette di ricercare tutte le aziende a partire da un ID.
     * @param codice_associazione
     * @return List<AziendaBean>
     * @throws SQLException
     */
    AziendaBean retrieveByCode(String codice_associazione) throws SQLException;


    /**
     * Metodo update che permette di modificare dati già presenti nel DB.
     * @param utente
     * @throws SQLException
     */
    void update(AziendaBean utente) throws SQLException;
    /**
     * Metodo update che permette di modificare dati già presenti nel DB anche l email.
     * @param utente
     * @param VecchiaEmail
     * @throws SQLException
     */
    void update(AziendaBean utente, String VecchiaEmail) throws SQLException;

    /**
     * Metodo delete che permette di eliminare un'azienda dal sistema.
     * @param email
     * @throws SQLException
     */
    void delete(String email) throws SQLException;

}


