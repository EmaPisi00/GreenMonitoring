package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;
import it.unisa.greenmonitoring.dataccess.dao.TerrenoDAO;
import it.unisa.greenmonitoring.dataccess.dao.TerrenoDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TerrenoManager {

    /**
     * terrenodao.
     */
    private TerrenoDAO td;
    /**
     * inserisce un terreno.
     * @param t
     * @throws SQLException
     * @return List
     */
    public TerrenoBean createTerreno(TerrenoBean t) throws SQLException {
         td = new TerrenoDAOImpl();
        TerrenoBean errore = new TerrenoBean(0F, 0F, "a", "a", "a");
        errore.setId(0);
        int numeroErrori = 0;
        System.out.println("vov" + t);
        if (!(t.getSuperficie().matches("^[0-9]+$"))) {
            System.out.println("errore nella superfice");
            errore.setSuperficie(null);
            numeroErrori++;
        }

        if (t.getLatitudine() < 0 || t.getLatitudine() > 90) {
            System.out.println("errore: latitudine minore di 0 ");
            System.out.println("errore: latitudine maggiore di 90");
            errore.setLatitudine(null);
            numeroErrori++;
        }
        if (t.getLongitudine() < 0 || t.getLongitudine() > 180) {
            System.out.println("errore: longitudine minore di 0 ");
            System.out.println("errore: longitudine maggiore di 180");
            errore.setLongitudine(null);
            numeroErrori++;
        }
        List<TerrenoBean> listaterreni = td.retrieveTerreno();
        for (TerrenoBean tt : listaterreni) {
            System.out.println(tt.getId());
            if (t.getLongitudine().compareTo(tt.getLongitudine()) == 0 && t.getLatitudine().compareTo(tt.getLatitudine()) == 0) {
                System.out.println(tt.getLongitudine());
                System.out.println(t.getLongitudine());
                System.out.println("esiste già un terreno in questa posizione");
                errore.setId(null);
                numeroErrori++;
                break;
            }
        }
        System.out.println(errore);
        System.out.println(numeroErrori);
        if (numeroErrori == 0) {
            td.createTerreno(t);
            return null;
        }
            return errore;

    }





    /**
     * Questo metodo restituisce un terreno a partire da un id.
     * @param id_terreno
     * @return TerrenoBean
     * @throws SQLException
     */
    public TerrenoBean retrieveTerreno(String id_terreno) throws SQLException {
        return null;
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
     */
    public void rimuoviTerreno(int id_terreno) {
        try {
             td = new TerrenoDAOImpl();
            td.deleteTerreno(id_terreno);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo usato per visualizzare la lista di terreni di una azienda.
     * @param id_azienda
     * @return List&ltTerrenoBean&gt
     */
    public ArrayList<TerrenoBean> visualizzaListaTerreni(String id_azienda) {
        ArrayList<TerrenoBean> list = new ArrayList<>();
        try {
            td = new TerrenoDAOImpl();
            td.retrieveTerreno().stream().filter(o -> o.getAzienda().equals(id_azienda)).forEach(o -> list.add(o));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
