package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.FisiopatieBean;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementazione fisiopatie.
 */
public interface FisiopatieDAO {
    /**
     * Metodo retrieve all per ottenere tutte le fisiopatie dal db.
     * @throws SQLException
     * @return FisiopatieBean
     */
    ArrayList<FisiopatieBean> retrieveAll() throws SQLException;
}
