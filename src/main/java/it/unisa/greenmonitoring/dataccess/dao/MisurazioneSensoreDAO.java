package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MisurazioneSensoreDAO {
    /**
     * Questo metodo crea un'istanza sul db di Misurazioni.
     * @param msb
     * @param cb
     * @param sb
     * @return MisurazioneSensoreBean
     * @throws SQLException
     */
    MisurazioneSensoreBean createMisurazione(MisurazioneSensoreBean msb, ColtivazioneBean cb, SensoreBean sb) throws SQLException, Exception;
    /**
     * Questo metodo restituisce un'istanza di Misurazioni sulla base dell'id di una coltivazione.
     * @param id
     * @return MisurazioneSensoreBean
     * @throws SQLException
     */
    ArrayList<MisurazioneSensoreBean> retreive(String id) throws SQLException;

    /**
     * Questo metodo restituisce le misurazioni più recenti a partire da un'azienda.
     * @param tipo
     * @param id_coltivazione
     * @return List&ltMisurazioneSensoreBean&gt l con l.size() > 0 se e solo se ci sono record nel db.
     * @throws SQLException
     */
    Double retrieveMostRecentMesurement(String tipo, Integer id_coltivazione) throws SQLException;
    /**
     * Questo metodo restituisce le misurazioni effettuate in un certo intervallo.
     * @param data_inizio
     * @param data_fine
     * @param id_coltivazione
     * @return List&ltMisurazioneSensoreBean&gt l con l.size() > 0 se e solo se ci sono record nel db.
     * @throws SQLException
     */
    List<MisurazioneSensoreBean> retrieveMeasurementPerTimeInterval(java.sql.Date data_inizio, java.sql.Date data_fine, Integer id_coltivazione, String tipo) throws SQLException;
    /**
     *
     * @param msb
     * @param sensore
     * @return MisurazioneSensoreBean
     */
    MisurazioneSensoreBean createMisurazioneManuel(MisurazioneSensoreBean msb, SensoreBean sensore);


}
