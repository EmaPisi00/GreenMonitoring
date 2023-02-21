package it.unisa.greenmonitoring.dataccess.beans;

import java.util.Objects;

public class TerrenoBean {
    /**
     * Questo campo contiene l'id.
     */
    private Integer id;
    /**
     * nome terreno.
     */
    private String nome;

    /**
     * Questo campo contiene la latitudine del terreno.
     */

    private Float  latitudine;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private Float  longitudine;
    /**
     * Questo campo contiene il tipo di superfice.
     */
    private Float superfice;
    /**
     * Questo campo contiene il link all'immagine.
     */
    private byte[] immagine;
    /**
     * Questo campo contiene l'azienda associata al terreno.
     */
    private String azienda;

    /**
     * Questo metodo crea un TerrenoBean a partire da i seguenti parametri.
     * @param given_nome
     * @param given_latitudine
     * @param given_longitudine
     * @param given_superfice
     * @param given_immagine
     * @param given_azienda
     */
    public TerrenoBean(String given_nome, Float given_latitudine, Float given_longitudine, Float given_superfice, byte[] given_immagine, String given_azienda) {
        this.nome = given_nome;
        this.latitudine = given_latitudine;
        this.longitudine = given_longitudine;
        this.superfice = given_superfice;
        this.immagine = given_immagine;
        this.azienda = given_azienda;
    }
    /**
     * nome get.
     * @return String
     */
    public String getNome() {
        return nome;
    }
    /**
     * nome set.
     * @param nomeParam
     */
    public void setNome(String nomeParam) {
        this.nome = nomeParam;
    }

    /**
     * niente.
     */
    public TerrenoBean() {
    }

    /**
     *
     * @return String
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param given_id
     */
    public void setId(Integer given_id) {
        this.id = given_id;
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
    public Float getSuperficie() {
        return superfice;
    }

    /**
     *
     * @param given_superfice
     */
    public void setSuperficie(Float given_superfice) {
        this.superfice = given_superfice;
    }

    /**
     *
     * @return byte[]
     */
    public byte[] getImmagine() {
        return immagine;
    }

    /**
     *
     * @param given_immagine
     */
    public void setImmagine(byte[] given_immagine) {
        this.immagine = given_immagine;
    }
    /**
     *
     * @param given_azienda
     */
    public void setAzienda(String given_azienda) {
        this.azienda = given_azienda;
    }

    @Override
    public String toString() {
        return "TerrenoBean{"
                + "id=" + id
                + "nome=" + nome
                + ", latitudine=" + latitudine
                + ", longitudine=" + longitudine
                + ", superfice='" + superfice + '\''
                + ", immagine='" + immagine + '\''
                + ", azienda='" + azienda + '\''
                + '}';
    }

    /**
     *
     * @return String
     */
    public String getAzienda() {
        return azienda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TerrenoBean that)) {
            return false;
        }
        return nome.equals(that.nome) && id.equals(that.id) && latitudine.equals(that.latitudine) && longitudine.equals(that.longitudine) && superfice.equals(that.superfice) && immagine.equals(that.immagine) && azienda.equals(that.azienda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, id, latitudine, longitudine, superfice, immagine, azienda);
    }
}
