package it.unisa.greenmonitoring.dataccess.beans;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

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
     * Questo campo contiene la foreign key di coltivazione.
     */
    private Integer coltivazione;
    /**
     * Questo campo contiene la foreign key di sensore.
     */
    private Integer sensore_id;

    /**
     * Questo è il costruttore vuoto.
     */
    public MisurazioneSensoreBean() { }

    /**
     * Questo è il getter di sensore_id.
     * @return Integer
     */
    public Integer getSensore_id() {
        return sensore_id;
    }

    /**
     * Questo è il setter di sensore_id.
     * @param given_sensore_id
     */
    public void setSensore_id(Integer given_sensore_id) {
        this.sensore_id = given_sensore_id;
    }

    /**
     * costruttore.
     * @param given_data
     * @param given_ora
     * @param given_tipo
     * @param given_valore
     * @param given_coltivazione
     * @param given_sensore
     */
    public MisurazioneSensoreBean(Date given_data, Time given_ora, String given_tipo, Integer given_valore, Integer given_coltivazione, int given_sensore) {
        this.coltivazione = given_coltivazione;
        this.sensore_id = given_sensore;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MisurazioneSensoreBean that)) {
            return false;
        }
        return id == that.id && valore == that.valore && data.equals(that.data) && ora.equals(that.ora) && tipo.equals(that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, ora, tipo, valore);
    }

    /**
     * To string di MisurazioneSensoreBean.
     * @return string
     */
    @Override
    public String toString() {
        return "MisurazioneSensoreBean{"
                + "id=" + id
                + ", data=" + data
                + ", ora=" + ora
                + ", tipo='" + tipo + '\''
                + ", valore=" + valore
                + '}';
    }

    /**
     * Questo metodo restituisce la coltivazione associata alla misurazione.
     * @return Integer
     */
    public Integer getColtivazione() {
        return coltivazione;
    }

    /**
     * Questo metodo imposta la coltivazione alla misurazione.
     * @param given_coltivazione
     */
    public void setColtivazione(Integer given_coltivazione) {
        this.coltivazione = given_coltivazione;
    }
}
