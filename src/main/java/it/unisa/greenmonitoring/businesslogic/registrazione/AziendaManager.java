package it.unisa.greenmonitoring.businesslogic.registrazione;

import it.unisa.greenmonitoring.dataccess.beans.AziendaBean;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAO;
import it.unisa.greenmonitoring.dataccess.dao.AziendaDAOImpl;

import java.sql.SQLException;
import java.util.ListIterator;

public class AziendaManager {

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

        ListIterator<AziendaBean> listaAziende = ad.retrieveAll().listIterator();

        if (listaAziende.hasNext()) {
            AziendaBean ab = listaAziende.next();
             if ((ab.getNome_azienda() == aziendaBean.getNome_azienda()) || (ab.getEmail() == aziendaBean.getEmail()) || (ab.getPartita_iva() == aziendaBean.getPartita_iva())) {
                    System.out.println("Un'azienda con gli stessi campi è già presente nel database");
             } else {
                 ad.create(aziendaBean);
             }
        }



    }
}
