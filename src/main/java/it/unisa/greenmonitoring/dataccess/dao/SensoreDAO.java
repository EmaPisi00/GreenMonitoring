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
     * Metodo retrieve che permette di ricercare tutti i dati di tutti i sensori.
     * @return List<SensoreBean>
     * @throws SQLException
     */
    List<SensoreBean> retrieveAll() throws SQLException;

    /**
     * Metodo retrieve che permette di ricercare un sensore in base all'id.
     * @param id_sensore
     * @return SensoreBean
     * @throws SQLException
     */
    SensoreBean retrieveByKey(int id_sensore) throws SQLException;

    /**
     * Metodo retrieve che permette di ricercare tutti i sensori di un'azienda.
     * @param azienda
     * @return List<SensoreBean>
     * @throws SQLException
     */
    List<SensoreBean> retrieveAllByAzienda(String azienda) throws SQLException;

    /**
     * Metodo update che permette di modificare dati già presenti nel DB.
     * @param id_sensore
     * @param s
     * @throws SQLException
     */

    void update(int id_sensore, SensoreBean s) throws SQLException;

    /**
     * Metodo delete che permette di eliminare un sensore di una azienda dal db.
     * @param sensore
     * @throws SQLException
     */
    void delete(SensoreBean sensore) throws SQLException;

    /**
     *
     * @param idM
     * @return SensoreBean
     * @throws SQLException
     */
    SensoreBean retrieveByidM(String idM) throws SQLException;

    /**
     *
     * @return List
     */
    List<SensoreBean> SensoriColtivazioneAvviata();
}
