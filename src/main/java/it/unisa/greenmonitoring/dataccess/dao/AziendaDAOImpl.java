package it.unisa.greenmonitoring.dataccess.dao;
import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
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
public class AziendaDAOImpl implements AziendaDAO {

    /**
     * Dichiaro la Variabile final "azienda" che mi identifica la tabella nel db.
     */
    private static final String TABLE_NAME = "Azienda";

    /**
     * Dichiaro la variabile statica che mi permette di richiamare la classe per la connessione al db.
     */
    private static Connection connection;

    /**
     * Costruttore di AziendaDAOImpl.
     * @throws SQLException
     */
    public AziendaDAOImpl() {
    }


    /**
     * Metodo create che implementa la creazione di una nuova azienda.
     * @param aziendaBean
     * @throws SQLException
     */
    @Override
    public void create(AziendaBean aziendaBean) throws SQLException {

        PreparedStatement preparedStatement = null;

        String insertSQL = "Insert Into " + TABLE_NAME + "(email, password, telefono, citta, indirizzo,provincia, nome_azienda,codice_associazione,partita_iva)" + "Values (?,?,?,?,?,?,?,?,?)";

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
            preparedStatement.setString(8, aziendaBean.getCodice_associazione());
            preparedStatement.setString(9, aziendaBean.getPartita_iva());

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
     * Metodo retrieve che restituisce di dati di un'azienda ad un bean di tipo UtenteBean.
     * @throws SQLException
     * @param beanInput
     * @return restituisce il bean
     */
    public static UtenteBean doRetrieve(UtenteBean beanInput) {

        connection = null;
        PreparedStatement preparedStatement = null;

        String email = beanInput.getEmail();
        String password = beanInput.getPassword();

        String searchQuery =
                "select * from " + TABLE_NAME + " where email = ? and password = ?";



        UtenteBean bean = new AziendaBean();

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
                ((AziendaBean) bean).setNome_azienda(rs.getString("nome_azienda"));
                ((AziendaBean) bean).setPartita_iva(rs.getString("partita_iva"));
            }
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }

        return bean;
    }


    /**
     * Implementazione metodo che permette la ricerca di un'azienda in base alla sua email.
     * @param email
     * @return List<AziendaBean>
     * @throws SQLException
     */
    @Override
    public AziendaBean retrieveForKey(String email) throws SQLException {

        AziendaBean bean = new AziendaBean();
        PreparedStatement preparedStatement = null;

        String retrieveSQL = "SELECT * FROM " + TABLE_NAME + " WHERE email = ?";
        //NON FUNZIONA PERCHé CI DEVI METTERE UNO SPAZIO PRIMA DEL WHERE

        try {
            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(retrieveSQL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                bean.setEmail(resultSet.getString("email"));
                bean.setPassword(resultSet.getString("password"));
                bean.setTelefono(resultSet.getString("telefono"));
                bean.setCitta(resultSet.getString("citta"));
                bean.setProvincia(resultSet.getString("provincia"));
                bean.setIndirizzo(resultSet.getString("indirizzo"));
                bean.setNome_azienda(resultSet.getString("nome_azienda"));
                bean.setPartita_iva(resultSet.getString("partita_iva"));

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
        return  bean;


    }

    /**
     * Implementazione metodo che permette la ricerca di un'azienda in base al suo codice azienda.
     * @param codice_associazione
     * @return List<AziendaBean>
     * @throws SQLException
     */

    public AziendaBean retrieveByCode(String codice_associazione) throws SQLException {

        AziendaBean azienda = new AziendaBean();
        PreparedStatement preparedStatement = null;

        String retrieveSQL = "SELECT * FROM " + TABLE_NAME + " WHERE codice_associazione = ?";
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(retrieveSQL);
            preparedStatement.setString(1, codice_associazione);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                azienda.setEmail(resultSet.getString("email"));
                azienda.setPassword(resultSet.getString("password"));
                azienda.setTelefono(resultSet.getString("telefono"));
                azienda.setCitta(resultSet.getString("citta"));
                azienda.setProvincia(resultSet.getString("provincia"));
                azienda.setIndirizzo(resultSet.getString("indirizzo"));
                azienda.setNome_azienda(resultSet.getString("nome_azienda"));
                azienda.setCodice_associazione(resultSet.getString("codice_associazione"));
                azienda.setPartita_iva(resultSet.getString("partita_iva"));
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
     * Metodo update chee permette di modificare dati già presenti nel DB.
     *
     * @param utente
     * @throws SQLException
     */
    @Override
    public void update(AziendaBean utente) throws SQLException {

    }


    /**
     * Metodo update che aggiorna anche l'id dell'azienda.
     * @param utente
     * @param emailVecchia
     * @throws SQLException
     */

    public void update(AziendaBean utente, String emailVecchia) throws SQLException {
        PreparedStatement preparedStatement = null;
        String retrieveSQL = "UPDATE " + TABLE_NAME + " SET email= ?, password= ?, telefono= ?, citta= ?,"
                + "   indirizzo= ?, provincia= ?, nome_azienda= ?, partita_iva= ?" + "WHERE email = ?";

        try {
            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(retrieveSQL);
            preparedStatement.setString(1, utente.getEmail());
            preparedStatement.setString(2, utente.getPassword());
            preparedStatement.setString(3, utente.getTelefono());
            preparedStatement.setString(4, utente.getCitta());
            preparedStatement.setString(5, utente.getIndirizzo());
            preparedStatement.setString(6, utente.getProvincia());
            preparedStatement.setString(7, utente.getNome_azienda());
            preparedStatement.setString(8, utente.getPartita_iva());
            preparedStatement.setString(9, emailVecchia);

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

    /**
     * Metodo dis-associazione.
     * @param emailAzienda
     * @param emailDipendente
     *
     */
    @Override
    public void removeAssociation(String emailAzienda, String emailDipendente) throws SQLException {
        PreparedStatement preparedStatement = null;

        final String TABLE_NAME2 = "dipendente";
        String removeSQL = "UPDATE " + TABLE_NAME2 + " SET azienda = null WHERE email = ? and azienda = ?";
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(removeSQL);

            preparedStatement.setString(1, emailDipendente);
            preparedStatement.setString(2, emailAzienda);
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
