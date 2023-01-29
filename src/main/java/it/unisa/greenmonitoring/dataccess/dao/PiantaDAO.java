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
    List<PiantaBean> RetriveAllPiantaDefault() throws SQLException;

    /**
     * Questo metodo restituisce ogni terreno del DB.
     * @param id
     * @throws SQLException
     */
   void deletePianta(int id) throws SQLException;

    /**
     *
     * @param email
     * @return List
     * @throws SQLException
     */
    List<PiantaBean> RetriveAllPiantaAzienda(String email) throws SQLException;
}
