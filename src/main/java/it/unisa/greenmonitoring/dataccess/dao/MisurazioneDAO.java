package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;

import java.sql.SQLException;
import java.util.List;

public interface MisurazioneDAO {
    /**
     * Questo metodo restituisce le misurazioni più recenti a partire da un'azienda.
     * @param id_azienda
     * @return List&ltMisurazioneSensoreBean&gt l con l.size() > 0 se e solo se ci sono record nel db.
     * @throws SQLException
     */
    List<MisurazioneSensoreBean> restituisciMisurazioniRecenti(String id_azienda) throws SQLException;
}
