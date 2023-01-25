package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;


import java.sql.*;
import java.util.ArrayList;

public class MisurazioneSensoreDAOImpl implements MisurazioneSensoreDAO {

    /**
     * Start Connection.
     */
    private Connection connection;

    /**
     * Costruttore di MisurazioneSensoreDAOImpl.
     * @throws SQLException
     */
    public MisurazioneSensoreDAOImpl() {
    }

    /**
     * Costruttore di MisurazioneSensoreDAOImpl.
     */

    @Override
    public MisurazioneSensoreBean createMisurazione(MisurazioneSensoreBean msb, ColtivazioneBean cb, SensoreBean sb) throws SQLException, Exception {
        PreparedStatement preparedStatement = null;
        String insertSQL = "insert into misurazione_sensore(coltivazione, valore, data, ora, tipo, sensore_id) values(?, ?, ?, ?, ?, ?);";
        try {

            if (msb.getValore() > 9 || msb.getValore() < 0) {
                throw new Exception("Sensor value out of bounds");
            } else if (!sb.getTipo().equals("pH") || !sb.getTipo().equals("temperatura") || !sb.getTipo().equals("umidità")) {
                throw new Exception("Sensor type does not exist");
            }

            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, cb.getId());
            preparedStatement.setInt(2, msb.getValore());
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            Time time = new Time(millis);
            preparedStatement.setDate(3, date);
            preparedStatement.setTime(4, time);
            preparedStatement.setString(5, sb.getTipo());
            preparedStatement.setInt(6, sb.getId());
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
        return msb;
    }

    @Override
    public ArrayList<MisurazioneSensoreBean> retreive(String id) throws SQLException {
        String selectSQL = "SELECT misurazione_sensore.id, misurazione_sensore.coltivazione, misurazione_sensore.valore, misurazione_sensore.data, misurazione_sensore.ora, sensore.tipo, misurazione_sensore.sensore_id FROM \n"
                + "misurazione_sensore join sensore on misurazione_sensore.sensore_id = sensore.id WHERE sensore.coltivazione = ?;";
        ArrayList<MisurazioneSensoreBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                MisurazioneSensoreBean msb = new MisurazioneSensoreBean();
                msb.setTipo(rs.getString("tipo"));
                msb.setId(rs.getInt("id"));
                msb.setValore(rs.getInt("valore"));
                msb.setData(rs.getDate("data"));
                msb.setOra(rs.getTime("ora"));
                connection.commit();
                list.add(msb);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }
}
