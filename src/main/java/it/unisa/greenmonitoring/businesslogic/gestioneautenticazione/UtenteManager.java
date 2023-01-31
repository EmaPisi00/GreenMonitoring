package it.unisa.greenmonitoring.businesslogic.gestioneautenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAO;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;

import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;


import java.sql.SQLException;
import java.util.List;

public class UtenteManager {
    /**
     * DipendenteDao.
     */
    private DipendenteDAO dipendenteDAO;
    /**
     * AziendaDao.
     */
    private AziendaDAO aziendaDAO;

    /**
     * Costruttore di UtenteManager.
     */
    public UtenteManager() {
        this.dipendenteDAO = new DipendenteDAOImpl();
        this.aziendaDAO = new AziendaDAOImpl();
    }

    /**
     * Metodo che permette la rimozione di una associazione ad un'azienda.
     * @param email
     * @return dipendente
     * @throws SQLException
     */
    public List<DipendenteBean> retrieveAll(String email) throws SQLException {
        return null;
    }
    /**
     * Metodo che permette l'associazione di un dipendente ad un'azienda.
     * @param user
     * @param codiceAzienda
     * @return true/false
     */
    public boolean associazioneDipendente(DipendenteBean user, String codiceAzienda) throws SQLException {
        AziendaBean azienda;
        try {
            azienda = aziendaDAO.retrieveByCode(codiceAzienda);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (azienda != null) {
            user.setAzienda(azienda.getEmail());
            try {
                dipendenteDAO.doUpdate(user);
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * Metodo che permette la cancellazione dell'associazione di un dipendente ad un'azienda.
     * @param id
     */
    public void rimuoviAssociazioneDipendente(String id) throws SQLException {
        DipendenteBean user = (DipendenteBean) dipendenteDAO.doRetrieve(id);
        user.setAzienda(null);
        dipendenteDAO.doUpdate(user);
    }

    /**
     * Metodo che permette di aggiornare un dipendente.
     * @param utenteNuovo
     * @param vecchioUtente
     * @throws SQLException
     */
    public void controllaDatiAggiorna(UtenteBean utenteNuovo, UtenteBean vecchioUtente) throws SQLException {

        if (utenteNuovo instanceof AziendaBean) {
            AziendaDAOImpl aziendaDao = new AziendaDAOImpl();
            if (((AziendaBean) utenteNuovo).compareTo((AziendaBean) vecchioUtente) != 0) {
                aziendaDao.update((AziendaBean) utenteNuovo, vecchioUtente.getEmail());
            }

        } else if (utenteNuovo instanceof DipendenteBean) {
            DipendenteDAOImpl dipendenteDao = new DipendenteDAOImpl();
            if (((DipendenteBean) utenteNuovo).compareTo((DipendenteBean) vecchioUtente) != 0) {
                System.out.println("ci sono modifiche da aggiornare");
                dipendenteDao.update((DipendenteBean) utenteNuovo, vecchioUtente.getEmail());
            }

        }
    }
}


