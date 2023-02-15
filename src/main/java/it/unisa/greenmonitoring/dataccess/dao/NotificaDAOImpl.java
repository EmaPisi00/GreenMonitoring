package it.unisa.greenmonitoring.dataccess.dao;


import it.unisa.greenmonitoring.dataccess.beans.NotificaBean;


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
    public void aggiungiNotifica(NotificaBean notifica) {
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT " + TABLE_NAME + "(coltivazione,azienda,tipo,data_ora,contenuto,visualizzazioneNotifica)" + " VALUES (?,?,?,?,?,?)";
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, notifica.getColtivazioneID());
            preparedStatement.setString(2, notifica.getAziendaID());
            preparedStatement.setString(3, notifica.getTipo());
            preparedStatement.setTimestamp(4, notifica.getData());
            preparedStatement.setString(5, notifica.getContenuto());
            preparedStatement.setBoolean(6, notifica.getVisualizzazioneNotifica());
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
    @Override
    public List<NotificaBean> retriveNotifichePerAziendaDaVisualizzare(String azienda) {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE azienda = ? and visualizzazioneNotifica=0 order by data_ora DESC";
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
    public  void updateLetturaNotifica(int id) {
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
}

