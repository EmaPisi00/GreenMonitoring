package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementazione dell'interfaccia RegistrazioneDAO.
 */
public class AziendaDAOImpl implements AziendaDAO {

    /**
     * Metodo create che implementa la creazione di una nuova azienda.
     * @param aziendaBean
     * @throws SQLException
     */
    @Override
    public void create(AziendaBean aziendaBean) throws SQLException {

    }

    /**
     * Metodo retrieve che implementa la ricerca di un'azienda attraverso il passaggio di un ID.
     *
     * @param idAzienda
     * @return
     * @throws SQLException
     */

    @Override
    public List<AziendaBean> retrieve(String idAzienda) throws SQLException {
        return null;
    }

    /**
     * Metodo update che implementa un aggiornamento al DB attraverso il passaggio di un ID.
     * @param idAzienda
     * @throws SQLException
     */
    @Override
    public void update(String idAzienda) throws SQLException {

    }

    /**
     * Metodo delete che implementa una cancellazione dal sistema attraverso il passaggio di un ID.
     * @param idAzienda
     * @throws SQLException
     */
    @Override
    public void delete(String idAzienda) throws SQLException {

    }
}
