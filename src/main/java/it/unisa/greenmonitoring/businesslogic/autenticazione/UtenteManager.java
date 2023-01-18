package it.unisa.greenmonitoring.businesslogic.autenticazione;

import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;


import java.sql.SQLException;
import java.util.List;

public class UtenteManager {

    /**
     * Questo metodo restituisce un da un id.
     * @param email
     * @return List<DipendenteBean>
     * @throws SQLException
     */
    public List<DipendenteBean> retrieveAll(String email) throws SQLException {
        return null;
    }
    /**
     * Metodo che permette di eliminare un dipendente.
     * @param email
     * @throws SQLException
     */
    public void delete(String email) throws SQLException {
        try {
            DipendenteDAO dipendente = new DipendenteDAOImpl();
            dipendente.delete(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
