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
    private int coltivazione;

    /**
     * Variabile locale azienda.
     */
    private String azienda;
    /**
     * Costruttore UtenteBean.
     * @param give_id
     * @param give_tipo
     * @param give_coltivazione
     * @param give_azienda
     */
    public SensoreBean(int give_id, String give_tipo, int give_coltivazione,
                      String give_azienda) {
        this.id = give_id;
        this.tipo = give_tipo;
        this.coltivazione = give_coltivazione;
        this.azienda = give_azienda;
    }
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
     * @param give_id
     */
    public void setId(int give_id) {
        this.id = give_id;
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
     * @param give_tipo
     */
    public void setTipo(String give_tipo) {
        this.tipo = give_tipo;
    }

    /**
     * Get di coltivazione.
     *
     * @return coltivazione
     */
    public int getColtivazione() {
        return coltivazione;
    }

    /**
     * Set di coltivazione.
     *
     * @param give_coltivazione
     */
    public void setColtivazione(int give_coltivazione) {
        this.coltivazione = give_coltivazione;
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
     * @param give_azienda
     */
    public void setAzienda(String give_azienda) {
        this.azienda = give_azienda;
    }
}
