package it.unisa.greenmonitoring.businesslogic.gestioneautenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;

import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAO;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;

import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;


import java.sql.SQLException;

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
     * Metodo che permette l'associazione di un dipendente ad un'azienda.
     * @param user
     * @param codiceAzienda
     * @return true se e solo se l'associazione va a buon fine.
     */
    public boolean associazioneDipendente(DipendenteBean user, String codiceAzienda) throws SQLException {
        AziendaBean azienda;
        try {
            azienda = aziendaDAO.retrieveByCode(codiceAzienda);
        } catch (SQLException e) {
            return false;
        }
        if (azienda != null) {
            user.setAzienda(azienda.getEmail());
            try {
                dipendenteDAO.doUpdate(user);
                return true;
            } catch (SQLException e) {
                return false;
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
     * @return UtenteBean
     */
    public UtenteBean modificaDatiUtente(UtenteBean utenteNuovo, UtenteBean vecchioUtente) throws SQLException {

        try {
            if (utenteNuovo instanceof AziendaBean) {
                //se ci sono delle modifiche da aggiornare
                if (((AziendaBean) utenteNuovo).compareTo((AziendaBean) vecchioUtente) != 0) {
                    this.aziendaDAO.update((AziendaBean) utenteNuovo, vecchioUtente.getEmail());
                }

            } else if (utenteNuovo instanceof DipendenteBean) {
                if (((DipendenteBean) utenteNuovo).compareTo((DipendenteBean) vecchioUtente) != 0) {
                    System.out.println("ci sono modifiche da aggiornare");
                    this.dipendenteDAO.update((DipendenteBean) utenteNuovo, vecchioUtente.getEmail());
                }
            }
        } catch (Exception e) {
            return null;
        }
        return utenteNuovo;
    }
    /**
     * retrieve dati azienda basandosi sul codice di associazione.
     * @param codice_associazione
     * @return AziendaBean
     */
    public AziendaBean visualizzaAziendaPerCodiceAssociazione(String codice_associazione) throws SQLException {
        return aziendaDAO.retrieveByCode(codice_associazione);
    }
}


