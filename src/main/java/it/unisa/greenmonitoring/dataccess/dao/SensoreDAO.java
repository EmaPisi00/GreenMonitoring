package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe che implementa il DAO dei sensori.
 */
public interface SensoreDAO {

    /**
     * Metodo create che permette di salvare i dati nel DB.
     * @param registrazioneSensoreBean
     * @throws SQLException
     */
    void create(SensoreBean registrazioneSensoreBean) throws SQLException;

    /**
     * Metodo retrieve che permette di ricercare tutti i dati di tutte le aziende.
     * @return List<SensoreBean>
     * @throws SQLException
     */
    List<SensoreBean> retrieveAll() throws SQLException;

    /**
     * Metodo update che permette di modificare dati già presenti nel DB.
     * @param id_sensore
     * @param s
     * @throws SQLException
     */

    void update(int id_sensore, SensoreBean s) throws SQLException;

    /**
     * Metodo delete che permette di eliminare un'azienda dal sistema.
     * @param id
     * @throws SQLException
     */
    void delete(int id) throws SQLException;

    /**
     * Metodo che permette la rimozione di una associazione.
     * @param emailAzienda
     * @param id
     * @throws SQLException
     */
    void removeAssociation(String emailAzienda, int id) throws SQLException;
}

