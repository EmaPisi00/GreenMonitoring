package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;

import it.unisa.greenmonitoring.dataccess.dao.PiantaDAO;
import it.unisa.greenmonitoring.dataccess.dao.PiantaDAOImpl;



import java.sql.SQLException;
import java.util.List;

public class PiantaManager {
    /**
     * contiene gli errori e la descrizione.
     */
    private  PiantaBean errori;
    /**
     * piantadao.
     */
    private PiantaDAO td;
    //TO-DO implementare i metodi di pianta manager
    /**
     * @param t
     * @throws SQLException
     * @return List
     */
    public PiantaBean CreaPiantaManager(PiantaBean t) throws SQLException {
        td = new PiantaDAOImpl();
        errori = new PiantaBean("a", "a", "a", "a", "a", "a", "a");
        errori.setId(1);
        errori.setUmidita_min("a");
        errori.setUmidita_max("a");
        errori.setImmagine("a");
        int countErrori = 0;

        //errori Nome
        if ((t.getNome().length() < 3 || t.getNome().length() > 30)) {
            System.out.println("errore nella dimensione del nome. Deve essere compresa tra 3 e 30" + t.getNome());
            errori.setNome(null);
            countErrori++;
        }

        //Controllo PH
        if (t.getPh_min().matches("^[0-9]+$") && t.getPh_max().matches("^[0-9]+$")) {
            Float phMaxFloat = Float.parseFloat(t.getPh_max());
            Float phMinFloat = Float.parseFloat(t.getPh_min());
            // controlla che sia rispettato il range
            if (phMinFloat < 1 || phMinFloat > 14
                    || phMaxFloat < 1 || phMaxFloat > 14) {
                System.out.println("ph min o max minore di 1 o maggiore di 14");
                //setto ph max a null per indicare che il range di uno tra max e min è errato
                errori.setPh_max(null);
                countErrori++;
            }
            // se ph max è minore di minima
            if (phMaxFloat < phMinFloat) {
                System.out.println("errore,ph min maggiore di max");
                errori.setPh_max(null);
                countErrori++;
            }
        } else {
            System.out.println("errore: phmin e max non sono numeri");
            //setto phmin a null se non sono numeri phmax e min
            errori.setPh_min(null);
            countErrori++;
        }

        // controllo Temperatura
        if (t.getTemperatura_min().matches("^[0-9]+$") && t.getTemperatura_max().matches("^[0-9]+$")) {
            Float TempMaxFloat = Float.parseFloat(t.getTemperatura_max());
            Float TempMinFloat = Float.parseFloat(t.getTemperatura_min());
            // controlla che sia rispettato il range
            if (TempMinFloat < 10 || TempMinFloat > 100
                    || TempMaxFloat < 10 || TempMaxFloat > 100) {
                System.out.println("temperatura min o max minore di 10 o maggiore di 100");
                //setto temperatura max a null per indicare che il range di uno tra max e min è errato
                errori.setTemperatura_max(null);
                countErrori++;
            }
            // se temp max è minore di minima
            if (TempMaxFloat < TempMinFloat) {
                System.out.println("errore,temp  min maggiore di max");
                errori.setTemperatura_max(null);
                countErrori++;
            }
        } else {
            System.out.println("errore: temp min e max non sono numeri");
            //setto temp min a null se non sono numeri tempmin e min
            errori.setTemperatura_min(null);
            countErrori++;
        }

        // controllo Umidità
        if (t.getUmidita_min().matches("^[0-9]+$") && t.getUmidita_max().matches("^[0-9]+$")) {
            Float umidMaxFloat = Float.parseFloat(t.getUmidita_max());
            Float umidMinFloat = Float.parseFloat(t.getUmidita_min());
            // controlla che sia rispettato il range
            if (umidMinFloat < 1 || umidMinFloat > 100
                    || umidMaxFloat < 1 || umidMaxFloat > 100) {
                System.out.println("umidita min o max minore di 1 o maggiore di 100");
                //setto umidita max a null per indicare che il range di uno tra max e min è errato
                errori.setUmidita_max(null);
                countErrori++;
            }
            // se umidita max è minore di minima
            if (umidMaxFloat < umidMinFloat) {
                System.out.println("errore,umidita  min maggiore di max");
                errori.setUmidita_max(null);
                countErrori++;
            }
        } else {
            System.out.println("errore: umidita min e max non sono numeri");
            //setto umidita min a null se non sono numeri umidita min e min
            errori.setUmidita_min(null);
            countErrori++;
        }


        if (t.getDescrizione().length() < 1 || t.getDescrizione().length() > 255) {
            System.out.println("la lunghezza descrizione deve essere >1 e <255");
            errori.setDescrizione(null);
            countErrori++;

        }

        List<PiantaBean> listaPiante = td.RetriveAllPiantaDefault();
        listaPiante.addAll(td.RetriveAllPiantaAzienda(t.getAzienda()));
        for (PiantaBean tt : listaPiante) {

            if (tt.getNome().matches(t.getNome())) {
                System.out.println("simili= " + tt + "\n" + t);
                    System.out.println("esiste già una pianta con questo nome ");
                    errori.setId(null);
                    countErrori++;
                    break;
                }
            }

        System.out.println("count = " + countErrori + " pianta " + errori);
        if (countErrori++ == 0) {
            td.aggiungiPiantaPersonalizzata(t);
            return null;
        }
        return errori;
    }

    /**
     * ritorna la lista delle piante per utente.
     * @param email
     * @return List
     * @throws SQLException
     */
    public List<PiantaBean> ListaPianteManager(String email) throws SQLException {
        td = new PiantaDAOImpl();
        List<PiantaBean> listaPiante = td.RetriveAllPiantaDefault();
        listaPiante.addAll(td.RetriveAllPiantaAzienda(email));
        return (!listaPiante.isEmpty()) ? listaPiante : null;
    }

}

