package it.unisa.greenmonitoring.businesslogic.gestionesensore;

import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.dao.SensoreDAO;
import it.unisa.greenmonitoring.dataccess.dao.SensoreDAOImpl;


import java.sql.SQLException;
import java.util.ArrayList;


public class SensoreManager {
    /**
     * DAO.
     */
    private SensoreDAO sns;
    /**
     * Costruttore.
     */
    public SensoreManager() {
        sns = new SensoreDAOImpl();
    }
    /**
     * @param s
     * @throws SQLException
     */
    public void creaSensore(SensoreBean s) throws SQLException {
        sns.create(s);
    }

    /**
     * Questo metodo restituisce un sensore a partire da un id.
     * @param id_sensore
     * @return SensoreBean
     * @throws SQLException
     */
    public SensoreBean retrieveSensore(int id_sensore) throws SQLException {
        return sns.retrieveByKey(id_sensore);
    }

    /**
     * Questo metodo aggiorna una riga in Sensore in corrispondenza ad id.
     * @param sensore
     * @throws SQLException
     */
    public void cancellaSensore(SensoreBean sensore) throws SQLException {
        try {
            sns.update(sensore.getId(), sensore);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo usato per associare un sensore.
     * @param sensore
     * @param id_coltivazione
     * @post la relazione tra t e la coltivazione nel database non esiste più.
     */
    public void aggiungiAssociazioneSensore(int id_coltivazione, SensoreBean sensore) {
        try {
            sensore.setColtivazione(id_coltivazione);
            sns.update(sensore.getId(), sensore);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo usato per rimuovere l'associazione di un sensore.
     * @param sensore
     * @post la relazione tra t e la coltivazione nel database non esiste più.
     */
    public void cancellaAssociazioneSensore(SensoreBean sensore) {
        try {
            sns.update(sensore.getId(), sensore);
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
        ArrayList<SensoreBean> list = new ArrayList<>();
        try {
            sns.retrieveAll().stream().filter(o -> o.getAzienda().equals(id_azienda)).forEach(o -> list.add(o));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
