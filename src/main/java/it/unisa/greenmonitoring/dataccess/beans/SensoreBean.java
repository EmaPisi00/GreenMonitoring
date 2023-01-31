package it.unisa.greenmonitoring.dataccess.beans;

public class SensoreBean {
    /**
     * Variabile locale id.
     */
    private int id;

    /**
     * Variabile locale tipo.
     */
    private String tipo;

    /**
     * Variabile locale coltivazione.
     */
    private Integer coltivazione;

    /**
     * Variabile locale azienda.
     */
    private String azienda;
    /**
     * Variabile locale idM.
     */
    private String idM;
    /**
     * Costruttore SensoreBean.
     * @param given_id
     * @param given_tipo
     * @param given_coltivazione
     * @param given_azienda
     * @param given_idM
     */
    public SensoreBean(int given_id, String given_tipo, Integer given_coltivazione,
                      String given_azienda, String given_idM) {
        this.id = given_id;
        this.tipo = given_tipo;
        this.coltivazione = given_coltivazione;
        this.azienda = given_azienda;
        this.idM = given_idM;
    }

    /**
     * Quello base.
     */
    public SensoreBean() {

    }

    /**
     * Get di id.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set di email.
     *
     * @param given_id
     */
    public void setId(int given_id) {
        this.id = given_id;
    }

    /**
     * Get di Tipo.
     *
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Set di tipo.
     *
     * @param given_tipo
     */
    public void setTipo(String given_tipo) {
        this.tipo = given_tipo;
    }

    /**
     * Get di coltivazione.
     *
     * @return coltivazione
     */
    public Integer getColtivazione() {
        return coltivazione;
    }

    /**
     * Set di coltivazione.
     *
     * @param given_coltivazione
     */
    public void setColtivazione(Integer given_coltivazione) {
        this.coltivazione = given_coltivazione;
    }

    /**
     * Get di azienda.
     *
     * @return azienda
     */
    public String getAzienda() {
        return azienda;
    }

    /**
     * Set di azienda.
     *
     * @param given_azienda
     */
    public void setAzienda(String given_azienda) {
        this.azienda = given_azienda;
    }
    /**
     * Get di idM.
     *
     * @return idM
     */
    public String getIdM() {
        return idM;
    }

    /**
     * Set di idM.
     *
     * @param given_idM
     */
    public void setIdM(String given_idM) {
        this.idM = given_idM;
    }
}
