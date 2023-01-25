package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.dao.MisurazioneSensoreDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class MisurazioneSensoreManager {
        /**
         * Contiene la media del pH.
         */
        private int mediaPH;
        /**
         * Contiene la media della temperatura.
         */
        private int mediaTEMP;
        /**
         * Contiene la media dell'umidità.
         */
        private int mediaUMI;

        /**
         * setter della media del ph.
         * @param given_mediaPH
         */
        public void setMediaPH(int given_mediaPH) {
            this.mediaPH = given_mediaPH;
        }

        /**
         * setter della media della temperatura.
         * @param given_mediaTEMP
         */
        public void setMediaTEMP(int given_mediaTEMP) {
            this.mediaTEMP = given_mediaTEMP;
        }

        /**
         * setter della media dell'umidità.
         * @param given_mediaUMI
         */
        public void setMediaUMI(int given_mediaUMI) {
            this.mediaUMI = given_mediaUMI;
        }

        /**
         * getter della media del ph.
         * @return mediaPH
         */
        public int getMediaPH() {
            return mediaPH;
        }

        /**
         * getter della media della temperatura.
         * @return mediaTEMP
         */
        public int getMediaTEMP() {
            return mediaTEMP;
        }

        /**
         * getter della media dell'umidità.
         * @return mediaUMI
         */
        public int getMediaUMI() {
            return mediaUMI;
        }
    /**
     * Questo metodo calcola la media per ogni tipo di sensore.
     * @param id_coltivazione
     * @return ms
     */
    public int[] visualizzaMediaSensori(String id_coltivazione) throws SQLException {
        MisurazioneSensoreDAOImpl msdao = new MisurazioneSensoreDAOImpl();
        ArrayList<MisurazioneSensoreBean> msbList = msdao.retreive(id_coltivazione);
        int[] media = new int[3];
        for (int i = 0; i < msbList.size(); i++) {
            MisurazioneSensoreBean msb = msbList.get(i);
            if (msb.getTipo().equals("pH")) {
                media[0] = media[0] + msb.getValore();
            } else if (msb.getTipo().equals("temperatura")) {
                media[1] = media[1] + msb.getValore();
            } else if (msb.getTipo().equals("umidità")) {
                media[2] = media[2] + msb.getValore();
            }
        }
        return media;
    }
}
