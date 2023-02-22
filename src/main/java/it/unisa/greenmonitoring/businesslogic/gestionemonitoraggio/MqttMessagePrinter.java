package it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio;

import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.NotificaBean;
import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.dao.*;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;


public class MqttMessagePrinter implements MqttCallback {
    /**
     * This method is called when a connection is lost with the MQTT broker.
     * It can be used to handle the reconnection logic.
     * @param throwable
     */
    @Override
    public void connectionLost(Throwable throwable) {

    }

    /**
     * his method is called when a message is received from the MQTT broker.
     * It prints the received message with the topic as well.
     * @param topic
     * @param message
     * @throws MqttException
     */

    //chiama i DAO
    public void messageArrived(String topic, MqttMessage message) throws MqttException, SQLException {
        System.out.println("Received message: " + "Topic= " + topic + new String(message.getPayload()));
        String messaggio = new String(message.getPayload());
        float valore = prelevaNumero(messaggio);
        if (valore != 0.0f) {
            MisurazioneSensoreDAO msd = new MisurazioneSensoreDAOImpl();
            SensoreDAO sensoreDAO = new SensoreDAOImpl();
            AziendaDAO azienda = new AziendaDAOImpl();
            PiantaDAO pd = new PiantaDAOImpl();
            SensoreBean sensore = sensoreDAO.retrieveByidM(topic);
            MisurazioneSensoreBean storico = new MisurazioneSensoreBean(new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()),
                    sensore.getTipo(), (int) valore, sensore.getColtivazione(), sensore.getId());
            msd.createMisurazioneManuel(storico);
            System.out.println("storico=" + storico);
            if (storico.getTipo().matches("Temperatura")) {
                PiantaBean pianta = pd.ritornaPiantaPerColtivazione(sensore.getColtivazione());
                if (storico.getValore() > pianta.getTemperatura_max()
                        || storico.getValore() < pianta.getTemperatura_min()) {
                    NotificaDAO nd = new NotificaDAOImpl();
                    String erroreTemp = "Sensore id " + sensore.getId()
                            + "\\n Tipo " + sensore.getTipo()
                            + "\\nValore " + valore
                            + "\\nHa rilevato un errore sulla temperatura della pianta " + pianta.getNome() + " con"
                            + "\\nTemperatura massima " + pianta.getTemperatura_max()
                            + "\\nTemperatura minima " + pianta.getTemperatura_min();
                    NotificaBean notificaBean = new NotificaBean(sensore.getColtivazione(), sensore.getAzienda(),
                            "ErroreTemperatura", new Timestamp(storico.getOra().getTime()), erroreTemp);
                    System.out.println("***************+" + notificaBean);
                    List<String> listaDipendenti = azienda.ListaEmailDipendenti(sensore.getAzienda());
                    nd.aggiungiNotifica(notificaBean, listaDipendenti);
                }
            } else if (storico.getTipo().matches("Umidità")) {
                //prendo la pianta dal sensore che mi da la coltivazione.
                PiantaBean pianta = pd.ritornaPiantaPerColtivazione(sensore.getColtivazione());
                // se temperatura del sensore rilevata è diversa da max e min della pianta
                if (storico.getValore() > pianta.getUmidita_max()
                        || storico.getValore() < pianta.getUmidita_min()) {
                    NotificaDAO nd = new NotificaDAOImpl();
                    String erroreTemp = "Sensore id: " + sensore.getId()
                            + "\\n Tipo " + sensore.getTipo()
                            + "\\nValore " + valore
                            + "\\nHa rilevato un errore sull'umidità della pianta " + pianta.getNome() + " con"
                            + "\\Umidità massima " + pianta.getUmidita_max()
                            + "\\Umidità minima " + pianta.getUmidita_min();
                    NotificaBean nb = new NotificaBean(sensore.getColtivazione(), sensore.getAzienda(),
                            "ErroreUmidita", new Timestamp(storico.getOra().getTime()), erroreTemp);
                    nd.aggiungiNotifica(nb, azienda.ListaEmailDipendenti(sensore.getAzienda()));
                }
            } else if (storico.getTipo().matches("PH")) {
                //prendo la pianta dal sensore che mi da la coltivazione.
                PiantaBean pianta = pd.ritornaPiantaPerColtivazione(sensore.getColtivazione());
                // se temperatura del sensore rilevata è diversa da max e min della pianta
                if (storico.getValore() > pianta.getPh_max()
                        || storico.getValore() < pianta.getPh_min()) {
                    NotificaDAO nd = new NotificaDAOImpl();
                    String errorePh = "Sensore id: " + sensore.getId()
                            + "\\n Tipo " + sensore.getTipo()
                            + "\\nValore " + valore
                            + "\\nHa rilevato un errore sulla temperatura della pianta " + pianta.getNome() + " con"
                            + "\\Ph massimo " + pianta.getPh_max()
                            + "\\Ph minimo " + pianta.getPh_min();
                    NotificaBean nb = new NotificaBean(sensore.getColtivazione(), sensore.getAzienda(),
                            "ErrorePH", new Timestamp(storico.getOra().getTime()),  errorePh);
                    nd.aggiungiNotifica(nb, azienda.ListaEmailDipendenti(sensore.getAzienda()));
                }
            }

        } else {
            System.out.println("No number found");
        }

    }

    /**
     * This method is called when a message is successfully delivered to the MQTT broker.
     * It can be used to handle any post-delivery logic.
     * @param iMqttDeliveryToken
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    private float prelevaNumero(String messaggio) {
        float number = 0.0f;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messaggio.length(); i++) {
            char c = messaggio.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                sb.append(c);
            } else if (sb.length() > 0) {
                try {
                    number = Float.parseFloat(sb.toString());
                    break;
                } catch (NumberFormatException e) {
                    sb.setLength(0);
                }
            }
        }
        return number;
    }
}
