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
    public ColtivazioneBean createColtivazione(ColtivazioneBean c) throws SQLException {
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT Coltivazione" + "(pianta,terreno,stato_archiviazione,data_inizio,data_fine)" + " VALUES (?,?,?,?,?)";
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, c.getPianta());
            preparedStatement.setInt(2, c.getTerreno());
            preparedStatement.setByte(3, c.getStato_archiviazione());
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            preparedStatement.setDate(4, date);
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
        return c;
    }

    @Override
    public ArrayList<ColtivazioneBean> retrieveColtivazione(String id_azienda) throws SQLException {
        String selectSQL = "SELECT * FROM Coltivazione JOIN Terreno ON Coltivazione.terreno = Terreno.id WHERE Terreno.azienda = ?";
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
                c.setStato_archiviazione(rs.getByte("stato_archiviazione"));
               /* c.setInListaSensori(rs.getInt("sensore_id"));
                c.setInListaMisurazioni(rs.getInt("valore")); */
                connection.commit();
                list.add(c);
            }

        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }

    @Override
    public void updateColtivazione(String id_coltivazione) throws SQLException {
    }

    @Override
    public void deleteColtivazione(String id_coltivazione) throws SQLException {
    }
}
