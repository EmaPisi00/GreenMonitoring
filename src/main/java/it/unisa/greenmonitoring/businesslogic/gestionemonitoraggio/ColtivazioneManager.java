package it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio;

import it.unisa.greenmonitoring.dataccess.beans.*;
import it.unisa.greenmonitoring.dataccess.dao.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColtivazioneManager {
    /**
     * MisurazioneSensoreDAO.
     */
    private MisurazioneSensoreDAO misurazioneSensoreDAO;
    /**
     * ColtivazioneDAO.
     */
    private ColtivazioneDAO cd;

    /**
     * PiantaDAO.
     */
    private PiantaDAO pd;

    /**
     * PiantaDAO.
     */
    private TerrenoDAO td;

    /**
     * SensoreDAO.
     */
    private SensoreDAO sensoreDAO;

    /**
     * Questa costante indica lo stato di una coltivazione avviata.
     */
    private final int stato_coltivazione_avviata = 0;
    /**
     * Costruttore ColtivazioneManager.
     */
    public ColtivazioneManager() {
        cd = new ColtivazioneDAOImpl();
        pd = new PiantaDAOImpl();
        td = new TerrenoDAOImpl();
        sensoreDAO = new SensoreDAOImpl();
        misurazioneSensoreDAO = new MisurazioneSensoreDAOImpl();
    }

    /**
     * Questo metodo crea una nuova coltivazione sul database.
     * @param c
     * @param id_azienda
     * @param collezioneSensori
     * @return ColtivazioneBean
     */

    public ColtivazioneBean avvioColtivazione(ColtivazioneBean c, String id_azienda, ArrayList<SensoreBean> collezioneSensori) throws Exception {
         try {
            /* Verifico l'esistenza della pianta associata a una certa coltivazione c */
             PiantaBean piantaBean = pd.retrieveByKey(c.getPianta());
             if (piantaBean == null) {
                 return null;
             }

             TerrenoBean terrenoBean = td.retrieveByKey(c.getTerreno());
             if (terrenoBean == null) {
                 return null;
             } else {
                 //prende tutte le coltivazioni e cerca il terreno
                 ArrayList<ColtivazioneBean> listaColtivazioni = cd.retrieveColtivazione(id_azienda);
                 for (ColtivazioneBean coltivazione : listaColtivazioni) {
                     if (terrenoBean.getId() == coltivazione.getTerreno()) {
                         return null;
                     }
                 }
             }

            /* Verifico il formato della data */
            if (c.getData_inizio().toString().matches("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")) {
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                if (!(java.sql.Date.valueOf(c.getData_inizio().toString()).before(date))) {
                    return null;
                }
            } else {
                return null;
            }

            /* Verifico che i sensori associati siano > 0, presenti nel db e non occupati */
             if (collezioneSensori.size() == 0) {
                 return null;
             } else {
                 for (SensoreBean sensore : collezioneSensori) {
                     if (sensore.getColtivazione() != null) {
                         return null;
                     }
                 }
             }
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
    public ArrayList<ColtivazioneBean> visualizzaStatoColtivazioni(String id_azienda) throws SQLException {
        return cd.retrieveColtivazione(id_azienda);
    }

    /**
     * Questo metodo restituisce una lista di coltivazioni avviate di una azienda.
     * @param id_azienda
     * @return ArrayList of ColtivazioneBean
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
    /**
     * Questo metodo restituisce le misurazioni più recenti a partire da un'azienda.
     * @param id_coltivazione
     * @param tipo
     * @return List&ltMisurazioneSensoreBean&gt l con l.size() > 0 se e solo se ci sono record nel db.
     */
    public Double restituisciMisurazioniRecenti(String tipo, Integer id_coltivazione) {
        try {
            return misurazioneSensoreDAO.retrieveMostRecentMesurement(tipo, id_coltivazione);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Questo metodo restituisce le misurazioni per periodo.
     * @param data_inizio_periodo
     * @param data_fine_periodo
     * @param coltivazione
     * @param tipo
     * @return List&ltMisurazioneSensoreBean&gt l con l.size() > 0 se e solo se ci sono record nel db.
     */
    public List<MisurazioneSensoreBean> restituisciMisurazioniPerPeriodo(java.sql.Date data_inizio_periodo, java.sql.Date data_fine_periodo, Integer coltivazione, String tipo) {
        try {
            return misurazioneSensoreDAO.retrieveMeasurementPerTimeInterval(data_inizio_periodo, data_fine_periodo, coltivazione, tipo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Questo metodo restituisce le misurazioni a partire da oggi.
     * @param id_coltivazione
     * @param tipo
     * @return List&ltMisurazioneSensoreBean&gt l con l.size() > 0 se e solo se ci sono record nel db.
     */
    public List<MisurazioneSensoreBean> visualizzaMisurazioneOggiColtivazione(Integer id_coltivazione, String tipo) throws SQLException {
        return misurazioneSensoreDAO.retreiveMisurazioneOggiColtivazione(id_coltivazione, tipo);
    }
    /**
     * Questo metodo restituisce le misurazioni sulla base del tipo di sensore e della coltivazione.
     * @param id_coltivazione
     * @param tipo
     * @return List&ltMisurazioneSensoreBean&gt l con l.size() > 0 se e solo se ci sono record nel db.
     */
    public List<MisurazioneSensoreBean> visualizzaMisurazioneColtivazione(Integer id_coltivazione, String tipo) throws SQLException {
        return misurazioneSensoreDAO.retreiveMisurazioneOggiColtivazione(id_coltivazione, tipo);
    }
    /**
     * Questo metodo restituisce esattamente una coltivazione.
     * @param id
     * @return coltivazioneBean
     */
    public ColtivazioneBean retrieveColtivazioneSingola(int id) throws SQLException {
        return cd.retrieveByKey(id);
    }
}
