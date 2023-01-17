package it.unisa.greenmonitoring.businesslogic.autenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class LoginManager {

    /**
     * Verifica le credenziali dell'utente e restituisce il tipo di utente (azienda o dipendente) se le credenziali sono corrette, altrimenti restituisce null.
     * @param email email dell'utente
     * @param password password dell'utente
     * @return il tipo di utente se le credenziali sono corrette, altrimenti null
     * @throws SQLException
     */
    public String CheckData(String email, String password) throws SQLException {
        AziendaDAOImpl aziendaDao = new AziendaDAOImpl();
        List<AziendaBean> listAziende = aziendaDao.retrieveAll();
        for (AziendaBean azienda : listAziende) {
            if (azienda.getEmail().matches(email)) {
                if (azienda.getPassword().matches(password)) {
                    return "azienda";
                }
            }
        }
        DipendenteDAOImpl dipendenteDao = new DipendenteDAOImpl();
        List<DipendenteBean> listDipendenti = dipendenteDao.retrieveAll();
        for (DipendenteBean dipendente : listDipendenti) {
            if (dipendente.getEmail().matches(email)) {
                if (dipendente.getPassword().matches(password)) {
                    return "dipendente";
                }
            }
        }
        return "null";
    }
}
