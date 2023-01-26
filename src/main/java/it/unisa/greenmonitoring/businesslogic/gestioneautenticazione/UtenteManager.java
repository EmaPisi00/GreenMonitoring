package it.unisa.greenmonitoring.businesslogic.gestioneautenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
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
     * Metodo che permette la rimozione di una associazione ad un'azienda.
     * @param email
     * @return dipendente
     * @throws SQLException
     */
    public List<DipendenteBean> retrieveAll(String email) throws SQLException {
        return null;
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


