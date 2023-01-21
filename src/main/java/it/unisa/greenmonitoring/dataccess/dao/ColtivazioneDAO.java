package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ColtivazioneDAO {
    /**
     * Questo metodo crea un nuovo record nella tabella coltivazione.
     * @param t
     * @throws SQLException
     * @return ColtivazioneBean
     */
    ColtivazioneBean createColtivazione(ColtivazioneBean t) throws SQLException;

    /**
     * Questo metodo restituisce ogni coltivazione del DB a partire da una azienda.
     * @param azienda
     * @return ColtivazioneBean
     * @throws SQLException
     */
    ArrayList<ColtivazioneBean> retrieveColtivazione(String azienda) throws SQLException;

    /**
     * Questo metodo aggiorna un record nella tabella coltivazione.
     * @param id_coltivazione
     * @throws SQLException
     */
    void updateColtivazione(String id_coltivazione) throws SQLException;

    /**
     * Questo metodo distrugge un record nella tabella coltivazione.
     * @param id_coltivazione
     * @throws SQLException
     */
    void deleteColtivazione(String id_coltivazione) throws SQLException;
}

