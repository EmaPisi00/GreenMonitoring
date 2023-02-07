package it.unisa.greenmonitoring.businesslogic.gestionesensore;

import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.dao.MisurazioneDAO;
import it.unisa.greenmonitoring.dataccess.dao.MisurazioneDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class MisurazioneSensoreManager {

    /**
     * MisurazioneSensoreDAO.
     */
    private MisurazioneDAO misurazioneDAO;
    /**
     * Questo metodo restituisce le misurazioni più recenti a partire da un'azienda.
     * @param id_azienda
     * @return List&ltMisurazioneSensoreBean&gt l con l.size() > 0 se e solo se ci sono record nel db.
     */
    public List<MisurazioneSensoreBean> restituisciMisurazioniRecenti(String id_azienda) {
        misurazioneDAO = new MisurazioneDAOImpl();
        try {
            return misurazioneDAO.restituisciMisurazioniRecenti(id_azienda);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
