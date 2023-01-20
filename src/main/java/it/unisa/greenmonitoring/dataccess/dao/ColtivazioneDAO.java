package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ColtivazioneDAO {
    /**
     * Questo metodo crea un nuovo record nella tabella coltivazione.
     * @param t
     * @throws SQLException
     */
    void createcoltivazione(ColtivazioneBean t) throws SQLException;

    /**
     * Questo metodo restituisce ogni coltivazione del DB.
     * @return ColtivazioneBean
     * @throws SQLException
     */
    ArrayList<ColtivazioneBean> retrievecoltivazione() throws SQLException;

    /**
     * Questo metodo aggiorna un record nella tabella coltivazione.
     * @param id_coltivazione
     * @throws SQLException
     */
    void updatecoltivazione(String id_coltivazione) throws SQLException;

    /**
     * Questo metodo distrugge un record nella tabella coltivazione.
     * @param id_coltivazione
     * @throws SQLException
     */
    void deletecoltivazione(String id_coltivazione) throws SQLException;
}

