package my;

import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.NotificaBean;
import it.unisa.greenmonitoring.dataccess.beans.PiantaBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.dao.*;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;


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

        //prendo il valore dal messaggio
        String messaggio = new String(message.getPayload());
        float valore = prelevaNumero(messaggio);
        if (valore != 0.0f) {
            System.out.println("Number found: " + valore);
            MisurazioneSensoreDAO msd = new MisurazioneSensoreDAOImpl();
            SensoreDAO sensoreDAO = new SensoreDAOImpl();
            //prendi il sensore con l idM
            SensoreBean sensore = sensoreDAO.retrieveByidM(topic);
            //crea uno storico.
            MisurazioneSensoreBean storico = new MisurazioneSensoreBean(new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()),
                    sensore.getTipo(), (int) valore, sensore.getId(), sensore.getColtivazione());
            msd.createMisurazioneManuel(storico);
            PiantaDAO pd= new PiantaDAOImpl();
            if (storico.getTipo().matches("temperature")) {

                //prendo la pianta dal sensore che mi da la coltivazione.
                PiantaBean pianta = pd.ritornaPiantaPerColtivazione(sensore.getColtivazione());

                // se temperatura del sensore rilevata è diversa da max e min della pianta
                if (storico.getValore() > Float.parseFloat(pianta.getTemperatura_max())
                        || storico.getValore() < Float.parseFloat(pianta.getTemperatura_min())) {
                    NotificaDAO nd= new NotificaDAOImpl();
                    NotificaBean nb = new NotificaBean(sensore.getColtivazione(), sensore.getAzienda(),
                            "ErroreTemperatura", new Timestamp(storico.getOra().getTime()), "errore nella temperatura");
                    System.out.println(nb);
                    nd.aggiungiNotifica(nb);
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
