package it.unisa.greenmonitoring.businesslogic.autenticazione;


import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;


import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;




import java.sql.SQLException;
import java.util.List;

public class UtenteManager {
    /**
     * DipendenteDao.
     */
    private DipendenteDAO dipendenteDAO;

    /**
     * Metodo che permette la rimozione di una associazione ad un'azienda.
     * @param email
     * @return dipendente
     * @throws SQLException
     */
    public boolean rimuoviDipendente(String email) throws SQLException {
        DipendenteBean dipendente = (DipendenteBean) dipendenteDAO.retrieveAllForKey(email);
        if (dipendente != null) {
            dipendenteDAO.updateAziendaToNull(email);
            return true;
        }
        return false;
    }
}


