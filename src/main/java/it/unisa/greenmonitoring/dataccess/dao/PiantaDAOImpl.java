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
    public void aggiungiPiantaPersonalizzata(PiantaBean p) throws SQLException {
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT " + TABLE_NAME + "(azienda,nome,descrizione,ph_min,ph_max,temperatura_min,"
                + "temperatura_max,umidita_min,umidita_max,immagine)" + " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            connection = ConnectionPool.getConnection();
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

    /**
     * Questo metodo restituisce ogni terreno del DB.
     *
     * @return TerrenoBean
     * @throws SQLException
     */
    @Override
    public List<PiantaBean> RetriveAllPiantaDefault() throws SQLException {
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
            connection.close();
        }
        return list;
    }


    /**
     * Questo metodo restituisce ogni terreno del DB.
     *
     * @throws SQLException
     */
    @Override
    public void removePianta() throws SQLException {

    }

    /**
     * @param email
     * @return
     * @throws SQLException
     */
    @Override
    public List<PiantaBean> RetriveAllPiantaAzienda(String email) throws SQLException {
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
            connection.close();
        }
        return list;
    }
}
