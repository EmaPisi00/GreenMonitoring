package it.unisa.greenmonitoring.businesslogic.gestioneautenticazione.GeneraCodice;

import java.util.Random;

/**
 * Genera codice Random.
 */
public class GeneraCodiceRandom {

    /**
     * Variabile private String caratteri.
     */
    private String[] Caratteri = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "z", "y",
                "j", "k", "x", "w", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z", "Y",
                "J", "K", "X", "W"};

    /**
     * Variabile private string[] numeri.
     */
    private String[] Numeri = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

    /**
     * Variabile private Random.
     */
    private Random rand = new Random();

    /**
     * Metodo GeneraCodice.
     * @return String
     */
        public String GeneraCodice() {

            // ottengo la lunghezza di ogni array
            int lunghezzaCaratteri = Caratteri.length;
            int lunghezzaNumeri = Numeri.length;

            // istanzio la variabile che conterrà il prodotto finale
            String stringaRandom = "";

            while (stringaRandom.length() < 6) {

                // ottengo un elemento casuale per ogni array
                int c = rand.nextInt(lunghezzaCaratteri);
                int n = rand.nextInt(lunghezzaNumeri);

                // aggiungo una lettera casuale
                stringaRandom += Caratteri[c];
                // aggiungo un numero random
                stringaRandom += Numeri[n];

            }

            // restituisco la stringa generata
            return stringaRandom;
        }

}

