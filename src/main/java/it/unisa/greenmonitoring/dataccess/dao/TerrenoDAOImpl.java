package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
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


    @Override
    public TerrenoBean retrieveTerreno(String id_terreno) throws SQLException {
        return null;
    }

    @Override
    public void updateTerreno(String id_terreno) throws SQLException {

    }

    @Override
    public void deleteTerreno(String id_terreno) throws SQLException {

    }
}
