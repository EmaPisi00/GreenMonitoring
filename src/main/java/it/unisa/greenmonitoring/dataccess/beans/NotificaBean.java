package it.unisa.greenmonitoring.dataccess.beans;

import java.sql.Timestamp;
import java.util.HashMap;

public class NotificaBean {
    /**
     * id.
     */
    private int id;
    /**
     * coltivazioneid.
     */
    private int coltivazioneID;
    /**
     * aziendaID.
     */
    private String aziendaID;
    /**
     * tipo.
     */
    private String tipo;
    /**
     * data.
     */
    private Timestamp data;
    /**
     * contenuto.
     */
    private String contenuto;
    /**
     * visualizzazione.
     */
    private boolean visualizzazioneNotifica;
    /**
     * listaDipendenti.
     */
    private HashMap<String, Boolean> ListaDipendenti;

    /**
     *
     * @return HashMap
     */
    public HashMap<String, Boolean> getListaDipendenti() {
        return ListaDipendenti;
    }

    /**
     *
     * @param listaDipendenti
     */
    public void setListaDipendenti(HashMap<String, Boolean> listaDipendenti) {
        ListaDipendenti = listaDipendenti;
    }

    /**
     *
     * @return boolean
     */
    public boolean getVisualizzazioneNotifica() {
        return visualizzazioneNotifica;
    }
    /**
     *
     * @param param_visualizzazioneNotifica
     */
    public void setVisualizzazioneNotifica(boolean param_visualizzazioneNotifica) {
        this.visualizzazioneNotifica = param_visualizzazioneNotifica;
    }
    /**
     *
     * @param Param_coltivazioneID
     * @param Param_aziendaID
     * @param Param_tipo
     * @param Param_data
     * @param Param_contenuto
     */
    public NotificaBean(int Param_coltivazioneID, String Param_aziendaID, String Param_tipo, Timestamp Param_data, String Param_contenuto) {
        this.coltivazioneID = Param_coltivazioneID;
        this.aziendaID = Param_aziendaID;
        this.tipo = Param_tipo;
        this.data = Param_data;
        this.contenuto = Param_contenuto;
        this.visualizzazioneNotifica = false;
    }

    /**
     * default.
     */
    public NotificaBean() {

    }

    /**
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param Param_id
     */
    public void setId(int Param_id) {
        this.id = Param_id;
    }

    /**
     *
     * @return int
     */
    public int getColtivazioneID() {
        return coltivazioneID;
    }

    /**
     *
     * @param Param_coltivazioneID
     */
    public void setColtivazioneID(int Param_coltivazioneID) {
        this.coltivazioneID = Param_coltivazioneID;
    }

    /**
     *
     * @return String
     */
    public String getAziendaID() {
        return aziendaID;
    }

    /**
     *
     * @param Param_aziendaID
     */
    public void setAziendaID(String Param_aziendaID) {
        this.aziendaID = Param_aziendaID;
    }

    /**
     *
     * @return String
     */
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @param Param_tipo
     */
    public void setTipo(String Param_tipo) {
        this.tipo = Param_tipo;
    }

    /**
     *
     * @return Timestamp
     */
    public Timestamp getData() {
        return data;
    }

    /**
     *
     * @param Param_data
     */
    public void setData(Timestamp Param_data) {
        this.data = Param_data;
    }

    /**
     *
     * @return String
     */
    public String getContenuto() {
        return contenuto;
    }

    @Override
    public String toString() {
        return "NotificaBean{"
                + "id=" + id
                + ", coltivazioneID=" + coltivazioneID
                + ", aziendaID='" + aziendaID + '\''
                + ", tipo='" + tipo + '\''
                + ", data=" + data
                + ", contenuto='" + contenuto + '\''
                + ", visualizzazioneNotifica=" + visualizzazioneNotifica
                + '}';
    }

    /**
     *
     * @param Param_contenuto
     */
    public void setContenuto(String Param_contenuto) {
        this.contenuto = Param_contenuto;
    }
}
