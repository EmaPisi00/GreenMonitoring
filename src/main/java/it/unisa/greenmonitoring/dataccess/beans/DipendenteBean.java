package it.unisa.greenmonitoring.dataccess.beans;

import java.util.Objects;

public class DipendenteBean extends UtenteBean implements Comparable<DipendenteBean> {

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
     * Costruttore UtenteBean.
     *
     * @param give_email
     * @param give_password
     * @param give_telefono
     * @param give_citta
     * @param give_indirizzo
     * @param give_provincia
     */
    public DipendenteBean(String give_email, String give_password, String give_telefono, String give_citta, String give_indirizzo, String give_provincia) {
        super(give_email, give_password, give_telefono, give_citta, give_indirizzo, give_provincia);
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
    public int compareTo(DipendenteBean o) {
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
        if (this.getAzienda().compareTo(o.getAzienda()) != 0) {
            return 1;
        }
        if (this.getNome().compareTo(o.getNome()) != 0) {
            return 1;
        }
        if (this.getCognome().compareTo(o.getCognome()) != 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DipendenteBean that)) {
            return false;
        }
        return Objects.equals(azienda, that.azienda) && nome.equals(that.nome) && cognome.equals(that.cognome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(azienda, nome, cognome);
    }
}
