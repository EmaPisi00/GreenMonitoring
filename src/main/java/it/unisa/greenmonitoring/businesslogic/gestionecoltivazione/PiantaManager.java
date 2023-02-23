package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;

import it.unisa.greenmonitoring.dataccess.dao.PiantaDAO;
import it.unisa.greenmonitoring.dataccess.dao.PiantaDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class PiantaManager {
    /**
     * piantadao.
     */
    private PiantaDAO pd;
    /**
     * coltivazionemanager.
     */
    private ColtivazioneManager cm;
    /**
     * Costruttore.
     */
    public PiantaManager() {
        this.pd = new PiantaDAOImpl();
        this.cm = new ColtivazioneManager();
    }
    //TO-DO implementare i metodi di pianta manager
    /**
     * Questo metodo inserisce una nuova pianta nel a partire da un PiantaBean DB.
     * @param pianta
     * @return PiantaBean
     */
    public PiantaBean inserisciPianta(PiantaBean pianta) {
        try {
            List<PiantaBean> piante = ListaPianteManager(pianta.getAzienda());
            for (PiantaBean pianteA : piante) {
                if (pianta.getNome().matches(pianteA.getNome())) {
                    return null;
                }
            }
            pd.aggiungiPiantaPersonalizzata(pianta);
            return pianta;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Ritorna la lista delle piante per utente.
     * @param email
     * @return List
     */
    public List<PiantaBean> ListaPianteManager(String email) {
        List<PiantaBean> listaPiante = pd.RetriveAllPiantaDefault();
        listaPiante.addAll(pd.RetriveAllPiantaAzienda(email));
        return  listaPiante;
    }
    /**
     * Questo metodo rimuove una pianta da db.
     * @param emailAzienda
     * @param id_pianta
     * @return true se e solo se la pianta è rimossa con successo dal database.
     */
    public boolean rimuoviPiantaManager(int id_pianta, String emailAzienda) {
        ArrayList<ColtivazioneBean> c = cm.visualizzaColtivazioniAvviate(emailAzienda);
        for (ColtivazioneBean colti : c) {
            if (colti.getPianta() == id_pianta) {
                    System.out.println("sono uguali errore");
                    return false;
            }
        }
        pd.deletePianta(id_pianta);
        return true;
    }

    /**
     * Questo metodo restituisce una pianta a partire dal suo id.
     * @param id
     * @return PiantaBean
     */
    public PiantaBean visualizzaPianta(Integer id) {
        PiantaBean pianta = pd.retrieveByKey(id);
        return pianta;
    }
    /**
     * Questo metodo aggiorna una pianta partendo da un'istanza di PiantaBean.
     * @param pianta
     * @return PiantaBean
     */
    public PiantaBean aggiornaPianta(PiantaBean pianta) {
        if (pianta.getPh_min() > pianta.getPh_max()
                || pianta.getTemperatura_min() > pianta.getUmidita_max()
                || pianta.getUmidita_min() > pianta.getUmidita_max()) {
            return null;
        }
            pd.updateValue(pianta);
            return pianta;
    }
}

