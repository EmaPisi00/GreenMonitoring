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
    public void ColtivazioneDAOImpl() throws SQLException {
        try {
            connection = ConnectionPool.getConnection();
        } catch (SQLException s) {
            System.out.println("errore nel creare la connessione: " + s);
        } finally {
            connection.close();
        }

    }

    @Override
    public ColtivazioneBean createColtivazione(ColtivazioneBean c) throws SQLException {
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT Coltivazione" + "(id,pianta,terreno,stato_archiviazione,data_inizio,data_fine)" + " VALUES (?,?,?,?,?,?)";
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, c.getId());
            preparedStatement.setInt(2, c.getPianta());
            preparedStatement.setInt(3, c.getTerreno());
            preparedStatement.setByte(4, c.getStato_archiviazione());
            preparedStatement.setDate(5, c.getData_inizio());
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
    public ArrayList<ColtivazioneBean> retrieveColtivazione() throws SQLException {
        String selectSQL = "SELECT * FROM Coltivazione";
        ArrayList<ColtivazioneBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ColtivazioneBean c = new ColtivazioneBean();
                c.setId(rs.getInt("id"));
                c.setData_fine(rs.getDate("data_fine"));
                c.setData_inizio(rs.getDate("data_fine"));
                c.setPianta(rs.getInt("pianta"));
                c.setTerreno(rs.getInt("terreno"));
                c.setStato_archiviazione(rs.getByte("stato_archiviazione"));
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
        //TO-DO
    }

    @Override
    public void deleteColtivazione(String id_coltivazione) throws SQLException {
        //TO-DO
    }
}
