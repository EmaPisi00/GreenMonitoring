package it.unisa.greenmonitoring.dataccess.beans;

import java.util.Objects;

public class FisiopatieBean {
    /**
     * Variabile locale id.
     */
    private int id;

    /**
     * Variabile locale id_pianta.
     */
    private int id_pianta;

    /**
     * Variabile locale nome.
     */
    private String nome;

    /**
     * Variabile locale descrizione.
     */
    private String descrizione;
    /**
     * Variabile locale umid_terr_min.
     */
    private float umid_terr_min;
    /**
     * Variabile locale umid_terr_max.
     */
    private float umid_terr_max;
    /**
     * Variabile locale temp_min.
     */
    private float temp_min;
    /**
     * Variabile locale temp_max.
     */
    private float temp_max;

    /**
     * Variabile locale umid_aria_min.
     */
    private float umid_aria_min;
    /**
     * Variabile locale temp.
     */
    private float umid_aria_max;

    /**
     * Costruttore SensoreBean.
     *
     * @param given_id
     * @param given_id_pianta
     * @param given_nome
     * @param given_descrizione
     * @param given_umid_terr_min
     * @param given_umid_terr_max
     * @param given_temp_min
     * @param given_temp_max
     * @param given_umid_aria_min
     * @param given_umid_aria_max
     */

    public FisiopatieBean(int given_id, int given_id_pianta, String given_nome, String given_descrizione, float given_umid_terr_min, float given_umid_terr_max,
                          float given_temp_min, float given_temp_max, float given_umid_aria_min, float given_umid_aria_max) {
        this.id = given_id;
        this.id_pianta = given_id_pianta;
        this.nome = given_nome;
        this.descrizione = given_descrizione;
        this.umid_terr_min = given_umid_terr_min;
        this.umid_terr_max = given_umid_terr_max;
        this.temp_min = given_temp_min;
        this.temp_max = given_temp_max;
        this.umid_aria_min = given_umid_aria_min;
        this.umid_aria_max = given_umid_aria_max;
    }

    /**
     * Quello base.
     */
    public FisiopatieBean() {
    }
    /**
     * Get di id.
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Set di id.
     * @param given_id
     */
    public void setId(int given_id) {
        this.id = given_id;
    }
    /**
     * Get di id_pianta.
     * @return id_pianta
     */
    public int getId_pianta() {
        return id_pianta;
    }
    /**
     * Set di id_pianta.
     * @param given_id_pianta
     */
    public void setId_pianta(int given_id_pianta) {
        this.id_pianta = given_id_pianta;
    }
    /**
     * Get di nome.
     * @return nome
     */
    public String getNome() {
        return nome;
    }
    /**
     * Set di nome.
     * @param given_nome
     */
    public void setNome(String given_nome) {
        this.nome = given_nome;
    }
    /**
     * Get di descrizione.
     * @return descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }
    /**
     * Set di descrizione.
     * @param given_descrizione
     */
    public void setDescrizione(String given_descrizione) {
        this.descrizione = given_descrizione;
    }
    /**
     * Get di umidità terreno minima.
     * @return umid_terr_min
     */
    public float getUmid_terr_min() {
        return umid_terr_min;
    }
    /**
     * Set di umidità terreno minima.
     * @param given_umid_terr_min
     */
    public void setUmid_terr_min(float given_umid_terr_min) {
        this.umid_terr_min = given_umid_terr_min;
    }
    /**
     * Get di umidità terreno massima.
     * @return umid_terr_max
     */
    public float getUmid_terr_max() {
        return umid_terr_max;
    }
    /**
     * Set di umidità terreno massima.
     * @param given_umid_terr_max
     */
    public void setUmid_terr_max(float given_umid_terr_max) {
        this.umid_terr_max = given_umid_terr_max;
    }
    /**
     * Get di temperatura minima.
     * @return temp_min
     */
    public float getTemp_min() {
        return temp_min;
    }
    /**
     * Set di temperatura minima.
     * @param given_temp_min
     */
    public void setTemp_min(float given_temp_min) {
        this.temp_min = given_temp_min;
    }
    /**
     * Get di temperatura massima.
     * @return temp_max
     */
    public float getTemp_max() {
        return temp_max;
    }
    /**
     * Set di temperatura massima.
     * @param given_temp_max
     */
    public void setTemp_max(float given_temp_max) {
        this.temp_max = given_temp_max;
    }
    /**
     * Get di umidità aria minima.
     * @return umid_aria_min
     */
    public float getUmid_aria_min() {
        return umid_aria_min;
    }
    /**
     * Set di umidità aria minima.
     * @param given_umid_aria_min
     */
    public void setUmid_aria_min(float given_umid_aria_min) {
        this.umid_aria_min = given_umid_aria_min;
    }
    /**
     * Get di umidità aria massima.
     * @return umid_aria_max
     */
    public float getUmid_aria_max() {
        return umid_aria_max;
    }
    /**
     * Set di umidità aria massima.
     * @param given_umid_aria_max
     */
    public void setUmid_aria_max(float given_umid_aria_max) {
        this.umid_aria_max = given_umid_aria_max;
    }

    @Override
    public String toString() {
        return "FisiopatieBean{"
                + "id=" + id
                + ", id_pianta=" + id_pianta
                + ", nome='" + nome + '\''
                + ", descrizione='" + descrizione + '\''
                + ", umid_terr_min=" + umid_terr_min
                + ", umid_terr_max=" + umid_terr_max
                + ", temp_min=" + temp_min
                + ", temp_max=" + temp_max
                + ", umid_aria_min=" + umid_aria_min
                + ", umid_aria_max=" + umid_aria_max
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FisiopatieBean that)) {
            return false;
        }
        return id == that.id && id_pianta == that.id_pianta && Float.compare(that.umid_terr_min, umid_terr_min) == 0 && Float.compare(that.umid_terr_max, umid_terr_max) == 0 && Float.compare(that.temp_min, temp_min) == 0 && Float.compare(that.temp_max, temp_max) == 0 && Float.compare(that.umid_aria_min, umid_aria_min) == 0 && Float.compare(that.umid_aria_max, umid_aria_max) == 0 && Objects.equals(nome, that.nome) && Objects.equals(descrizione, that.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_pianta, nome, descrizione, umid_terr_min, umid_terr_max, temp_min, temp_max, umid_aria_min, umid_aria_max);
    }
}
