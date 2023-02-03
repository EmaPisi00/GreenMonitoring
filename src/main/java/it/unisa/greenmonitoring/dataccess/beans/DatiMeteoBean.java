package it.unisa.greenmonitoring.dataccess.beans;

import java.util.Objects;

public class DatiMeteoBean {
    /**
     * Temperatura massima.
     */
    private double temperatura_max;
    /**
     * Temperatura minima.
     */
    private double temperatura_min;
    /**
     * Quantità di pioggia.
     */
    private double rain;
    /**
     * Codice meteo.
     */
    private long weather_code;

    /**
     * Costruttore.
     */

    public DatiMeteoBean() {
    }
    /**
     * Getter della temperatura massima.
     * @return temperatura_max
     */
    public double getTemperatura_max() {
        return temperatura_max;
    }
    /**
     * Setter della temperatura massima.
     * @param temp_max
     */
    public void setTemperatura_max(Double temp_max) {
        this.temperatura_max = temp_max;
    }
    /**
     * Getter della temperatura minima.
     * @return temperatura_min
     */
    public double getTemperatura_min() {
        return temperatura_min;
    }
    /**
     * Setter della temperatura minima.
     * @param temp_min
     */
    public void setTemperatura_min(Double temp_min) {
        this.temperatura_min = temp_min;
    }
    /**
     * Getter della quantità di pioggia.
     * @return rain
     */
    public double getRain() {
        return rain;
    }
    /**
     * Setter della quantità di pioggia.
     * @param Rain
     */
    public void setRain(Double Rain) {
        this.rain = Rain;
    }
    /**
     * Getter del codice meteo.
     * @return weather_code
     */
    public long getWeather_code() {
        return weather_code;
    }
    /**
     * Setter del codice meteo.
     * @param Weather_code
     */
    public void setWeather_code(long Weather_code) {
        this.weather_code = Weather_code;
    }
    /**
     * Metodo di conversione a stringa.
     */
    @Override
    public String toString() {
        return "DatiMeteoBean{"
                + "temperatura_max=" + temperatura_max
                + ", temperatura_min=" + temperatura_min
                + ", rain=" + rain
                + ", weather_code=" + weather_code
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DatiMeteoBean that)) {
            return false;
        }
        return Double.compare(that.temperatura_max, temperatura_max) == 0 && Double.compare(that.temperatura_min, temperatura_min) == 0 && Double.compare(that.rain, rain) == 0 && weather_code == that.weather_code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperatura_max, temperatura_min, rain, weather_code);
    }
}
