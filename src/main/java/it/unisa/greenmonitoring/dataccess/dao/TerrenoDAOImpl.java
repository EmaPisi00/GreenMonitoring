package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TerrenoDAOImpl implements TerrenoDAO {

    private Connection connection;

    public TerrenoDAOImpl () throws SQLException {
        try{
            connection= ConnectionPool.getConnection();
        }catch (SQLException s){
            System.out.println("errore nel creare la connessione: "+s);
        }finally {
            connection.close();
        }

    }
    @Override
    public TerrenoBean createTerreno(TerrenoBean t,String email) throws SQLException {
        PreparedStatement preparedStatement = null;
        String insertSQL="INSERT INTO Terreno(azienda, immagine, latitudine, longitudine, superfice) VALUES(?, ? ?, ?, ?)";
        try{
            connection= ConnectionPool.getConnection();
            preparedStatement=connection.prepareStatement(insertSQL);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,t.getImmagine());
            preparedStatement.setString(3,t.getLatitudine());
            preparedStatement.setString(4,t.getLongitudine());
            preparedStatement.setString(5,t.getSuperficie());
            preparedStatement.execute();
        }catch (SQLException s){
            System.out.println("errore nel salvare il terreno: "+s);
        }
        finally {
            connection.close();
        }
        return t;
    }

    /**
     * Questo metodo restituisce un TerrenoBean a partire da un id.
     * @param id_terreno
     * @return
     * @throws SQLException
     */
    @Override
    public TerrenoBean retrieveTerreno(String id_terreno) throws SQLException {

        String selectSQL="SELECT * FROM Terreno WHERE Terreno.id = ?";
        TerrenoBean t = null;
        try{
            connection= ConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, id_terreno);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                t.setId(rs.getString("id"));
                t.setImmagine(rs.getString("immagine"));
                t.setSuperficie(rs.getString("superficie"));
                t.setLatitudine(rs.getFloat("latitudine"));
                t.setLongitudine(rs.getFloat("longitudine"));
                t.setAzienda(rs.getString("azienda"));
            }

        }catch (SQLException s){
            s.printStackTrace();
        }
        finally {
            connection.close();
        }
        return t;
    }

    @Override
    public void updateTerreno(String id_terreno) throws SQLException {

    }

    @Override
    public void deleteTerreno(String id_terreno) throws SQLException {

    }
}
