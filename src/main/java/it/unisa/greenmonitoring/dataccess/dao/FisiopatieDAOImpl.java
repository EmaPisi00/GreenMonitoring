package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.FisiopatieBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FisiopatieDAOImpl implements FisiopatieDAO {
    /**
     * Start Connection.
     */
    private Connection connection;

    /**
     * Dichiaro la Variabile final "sensore" che mi identifica la tabella nel db.
     */
    private static final String TABLE_NAME = "Fisiopatie";

    /**
     * Classe per l'implementazione di SensoreDAOImpl.
     */
    public FisiopatieDAOImpl() {
    }

    @Override
    public ArrayList<FisiopatieBean> retrieveAll() throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME;
        ArrayList<FisiopatieBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                FisiopatieBean s = new FisiopatieBean();
                s.setId(rs.getInt("id"));
                s.setId_pianta(rs.getInt("pianta"));
                s.setNome(rs.getString("nome"));
                s.setDescrizione(rs.getString("descrizione"));
                s.setUmid_terr_min(rs.getFloat("umidita_terreno_min"));
                s.setUmid_terr_max(rs.getFloat("umidita_terreno_max"));
                s.setTemp_min(rs.getFloat("temperatura_min"));
                s.setTemp_max(rs.getFloat("temperatura_max"));
                s.setUmid_aria_min(rs.getFloat("umidita_aria_min"));
                s.setUmid_aria_max(rs.getFloat("umidita_aria_min"));

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
    public ArrayList<FisiopatieBean> retrieveAllByPianta(int id_pianta) throws SQLException {
        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE pianta = ?";
        ArrayList<FisiopatieBean> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id_pianta);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                FisiopatieBean s = new FisiopatieBean();
                s.setId(rs.getInt("id"));
                s.setId_pianta(rs.getInt("pianta"));
                s.setNome(rs.getString("nome"));
                s.setDescrizione(rs.getString("descrizione"));
                s.setUmid_terr_min(rs.getFloat("umidita_terreno_min"));
                s.setUmid_terr_max(rs.getFloat("umidita_terreno_max"));
                s.setTemp_min(rs.getFloat("temperatura_min"));
                s.setTemp_max(rs.getFloat("temperatura_max"));
                s.setUmid_aria_min(rs.getFloat("umidita_aria_min"));
                s.setUmid_aria_max(rs.getFloat("umidita_aria_min"));

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
}
