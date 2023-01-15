package it.unisa.greenmonitoring.dataccess.beans;

public class TerrenoBean {
    /**
     * Questo dato contiene l'id per il terreno.
     */
    private String id;
    /**
     * Questo dato contiene la locazione del terreno.
     */
    private String locazione;
    /**
     * Questo dato contiene la superficie del terreno.
     */
    private String superficie;
    /**
     * Questo dato contiene il link all'immagine del terreno.
     */
    private String immagine;

    /**
     * Questo metodo crea un terreno bean a partire da i seguenti parametri.
     * @param given_id
     * @param given_locazione
     * @param given_superficie
     * @param given_immagine
     */
    public TerrenoBean(String given_id, String given_locazione, String given_superficie, String given_immagine) {
        this.id = id;
        this.locazione = locazione;
        this.superficie = superficie;
        this.immagine = immagine;
    }

    /**
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param given_id
     */
    public void setId(String given_id) {
        this.id = id;
    }

    /**
     *
     * @return String
     */
    public String getLocazione() {
        return locazione;
    }

    /**
     *
     * @param given_locazione
     */
    public void setLocazione(String given_locazione) {
        this.locazione = given_locazione;
    }

    /**
     *
     * @return String
     */
    public String getSuperficie() {
        return superficie;
    }

    /**
     *
     * @param given_superficie
     */
    public void setSuperficie(String given_superficie) {
        this.superficie = given_superficie;
    }

    /**
     *
     * @return String
     */
    public String getImmagine() {
        return immagine;
    }

    /**
     *
     * @param given_immagine
     */
    public void setImmagine(String given_immagine) {
        this.immagine = given_immagine;
    }
}
