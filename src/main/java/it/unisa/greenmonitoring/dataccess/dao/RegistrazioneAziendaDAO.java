package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.RegistrazioneAziendaBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe che implementa il DAO della registrazione di un'azienda.
 */
public interface RegistrazioneAziendaDAO {

    /**
     * Metodo create che permette di salvare i dati nel DB.
     * @param registrazioneAziendaBean
     * @throws SQLException
     */
    void create(RegistrazioneAziendaBean registrazioneAziendaBean) throws SQLException;

    /**
     * Metodo retrieve che permette di ricercare tutte le aziende a partire da un ID.
     * @param idRegistrazioneAzienda
     * @return List<RegistrazioneAziendaBean>
     * @throws SQLException
     */
    List<RegistrazioneAziendaBean> retrieve(String idRegistrazioneAzienda) throws SQLException;

    /**
     * Metodo update che permette di modificare dati già presenti nel DB.
     * @param idRegistrazioneAzienda
     * @throws SQLException
     */
    void update(String idRegistrazioneAzienda) throws SQLException;

    /**
     * Metodo delete che permette di eliminare un'azienda dal sistema.
     * @param idRegistrazioneAzienda
     * @throws SQLException
     */
    void delete(String idRegistrazioneAzienda) throws SQLException;

}


