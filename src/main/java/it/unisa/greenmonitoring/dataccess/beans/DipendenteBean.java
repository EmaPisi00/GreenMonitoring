package it.unisa.greenmonitoring.dataccess.beans;

public class DipendenteBean extends UtenteBean {

    /**
     * This field contains the agency.
     */
    private String azienda;
    /**
     * This field contains the name.
     */
    private String nome;
    /**
     * This field contains the surname.
     */
    private String cognome;

    /**
     * Costruttore DipendenteBean.
     *
     * @param give_azienda
     * @param give_nome
     * @param give_cognome
     */
    public DipendenteBean(String give_azienda, String give_nome, String give_cognome) {
        super();

        this.azienda = give_azienda;
        this.nome = give_nome;
        this.cognome = give_cognome;


    }

    /**
     * Costruttore vuoto.
     */
    public DipendenteBean() {

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
    /**
     * Get di nome.
     *
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set di nome.
     *
     * @param give_nome
     */
    public void setNome(String give_nome) {
        this.nome = give_nome;
    }
    /**
     * Get di cognome.
     *
     * @return cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Set di cognome.
     *
     * @param give_cognome
     */
    public void setCognome(String give_cognome) {
        this.cognome = give_cognome;
    }
}
