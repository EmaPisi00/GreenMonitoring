package it.unisa.greenmonitoring.dataccess.beans;

import java.util.Objects;

public class TerrenoBean {
    /**
     * Questo campo contiene l'id.
     */
    private Integer id;
    /**
     * Questo campo contiene la latitudine del terreno.
     */
    private Float  latitudine;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private Float  longitudine;
    /**
     * Questo campo contiene il tipo di superficie.
     */
    private String superficie;
    /**
     * Questo campo contiene il link all'immagine.
     */
    private String immagine;
    /**
     * Questo campo contiene l'azienda associata al terreno.
     */
    private String azienda;

    /**
     * Questo metodo crea un TerrenoBean a partire da i seguenti parametri.
     * @param given_latitudine
     * @param given_longitudine
     * @param given_superficie
     * @param given_immagine
     * @param given_azienda
     */
    public TerrenoBean(Float given_latitudine, Float given_longitudine, String given_superficie, String given_immagine, String given_azienda) {
        this.latitudine = given_latitudine;
        this.longitudine = given_longitudine;
        this.superficie = given_superficie;
        this.immagine = given_immagine;
        this.azienda = given_azienda;
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

    @Override
    public String toString() {
        return "TerrenoBean{"
                + "id=" + id
                + ", latitudine=" + latitudine
                + ", longitudine=" + longitudine
                + ", superficie='" + superficie + '\''
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
        return id.equals(that.id) && latitudine.equals(that.latitudine) && longitudine.equals(that.longitudine) && superficie.equals(that.superficie) && immagine.equals(that.immagine) && azienda.equals(that.azienda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitudine, longitudine, superficie, immagine, azienda);
    }
}
