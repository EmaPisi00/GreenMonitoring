package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class TerrenoDAOImpl implements TerrenoDAO {

    /**
     * Start Connection.
     */
    private Connection connection;

    /**
     * Dichiaro la Variabile final "azienda" che mi identifica la tabella nel db.
     */
    private static final String TABLE_NAME = "Terreno";

    /**
     * Classe per l'implementazione di TerrenoDAOImpl.
     */
    public TerrenoDAOImpl() throws SQLException {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException s) {
            System.out.println("errore nel creare la connessione: " + s);
        } finally {
            connection.close();
        }

    }
    @Override
    public void createTerreno(TerrenoBean t) throws SQLException {
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT " + TABLE_NAME + "(azienda,immagine,latitudine,longitudine,superfice)" + " VALUES (?,?,?,?,?)";
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, t.getAzienda());
            preparedStatement.setString(2, t.getImmagine());
            preparedStatement.setFloat(3, t.getLatitudine());
            preparedStatement.setFloat(4, t.getLongitudine());
            preparedStatement.setString(5, t.getSuperficie());
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
    public List<TerrenoBean> retrieveTerreno() throws SQLException {

        String selectSQL = "SELECT * FROM Terreno";
        List<TerrenoBean> list = new Vector<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                TerrenoBean t = new TerrenoBean(null, null, null, null, null);
                t.setId(rs.getString("id"));
                t.setImmagine(rs.getString("immagine"));
                t.setSuperficie(rs.getString("superfice"));
                t.setLatitudine(rs.getFloat("latitudine"));
                t.setLongitudine(rs.getFloat("longitudine"));
                t.setAzienda(rs.getString("azienda"));
                list.add(t);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }

    @Override
    public synchronized void updateTerreno(String id_terreno) throws SQLException {

    }

    @Override
    public synchronized void deleteTerreno(String id_terreno) throws SQLException {

        String deleteSQL = "DELETE FROM Terreno WHERE Terreno.id = ?";
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, id_terreno);
            preparedStatement.execute();

        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
