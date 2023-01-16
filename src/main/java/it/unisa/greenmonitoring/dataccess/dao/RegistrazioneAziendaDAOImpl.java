package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.RegistrazioneAziendaBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementazione dell'interfaccia RegistrazioneDAO.
 */
public class RegistrazioneAziendaDAOImpl implements RegistrazioneAziendaDAO {

    /**
     * Metodo create che implementa la creazione di una nuova azienda.
     * @param registrazioneAziendaBean
     * @throws SQLException
     */
    @Override
    public void create(RegistrazioneAziendaBean registrazioneAziendaBean) throws SQLException {

    }

    /**
     * Metodo retrieve che implementa la ricerca di un'azienda attraverso il passaggio di un ID.
     * @param idRegistrazioneAzienda
     * @return
     * @throws SQLException
     */

    @Override
    public List<RegistrazioneAziendaBean> retrieve(String idRegistrazioneAzienda) throws SQLException {
        return null;
    }

    /**
     * Metodo update che implementa un aggiornamento al DB attraverso il passaggio di un ID.
     * @param idRegistrazioneAzienda
     * @throws SQLException
     */
    @Override
    public void update(String idRegistrazioneAzienda) throws SQLException {

    }

    /**
     * Metodo delete che implementa una cancellazione dal sistema attraverso il passaggio di un ID.
     * @param idRegistrazioneAzienda
     * @throws SQLException
     */
    @Override
    public void delete(String idRegistrazioneAzienda) throws SQLException {

    }
}
