package it.unisa.greenmonitoring.businesslogic.autenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAO;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;

import java.sql.SQLException;
import java.util.ListIterator;

public class AutenticazioneManager {
    /**
     * Metodo registraAzienda che attraverso i controlli sugli inserimenti e il richiamo dei metodi implementati nel DAO inserisce una nuova azienda nel DB.
     * @param aziendaBean
     * @throws SQLException
     */

    public void registraAzienda(AziendaBean aziendaBean) throws SQLException {

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
            } else {
             ad.create(aziendaBean);
             System.out.println("Inserimento fatto con successo");
        }
        }



    /**
     * Metodo che permette di inserire un nuovo dipendente nel DB.
     * @param dipendenteBean
     * @throws SQLException
     */

    public void registraDipendente(DipendenteBean dipendenteBean) throws SQLException {

        DipendenteDAO dp = new DipendenteDAOImpl();

        ListIterator<DipendenteBean> listaDipendenti = dp.retrieveAll().listIterator();

        if (listaDipendenti.hasNext()) {
            DipendenteBean bean = listaDipendenti.next();
                if ((bean.getNome().equals(dipendenteBean.getNome())) && (bean.getCognome().equals(dipendenteBean.getCognome())) && (bean.getAzienda().equals(dipendenteBean.getAzienda()))) {
                    System.out.println("\nDipendente già presente\n");
                }
        } else {
            dp.create(dipendenteBean);
            System.out.println("Inserimento fatto con successo");
        }

    }

    /**
     * Metodo che permette di effettuare il login.
     * @param email
     * @param password
     * @throws SQLException
     */
    public void login(String email, String password) throws SQLException {

    }

}
