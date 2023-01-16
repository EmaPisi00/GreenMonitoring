package it.unisa.greenmonitoring.dataccess.beans;

public class TerrenoBean {
    /**
     * This field contains the id.
     */
    private String id;
    /**
     * This field contains latitudine.
     */
    private String latitudine;
    /**
     * This field contains longitudine.
     */
    private String longitudine;
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
     * @param given_latitudine
     * @param given_longitudine
     * @param given_superficie
     * @param given_immagine
     */
    public TerrenoBean(String given_id, String given_latitudine, String given_longitudine, String given_superficie, String given_immagine) {
        this.id = id;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
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
    public String getLatitudine() {
        return latitudine;
    }

    /**
     *
     * @param given_latitudine
     */
    public void setLatitudine(String given_latitudine) {
        this.latitudine = given_latitudine;
    }

    /**
     *
     * @return String
     */
    public String getLongitudine() {
        return longitudine;
    }

    /**
     *
     * @param given_longitudine
     */
    public void setLongitudine(String given_longitudine) {
        this.longitudine = given_longitudine;
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
