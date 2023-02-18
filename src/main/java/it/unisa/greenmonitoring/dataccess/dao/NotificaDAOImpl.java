package it.unisa.greenmonitoring.dataccess.dao;


import it.unisa.greenmonitoring.dataccess.beans.NotificaBean;


import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificaDAOImpl implements NotificaDAO {

    /**
     * Start Connection.
     */
    private Connection connection;

    /**
     * Costruttore di MisurazioneSensoreDAOImpl.
     */
    public NotificaDAOImpl() {
    }

    /**
     * Dichiaro la Variabile final "azienda" che mi identifica la tabella nel db.
     */
    private static final String TABLE_NAME = "Notifica";

    /**
     * crep una notifica da inserire nl de.
     *
     * @param notifica
     */
    @Override
    public void aggiungiNotifica(NotificaBean notifica, List<String> dipendenti) {
        System.out.println("*****" + notifica + dipendenti.size());
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT " + TABLE_NAME + "(coltivazione,azienda,tipo,data_ora,contenuto,visualizzazioneNotifica)" + " VALUES (?,?,?,?,?,?)";
        String insertSQL2 = "INSERT NotificaDipendenti" + "(notifica,dipendente,visualizzazioneNotifica)" + " VALUES (?,?,?)";
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, notifica.getColtivazioneID());
            preparedStatement.setString(2, notifica.getAziendaID());
            preparedStatement.setString(3, notifica.getTipo());
            preparedStatement.setTimestamp(4, notifica.getData());
            preparedStatement.setString(5, notifica.getContenuto());
            preparedStatement.setBoolean(6, notifica.getVisualizzazioneNotifica());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                notifica.setId(id);
                System.out.println("Id della notifica appena generato: " + id);
            }

            ///prendi Lid appena inserito da sensore
            preparedStatement = connection.prepareStatement(insertSQL2);
            for (String idD : dipendenti) {
                System.out.println("notificaID****** " + notifica.getId());
                preparedStatement.setInt(1, notifica.getId());
                preparedStatement.setString(2, idD);
                preparedStatement.setBoolean(3, false);
                preparedStatement.executeUpdate();
            }

            connection.commit();


        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    @Override
    public List<NotificaBean> retriveNotifichePerAzienda(String azienda) {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE azienda = ? order by data_ora DESC";
        List<NotificaBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, azienda);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                NotificaBean s = new NotificaBean();
                s.setId(rs.getInt("id"));
                s.setColtivazioneID(rs.getInt("coltivazione"));
                s.setAziendaID(rs.getString("azienda"));
                s.setTipo(rs.getString("tipo"));
                s.setData(rs.getTimestamp("data_ora"));
                s.setContenuto(rs.getString("contenuto"));
                s.setVisualizzazioneNotifica(rs.getBoolean("visualizzazioneNotifica"));

                list.add(s);
            }
            connection.commit();
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }

    /**
     *
     * @param id
     */
    @Override
    public  void updateLetturaNotificaAzienda(int id) {
        String updateSQL = "UPDATE " + TABLE_NAME + " SET visualizzazioneNotifica = ? WHERE id = ?";
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * @param idNotifica
     * @param idDipendente
     */
    @Override
    public void updateLetturaNotificaDipendente(int idNotifica, String idDipendente) {
        System.out.println("sono in dao upda letturadip" + idNotifica + "  " + idDipendente);
        String updateSQL = "UPDATE NotificaDipendenti SET visualizzazioneNotifica = ? WHERE notifica = ? AND dipendente = ?";
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, idNotifica);
            preparedStatement.setString(3, idDipendente);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     *
     * @param emailDipendente
     * @return List
     */
    public List<NotificaBean> retriveNotifichePerDipendente(String emailDipendente) {
        String selectSQL = "SELECT n.*, nd.visualizzazioneNotifica AS visualizzazioneDipendente FROM Notifica n"
                + " LEFT JOIN NotificaDipendenti nd ON n.id = nd.notifica where nd.dipendente=? and n.id=nd.notifica;";
        List<NotificaBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, emailDipendente);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                NotificaBean s = new NotificaBean();
                s.setId(rs.getInt("id"));
                s.setColtivazioneID(rs.getInt("coltivazione"));
                s.setAziendaID(rs.getString("azienda"));
                s.setTipo(rs.getString("tipo"));
                s.setData(rs.getTimestamp("data_ora"));
                s.setContenuto(rs.getString("contenuto"));
                s.setVisualizzazioneNotifica(rs.getBoolean("visualizzazioneDipendente"));

                list.add(s);
            }
            connection.commit();
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }
}

