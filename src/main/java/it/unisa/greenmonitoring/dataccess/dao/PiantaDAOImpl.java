package it.unisa.greenmonitoring.dataccess.dao;


import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiantaDAOImpl implements PiantaDAO {


    /**
     * Start Connection.
     */
    private Connection connection;

    /**
     * Dichiaro la Variabile final "azienda" che mi identifica la tabella nel db.
     */
    private static final String TABLE_NAME = "Pianta";

    /**
     * costruttore connessione PiantaDAOImpl.
     */
    public PiantaDAOImpl() {
    }

    @Override
    public void aggiungiPiantaPersonalizzata(PiantaBean p) {

        String insertSQL = "INSERT " + TABLE_NAME + "(azienda,nome,descrizione,ph_min,ph_max,temperatura_min,"
                + "temperatura_max,umidita_min,umidita_max,immagine)" + " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, p.getAzienda());
            preparedStatement.setString(2, p.getNome());
            preparedStatement.setString(3, p.getDescrizione());
            preparedStatement.setFloat(4, Float.parseFloat(p.getPh_min()));
            preparedStatement.setFloat(5, Float.parseFloat(p.getPh_max()));
            preparedStatement.setFloat(6, Float.parseFloat(p.getTemperatura_min()));
            preparedStatement.setFloat(7, Float.parseFloat(p.getTemperatura_max()));
            preparedStatement.setFloat(8, Float.parseFloat(p.getUmidita_min()));
            preparedStatement.setFloat(9, Float.parseFloat(p.getUmidita_max()));
            preparedStatement.setString(10, p.getImmagine());
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
     * Questo metodo restituisce ogni terreno del DB.
     *
     * @return TerrenoBean
     * @throws SQLException
     */
    @Override
    public List<PiantaBean> RetriveAllPiantaDefault() {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE azienda is null";
        ArrayList<PiantaBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("va bene");
            while (rs.next()) {
                PiantaBean t = new PiantaBean();
                t.setId(rs.getInt("id"));
                t.setAzienda(rs.getString("azienda"));
                t.setNome(rs.getString("nome"));
                t.setDescrizione(rs.getString("descrizione"));

                t.setPh_min(Float.toString(rs.getFloat("ph_min")));
                t.setPh_max(rs.getString("ph_max"));
                t.setTemperatura_min(rs.getString("temperatura_min"));
                t.setTemperatura_max(rs.getString("temperatura_max"));
                t.setUmidita_min(rs.getString("umidita_min"));
                t.setUmidita_max(rs.getString("umidita_max"));
                t.setImmagine(rs.getString("immagine"));
                System.out.println(t);
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


    /**
     * Questo metodo restituisce ogni terreno del DB.
     *
     * @param id_pianta
     * @throws SQLException
     */
    @Override
    public void deletePianta(int id_pianta) {
        String deleteSQL = "DELETE FROM Pianta WHERE id = ?";
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id_pianta);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("sono in dao rimuovo");
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
     * @param email
     * @return
     * @throws SQLException
     */
    @Override
    public List<PiantaBean> RetriveAllPiantaAzienda(String email) {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE azienda= ?";
        ArrayList<PiantaBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("va bene");
            while (rs.next()) {
                PiantaBean t = new PiantaBean();
                t.setId(rs.getInt("id"));
                t.setAzienda(rs.getString("azienda"));
                t.setNome(rs.getString("nome"));
                t.setDescrizione(rs.getString("descrizione"));

                t.setPh_min(Float.toString(rs.getFloat("ph_min")));
                t.setPh_max(rs.getString("ph_max"));
                t.setTemperatura_min(rs.getString("temperatura_min"));
                t.setTemperatura_max(rs.getString("temperatura_max"));
                t.setUmidita_min(rs.getString("umidita_min"));
                t.setUmidita_max(rs.getString("umidita_max"));
                t.setImmagine(rs.getString("immagine"));
                System.out.println(t);
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

    /**
     * @param idPianta
     * @return
     */
    @Override
    public PiantaBean retrieveByKey(Integer idPianta) {
        String selectSQL = "SELECT * FROM Pianta WHERE id = ?";
        PiantaBean p = new PiantaBean();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idPianta);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setAzienda(rs.getString("azienda"));
                p.setNome(rs.getString("nome"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPh_min(String.valueOf(rs.getFloat("ph_min")));
                p.setPh_max(String.valueOf(rs.getFloat("ph_max")));
                p.setTemperatura_min(String.valueOf(rs.getFloat("temperatura_min")));
                p.setTemperatura_max(String.valueOf(rs.getFloat("temperatura_max")));
                p.setUmidita_min(String.valueOf(rs.getFloat("umidita_min")));
                p.setUmidita_max(String.valueOf(rs.getFloat("umidita_max")));
                p.setImmagine(rs.getString("immagine"));

                System.out.println("singola pianta da ritornare" + p);
                connection.commit();
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

        return p;
    }

    /**
     * @param p
     * @return
     */
    @Override
    public void updateValue(PiantaBean p) {
        String retrieveSQL = "UPDATE " + TABLE_NAME + " SET ph_min = ?, ph_max = ?, temperatura_min = ?, temperatura_max = ?,"
                + " umidita_min = ?, umidita_max = ? "
                + "WHERE id = ?;";
        System.out.println("singola pianta da aggiornare" + p);
        try {
            PreparedStatement preparedStatement = null;
            connection = ConnectionPool.getConnection();

            preparedStatement = connection.prepareStatement(retrieveSQL);
            preparedStatement.setFloat(1, Float.parseFloat(p.getPh_min()));
            preparedStatement.setFloat(2, Float.parseFloat(p.getPh_max()));
            preparedStatement.setFloat(3, Float.parseFloat(p.getTemperatura_min()));
            preparedStatement.setFloat(4, Float.parseFloat(p.getTemperatura_max()));
            preparedStatement.setFloat(5, Float.parseFloat(p.getUmidita_min()));
            preparedStatement.setFloat(6, Float.parseFloat(p.getUmidita_max()));
            preparedStatement.setInt(7, p.getId());

            preparedStatement.executeUpdate();
            System.out.println("singola pianta da aggiornare" + p);
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
