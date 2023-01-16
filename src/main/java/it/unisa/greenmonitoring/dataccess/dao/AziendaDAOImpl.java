package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementazione dell'interfaccia RegistrazioneDAO.
 */
public class AziendaDAOImpl implements AziendaDAO {

    /**
     * Dichiaro la Variabile final "azienda" che mi identifica la tabella nel db.
     */
    private static final String TABLE_NAME = "azienda";

    /**
     * Dichiaro la variabile statica che mi permette di richiamare la classe per la connessione al db.
     */
    private static Connection connection;

    /**
     * Costruttore di AziendaDAOImpl.
     * @throws SQLException
     */
    public AziendaDAOImpl() throws SQLException {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("\nErrore nessuna connessione: +" + e);
        } finally {
            connection.close();
        }
    }


    /**
     * Metodo create che implementa la creazione di una nuova azienda.
     * @param aziendaBean
     * @throws SQLException
     */
    @Override
    public void create(AziendaBean aziendaBean) throws SQLException {

        PreparedStatement preparedStatement = null;

        String insertSQL = "Insert Into" + TABLE_NAME + "('email','password','telefono','citta','indirizzo','provincia', 'nome_azienda','partita_iva')" + "Values (?,?,?,?,?,?,?,?)";

        try {

            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, aziendaBean.getEmail());
            preparedStatement.setString(2, aziendaBean.getPassword());
            preparedStatement.setString(3, aziendaBean.getTelefono());
            preparedStatement.setString(4, aziendaBean.getCitta());
            preparedStatement.setString(5, aziendaBean.getIndirizzo());
            preparedStatement.setString(6, aziendaBean.getProvincia());
            preparedStatement.setString(7, aziendaBean.getNome_azienda());
            preparedStatement.setString(8, aziendaBean.getPartita_iva());

            preparedStatement.executeUpdate();
            connection.commit();

        } finally {
                try {
                     if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                } finally {
                     if (connection != null) {
                        connection.close();
                    }
                }
        }

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
