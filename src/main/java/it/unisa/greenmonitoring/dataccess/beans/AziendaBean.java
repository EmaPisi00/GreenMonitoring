package it.unisa.greenmonitoring.dataccess.beans;

/**
 * Registrazione di un'azienda bean.
 */

public class AziendaBean {

    /**
     * Variabile locale email.
     */
    private String email;

    /**
     * Variabile locale password.
     */
    private String password;

    /**
     * Variabile locale nome_azienda.
     */
    private String nome_azienda;

    /**
     * Variabile locale partita_iva.
     */
    private String partita_iva;

    /**
     * Costruttore RegistrazioneAziendaBean.
     *
     * @param give_email
     * @param give_password
     * @param give_nomeAzienda
     * @param give_partitaIva
     */
    @SuppressWarnings({})
    public AziendaBean(String give_email, String give_password,
                                     String give_nomeAzienda, String give_partitaIva) {

        this.email = give_email;
        this.password = give_password;
        this.nome_azienda = give_nomeAzienda;
        this.partita_iva = give_partitaIva;

    }

    /**
     * Get di email.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set di email.
     *
     * @param give_email
     */
    public void setEmail(String give_email) {
        this.email = give_email;
    }

    /**
     * Get di Password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set di password.
     *
     * @param give_password
     */
    public void setPassword(String give_password) {
        this.password = give_password;
    }

    /**
     * Get di nome_zienda.
     *
     * @return nome_azienda
     */
    public String getNome_azienda() {
        return nome_azienda;
    }

    /**
     * Set di nome_azienda.
     *
     * @param give_nome_azienda
     */
    public void setNome_azienda(String give_nome_azienda) {
        this.nome_azienda = give_nome_azienda;
    }

    /**
     * Get di Partita_iva.
     *
     * @return partita_iva
     */
    public String getPartita_iva() {
        return partita_iva;
    }

    /**
     * Set di partita_iva.
     *
     * @param give_partita_iva
     */
    public void setPartita_iva(String give_partita_iva) {
        this.partita_iva = give_partita_iva;
    }


}
