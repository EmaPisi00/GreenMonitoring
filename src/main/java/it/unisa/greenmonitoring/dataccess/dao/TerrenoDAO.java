package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;
import java.sql.SQLException;
import java.util.ArrayList;


public interface TerrenoDAO {
    /**
     * Questo metodo crea un nuovo record nella tabella TERRENO.
     * @param t
     * @throws SQLException
     */
    void createTerreno(TerrenoBean t);

    /**
     * Questo metodo restituisce ogni terreno del DB.
     * @return TerrenoBean
     * @throws SQLException
     */
    ArrayList<TerrenoBean> retrieveAll();

    /**
     * Questo metodo ricerca un terreno in base all'id.
     * @param id_terreno
     * @throws SQLException
     * @return TerrenoBean.
     */
    TerrenoBean retrieveByKey(int id_terreno) throws SQLException;

    /**
     * Questo metodo aggiorna un record nella tabella TERRENO.
     * @param id_terreno
     * @throws SQLException
     */
    void updateTerreno(int id_terreno) throws SQLException;

    /**
     * Questo metodo distrugge un record nella tabella TERRENO.
     * @param id_terreno
     * @throws SQLException
     */
    void deleteTerreno(int id_terreno) throws SQLException;
}
