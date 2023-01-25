package it.unisa.greenmonitoring.businesslogic.gestionecoltivazione;

import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;

import java.util.ArrayList;

public class MisurazioneSensoreManager {
    public class MediaSensori {
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
    }
    /**
     * Questo metodo calcola la media per ogni tipo di sensore.
     * @param msbList
     * @return ms
     */
    public MediaSensori visualizzaMediaSensori(ArrayList<MisurazioneSensoreBean> msbList) {
        MediaSensori ms = new MediaSensori();
        for (int i = 0; i < msbList.size(); i++) {
            MisurazioneSensoreBean msb = msbList.get(i);
            if (msb.getTipo().equals("pH")) {
                ms.setMediaPH(ms.getMediaPH() + msb.getValore());
            } else if (msb.getTipo().equals("temperatura")) {
                ms.setMediaPH(ms.getMediaTEMP() + msb.getValore());
            } else if (msb.getTipo().equals("umidità")) {
                ms.setMediaUMI(ms.getMediaUMI() + msb.getValore());
            }
        }
        return ms;
    }
}
