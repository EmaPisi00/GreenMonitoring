package it.unisa.greenmonitoring.dataccess.beans;

public class UtenteBean {
    /**
     * Variabile locale email.
     */
    private String email;

    /**
     * Variabile locale password.
     */
    private String password;

    /**
     * Variabile locale telefono.
     */
    private String telefono;

    /**
     * Variabile locale citta.
     */
    private String citta;

    /**
     * Variabile locale indirizzo.
     */
    private String indirizzo;

    /**
     * Variabile locale provincia.
     */
    private String provincia;

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

    public UtenteBean(String give_email, String give_password, String give_telefono,
                            String give_citta, String give_indirizzo, String give_provincia) {

        this.email = give_email;
        this.password = give_password;
        this.telefono = give_telefono;
        this.citta = give_citta;
        this.indirizzo = give_indirizzo;
        this.provincia = give_provincia;

    }

    /**
     * Connessione DipendenteBean.
     */
    public UtenteBean() {

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
     * Get di telefono.
     *
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Set di telefono.
     *
     * @param give_telefono
     */
    public void setTelefono(String give_telefono) {
        this.telefono = give_telefono;
    }

    /**
     * Get di città.
     *
     * @return citta
     */
    public String getCitta() {
        return citta;
    }

    /**
     * Set di citta.
     *
     * @param give_citta
     */
    public void setCitta(String give_citta) {
        this.citta = give_citta;
    }

    /**
     * Get di Indirizzo.
     *
     * @return indirizzo
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Set di indirizzo.
     *
     * @param give_indirizzo
     */
    public void setIndirizzo(String give_indirizzo) {
        this.indirizzo = give_indirizzo;
    }

    /**
     * Get di Provincia.
     *
     * @return provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Set di provincia.
     *
     * @param give_provincia
     */
    public void setProvincia(String give_provincia) {
        this.provincia = give_provincia;
    }
}
