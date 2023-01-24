package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;

import java.sql.SQLException;
import java.util.ArrayList;

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

}
