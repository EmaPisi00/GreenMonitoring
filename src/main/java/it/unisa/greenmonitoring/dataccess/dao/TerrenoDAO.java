package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.SQLException;


public interface TerrenoDAO {
    /**
     * Questo metodo crea un record nella tabella TERRENO.
     * @param t
     * @throws SQLException
     */
    void create(TerrenoBean t) throws SQLException;
    /**
     * Questo metodo restituisce una istanza TerrenoBean costruita dai dati nel record con l'id passato come parametro.
     * @param id_terreno
     * @throws SQLException
     * @return TerrenoBean
     */
    TerrenoBean retrieve(String id_terreno) throws SQLException;

    /**
     * Questo metodo aggiorna un record nella tabella TERRENO.
     * @param id_terreno
     * @throws SQLException
     */
    void update(String id_terreno) throws SQLException;
    /**
     * Questo metodo distrugge un record nella tabella TERRENO.
     * @param id_terreno
     * @throws SQLException
     */
    void delete(String id_terreno) throws SQLException;
}
