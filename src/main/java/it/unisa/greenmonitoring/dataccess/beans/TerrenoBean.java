package it.unisa.greenmonitoring.dataccess.beans;

public class TerrenoBean {
    private String id;
    private String locazione;
    private String superficie;
    private String immagine;

    /**
     * Questo metodo crea un terreno bean a partire da i seguenti parametri.
     * @param id
     * @param locazione
     * @param superficie
     * @param immagine
     * @return un'istanza di TerrenoBean
     */
    public TerrenoBean(String id, String locazione, String superficie, String immagine) {
        this.id = id;
        this.locazione = locazione;
        this.superficie = superficie;
        this.immagine = immagine;
    }

}
