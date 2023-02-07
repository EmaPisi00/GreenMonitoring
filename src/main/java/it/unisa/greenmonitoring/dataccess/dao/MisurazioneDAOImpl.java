package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MisurazioneDAOImpl implements MisurazioneDAO {
    @Override
    public List<MisurazioneSensoreBean> restituisciMisurazioniRecenti(String id_azienda) throws SQLException {
        String selectSQL = "SELECT i.id, i.data, b.azienda, i.tipo, i.ora, i.valore "
        + "FROM misurazione_sensore i INNER JOIN coltivazione t JOIN terreno b ON t.terreno = b.id ON t.id = i.coltivazione "
        + "INNER JOIN ( SELECT MAX(i.data) data, MAX(i.ora) ora, tipo FROM misurazione_sensore i "
        + "INNER JOIN coltivazione t ON t.id = i.coltivazione GROUP BY i.tipo) t1 ON t1.data = i.data "
        + "and t1.ora = i.ora "
        + "and t1.tipo = i.tipo where b.azienda = ?";
        Connection connection = null;
        List<MisurazioneSensoreBean> misurazioneSensoreBeanList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, id_azienda);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                MisurazioneSensoreBean misurazioneSensoreBean = new MisurazioneSensoreBean();
                misurazioneSensoreBean.setId(rs.getInt("id"));
                misurazioneSensoreBean.setOra(rs.getTime("ora"));
                misurazioneSensoreBean.setData(rs.getDate("data"));
                misurazioneSensoreBean.setValore(rs.getInt("valore"));
                misurazioneSensoreBean.setTipo(rs.getString("tipo"));
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return misurazioneSensoreBeanList;
    }
}
