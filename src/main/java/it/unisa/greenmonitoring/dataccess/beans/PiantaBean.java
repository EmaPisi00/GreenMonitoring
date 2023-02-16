package it.unisa.greenmonitoring.dataccess.beans;

import java.util.Objects;

public class PiantaBean {

    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private Integer id;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private String azienda;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private String nome;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private String descrizione;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private String ph_min;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private String ph_max;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private String temperatura_min;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private String temperatura_max;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private String umidita_min;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private String umidita_max;
    /**
     * Questo campo contiene la longitudine del terreno.
     */
    private byte[] immagine;

    /**
     * Questo metodo crea un TerrenoBean a partire da i seguenti parametri.
     * @param Pazienda
     * @param Pnome
     * @param Pdescrizione
     * @param Pph_min
     * @param Pph_max
     * @param Ptemperatura_min
     * @param Ptemperatura_max
     */
    public PiantaBean(String Pazienda, String Pnome, String Pdescrizione, String Pph_min, String Pph_max,
                      String Ptemperatura_min, String Ptemperatura_max) {
        this.azienda = Pazienda;
        this.nome = Pnome;
        this.descrizione = Pdescrizione;
        this.ph_min = Pph_min;
        this.ph_max = Pph_max;
        this.temperatura_min = Ptemperatura_min;
        this.temperatura_max = Ptemperatura_max;
    }

    /**
     * classico.
     */
    public PiantaBean() {
    }

    /**
     *
     * @return Float
     */
    public Integer getId() {
        return id;
    }
    /**
     *
     * @param Pid
     */
    public void setId(Integer Pid) {
        this.id = Pid;
    }
    /**
     *
     * @return Float
     */
    public String getAzienda() {
        return azienda;
    }
    /**
     *
     * @param Pazienda
     */
    public void setAzienda(String Pazienda) {
        this.azienda = Pazienda;
    }
    /**
     *
     * @return Float
     */
    public String getNome() {
        return nome;
    }
    /**
     *
     * @param Pnome
     */
    public void setNome(String Pnome) {
        this.nome = Pnome;
    }
    /**
     *
     * @return Float
     */
    public String getDescrizione() {
        return descrizione;
    }
    /**
     *
     * @param Pdescrizione
     */
    public void setDescrizione(String Pdescrizione) {
        this.descrizione = Pdescrizione;
    }
    /**
     *
     * @return Float
     */
    public String getPh_min() {
        return ph_min;
    }

    /**
     *
     * @param Pph_min
     */
    public void setPh_min(String Pph_min) {
        this.ph_min = Pph_min;
    }
    /**
     *
     * @return Float
     */
    public String getPh_max() {
        return ph_max;
    }
    /**
     *
     * @param Pph_max
     */
    public void setPh_max(String Pph_max) {
        this.ph_max = Pph_max;
    }
    /**
     *
     * @return Float
     */
    public String getTemperatura_min() {
        return temperatura_min;
    }
    /**
     *
     * @param Ptemperatura_min
     */
    public void setTemperatura_min(String Ptemperatura_min) {
        this.temperatura_min = Ptemperatura_min;
    }
    /**
     *
     * @return Float
     */
    public String getTemperatura_max() {
        return temperatura_max;
    }
    /**
     *
     * @param Ptemperatura_max
     */
    public void setTemperatura_max(String Ptemperatura_max) {
        this.temperatura_max = Ptemperatura_max;
    }
    /**
     *
     * @return Float
     */
    public String getUmidita_min() {
        return umidita_min;
    }
    /**
     *
     * @param Pumidita_min
     */
    public void setUmidita_min(String Pumidita_min) {
        this.umidita_min = Pumidita_min;
    }
    /**
     *
     * @return Float
     */
    public String getUmidita_max() {
        return umidita_max;
    }
    /**
     *
     * @param Pumidita_max
     */
    public void setUmidita_max(String Pumidita_max) {
        this.umidita_max = Pumidita_max;
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
     * @param Pimmagine
     */
    public void setImmagine(byte[] Pimmagine) {
        this.immagine = Pimmagine;
    }

    @Override
    public String toString() {
        return "PiantaBean{"
                + "id=" + id
                + ", azienda='" + azienda + '\''
                + ", nome='" + nome + '\''
                + ", descrizione='" + descrizione + '\''
                + ", ph_min='" + ph_min + '\''
                + ", ph_max='" + ph_max + '\''
                + ", temperatura_min='" + temperatura_min + '\'' + ", temperatura_max='" + temperatura_max + '\'' + ", umidita_min='" + umidita_min + '\''
                + ", umidita_max='" + umidita_max + '\'' + ", immagine='" + immagine + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PiantaBean that)) {
            return false;
        }
        return id.equals(that.id) && Objects.equals(azienda, that.azienda) && nome.equals(that.nome) && descrizione.equals(that.descrizione) && ph_min.equals(that.ph_min) && ph_max.equals(that.ph_max) && temperatura_min.equals(that.temperatura_min) && temperatura_max.equals(that.temperatura_max) && umidita_min.equals(that.umidita_min) && umidita_max.equals(that.umidita_max) && immagine.equals(that.immagine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, azienda, nome, descrizione, ph_min, ph_max, temperatura_min, temperatura_max, umidita_min, umidita_max, immagine);
    }
}
