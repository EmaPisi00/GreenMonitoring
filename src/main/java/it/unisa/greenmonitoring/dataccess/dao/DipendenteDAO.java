package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe che implementa il DAO della registrazione di un dipendente.
 */
public interface DipendenteDAO {

    /**
     * Metodo create che permettee di salvare i dati nel DB.
     * @param registrazioneDipendenteBean
     * @throws SQLException
     */
    void create(DipendenteBean registrazioneDipendenteBean) throws SQLException;

    /**
     * Metodo retrieve che permette di ricercare un dipendente.
     * @param email
     * @return DipendenteBean
     * @throws SQLException
     */
     DipendenteBean doRetrieve(String email);

    /**
     * Metodo retrieve che permette di ricercare tutti i dati di tutti i dipendenti.
     * @return List<DipendenteBean>
     * @throws SQLException
     */
    List<DipendenteBean> retrieveAll() throws SQLException;

    /**
     * Metodo retrieve che permette di ottenere tutti i dipendenti di una singola azienda.
     * @param email
     * @return List<DipendenteBean>
     * @throws SQLException
     */
    List<DipendenteBean> retrieveAllByCode(String email) throws SQLException;

    /**
     * Metodo update che permette di modificare dati già presenti nel DB.
     * @param dipendente
     * @param emailVecchia
     * @throws SQLException
     */
    void update(DipendenteBean dipendente, String emailVecchia) throws SQLException;


    /**
     * Metodo update che permette di modificare dati già presenti nel DB (senza modificare l'email).
     * @param dipendenteBean
     * @throws SQLException
     */
    void doUpdate(DipendenteBean dipendenteBean) throws SQLException;

    /**
     * Metodo delete che permette di eliminare un dipendente dal sistema.
     * @param email
     * @throws SQLException
     */
    void delete(String email) throws SQLException;

}



