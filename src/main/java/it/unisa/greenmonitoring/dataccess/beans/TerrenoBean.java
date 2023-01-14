package it.unisa.greenmonitoring.dataccess.beans;

public class TerrenoBean {
    private String id;
    private String locazione;
    private String superficie;
    private String immagine;

    /**
     * Questo metodo crea un terreno bean a partire da i seguenti parametri.
     * @param id
     * @param locazione
     * @param superficie
     * @param immagine
     * @return un'istanza di TerrenoBean
     */
    public TerrenoBean(String id, String locazione, String superficie, String immagine) {
        this.id = id;
        this.locazione = locazione;
        this.superficie = superficie;
        this.immagine = immagine;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocazione() {
        return locazione;
    }

    public void setLocazione(String locazione) {
        this.locazione = locazione;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
}
