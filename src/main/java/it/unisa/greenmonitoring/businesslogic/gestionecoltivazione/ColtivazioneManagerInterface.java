package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.TerrenoBean;

import java.sql.SQLException;

public interface ColtivazioneManagerInterface {
    /**
     * Metodo per la crezione di un terreno.
     * @param t
     * @param email
     * @throws SQLException
     * @return TerrenoBean
     */
    TerrenoBean createTerreno(TerrenoBean t, String email) throws SQLException;

    /**
     *Metodo per il retrieve di un terreno.
     * @param id_terreno
     * @throws SQLException
     * @return TerrenoBean
     */
    TerrenoBean retrieveTerreno(String id_terreno) throws SQLException;

    /**
     * Metodo per l'update di un terreno.
     * @param id_terreno
     * @throws SQLException
     */
    void updateTerreno(String id_terreno) throws SQLException;

    /**
     * Metodo per il delete di un terreno.
     * @param id_terreno
     * @throws SQLException
     */
    void deleteTerreno(String id_terreno) throws SQLException;
}
