package it.unisa.greenmonitoring.dataccess.beans;

import java.sql.Date;
import java.util.ArrayList;

public class ColtivazioneBean {
    /**
     * Questo campo contiene l'id.
     */
    private int id;
    /**
     * Questo campo contiene l'id della pianta.
     */
    private Integer pianta;
    /**
     * Questo campo contiene l'id del terreno.
     */
    private Integer terreno;
    /**
     * Questo campo contiene la data di fine della coltivazione.
     */
    private byte stato_archiviazione;
    /**
     * Questo campo contiene la data di inizio della coltivazione.
     */
    private Date data_inizio;
    /**
     * Questo campo contiene la data di fine della coltivazione.
     */
    private Date data_fine;

    /**
     * Questo campo contiene i sensori associati alla coltivazione.
     */
    private ArrayList<SensoreBean> listaSensori;

    /**
     * Questo campo contiene le misurazioni effettuate da ogni sensore.
     */
    private ArrayList<MisurazioneSensoreBean> listaMisurazioni;

    /**
     * Questo è un costruttore vuoto per una coltivazione.
     */
    public ColtivazioneBean() {

    }

    /**
     * Questo metodo è il costruttore di ColtivazioneBean.
     * @param given_id
     * @param given_pianta
     * @param given_terreno
     * @param given_stato_archiviazione
     * @param given_data_inizio
     * @param given_data_fine
     */
    public ColtivazioneBean(int given_id, int given_pianta, Integer given_terreno, byte given_stato_archiviazione, Date given_data_inizio, Date given_data_fine) {
        this.id = given_id;
        this.pianta = given_pianta;
        this.terreno = given_terreno;
        this.stato_archiviazione = given_stato_archiviazione;
        this.data_inizio = given_data_inizio;
        this.data_fine = given_data_fine;
    }

    /**
     * Questo metodo è il costruttore di ColtivazioneBean. Non ha nè id né data in quanto sono generati direttamente dal DAO.
     * @param given_pianta
     * @param given_terreno
     * @param given_stato_archiviazione

     */
    public ColtivazioneBean(int given_pianta, Integer given_terreno, byte given_stato_archiviazione) {
        this.pianta = given_pianta;
        this.terreno = given_terreno;
        this.stato_archiviazione = given_stato_archiviazione;
    }


    /**
     * Questo metodo restituisce l'id di una coltivazione.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Questo metodo restituisce l'id della pianta di una coltivazione.
     * @return int
     */
    public Integer getPianta() {
        return pianta;
    }

    /**
     * Questo metodo restituisce l'id del terreno di una coltivazione.
     * @return int
     */
    public Integer getTerreno() {
        return terreno;
    }

    /**
     * Questo metodo restituisce lo stato di una coltivazione.
     * @return byte
     */
    public byte getStato_archiviazione() {
        return stato_archiviazione;
    }

    /**
     * Questo metodo restituisce la data di inizio di una coltivazione.
     *
     * @return Date
     */
    public Date getData_inizio() {
        return data_inizio;
    }

    /**
     * Questo metodo restituisce la data di fine di una coltivazione.
     * @return Date
     */
    public Date getData_fine() {
        return data_fine;
    }

    /**
     * Questo metodo imposta l'id di una coltivazione.
     * @param given_id
     */
    public void setId(int given_id) {
        this.id = given_id;
    }

    /**
     * Questo metodo imposta l'id di una pianta di una coltivazione.
     * @param given_pianta
     */
    public void setPianta(int given_pianta) {
        this.pianta = given_pianta;
    }

    /**
     * Questo metodo imposta il terreno associato ad una coltivazione.
     * @param given_terreno
     */
    public void setTerreno(Integer given_terreno) {
        this.terreno = given_terreno;
    }

    /**
     * Questo metodo imposta lo stato di una coltivazione.
     * @param given_stato_archiviazione
     */
    public void setStato_archiviazione(byte given_stato_archiviazione) {
        this.stato_archiviazione = given_stato_archiviazione;
    }

    /**
     * Questo metodo imposta la data di inizio di una coltivazione.
     * @param given_data_inizio
     */
    public void setData_inizio(Date given_data_inizio) {
        this.data_inizio = given_data_inizio;
    }

    /**
     * Questo metodo imposta la data di fine di coltivazione.
     * @param given_data_fine
     */
    public void setData_fine(Date given_data_fine) {
        this.data_fine = given_data_fine;
    }

    /**
     * Questo metodo restituisce la lista di sensori.
     * @return ArrayList'<SensoreBean>'
     */
    public ArrayList<SensoreBean> getListaSensori() {
        return listaSensori;
    }

    /**
     * Questo metodo restituisce la lista delle misurazioni.
     * @return ArrayList'<MisurazioneSensoreBean>'
     */
    public ArrayList<MisurazioneSensoreBean> getListaMisurazioni() {
        return listaMisurazioni; }

    /**
     * Questo metodo aggiunge un sensore a SensoreBean.
     * @param s
     */

    public void setInListaSensori(SensoreBean s) {
        this.listaSensori.add(s); }

    /**
     * Questo metodo aggiunge un sensore a SensoreBean.
     * @param ms
     */

    public void setInListaMisurazioni(MisurazioneSensoreBean ms) {
        this.listaMisurazioni.add(ms); }

    @Override
    public String toString() {
        return "ColtivazioneBean{" + "id=" + id + ", pianta=" + pianta + ", terreno=" + terreno + ", stato_archiviazione=" + stato_archiviazione + ", data_inizio=" + data_inizio + ", data_fine=" + data_fine + ", listaSensori=" + listaSensori + ", listaMisurazioni=" + listaMisurazioni + '}';
    }
}
