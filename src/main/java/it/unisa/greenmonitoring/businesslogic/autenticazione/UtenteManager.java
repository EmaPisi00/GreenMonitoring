package it.unisa.greenmonitoring.businesslogic.autenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;


import java.sql.SQLException;
import java.util.List;

public class UtenteManager {
    /**
     * Verifica le credenziali dell'utente e restituisce il tipo di utente (azienda o dipendente) se le credenziali sono corrette, altrimenti restituisce null.
     * @param email email dell'utente
     * @return il tipo di utente se le credenziali sono corrette, altrimenti null
     * @throws SQLException
     */
    public String CheckData(String email) throws SQLException {
        AziendaDAOImpl aziendaDao = new AziendaDAOImpl();
        List<AziendaBean> listAziende = aziendaDao.retrieveAll();
        for (AziendaBean azienda : listAziende) {
            if (azienda.getEmail().matches(email)) {
                return "azienda";
            }
        }

        DipendenteDAOImpl dipendenteDao = new DipendenteDAOImpl();
        List<DipendenteBean> listDipendenti = dipendenteDao.retrieveAll();
        for (DipendenteBean dipendente : listDipendenti) {
            if (dipendente.getEmail().matches(email)) {
                return "dipendente";
            }
        }

        return "null";
    }

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
            DipendenteDAO d = new DipendenteDAOImpl();
            d.delete(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
