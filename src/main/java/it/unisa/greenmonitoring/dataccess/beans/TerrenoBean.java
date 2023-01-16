package it.unisa.greenmonitoring.dataccess.beans;

public class TerrenoBean {
    /**
     * This field contains the id.
     */
    private String id;
    /**
     * This field contains latitudine.
     */
    private Float  latitudine;
    /**
     * This field contains longitudine.
     */
    private Float  longitudine;
    /**
     * This field contains the type of surface.
     */
    private String superficie;
    /**
     * This field contains the link to the image.
     */
    private String immagine;
    /**
     * This field contains the azienda foreign key.
     */
    private String azienda;

    /**
     * This method creates a TerrenoBean from the following parameters.
     * @param given_id
     * @param given_latitudine
     * @param given_longitudine
     * @param given_superficie
     * @param given_immagine
     * @param given_azienda
     */
    public TerrenoBean(String given_id, Float given_latitudine, Float given_longitudine, String given_superficie, String given_immagine, String given_azienda) {
        this.id = given_id;
        this.latitudine = given_latitudine;
        this.longitudine = given_longitudine;
        this.superficie = given_superficie;
        this.immagine = given_immagine;
        this.azienda = given_azienda;
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
     * @return Float
     */
    public Float getLatitudine() {
        return latitudine;
    }

    /**
     *
     * @param given_latitudine
     */
    public void setLatitudine(Float given_latitudine) {
        this.latitudine = given_latitudine;
    }

    /**
     *
     * @return Float
     */
    public Float getLongitudine() {
        return longitudine;
    }

    /**
     *
     * @param given_longitudine
     */
    public void setLongitudine(Float given_longitudine) {
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
    /**
     *
     * @param given_azienda
     */
    public void setAzienda(String given_azienda) {
        this.azienda = given_azienda;
    }
    /**
     *
     * @return String
     */
    public String getAzienda() {
        return azienda;
    }
}
