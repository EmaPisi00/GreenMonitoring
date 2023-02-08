package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public synchronized MisurazioneSensoreBean createMisurazione(MisurazioneSensoreBean msb, ColtivazioneBean cb, SensoreBean sb) throws SQLException, Exception {
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
    public synchronized ArrayList<MisurazioneSensoreBean> retreive(String id) throws SQLException {
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
                msb.setColtivazione(rs.getInt("coltivazione"));
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


    @Override
    public synchronized MisurazioneSensoreBean createMisurazioneManuel(MisurazioneSensoreBean msb, SensoreBean sensore) {
        PreparedStatement preparedStatement = null;
        String insertSQL = "insert into misurazione_sensore(coltivazione, valore, data, ora, tipo, sensore_id) values(?, ?, ?, ?, ?, ?);";
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, sensore.getColtivazione());
            preparedStatement.setInt(2, msb.getValore());
            preparedStatement.setDate(3, (Date) msb.getData());
            preparedStatement.setTime(4, msb.getOra());
            preparedStatement.setString(5, msb.getTipo());
            preparedStatement.setInt(6, sensore.getId());
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
        return msb;
    }

    @Override
    public synchronized List<MisurazioneSensoreBean> retreiveMisurazioneByColtivazione(Integer id_coltivazione, String tipo) throws SQLException {
        String selectSQL = "SELECT * FROM misurazione_sensore WHERE misurazione_sensore.coltivazione = ? and misurazione_sensore.tipo = ?";
        List<MisurazioneSensoreBean> misurazioneSensoreBeans = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id_coltivazione);
            preparedStatement.setString(2, tipo);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                MisurazioneSensoreBean misurazioneSensoreBean = new MisurazioneSensoreBean();
                misurazioneSensoreBean.setTipo(rs.getString("tipo"));
                misurazioneSensoreBean.setColtivazione(rs.getInt("coltivazione"));
                misurazioneSensoreBean.setId(rs.getInt("id"));
                misurazioneSensoreBean.setValore(rs.getInt("valore"));
                misurazioneSensoreBean.setData(rs.getDate("data"));
                misurazioneSensoreBean.setOra(rs.getTime("ora"));
                misurazioneSensoreBean.setSensore_id(rs.getInt("sensore_id"));
                connection.commit();
                misurazioneSensoreBeans.add(misurazioneSensoreBean);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            connection.close();
        }
        return misurazioneSensoreBeans;
    }

    @Override
    public synchronized List<MisurazioneSensoreBean> retreiveMisurazioneOggiColtivazione(Integer id_coltivazione, String tipo) throws SQLException {
        String selectSQL = "SELECT * FROM misurazione_sensore WHERE misurazione_sensore.coltivazione = ? and misurazione_sensore.tipo = ? and misurazione_sensore.data >= ?";
        List<MisurazioneSensoreBean> misurazioneSensoreBeans = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id_coltivazione);
            preparedStatement.setString(2, tipo);
            java.sql.Date oggi = new java.sql.Date(System.currentTimeMillis());
            preparedStatement.setDate(3, oggi);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                MisurazioneSensoreBean misurazioneSensoreBean = new MisurazioneSensoreBean();
                misurazioneSensoreBean.setTipo(rs.getString("tipo"));
                misurazioneSensoreBean.setColtivazione(rs.getInt("coltivazione"));
                misurazioneSensoreBean.setId(rs.getInt("id"));
                misurazioneSensoreBean.setValore(rs.getInt("valore"));
                misurazioneSensoreBean.setData(rs.getDate("data"));
                misurazioneSensoreBean.setOra(rs.getTime("ora"));
                misurazioneSensoreBean.setSensore_id(rs.getInt("sensore_id"));
                connection.commit();
                misurazioneSensoreBeans.add(misurazioneSensoreBean);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            connection.close();
        }
        return misurazioneSensoreBeans;
    }


    @Override
    public synchronized Double retrieveMostRecentMesurement(String tipo, Integer id_coltivazione) throws SQLException {
        String selectSQL = "SELECT AVG(valore) as v"
                + "FROM ("
                + "  SELECT valore"
                + "  FROM ("
                + "    SELECT id, valore, max(misurazione_sensore.data) data, max(misurazione_sensore.ora) ora, sensore_id"
                + "    FROM Misurazione_sensore"
                + "    WHERE tipo = ? AND coltivazione = ? GROUP BY sensore_id"
                + "  ) tab1"
                + ") tab2;";
        Double result = 0d;
        connection = null;
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, tipo);
            preparedStatement.setInt(2, id_coltivazione);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("v");
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return result;
    }

    @Override
    public synchronized List<MisurazioneSensoreBean> retrieveMeasurementPerTimeInterval(Date data_inizio, Date data_fine, Integer id_coltivazione, String tipo) throws SQLException {
        String selectSQL = "SELECT avg(valore) as v, misurazione_sensore.data "
                + "FROM misurazione_sensore "
                + "WHERE coltivazione = ? and tipo = ? and misurazione_sensore.data >= ?  and misurazione_sensore.data <= ?"
                + "GROUP BY misurazione_sensore.data;";
        connection = null;
        List<MisurazioneSensoreBean> misurazioneSensoreBeanList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id_coltivazione);
            preparedStatement.setString(2, tipo);
            preparedStatement.setDate(3, data_inizio);
            preparedStatement.setDate(4, data_fine);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                MisurazioneSensoreBean misurazioneSensoreBean = new MisurazioneSensoreBean();
                misurazioneSensoreBean.setData(rs.getDate("data"));
                misurazioneSensoreBean.setValore(rs.getInt("v"));
                misurazioneSensoreBean.setTipo(rs.getString("tipo"));
                misurazioneSensoreBean.setColtivazione(id_coltivazione);
                connection.commit();
                misurazioneSensoreBeanList.add(misurazioneSensoreBean);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return misurazioneSensoreBeanList;
    }
}
