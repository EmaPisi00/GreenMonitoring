package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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

        String insertSQL = "Insert Into " + TABLE_NAME + "(email, password, telefono, citta, indirizzo,provincia, nome_azienda,partita_iva)" + "Values (?,?,?,?,?,?,?,?)";

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
     * @return
     * @throws SQLException
     */
    @Override
    public List<AziendaBean> retrieveAll() throws SQLException {

        PreparedStatement preparedStatement = null;

        List<AziendaBean> azienda = new LinkedList<AziendaBean>();

        String retrieveSQL = "SELECT * FROM " + TABLE_NAME;

        try {

            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(retrieveSQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                AziendaBean bean = new AziendaBean();

                bean.setEmail(resultSet.getString("email"));
                bean.setPassword(resultSet.getString("password"));
                bean.setTelefono(resultSet.getString("telefono"));
                bean.setCitta(resultSet.getString("citta"));
                bean.setProvincia(resultSet.getString("provincia"));
                bean.setIndirizzo(resultSet.getString("indirizzo"));
                bean.setNome_azienda(resultSet.getString("nome_azienda"));
                bean.setPartita_iva(resultSet.getString("partita_iva"));

                azienda.add(bean);
            }

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
        return  azienda;
    }

    /**
     * Implementazione metodo che permette la ricerca di un'azienda in base alla sua email.
     * @param email
     * @return List<AziendaBean>
     * @throws SQLException
     */
    @Override
    public List<AziendaBean> retrieveForKey(String email) throws SQLException {

        List<AziendaBean> azienda = new LinkedList<AziendaBean>();
        PreparedStatement preparedStatement = null;

        String retrieveSQL = "SELECT * FROM " + TABLE_NAME + "WHERE email = ?";

        try {
            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(retrieveSQL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                AziendaBean bean = new AziendaBean();

                bean.setEmail(resultSet.getString("email"));
                bean.setPassword(resultSet.getString("password"));
                bean.setTelefono(resultSet.getString("telefono"));
                bean.setCitta(resultSet.getString("citta"));
                bean.setProvincia(resultSet.getString("provincia"));
                bean.setIndirizzo(resultSet.getString("indirizzo"));
                bean.setNome_azienda(resultSet.getString("nome_azienda"));
                bean.setPartita_iva(resultSet.getString("partita_iva"));

                azienda.add(bean);
            }

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
        return  azienda;


            }



    /**
     * Metodo update che implementa un aggiornamento al DB attraverso il passaggio di un ID.
     * @param email
     * @throws SQLException
     */
    @Override
    public void update(String email) throws SQLException {

    }

    /**
     * Metodo delete che implementa una cancellazione dal sistema attraverso il passaggio di un ID.
     * @param email
     * @throws SQLException
     */
    @Override
    public void delete(String email) throws SQLException {

        PreparedStatement preparedStatement = null;

        String deleteSQL =  "DELETE * FROM " + TABLE_NAME + "where email = ?";

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, email);

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
}
