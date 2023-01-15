package it.unisa.greenmonitoring.dataccess.beans;

public class TerrenoBean {
    /**
     * This field contains the id.
     */
    private String id;
    /**
     * This field contains the type of location.
     */
    private String locazione;
    /**
     * This field contains the type of surface.
     */
    private String superficie;
    /**
     * This field contains the link to the image.
     */
    private String immagine;

    /**
     * This meth0d creates a TerrenoBean from the following parameters.
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
