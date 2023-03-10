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
    void createColtivazione(ColtivazioneBean t) throws SQLException;

    /**
     * Questo metodo crea un nuovo record nella tabella coltivazione.
     * @param id_coltivazione
     * @throws SQLException
     * @return ColtivazioneBean
     */
    ColtivazioneBean retrieveByKey(int id_coltivazione) throws SQLException;
    /**
     * Questo metodo restituisce ogni coltivazione del DB a partire da una azienda.
     * @param id_azienda
     * @return ColtivazioneBean
     * @throws SQLException
     */
    ArrayList<ColtivazioneBean> retrieveColtivazione(String id_azienda);

    /**
     * Questo metodo aggiorna un record nella tabella coltivazione.
     * @param coltivazioneBean
     * @throws SQLException
     */
    void updateColtivazione(ColtivazioneBean coltivazioneBean) throws SQLException;

    /**
     * Questo metodo distrugge un record nella tabella coltivazione.
     * @param id_coltivazione
     * @throws SQLException
     */
    void deleteColtivazione(String id_coltivazione) throws SQLException;
}

