package it.unisa.greenmonitoring.dataccess.dao;


import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;

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
    private static final String TABLE_NAME = "Dipendente";

    /**
     * Dichiaro la variabile statica che mi permette di richiamare la classe per la connessione al db.
     */
    private static Connection connection;

    /**
     * Costruttore di DipendenteDAOImpl.
     * @throws SQLException
     */
    public DipendenteDAOImpl() {
    }


    /**
     * Metodo create che implementa la creazione di un nuovo dipendente.
     * @param dipendenteBean
     * @throws SQLException
     */
    @Override
    public void create(DipendenteBean dipendenteBean) throws SQLException {
        PreparedStatement preparedStatement = null;

        String updateSQL = "Insert Into " + TABLE_NAME + "(email, password, telefono, citta, indirizzo, provincia, azienda, nome, cognome)" + "Values (?,?,?,?,?,?,?,?,?)";

        try {

            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);
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

    /**
     * Metodo che implementa la ricerca di tutti i dipendenti.
     * @return dipendente
     * @throws SQLException
     */
    @Override
    public List<DipendenteBean> retrieveAll() throws SQLException {
        PreparedStatement preparedStatement = null;

        List<DipendenteBean> dipendente = new LinkedList<>();

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
     * Metodo retrieve che restituisce di dati di un'azienda ad un bean di tipo UtenteBean.
     * @throws SQLException
     * @param beanInput
     * @return restituisce il bean diocane
     */
    public static UtenteBean doRetrieve(UtenteBean beanInput) {

        connection = null;
        PreparedStatement preparedStatement = null;

        String email = beanInput.getEmail();
        String password = beanInput.getPassword();

        String searchQuery =
                "select * from " + TABLE_NAME + " where email = ? and password = ?";



        UtenteBean bean = new DipendenteBean();

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(searchQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);



            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()) {
                bean.setEmail(rs.getString("email"));
                bean.setPassword(rs.getString("password"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setCitta(rs.getString("citta"));
                bean.setProvincia(rs.getString("provincia"));
                bean.setIndirizzo(rs.getString("indirizzo"));
                ((DipendenteBean) bean).setAzienda(rs.getString("azienda"));
                ((DipendenteBean) bean).setNome(rs.getString("nome"));
                ((DipendenteBean) bean).setCognome(rs.getString("cognome"));
            }
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }

        return bean;
    }


    /**
     * Metodo retrieve che implesmenta la ricerca di un dipendente attraverso il passaggio di un ID.
     * @return
     * @throws SQLException
     */
    @Override
    public List<DipendenteBean> retrieveAllByCode(String email) throws SQLException {

        PreparedStatement preparedStatement = null;

        List<DipendenteBean> dipendente = new LinkedList<>();

        String retrieveSQL = "SELECT * FROM " + TABLE_NAME + " WHERE azienda =?";

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
     * Metodo update aggiorna anche l'email.
     * @param utente
     * @param emailVecchia
     * @throws SQLException
     */
    @Override
    public void update(DipendenteBean utente, String emailVecchia) throws SQLException {
        PreparedStatement preparedStatement = null;
        String retrieveSQL = "UPDATE " + TABLE_NAME + " SET email= ?, password= ?, telefono= ?, citta= ?,"
                + "   indirizzo= ?, provincia= ?, azienda= ?, nome= ?, cognome= ?" + "WHERE email = ?";

        try {
            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(retrieveSQL);
            preparedStatement.setString(1, utente.getEmail());
            preparedStatement.setString(2, utente.getPassword());
            preparedStatement.setString(3, utente.getTelefono());
            preparedStatement.setString(4, utente.getCitta());
            preparedStatement.setString(5, utente.getIndirizzo());
            preparedStatement.setString(6, utente.getProvincia());
            preparedStatement.setString(7, utente.getAzienda());
            preparedStatement.setString(8, utente.getNome());
            preparedStatement.setString(9, utente.getCognome());
            preparedStatement.setString(10, emailVecchia);

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
     * Metodo doUpdate che aggiorna i dati di un dipendente.
     * @param dipendenteBean
     */
    public void doUpdate(DipendenteBean dipendenteBean) throws SQLException {
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE " + TABLE_NAME + " SET password = ?, telefono = ?, citta = ?, indirizzo = ?, provincia = ?, azienda = ?, nome = ?, cognome = ? WHERE email = ?";

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(1, dipendenteBean.getPassword());
            preparedStatement.setString(2, dipendenteBean.getTelefono());
            preparedStatement.setString(3, dipendenteBean.getCitta());
            preparedStatement.setString(4, dipendenteBean.getIndirizzo());
            preparedStatement.setString(5, dipendenteBean.getProvincia());
            preparedStatement.setString(6, dipendenteBean.getAzienda());
            preparedStatement.setString(7, dipendenteBean.getNome());
            preparedStatement.setString(8, dipendenteBean.getCognome());
            preparedStatement.setString(9, dipendenteBean.getEmail());

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
     * @param email
     * @throws SQLException
     */
    @Override
    public void updateAziendaToNull(String email) throws SQLException {

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
