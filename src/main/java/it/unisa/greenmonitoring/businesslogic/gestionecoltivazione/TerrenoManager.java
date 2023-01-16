package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;
import it.unisa.greenmonitoring.dataccess.dao.TerrenoDAO;
import it.unisa.greenmonitoring.dataccess.dao.TerrenoDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class TerrenoManager {
    /**
     * TO-FILL.
     * @param t
     * @param email
     * @return TerrenoBean
     * @throws SQLException
     */
    public TerrenoBean createTerreno(TerrenoBean t, String email) throws SQLException {
        if (!(t.getSuperficie().matches("[a-zA-Z]]"))) {
            System.out.println("zzz");
        } else {
            System.out.println("la superfice non può contenere lettere, ma solo numeri");
        }
        /*devo ancora implementare altri controlli
         */
        return null;
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
     * Questo metodo cancella la riga corrispondente a id_terreno nella tabella TERRENO.
     * @param id_terreno
     * @throws SQLException
     */
    public void deleteTerreno(String id_terreno) throws SQLException {

    }

    /**
     * Metodo usato per rimuovere un terreno.
     * @param t
     * @pre t ha un id che esiste nel database.
     * @post la relazione tra t e la coltivazione nel database non esiste più.
     * @return void
     */
    public void rimuoviTerreno(TerrenoBean t){
        try {
            TerrenoDAO td = new TerrenoDAOImpl();
            td.deleteTerreno(t.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
