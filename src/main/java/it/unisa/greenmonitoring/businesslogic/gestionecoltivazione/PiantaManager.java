package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;

import it.unisa.greenmonitoring.dataccess.dao.PiantaDAO;
import it.unisa.greenmonitoring.dataccess.dao.PiantaDAOImpl;



import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PiantaManager {
    /**
     * contiene l'errore e la descrizione.
     */
    private HashMap<String, String> errori = new HashMap<String, String>();
    //TO-DO implementare i metodi di pianta manager
    /**
     * @param t
     * @throws SQLException
     * @return List
     */
    public HashMap<String, String> CreaPiantaManager(PiantaBean t) throws SQLException {
        PiantaDAO td = new PiantaDAOImpl();

        //errori Nome
        if ((t.getNome().length() < 3 || t.getNome().length() > 30)) {
            System.out.println("errore nella dimensione del nome. Deve essere compresa tra 3 e 30" + t.getNome());
            errori.put("erroreDimensioneNome", "errore nella dimensione del nome. Deve essere compresa tra 3 e 30");
        }

        check(t.getTemperatura_max(), t.getTemperatura_min(), 10, 100, "Temperatura");
        check(t.getPh_max(), t.getPh_min(), 1, 14, "Ph");
        check(t.getUmidita_max(), t.getUmidita_min(), 1, 100, "Umidita");
        if (t.getDescrizione().length() < 1 || t.getDescrizione().length() > 255) {
            System.out.println("la lunghezza descrizione deve essere >1 e <255");
            errori.put("erroreDescrizioneLunghezza", "la lunghezza descrizione deve essere >1 e <255");

        }

        List<PiantaBean> listaPiante = td.RetriveAllPianta();
        for (PiantaBean tt : listaPiante) {

            if (tt.getNome().matches(t.getNome())) {
                System.out.println("simili= " + tt + "\n" + t);
                    System.out.println("esiste già una pianta con questo nome ");
                    errori.put("errorePianta", "esiste già una pianta con questo nome ");
                    break;
                }
            }

        System.out.println(errori.size() + " " + t);
        if (errori.isEmpty()) {
            td.aggiungiPiantaPersonalizzata(t);
            return null;
        }
        return errori;
    }

    /**
     * @param valore_max
     * @param valore_min
     * @param range_min
     * @param range_max
     * @param testo
     */
    public void check(String valore_max, String valore_min, int range_min, int range_max, String testo) {
        //valore minima
        if (valore_min.matches("^[0-9]+$")) {
            if (Float.parseFloat(valore_min) < range_min || Float.parseFloat(valore_min) > range_max) {
                System.out.println(testo + " minima è minore di" + range_min + " o maggiore di " + valore_max);
                errori.put("errore" + testo + "MinDimensione", testo + " minima è minore di" + range_min + " o maggiore di " + valore_max);
            }
        } else {
            System.out.println("errore: non è un numero");
            errori.put("errore" + testo + "MinFormato", "errore: non è un numero");
        }
        //valore massimo
        if (valore_max.matches("^[0-9]+$")) {
            if (Float.parseFloat(valore_max) < range_min || Float.parseFloat(valore_max) > range_max) {
                System.out.println("temperatura massima è minore di 10 o maggiore di 100");
                errori.put("errore" + testo + "MaxDim", "temperatura massima è minore di 10 o maggiore di 100");
            }
        } else {
            System.out.println("errore: non è un numero");
            errori.put("errore" + testo + "MaxFormato", "errore: non è un numero");
        }
        //val_masisma minore di minima
        if ((valore_max.matches("^[0-9]+$")) && (valore_min.matches("^[0-9]+$"))) {
            Float TempMaxFloat = Float.parseFloat(valore_max);
            Float TempMinFloat = Float.parseFloat(valore_min);
            if (TempMaxFloat < TempMinFloat) {
                System.out.println("errore," + testo + " min maggiore di max");
                errori.put("errore" + testo + "MinMax", "errore," + testo + " min maggiore di max");
            }
        }
    }
}

