package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;
import java.util.List;


public interface PiantaDAO {
    /**
     * Questo metodo crea un nuovo record nella tabella TERRENO.
     * @param p
     */
    void aggiungiPiantaPersonalizzata(PiantaBean p);

    /**
     * Questo metodo restituisce ogni terreno del DB.
     * @return TerrenoBean
     */
    List<PiantaBean> RetriveAllPiantaDefault();

    /**
     * Questo metodo restituisce ogni terreno del DB.
     * @param id
     */
   void deletePianta(int id);

    /**
     *
     * @param email
     * @return List
     */
    List<PiantaBean> RetriveAllPiantaAzienda(String email);

    /**
     *
     * @param id
     * @return PiantaBean
     */
    PiantaBean retrieveByKey(Integer id);

    /**
     *
     * @param p
     */
    void updateValue(PiantaBean p);

    /**
     *
     * @param id
     * @return PiantaBean
     */
    PiantaBean ritornaPiantaPerColtivazione(int id);
}
