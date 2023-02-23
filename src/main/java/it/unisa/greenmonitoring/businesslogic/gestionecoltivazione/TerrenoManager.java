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
     * Terrenodao.
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
     * Inserisce un terreno.
     * @param t
     * @throws SQLException
     * @return TerrenoBean
     */
    public TerrenoBean inserisciTerreno(TerrenoBean t)  {

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
    //visualizzaTerreno
    public TerrenoBean restituisciTerrenoDaInt(int id_terreno) {
            return td.retrieveByKey(id_terreno);
    }


    /**
     * Metodo usato per rimuovere un terreno.
     * @param id_terreno
     * @param idAzienda
     * @pre t ha un id che esiste nel database.
     * @post la relazione tra t e la coltivazione nel database non esiste più.
     * @return true se e solo se la rimozione avviene con successo.
     */
    public boolean rimuoviTerreno(int id_terreno, String idAzienda) {
        try {
            if (coltivazioneManager.visualizzaStatoColtivazioni(idAzienda).stream().anyMatch(o -> o.getTerreno() == id_terreno)) {
                return false;
            } else {
                td.deleteTerreno(id_terreno);
                return true;
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
