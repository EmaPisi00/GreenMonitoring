package it.unisa.greenmonitoring.dataccess.beans;

/**
 * Registrazione di un'azienda bean.
 */

public class AziendaBean extends UtenteBean {

    /**
     * Variabile locale nome_azienda.
     */
    private String nome_azienda;

    /**
     * Variabile locale partita_iva.
     */
    private String partita_iva;

    /**
     * Variabile locale partita_iva.
     */
    private String codice_associazione;

    /**
     * Costruttore RegistrazioneAziendaBean.
     * @param give_nomeAzienda
     * @param give_partitaIva
     */
    @SuppressWarnings({})
    public AziendaBean(String give_nomeAzienda, String give_partitaIva) {
        super();

        this.nome_azienda = give_nomeAzienda;
        this.partita_iva = give_partitaIva;

    }


    /**
     * Costruttore vuoto.
     */
    public AziendaBean() {
        super();
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

    /**
     * Get di codice_associazione.
     *
     * @return codice_associazione
     */
    public String getCodice_associazione() {
        return codice_associazione;
    }

    /**
     * Set di codice_associazione.
     *
     * @param give_codice_associazione
     */
    public void setCodice_associazione(String give_codice_associazione) {
        this.codice_associazione = give_codice_associazione;
    }


}
