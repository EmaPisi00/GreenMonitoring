package it.unisa.greenmonitoring.dataccess.beans;

/**
 * Registrazione di un'azienda bean.
 */

public class AziendaBean extends UtenteBean implements Comparable<AziendaBean> {

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
     * Costruttore UtenteBean.
     *
     * @param give_email
     * @param give_password
     * @param give_telefono
     * @param give_citta
     * @param give_indirizzo
     * @param give_provincia
     */
    public AziendaBean(String give_email, String give_password, String give_telefono, String give_citta, String give_indirizzo, String give_provincia) {
        super(give_email, give_password, give_telefono, give_citta, give_indirizzo, give_provincia);
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


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(AziendaBean o) {
        if (this.getEmail().compareTo(o.getEmail()) != 0) {
            return 1;
        }
        if (this.getPassword().compareTo(o.getPassword()) != 0) {
            return 1;
        }
        if (this.getTelefono().compareTo(o.getTelefono()) != 0) {
            return 1;
        }
        if (this.getCitta().compareTo(o.getCitta()) != 0) {
            return 1;
        }
        if (this.getIndirizzo().compareTo(o.getIndirizzo()) != 0) {
            return 1;
        }
        if (this.getProvincia().compareTo(o.getProvincia()) != 0) {
            return 1;
        }
        if (this.getNome_azienda().compareTo(o.getNome_azienda()) != 0) {
            return 1;
        }
        if (this.getPartita_iva().compareTo(o.getPartita_iva()) != 0) {
            return 1;
        }
        return 0;
    }
}
