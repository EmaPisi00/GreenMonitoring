package it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.AdapterMeteo;

import it.unisa.greenmonitoring.dataccess.beans.DatiMeteoBean;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class OpenMeteoApiAdapterImpl implements MeteoApiAdapter {

    @Override
    public DatiMeteoBean getTomorrowRain(double latitudine, double longitudine) {
        //Serve per ottenere data e ora attuale
        LocalDateTime now = LocalDateTime.now();
        //setta il formato della data
        String dataFormat = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String nextDay = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String urlString = "https://api.open-meteo.com/v1/forecast?"
                + "latitude=" + latitudine
                + "&longitude=" + longitudine
                + "&daily=weathercode,temperature_2m_max,temperature_2m_min,rain_sum&current_weather=true&timezone=Europe%2FBerlin&"
                + "start_date="
                + dataFormat
                + "&end_date="
                + nextDay;
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            return null;
        }
        System.out.println(urlString);
        //invia una richiesta all'url indicato
        HttpURLConnection con = null;
        int responseCode;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            responseCode = con.getResponseCode();
        } catch (IOException e) {
            return null;
        }
        System.out.println("response code: " + responseCode);
        if (responseCode == 200) {
            //il contenuto della risposta è un json
            String jsonString;
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject;
            try {
                jsonString = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
                //conversione in oggetto JSON
                jsonObject = (JSONObject) jsonParser.parse(jsonString);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            System.out.println(jsonObject);
            //Estrazione dei dati dal JSON
            JSONObject daily = (JSONObject) jsonObject.get("daily");
            JSONArray tempMin = (JSONArray) daily.get("temperature_2m_min");
            JSONArray tempMax = (JSONArray) daily.get("temperature_2m_max");
            JSONArray weathercode = (JSONArray) daily.get("weathercode");
            JSONArray rain = (JSONArray) daily.get("rain_sum");

            DatiMeteoBean datiMeteoBean = new DatiMeteoBean();
            datiMeteoBean.setRain((Double) rain.get(1));
            datiMeteoBean.setTemperatura_min((Double) tempMin.get(1));
            datiMeteoBean.setTemperatura_max((Double) tempMax.get(1));
            datiMeteoBean.setWeather_code((Long) weathercode.get(1));

            System.out.println(tempMin);
            System.out.println(tempMax);
            System.out.println(weathercode);
            System.out.println(rain);

            return datiMeteoBean;
        }
        return null;
    }
}
