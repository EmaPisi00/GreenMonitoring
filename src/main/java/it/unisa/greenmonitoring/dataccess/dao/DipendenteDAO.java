package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe che implementa il DAO della registrazione di un dipendente.
 */
public interface DipendenteDAO {

    /**
     * Metodo create che permette di salvare i dati nel DB.
     * @param registrazioneDipendenteBean
     * @throws SQLException
     */
    void create(DipendenteBean registrazioneDipendenteBean) throws SQLException;

    /**
     * Metodo retrieve che permette di ricercare tutte le aziende a partire da un ID.
     * @param idRegistrazioneDipendente
     * @return List<RegistrazioneDipendenteBean>
     * @throws SQLException
     */
    List<DipendenteBean> retrieve(String idRegistrazioneDipendente) throws SQLException;

    /**
     * Metodo update che permette di modificare dati già presenti nel DB.
     * @param idRegistrazioneDipendente
     * @throws SQLException
     */
    void update(String idRegistrazioneDipendente) throws SQLException;

    /**
     * Metodo delete che permette di eliminare un'azienda dal sistema.
     * @param idRegistrazioneDipendente
     * @throws SQLException
     */
    void delete(String idRegistrazioneDipendente) throws SQLException;

}


