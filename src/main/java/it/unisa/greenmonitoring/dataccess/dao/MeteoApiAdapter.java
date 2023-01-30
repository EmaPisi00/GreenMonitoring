package it.unisa.greenmonitoring.dataccess.dao;

import it.unisa.greenmonitoring.dataccess.beans.DatiMeteoBean;

public interface MeteoApiAdapter {
    /**
     * Metodo per la pioggia.
     * @param latitudine
     * @param longitudine
     * @return bean di dati meteo
     */
     DatiMeteoBean getTomorrowRain(double latitudine, double longitudine);

}
