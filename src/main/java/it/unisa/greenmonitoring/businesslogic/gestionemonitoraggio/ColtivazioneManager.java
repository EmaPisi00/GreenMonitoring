package it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio;

import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
import it.unisa.greenmonitoring.dataccess.beans.*;
import it.unisa.greenmonitoring.dataccess.dao.*;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ColtivazioneManager {
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
    private final int stato_coltivazione_avviata = 1;
    /**
     * Costruttore ColtivazioneManager.
     */
    public ColtivazioneManager() {
        cd = new ColtivazioneDAOImpl();
        pd = new PiantaDAOImpl();
        td = new TerrenoDAOImpl();
        sensoreDAO = new SensoreDAOImpl();
    }

    /**
     * Questo metodo crea una nuova coltivazione sul database.
     * @param c
     * @param id_azienda
     * @return ColtivazioneBean
     */

    public ColtivazioneBean avvioColtivazione(ColtivazioneBean c, String id_azienda) throws Exception {
         try {
            /* Verifico l'esistenza della pianta associata a una certa coltivazione c */
            List<Integer> piantaBeanList = pd.RetriveAllPiantaDefault().stream().map(o -> o.getId()).toList();
            if (!piantaBeanList.contains(c.getPianta())) {
                throw new Exception("Pianta inesistente");
            }


            /* Verifico l'esistenza del terreno e che questo non sia già occupato */
            List<TerrenoBean> terrenoBeanList = td.retrieveTerreno();
            List<Integer> terrenoBeanIdList = terrenoBeanList.stream().map(o -> o.getId()).toList();
            List<ColtivazioneBean> coltivazioneBeans = cd.retrieveColtivazione(id_azienda);
            if (terrenoBeanIdList.contains(c.getTerreno())) {
                for (int i = 0; i < terrenoBeanIdList.size(); i++) {
                    for (int j = 0; j < coltivazioneBeans.size(); j++) {
                        if (terrenoBeanIdList.get(i).equals(coltivazioneBeans.get(j).getTerreno())) {
                            throw new Exception("Terreno occupato");
                        }
                    }
                }
            } else {
                throw new Exception("Terreno inesistente");
            }

            /* Verifico il formato della data */
            if (c.getData_inizio().toString().matches("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")) {
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                if (!(java.sql.Date.valueOf(c.getData_inizio().toString()).before(date))) {
                    throw new Exception("La data è futura rispetto ad oggi.");
                }
            } else {
                throw new Exception("La data non corrisponde al formato YY-MM-DD o YYYY-MM-DD.");
            }

            /* Verifico che i sensori associati siano > 0, presenti nel db e non occupati */
             List<SensoreBean> listSensori = sensoreDAO.retrieveAllByColtivazione(c.getId());
            if (listSensori == null || listSensori.size() == 0) {
                throw new Exception("Non ci sono sensori");
            } else {
                SensoreManager sm = new SensoreManager();
                List<SensoreBean> sensoreBeanList = sm.visualizzaListaSensori(id_azienda);
                for (int i = 0; i < sensoreBeanList.size(); i++) {
                    for (int j = 0; j < listSensori.size(); j++) {
                        if (listSensori.get(j).equals(sensoreBeanList.get(i)) && sensoreBeanList.get(i).getColtivazione() == null) {
                            throw new Exception("Sensore occupato");
                        }
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
     * Questo metodo calcola la media per ogni tipo di sensore.
     * @param id_coltivazione
     * @return ms
     */
    public int[] visualizzaMediaSensori(String id_coltivazione) throws SQLException {
        MisurazioneSensoreDAO msdao = new MisurazioneSensoreDAOImpl();
        ArrayList<MisurazioneSensoreBean> msbList = msdao.retreive(id_coltivazione);
        int[] media = new int[3];
        for (int i = 0; i < msbList.size(); i++) {
            MisurazioneSensoreBean msb = msbList.get(i);
            if (msb.getTipo().equals("pH")) {
                media[0] = media[0] + msb.getValore();
            } else if (msb.getTipo().equals("temperatura")) {
                media[1] = media[1] + msb.getValore();
            } else if (msb.getTipo().equals("umidità")) {
                media[2] = media[2] + msb.getValore();
            }
        }
        return media;
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
