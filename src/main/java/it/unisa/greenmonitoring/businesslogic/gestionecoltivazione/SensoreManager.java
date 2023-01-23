package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.dao.SensoreDAO;
import it.unisa.greenmonitoring.dataccess.dao.SensoreDAOImpl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SensoreManager {
    /**
     * @param s
     * @throws SQLException
     */
    public static void createSensore(SensoreBean s) throws SQLException {
        SensoreDAO sns = new SensoreDAOImpl();
        List<SensoreBean> listaSensori = sns.retrieveAll();
        sns.create(s);
        for (SensoreBean sns2 : listaSensori) {
            System.out.println(sns2.getId());
        }
    }

    /**
     * Questo metodo restituisce un sensore a partire da un id.
     * @param id_sensore
     * @return SensoreBean
     * @throws SQLException
     */
    public SensoreBean retrieveSensore(String id_sensore) throws SQLException {
        return null;
    }

    /**
     * Questo metodo aggiorna una riga in Sensore in corrispondenza ad id.
     * @param id_sensore
     * @throws SQLException
     */
    public void updateSensore(String id_sensore) throws SQLException {

    }

    /**
     * Metodo usato per rimuovere un sensore.
     * @param id_sensore
     * @param email
     * @pre t ha un id che esiste nel database.
     * @post la relazione tra t e la coltivazione nel database non esiste più.
     */
    public void rimuoviSensore(String email, int id_sensore) {
        try {
            SensoreDAO td = new SensoreDAOImpl();
            td.removeAssociation(email, id_sensore);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo usato per visualizzare la lista di sensori di una azienda.
     * @param id_azienda
     * @return list
     */
    public ArrayList<SensoreBean> visualizzaListaSensori(String id_azienda) {
        SensoreDAO td = null;
        ArrayList<SensoreBean> list = new ArrayList<>();
        try {
            td = new SensoreDAOImpl();
            td.retrieveAll().stream().filter(o -> o.getAzienda().equals(id_azienda)).forEach(o -> list.add(o));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
