package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementazione dell'interfaccia RegistrazioneDAO.
 */
public class DipendenteDAOImpl implements DipendenteDAO {

    /**
     * Dichiaro la Variabile final "dipendente" che mi identifica la tabella nel db.
     */
    private static final String TABLE_NAME = "dipendente";

    /**
     * Dichiaro la variabile statica che mi permette di richiamare la classe per la connessione al db.
     */
    private static Connection connection;

    /**
     * Costruttore di DipendenteDAOImpl.
     * @throws SQLException
     */
    public DipendenteDAOImpl() throws SQLException {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("\nErrore nessuna connessione: +" + e);
        } finally {
            connection.close();
        }
    }


    /**
     * Metodo create che implementa la creazione di un nuovo dipendente.
     * @param dipendenteBean
     * @throws SQLException
     */
    @Override
    public void create(DipendenteBean dipendenteBean) throws SQLException {
        PreparedStatement preparedStatement = null;

        String insertSQL = "Insert Into " + TABLE_NAME + "(email, password, telefono, citta, indirizzo, provincia, azienda, nome, cognome)" + "Values (?,?,?,?,?,?,?,?,?)";

        try {

            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, dipendenteBean.getEmail());
            preparedStatement.setString(2, dipendenteBean.getPassword());
            preparedStatement.setString(3, dipendenteBean.getTelefono());
            preparedStatement.setString(4, dipendenteBean.getCitta());
            preparedStatement.setString(5, dipendenteBean.getIndirizzo());
            preparedStatement.setString(6, dipendenteBean.getProvincia());
            preparedStatement.setString(7, dipendenteBean.getAzienda());
            preparedStatement.setString(8, dipendenteBean.getNome());
            preparedStatement.setString(9, dipendenteBean.getCognome());

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

    @Override
    public List<DipendenteBean> retrieve() throws SQLException {
        return null;
    }


    /**
     * Metodo retrieve che implementa la ricerca di un dipendente attraverso il passaggio di un ID.
     * @return
     * @throws SQLException
     */
    @Override
    public List<DipendenteBean> retrieveAll() throws SQLException {

        PreparedStatement preparedStatement = null;

        List<DipendenteBean> dipendente = new LinkedList<DipendenteBean>();

        String retrieveSQL = "SELECT * FROM " + TABLE_NAME;

        try {

            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(retrieveSQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                DipendenteBean bean = new DipendenteBean();

                bean.setEmail(resultSet.getString("email"));
                bean.setPassword(resultSet.getString("password"));
                bean.setTelefono(resultSet.getString("telefono"));
                bean.setCitta(resultSet.getString("citta"));
                bean.setProvincia(resultSet.getString("provincia"));
                bean.setIndirizzo(resultSet.getString("indirizzo"));
                bean.setAzienda(resultSet.getString("azienda"));
                bean.setNome(resultSet.getString("nome"));
                bean.setCognome(resultSet.getString("cognome"));

                dipendente.add(bean);
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
        return  dipendente;
    }

    /**
     * Implementazione metodo che permette la ricerca di un dipendente in base alla sua email.
     * @param email
     * @return List <DipendenteBean>
     * @throws SQLException
     */
    @Override
    public List<DipendenteBean> retrieveForKey(String email) throws SQLException {

        List<DipendenteBean> dipendente = new LinkedList<DipendenteBean>();
        PreparedStatement preparedStatement = null;

        String retrieveSQL = "SELECT * FROM " + TABLE_NAME + " WHERE email = ?";

        try {
            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(retrieveSQL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                DipendenteBean bean = new DipendenteBean();

                bean.setEmail(resultSet.getString("email"));
                bean.setPassword(resultSet.getString("password"));
                bean.setTelefono(resultSet.getString("telefono"));
                bean.setCitta(resultSet.getString("citta"));
                bean.setProvincia(resultSet.getString("provincia"));
                bean.setIndirizzo(resultSet.getString("indirizzo"));
                bean.setAzienda(resultSet.getString("azienda"));
                bean.setNome(resultSet.getString("nome"));
                bean.setCognome(resultSet.getString("cognome"));

                dipendente.add(bean);
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
        return  dipendente;


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
