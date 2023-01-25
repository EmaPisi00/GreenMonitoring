package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;


import java.sql.SQLException;
import java.util.List;


public interface PiantaDAO {
    /**
     * Questo metodo crea un nuovo record nella tabella TERRENO.
     * @param p
     * @throws SQLException
     */
    void aggiungiPiantaPersonalizzata(PiantaBean p) throws SQLException;

    /**
     * Questo metodo restituisce ogni terreno del DB.
     * @return TerrenoBean
     * @throws SQLException
     */
    List<PiantaBean> RetriveAllPianta() throws SQLException;

    /**
     * Questo metodo restituisce ogni terreno del DB.
     * @throws SQLException
     */
   void removePianta() throws SQLException;
}
