package it.unisa.greenmonitoring.businesslogic.gestioneautenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAO;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

public class AutenticazioneManager {
    /**
     * Metodo registraAzienda che attraverso i controlli sugli inserimenti e il richiamo dei metodi implementati nel DAO inserisce una nuova azienda nel DB.
     * @param aziendaBean
     * @return aziendaBean
     * @throws SQLException
     */

    public AziendaBean registraAzienda(AziendaBean aziendaBean) throws SQLException {

        AziendaDAO ad = new AziendaDAOImpl();

        if ((aziendaBean.getNome_azienda().matches("[a-zA-Z]"))) {
            System.out.println("\nErrore nel nome dell'azienda\n");
        }
        if ((aziendaBean.getCitta().matches("[a-zA-Z]"))) {
            System.out.println("\nErrore nel nome della città\n");
        }
        if ((aziendaBean.getProvincia().matches("[a-zA-Z]"))) {
            System.out.println("\nErrore nel nome della Provincia\n");
        }
        if ((aziendaBean.getPassword().matches(" [0-9a-zA-Z]{8,30} "))) {
            System.out.println("\nErrore nella password\n");
        }
        if ((aziendaBean.getPartita_iva().matches("0-9]{11}"))) {
            System.out.println("\nErrore nella partita iva\n");
        }

       // ListIterator<AziendaBean> listaAziende = ad.retrieveAll().listIterator();

        AziendaBean ricercaAzienda = ad.retrieveForKey(aziendaBean.getEmail());

        if (ricercaAzienda.getEmail() != null) {

                System.out.println("Errore");
                return null;
            } else {

             ad.create(aziendaBean);
             System.out.println("Inserimento fatto con successo");
            return aziendaBean;
        }
        }



    /**
     * Metodo che permette di inserire un nuovo dipendente nel DB.
     * @param dipendenteBean
     * @return dipendenteBean
     * @throws SQLException
     */

    public DipendenteBean registraDipendente(DipendenteBean dipendenteBean) throws SQLException {

        DipendenteDAO dp = new DipendenteDAOImpl();

        ListIterator<DipendenteBean> listaDipendenti = dp.retrieveAll().listIterator();

        if (listaDipendenti.hasNext()) {
            DipendenteBean bean = listaDipendenti.next();
                if ((bean.getNome().equals(dipendenteBean.getNome())) && (bean.getCognome().equals(dipendenteBean.getCognome())) && (bean.getAzienda().equals(dipendenteBean.getAzienda()))) {
                    System.out.println("\nDipendente già presente\n");

                }
            return null;
        } else {
            dp.create(dipendenteBean);
            System.out.println("Inserimento fatto con successo");
            return dipendenteBean;
        }

    }

    /**
     * Verifica le credenziali dell'utente e restituisce il tipo di utente (azienda o dipendente) se le credenziali sono corrette, altrimenti restituisce null.
     * @param email email dell'utente
     * @param password password dell'utente
     * @return il tipo di utente se le credenziali sono corrette, altrimenti null
     * @throws SQLException
     */
    public String CheckData(String email, String password) throws SQLException { //chiamalo login
        AziendaDAOImpl aziendaDao = new AziendaDAOImpl();
        List<AziendaBean> listAziende = aziendaDao.retrieveAll();
        for (AziendaBean azienda : listAziende) {
            if (azienda.getEmail().matches(email)) {
                if (azienda.getPassword().matches(password)) {
                    return "azienda"; //return oggetto azienda
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
