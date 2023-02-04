package it.unisa.greenmonitoring.businesslogic.gestioneautenticazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.beans.DipendenteBean;
import it.unisa.greenmonitoring.dataccess.beans.UtenteBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAO;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAO;
import it.unisa.greenmonitoring.dataccess.dao.DipendenteDAOImpl;

import java.sql.SQLException;
import java.util.ListIterator;

public class AutenticazioneManager {
    /**
     * Oggetto di tipo AziendaDAOImpl.
     */
    private AziendaDAO aziendaDao;
    /**
     * Oggetto di tipo DipendenteDAOImpl.
     */
    private DipendenteDAO dipendenteDao;

    /**
     * Costruttore di AutenticazioneManager.
     */
    public AutenticazioneManager() {
        this.aziendaDao = new AziendaDAOImpl();
        this.dipendenteDao = new DipendenteDAOImpl();
    }

    /**
     * Metodo registraAzienda che attraverso i controlli sugli inserimenti e il richiamo dei metodi implementati nel DAO inserisce una nuova azienda nel DB.
     * @param aziendaBean
     * @return aziendaBean
     * @throws SQLException
     */

    public AziendaBean registraAzienda(AziendaBean aziendaBean) throws SQLException {

        if (!(aziendaBean.getNome_azienda().matches("^[a-zA-Z]+$"))) {
            System.out.println("\nErrore nel nome dell'azienda\n");
            return null;
        }
        if (!(aziendaBean.getCitta().matches("^[a-zA-Z]+$"))) {
            System.out.println("\nErrore nel nome della città\n");
            return null;
        }
        if (!(aziendaBean.getProvincia().matches("^[a-zA-Z]+$"))) {
            System.out.println("\nErrore nel nome della Provincia\n");
            return null;
        }
        if (!(aziendaBean.getNome_azienda().matches("^[a-zA-Z0-9!@#$%^&*]+$"))) {
            /*Inserisci una password di lunghezza max 15 caratteri e minimo 8 con almeno un carattere speciale,un numero e una maiuscola*/
            System.out.println("\nErrore nella password \n");
            return null;
        }
        if ((aziendaBean.getPartita_iva().matches("^[0-11]+$"))) {
            System.out.println("\nErrore nella partita iva\n");
            return null;
        }

        AziendaBean ricercaAzienda = aziendaDao.retrieveForKey(aziendaBean.getEmail());

        if (ricercaAzienda.getEmail() != null) {

            System.out.println("Errore");
            return null;
        } else {

            aziendaDao.create(aziendaBean);
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


        if (!(dipendenteBean.getCitta().matches("^[a-zA-Z]+$"))) {
            System.out.println("\nErrore nel nome della città\n");
            return null;
        }
        if (!(dipendenteBean.getProvincia().matches("^[a-zA-Z]+$"))) {
            System.out.println("\nErrore nel nome della Provincia\n");
            return null;
        }
        if (!(dipendenteBean.getPassword().matches("^[a-zA-Z0-9!@#$%^&*]+$"))) {
            System.out.println("\nErrore nella password\n");
            return null;
        }
        if (!(dipendenteBean.getNome().matches("^[a-zA-Z]+$"))) {
            System.out.println("\nErrore nel nome");
            return null;
        }
        if (!(dipendenteBean.getCognome().matches("^[a-zA-Z]+$"))) {
            System.out.println("\nErrore nel nome");
            return null;
        }




        ListIterator<DipendenteBean> listaDipendenti = dipendenteDao.retrieveAll().listIterator();

        if (listaDipendenti.hasNext()) {
            DipendenteBean bean = listaDipendenti.next();
            if ((bean.getNome().equals(dipendenteBean.getNome())) && (bean.getCognome().equals(dipendenteBean.getCognome())) && (bean.getAzienda().equals(dipendenteBean.getAzienda()))) {
                System.out.println("\nDipendente già presente\n");

            }
            return null;
        } else {
            dipendenteDao.create(dipendenteBean);
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
    public UtenteBean Login(String email, String password) throws SQLException {
        AziendaBean azienda = aziendaDao.retrieveForKey(email);
        String pass = azienda.getPassword();
        if (pass != null && pass.matches(password)) {
            return azienda;
        }
        DipendenteBean dipendente = dipendenteDao.doRetrieve(email);
        pass = dipendente.getPassword();
        if (pass != null && pass.matches(password)) {
            return dipendente;
        }
        return null;
    }
}
