package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;
import java.sql.SQLException;
import java.util.List;


public interface TerrenoDAO {
    /**
     * Questo metodo crea un nuovo record nella tabella TERRENO.
     * @param t
     * @throws SQLException
     */
    void createTerreno(TerrenoBean t) throws SQLException;

    /**
     * Questo metodo restituisce ogni terreno del DB.
     * @return TerrenoBean
     * @throws SQLException
     */
    List<TerrenoBean> retrieveTerreno() throws SQLException;

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
