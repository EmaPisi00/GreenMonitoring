package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.SQLException;

public class ColtivazioneManager implements ColtivazioneManagerInterface {

    @Override
    public TerrenoBean createTerreno(TerrenoBean t, String email) throws SQLException {
        if(!(t.getSuperficie().matches("[a-zA-Z]]"))){

        }
        else {
            System.out.println("la superfice non può contenere lettere, ma solo numeri");
        }
        /*devo ancora implementare altri controlli
         */
        return null;
    }

    @Override
    public TerrenoBean retrieveTerreno(String id_terreno) throws SQLException {
        return null;
    }

    @Override
    public void updateTerreno(String id_terreno) throws SQLException {

    }

    @Override
    public void deleteTerreno(String id_terreno) throws SQLException {

    }
}
