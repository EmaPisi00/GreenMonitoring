package it.unisa.greenmonitoring.dataccess.beans;

import java.sql.Time;
import java.util.Date;

public class MisurazioneSensoreBean {
    /**
     * Questo campo contiene l'id della misurazione.
     */
    private int id;
    /**
     * Questo campo contiene la data della misurazione.
     */
    private Date data;
    /**
     * Questo campo contiene l'ora della misurazione.
     */
    private Time ora;
    /**
     * Questo campo contiene il tipo di sensore.
     */
    private String tipo;
    /**
     * Questo campo contiene il valore misurato dal sensore.
     */
    private int valore;

    /**
     * Questo è il costruttore vuoto.
     */
    public MisurazioneSensoreBean() { }

    /**
     * Questo è il costruttore con i campi.
     * @param given_id
     * @param given_data
     * @param given_ora
     * @param given_tipo
     * @param given_valore
     */
    public MisurazioneSensoreBean(int given_id, Date given_data, Time given_ora, String given_tipo, int given_valore) {
        this.id = given_id;
        this.data = given_data;
        this.ora = given_ora;
        this.tipo = given_tipo;
        this.valore = given_valore;
    }

    /**
     * Questo è il setter per l'id.
     * @param given_id
     */
    public void setId(int given_id) {
        this.id = given_id;
    }

    /**
     * Questo è il setter per la data.
     * @param given_data
     */
    public void setData(Date given_data) {
        this.data = given_data;
    }

    /**
     * Questo è il setter per l'ora.
     * @param given_ora
     */
    public void setOra(Time given_ora) {
        this.ora = given_ora;
    }

    /**
     * Questo è il setter per il tipo.
     * @param given_tipo
     */
    public void setTipo(String given_tipo) {
        this.tipo = given_tipo;
    }

    /**
     * Questo è il getter per la data.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Questo è il getter per la data.
     * @return Date
     */
    public Date getData() {
        return data;
    }

    /**
     * Questo è il getter dell'ora della misurazione.
     * @return Time
     */
    public Time getOra() {
        return ora;
    }

    /**
     * Questo è il getter del il tipo.
     * @return String
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Questo è il getter del valore misurato.
     * @return int
     */
    public int getValore() {
        return valore;
    }
    /**
     * Questo è il setter del valore misurato.
     * @param given_valore
     */
    public void setValore(int given_valore) {
        this.valore = given_valore;
    }
}
