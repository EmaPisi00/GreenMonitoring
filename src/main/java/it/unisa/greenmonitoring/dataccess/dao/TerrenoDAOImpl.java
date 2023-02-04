package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public TerrenoDAOImpl() {
    }
    @Override
    public void createTerreno(TerrenoBean t) {
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
    public ArrayList<TerrenoBean> retrieveTerreno() {

        String selectSQL = "SELECT * FROM Terreno";
        ArrayList<TerrenoBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                TerrenoBean t = new TerrenoBean();
                t.setId(rs.getInt("id"));
                t.setImmagine(rs.getString("immagine"));
                t.setSuperficie(rs.getString("superfice"));
                t.setLatitudine(rs.getFloat("latitudine"));
                t.setLongitudine(rs.getFloat("longitudine"));
                t.setAzienda(rs.getString("azienda"));
                connection.commit();
                list.add(t);
            }
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
    public TerrenoBean retrieveByKey(int id_terreno) throws SQLException {
        String selectSQL = "SELECT * FROM Terreno WHERE id = ?";
        TerrenoBean t = new TerrenoBean();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id_terreno);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                t.setId(rs.getInt("id"));
                t.setLongitudine(rs.getFloat("longitudine"));
                t.setLatitudine(rs.getFloat("latitudine"));
                t.setAzienda(rs.getString("azienda"));
                t.setSuperficie(rs.getString("superfice"));
                t.setImmagine(rs.getString("immagine"));

                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return t;
    }

    @Override
    public synchronized void updateTerreno(int id_terreno) throws SQLException {

    }

    @Override
    public synchronized void deleteTerreno(int id_terreno) throws SQLException {

        String deleteSQL = "DELETE FROM Terreno WHERE Terreno.id = ?";
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id_terreno);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
