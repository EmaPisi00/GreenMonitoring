package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.SQLException;


public interface TerrenoDAO {
    /**
     * This method creates a new record in table TERRENO.
     * @param t
     * @throws SQLException
     */
    void create(TerrenoBean t) throws SQLException;
    /**
     * This method return an istance of TerrenoBean constructed from an id.
     * @param id_terreno
     * @throws SQLException
     * @return TerrenoBean
     */
    TerrenoBean retrieve(String id_terreno) throws SQLException;

    /**
     * This method updates a record in table TERRENO.
     * @param id_terreno
     * @throws SQLException
     */
    void update(String id_terreno) throws SQLException;
    /**
     * This method destroys a record in table TERRENO.
     * @param id_terreno
     * @throws SQLException
     */
    void delete(String id_terreno) throws SQLException;
}
