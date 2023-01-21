package it.unisa.greenmonitoring.businesslogic.autenticazione;

import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.dao.ColtivazioneDAO;
import it.unisa.greenmonitoring.dataccess.dao.ColtivazioneDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class ColtivazioneManager {
    /**
     * ColtivazioneDAO.
     */
    private ColtivazioneDAO cd;
    /**
     * Questa costante indica lo stato di una coltivazione avviata.
     */
    private final int stato_coltivazione_avviata = 12;

    /**
     * Questo metodo crea una nuova coltivazione sul database.
     * @param c
     * @return ColtivazioneBean
     */
    public ColtivazioneBean avvioColtivazione(ColtivazioneBean c) {
        cd = new ColtivazioneDAOImpl();
        try {
            cd.createColtivazione(c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    /**
     * Questo metodo restituisce una lista di coltivazioni di una azienda.
     * @param id_azienda
     * @return ArrayList of ColtivazioneBean
     */
    public ArrayList<ColtivazioneBean> visualizzaStatoColtivazioni(String id_azienda) {
        cd = new ColtivazioneDAOImpl();
        ArrayList<ColtivazioneBean> l = new ArrayList<>();
        try {
            cd.retrieveColtivazione(id_azienda).stream().forEach(o -> l.add(o));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }

    /**
     * Questo metodo restituisce una lista di coltivazioni avviate di una azienda.
     * @param id_azienda
     * @return
     */
    public ArrayList<ColtivazioneBean> visualizzaColtivazioniAvviate(String id_azienda) {
        cd = new ColtivazioneDAOImpl();
        ArrayList<ColtivazioneBean> l = new ArrayList<>();
        try {
            cd.retrieveColtivazione(id_azienda).stream()
                    .filter(o -> o.getStato_archiviazione() == this.stato_coltivazione_avviata)
                    .forEach(o -> l.add(o));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return l;
    }
}
