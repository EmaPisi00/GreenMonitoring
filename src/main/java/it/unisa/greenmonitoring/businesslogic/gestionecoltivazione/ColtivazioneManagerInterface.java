package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.SQLException;

public interface ColtivazioneManagerInterface {
    TerrenoBean createTerreno(TerrenoBean t, String email) throws SQLException;
    TerrenoBean retrieveTerreno(String id_terreno) throws SQLException;
    void updateTerreno(String id_terreno) throws SQLException;
    void deleteTerreno(String id_terreno) throws SQLException;
}
