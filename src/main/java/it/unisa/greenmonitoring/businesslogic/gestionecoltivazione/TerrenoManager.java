package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;
import it.unisa.greenmonitoring.dataccess.dao.TerrenoDAO;
import it.unisa.greenmonitoring.dataccess.dao.TerrenoDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TerrenoManager {
    /**
     * ColtivazioneManager.
     */
    private ColtivazioneManager coltivazioneManager;
    /**
     * terrenodao.
     */
    private TerrenoDAO td;

    /**
     * Costruttore di AutenticazioneManager.
     */
    public TerrenoManager() {
        this.td = new TerrenoDAOImpl();
        this.coltivazioneManager = new ColtivazioneManager();
    }

    /**
     * inserisce un terreno.
     * @param t
     * @throws SQLException
     * @return List
     */
    public TerrenoBean inserisciTerreno(TerrenoBean t)  {

        if (!(t.getSuperficie().matches("^[0-9]+$"))) {
            System.out.println("errore nella superfice");
            return null;
        }
        if (t.getNome().length() < 2 || t.getNome().length() > 30) {
            System.out.println("errore nella dimensione del nome");
            return null;
        }

        if (t.getLatitudine() < 0 || t.getLatitudine() > 90) {
            System.out.println("errore: latitudine minore di 0 ");
            System.out.println("errore: latitudine maggiore di 90");
            return null;
        }
        if (t.getLongitudine() < 0 || t.getLongitudine() > 180) {
            System.out.println("errore: longitudine minore di 0 ");
            System.out.println("errore: longitudine maggiore di 180");
            return null;
        }
        List<TerrenoBean> listaterreni = td.retrieveAll();
        for (TerrenoBean tt : listaterreni) {
            System.out.println(tt.getId());
            if (t.getLongitudine().compareTo(tt.getLongitudine()) == 0 && t.getLatitudine().compareTo(tt.getLatitudine()) == 0) {
                System.out.println(tt.getLongitudine());
                System.out.println(t.getLongitudine());
                System.out.println("esiste già un terreno in questa posizione");
                return null;
            }
        }
            td.createTerreno(t);
            return t;
    }

    /**
     * Questo metodo restituisce un terreno a partire da un id intero.
     * @param id_terreno
     * @return TerrenoBean
     * @throws SQLException
     */
    public TerrenoBean restituisciTerrenoDaInt(int id_terreno) throws SQLException {
        return td.retrieveByKey(id_terreno);
    }

    /**
     * Questo metodo aggiorna una riga in TERRENO in corrispondenza ad id.
     * @param id_terreno
     * @throws SQLException
     */
    public void updateTerreno(String id_terreno) throws SQLException {

    }

    /**
     * Metodo usato per rimuovere un terreno.
     * @param id_terreno
     * @pre t ha un id che esiste nel database.
     * @post la relazione tra t e la coltivazione nel database non esiste più.
     * @return true se e solo se la rimozione avviene con successo.
     */
    public boolean rimuoviTerreno(int id_terreno) {
        try {
            System.out.println("[TerrenoManager] - retrieveColtivazioneSingola -" + coltivazioneManager.retrieveColtivazioneSingola(id_terreno).getTerreno());
            if (coltivazioneManager.retrieveColtivazioneSingola(id_terreno).getTerreno() == null) {
                td.deleteTerreno(id_terreno);
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Metodo usato per visualizzare la lista di terreni di una azienda.
     * @param id_azienda
     * @return List&ltTerrenoBean&gt
     */
    public ArrayList<TerrenoBean> visualizzaListaTerreni(String id_azienda) {
        ArrayList<TerrenoBean> list = new ArrayList<>();
            td.retrieveAll().stream().filter(o -> o.getAzienda().equals(id_azienda)).forEach(o -> list.add(o));
        return list;
    }
}
