package it.unisa.greenmonitoring.businesslogic.autenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;



import java.sql.SQLException;
import java.util.List;

public class UtenteManager<Connection> {
    /**
     * DipendenteDao.
     */
    private DipendenteDAO dipendenteDAO;
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
     * Metodo che permette di rimuovere un dipendente senza eliminarlo (la rimozione del dipendente avviene
     * sostituendo il nome dell'azienda con un valore null nella tabella dipendente).
     * @throws SQLException
     */
    public UtenteManager() throws SQLException {
        this.dipendenteDAO = new DipendenteDAO() {
            /**
             * Metodo create che permette di salvare i dati nel DB.
             * @param registrazioneDipendenteBean
             * @throws SQLException
             */
            @Override
            public void create(DipendenteBean registrazioneDipendenteBean) throws SQLException {

            }

            /**
             * Metodo retrieve che permette di ricercare tutti i dati di tutti i dipendenti.
             * @return List<DipendenteBean>
             * @throws SQLException
             */
            @Override
            public List<DipendenteBean> retrieveAll() throws SQLException {
                return null;
            }

            @Override
            public List<DipendenteBean> retrieveAllForKey() throws SQLException {
                return null;
            }

            /**
             * Metodo retrieve che permette di ricercare tutte le aziende a partire da un ID.
             * @param email
             * @return List<AziendaBean>
             * @throws SQLException
             */
            @Override
            public List<DipendenteBean> retrieveForKey(String email) throws SQLException {
                return null;
            }

            /**
             * Implementazione metodo che permette la ricerca di un dipendente in base alla sua email.
             * @param email
             * @return List <DipendenteBean>
             * @throws SQLException
             */
            @Override
            public List<DipendenteBean> retrieveAllForKey(String email) throws SQLException {
                return null;
            }

            /**
             * Metodo update che permette di modificare dati già presenti nel DB.
             * @param email
             * @throws SQLException
             */
            @Override
            public void update(String email) throws SQLException {

            }

            /**
             * Metodo update che implementa un aggiornamento al DB attraverso il passaggio di un ID.
             * @param email
             * @throws SQLException
             */
            @Override
            public void updateAziendaToNull(String email) throws SQLException {

            }

            /**
             * Metodo delete che permette di eliminare un dipendente dal sistema.
             * @param email
             * @throws SQLException
             */
            @Override
            public void delete(String email) throws SQLException {

            }
        };
    }

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


