package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.dataccess.beans.ColtivazioneBean;
import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;

import it.unisa.greenmonitoring.dataccess.dao.PiantaDAO;
import it.unisa.greenmonitoring.dataccess.dao.PiantaDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class PiantaManager {
    /**
     * contiene gli errori e la descrizione.
     */
    private  PiantaBean errori;
    /**
     * piantadao.
     */
    private PiantaDAO pd;
    /**
     * coltivazionemanager.
     */
    private ColtivazioneManager cm;
    //TO-DO implementare i metodi di pianta manager
    /**
     * @param t
     * @return List
     */
    public PiantaBean CreaPiantaManager(PiantaBean t) {
        System.out.println("_____ sono t= " + t);
        pd = new PiantaDAOImpl();
        boolean matchDefault = false;
        boolean matchAzienda = false;
        List<PiantaBean> listaPiante = pd.RetriveAllPiantaDefault();
        List<PiantaBean> listaPianteAzienda = pd.RetriveAllPiantaAzienda(t.getAzienda());
        for (PiantaBean tt : listaPiante) {
            if (tt.getNome().matches(t.getNome())) {
                matchDefault = true;
                break;
            }
        }
        //controlla se tnome esiste in azienda
        for (PiantaBean tt : listaPianteAzienda) {
            if (matchDefault) {
                System.out.println("*** t" + t.getNome().concat("(" + t.getAzienda() + ")"));
                System.out.println("*** tt" + tt.getNome());
                if (t.getNome().concat("(" + t.getAzienda() + ")").equals(tt.getNome())) {
                    return null;
                }
                //altrimenti controlla il nome normale
            } else {
                if (t.getNome().matches(tt.getNome())) {
                    return null;
                }
            }

        }
        //se il nome è uguale al default ma non alla pianta azienda, allora personalizza
        if (matchDefault) {
            t.setNome(t.getNome().concat("(" + t.getAzienda() + ")"));
        }
        System.out.println("FINE sono t= " + t);
        if (controlloDatiPiantaManager(t) == null) {
                return null;
        }
        pd.aggiungiPiantaPersonalizzata(t);
        return t;

    }

    /**
     * ritorna la lista delle piante per utente.
     * @param email
     * @return List
     */
    public List<PiantaBean> ListaPianteManager(String email) {
        pd = new PiantaDAOImpl();
        List<PiantaBean> listaPiante = pd.RetriveAllPiantaDefault();
        listaPiante.addAll(pd.RetriveAllPiantaAzienda(email));
        return (!listaPiante.isEmpty()) ? listaPiante : null;
    }
    /**
     * @param emailAzienda
     * @param id_pianta
     */
    public void rimuoviPiantaManager(int id_pianta, String emailAzienda) {
        cm = new ColtivazioneManager();
        ArrayList<ColtivazioneBean> c = cm.visualizzaColtivazioniAvviate(emailAzienda);
        for (ColtivazioneBean colti : c) {
            if (colti.getPianta() == id_pianta) {
                    System.out.println("sono uguali errore");
                    return;
            }
        }
        pd = new PiantaDAOImpl();
        pd.deletePianta(id_pianta);
        System.out.println("fatto elimitato");
    }

    /**
     *
     * @param id
     * @return PiantaBean
     */
    public PiantaBean ritornaPiantaManager(Integer id) {
        pd = new PiantaDAOImpl();
        PiantaBean pianta = pd.retrieveByKey(id);
        return pianta;
    }
    /**
     *
     * @param pianta
     * @return PiantaBean
     */
    public PiantaBean aggiornaValoriPiantaManager(PiantaBean pianta) {
        if (controlloDatiPiantaManager(pianta) == null) {
            System.out.println("errore in aggiornavaloriManager");
            return null;
        }
            pd = new PiantaDAOImpl();
            pd.updateValue(pianta);
            return pianta;
    }
    /**
     *
     * @param t
     * @return PiantaBean
     */
    public PiantaBean controlloDatiPiantaManager(PiantaBean t) {
        //errori Nome
        if ((t.getNome().length() <= 3 || t.getNome().length() >= 30)) {
            System.out.println("errore nella dimensione del nome. Deve essere compresa tra 3 e 30" + t.getNome());
            return null;
        }

        //Controllo PH
        if (t.getPh_min().matches("^[0-9]*\\.?[0-9]+$") && t.getPh_max().matches("^[0-9]*\\.?[0-9]+$")) {
            Float phMaxFloat = Float.parseFloat(t.getPh_max());
            Float phMinFloat = Float.parseFloat(t.getPh_min());
            // controlla che sia rispettato il range
            if (phMinFloat <= 1 || phMinFloat >= 14
                    || phMaxFloat <= 1 || phMaxFloat >= 14) {
                System.out.println("ph min o max minore di 1 o maggiore di 14");
                //setto ph max a null per indicare che il range di uno tra max e min è errato
                return null;

            }
            // se ph max è minore di minima
            if (phMaxFloat < phMinFloat) {
                System.out.println("errore,ph min maggiore di max");
                return null;

            }
        } else {
            System.out.println("errore: phmin e max non sono numeri" + t.getPh_min());
            //setto phmin a null se non sono numeri phmax e min
            return null;
        }

        // controllo Temperatura
        if (t.getTemperatura_min().matches("^[0-9]*\\.?[0-9]+$") && t.getTemperatura_max().matches("^[0-9]*\\.?[0-9]+$")) {
            Float TempMaxFloat = Float.parseFloat(t.getTemperatura_max());
            Float TempMinFloat = Float.parseFloat(t.getTemperatura_min());
            // controlla che sia rispettato il range
            if (TempMinFloat < 10 || TempMinFloat > 100
                    || TempMaxFloat < 10 || TempMaxFloat > 100) {
                System.out.println("temperatura min o max minore di 10 o maggiore di 100");
                //setto temperatura max a null per indicare che il range di uno tra max e min è errato
                return null;
            }
            // se temp max è minore di minima
            if (TempMaxFloat <= TempMinFloat) {
                System.out.println("errore,temp  min maggiore di max");
                return null;
            }
        } else {
            System.out.println("errore: temp min e max non sono numeri");
            //setto temp min a null se non sono numeri tempmin e min
            return null;
        }

        // controllo Umidità
        if (t.getUmidita_min().matches("^[0-9]*\\.?[0-9]+$") && t.getUmidita_max().matches("^[0-9]*\\.?[0-9]+$")) {
            Float umidMaxFloat = Float.parseFloat(t.getUmidita_max());
            Float umidMinFloat = Float.parseFloat(t.getUmidita_min());
            // controlla che sia rispettato il range
            if (umidMinFloat < 1 || umidMinFloat > 100
                    || umidMaxFloat < 1 || umidMaxFloat > 100) {
                System.out.println("umidita min o max minore di 1 o maggiore di 100");
                //setto umidita max a null per indicare che il range di uno tra max e min è errato
                return null;
            }
            // se umidita max è minore di minima
            if (umidMaxFloat <= umidMinFloat) {
                System.out.println("errore,umidita  min maggiore di max");
                return null;
            }
        } else {
            System.out.println("errore: umidita min e max non sono numeri");
            return null;
        }

        if (t.getDescrizione().length() < 1 || t.getDescrizione().length() > 255) {
            System.out.println("la lunghezza descrizione deve essere >1 e <255");
            return null;
        }
        return t;
    }

}

