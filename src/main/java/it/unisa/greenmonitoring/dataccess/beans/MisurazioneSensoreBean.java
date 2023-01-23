package it.unisa.greenmonitoring.dataccess.beans;

import java.sql.Time;
import java.util.Date;

public class MisurazioneSensoreBean {
    /**
     * Questo campo contiene l'id della misurazione.
     */
    public int id;
    /**
     * Questo campo contiene la data della misurazione.
     */
    private Date data;
    /**
     * Questo campo contiene l'ora della misurazione.
     */
    private Time ora;
    /**
     * Questo campo contiene il tipo di sensore
     */
    private String tipo;

    /**
     * Questo è il costruttore vuoto.
     */
    public MisurazioneSensoreBean() { }
    /**
     * Questo è il costruttore con tutti i campi.
     */
    public MisurazioneSensoreBean(int id, Date data, Time ora, String tipo) {
        this.id = id;
        this.data = data;
        this.ora = ora;
        this.tipo = tipo;
    }

    /**
     * Questo è il setter per l'id.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Questo è il setter per la data.
     * @param data
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Questo è il setter per l'ora.
     * @param ora
     */
    public void setOra(Time ora) {
        this.ora = ora;
    }

    /**
     * Questo è il setter per il tipo.
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
}
