package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementazione dell'interfaccia RegistrazioneDAO.
 */
public class DipendenteDAOImpl implements DipendenteDAO {

    /**
     * Metodo create che implementa la creazione di una nuova azienda.
     * @param dipendenteBean
     * @throws SQLException
     */
    @Override
    public void create(DipendenteBean dipendenteBean) throws SQLException {

    }

    /**
     * Metodo retrieve che implementa la ricerca di un dipendente attraverso il passaggio di un ID.
     *
     * @param idDipendente
     * @return
     * @throws SQLException
     */

    @Override
    public List<DipendenteBean> retrieve(String idDipendente) throws SQLException {
        return null;
    }

    /**
     * Metodo update che implementa un aggiornamento al DB attraverso il passaggio di un ID.
     * @param idDipendente
     * @throws SQLException
     */
    @Override
    public void update(String idDipendente) throws SQLException {

    }

    /**
     * Metodo delete che implementa una cancellazione dal sistema attraverso il passaggio di un ID.
     * @param idDipentente
     * @throws SQLException
     */
    @Override
    public void delete(String idDipentente) throws SQLException {

    }
}
