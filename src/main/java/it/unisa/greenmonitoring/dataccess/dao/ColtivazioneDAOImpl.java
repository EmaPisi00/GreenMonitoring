package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ColtivazioneDAOImpl implements ColtivazioneDAO {

    /**
     * Start Connection.
     */
    private Connection connection;

    /**
     * Costruttore di ColtivazioneDAOImpl.
     */
    public void ColtivazioneDAOImpl() {

    }

    @Override
    public void createColtivazione(ColtivazioneBean c) throws SQLException {
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO Coltivazione (pianta,terreno,stato_archiviazione,data_inizio,data_fine) VALUES (?,?,?,?,?)";
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, c.getPianta());
            preparedStatement.setInt(2, c.getTerreno());
            preparedStatement.setByte(3, c.getStato_archiviazione());
            preparedStatement.setDate(4, c.getData_inizio());
            preparedStatement.setDate(5, c.getData_fine());
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
    public ArrayList<ColtivazioneBean> retrieveColtivazione(String id_azienda) {
        String selectSQL = "SELECT Coltivazione.id , pianta, terreno, stato_archiviazione, data_inizio, data_fine, azienda FROM Coltivazione JOIN Terreno ON Coltivazione.terreno = Terreno.id WHERE Terreno.azienda = ?";
        ArrayList<ColtivazioneBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, id_azienda);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ColtivazioneBean c = new ColtivazioneBean();
                c.setId(rs.getInt("id"));
                c.setData_fine(rs.getDate("data_fine"));
                c.setData_inizio(rs.getDate("data_fine"));
                c.setPianta(rs.getInt("pianta"));
                c.setTerreno(rs.getInt("terreno"));
                c.setAzienda(rs.getString("azienda"));
                c.setStato_archiviazione(rs.getByte("stato_archiviazione"));
                connection.commit();
                list.add(c);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    @Override
    public ColtivazioneBean retrieveByKey(int id_coltivazione) throws SQLException {
        String selectSQL = "SELECT * FROM coltivazione WHERE id = ?";
        ColtivazioneBean s = new ColtivazioneBean();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id_coltivazione);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                s.setId(rs.getInt("id"));
                s.setPianta(rs.getInt("pianta"));
                s.setTerreno(rs.getInt("terreno"));
                s.setStato_archiviazione(rs.getByte("stato_archiviazione"));
                s.setData_inizio(rs.getDate("data_inizio"));
                s.getData_fine();

                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return s;
    }

    @Override
    public void updateColtivazione(String id_coltivazione) throws SQLException {
    }

    @Override
    public void deleteColtivazione(String id_coltivazione) throws SQLException {
    }
}
