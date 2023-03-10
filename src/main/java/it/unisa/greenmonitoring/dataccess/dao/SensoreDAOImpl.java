package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensoreDAOImpl implements SensoreDAO {
    /**
     * Start Connection.
     */
    private Connection connection;

    /**
     * Dichiaro la Variabile final "sensore" che mi identifica la tabella nel db.
     */
    private static final String TABLE_NAME = "Sensore";

    /**
     * Classe per l'implementazione di SensoreDAOImpl.
     */
    public SensoreDAOImpl() {
    }

    @Override
    public void create(SensoreBean s) throws SQLException {
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT " + TABLE_NAME + " (azienda,tipo,idM) VALUES (?,?,?)";
        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, s.getAzienda());
            preparedStatement.setString(2, s.getTipo());
            preparedStatement.setString(3, s.getIdM());
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
    public ArrayList<SensoreBean> retrieveAll() throws SQLException {
        String selectSQL = "SELECT * FROM Sensore";
        ArrayList<SensoreBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                SensoreBean s = new SensoreBean(-1, null, -1, null, null);
                s.setId(rs.getInt("id"));
                s.setTipo(rs.getString("tipo"));
                s.setColtivazione(rs.getInt("coltivazione"));
                s.setAzienda(rs.getString("azienda"));
                s.setIdM(rs.getString("idM"));

                connection.commit();
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }
    @Override
    public SensoreBean retrieveByKey(int id_sensore) throws SQLException {
        String selectSQL = "SELECT * FROM Sensore WHERE id = ?";
        SensoreBean s = new SensoreBean();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id_sensore);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                s.setId(rs.getInt("id"));
                s.setTipo(rs.getString("tipo"));
                s.setColtivazione(rs.getInt("coltivazione"));
                s.setAzienda(rs.getString("azienda"));
                s.setIdM(rs.getString("idM"));

                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return s;
    }
    @Override
    public List<SensoreBean> retrieveAllByAzienda(String azienda) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE azienda = ?";
        List<SensoreBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, azienda);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                SensoreBean s = new SensoreBean();
                s.setId(rs.getInt("id"));
                s.setTipo(rs.getString("tipo"));
                s.setColtivazione(rs.getInt("coltivazione"));
                s.setAzienda(rs.getString("azienda"));
                s.setIdM(rs.getString("idM"));

                list.add(s);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return list;
        }
    }

    @Override
    public List<SensoreBean> retrieveAllByColtivazione(int coltivazione) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE coltivazione = ?";
        List<SensoreBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, coltivazione);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                SensoreBean s = new SensoreBean();
                s.setId(rs.getInt("id"));
                s.setTipo(rs.getString("tipo"));
                s.setColtivazione(rs.getInt("coltivazione"));
                s.setAzienda(rs.getString("azienda"));
                s.setIdM(rs.getString("idM"));

                list.add(s);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return list;
        }
    }

    @Override
    public synchronized void update(int id_sensore, SensoreBean s) throws SQLException {
        String updateSQL = "UPDATE Sensore SET tipo = ?, azienda = ?, idM = ?, coltivazione = ? WHERE id = ?";
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, s.getTipo());
            preparedStatement.setString(2, s.getAzienda());
            preparedStatement.setString(3, s.getIdM());
            if (s.getColtivazione() != null) {
                preparedStatement.setInt(4, s.getColtivazione());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            preparedStatement.setInt(5, id_sensore);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Override
    public void delete(SensoreBean sensore) throws SQLException {
        String deleteSQL = "DELETE FROM Sensore WHERE Sensore id = ? AND azienda = ?";
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, sensore.getId());
            preparedStatement.setString(2, sensore.getAzienda());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    /**
     *
     * @param idM
     * @return
     * @throws SQLException
     */
    @Override
    public SensoreBean retrieveByidM(String idM) throws SQLException {
        String selectSQL = "SELECT * FROM Sensore WHERE idM = ?";
        SensoreBean s = new SensoreBean();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, idM);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                s.setId(rs.getInt("id"));
                s.setTipo(rs.getString("tipo"));
                s.setColtivazione(rs.getInt("coltivazione"));
                s.setAzienda(rs.getString("azienda"));
                s.setIdM(rs.getString("idM"));

                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return s;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<SensoreBean> SensoriColtivazioneAvviata() {
        //select * from Sensore where Coltivazione in (select id from Coltivazione as c where c.stato_archiviazione=1)
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE Coltivazione in (SELECT id from Coltivazione as c where c.stato_archiviazione=0)";
        ArrayList<SensoreBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("va bene");
            while (rs.next()) {
                SensoreBean s = new SensoreBean();
                s.setId(rs.getInt("id"));
                s.setTipo(rs.getString("tipo"));
                s.setColtivazione(rs.getInt("coltivazione"));
                s.setAzienda(rs.getString("azienda"));
                s.setIdM(rs.getString("idM"));
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
