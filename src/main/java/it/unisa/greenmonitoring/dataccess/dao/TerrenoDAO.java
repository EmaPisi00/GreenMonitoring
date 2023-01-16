package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.SQLException;


public interface TerrenoDAO {
    /**
     * Questo metodo crea un nuovo record nella tabella TERRENO.
     * @param t
     * @param email
     * @throws SQLException
     * @return TerrenoBean
     */
    TerrenoBean createTerreno(TerrenoBean t, String email) throws SQLException;

    /**
     * Questo metodo restituisce una istanza di TerrenoBean a partire da un id.
     * @param id_terreno
     * @throws SQLException
     * @return TerrenoBean
     */
    TerrenoBean retrieveTerreno(String id_terreno) throws SQLException;

    /**
     * Questo metodo aggiorna un record nella tabella TERRENO.
     * @param id_terreno
     * @throws SQLException
     */
    void updateTerreno(String id_terreno) throws SQLException;

    /**
     * Questo metodo distrugge un record nella tabella TERRENO.
     * @param id_terreno
     * @throws SQLException
     */
    void deleteTerreno(String id_terreno) throws SQLException;
}
