package my;

import it.unisa.greenmonitoring.dataccess.beans.MisurazioneSensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.dao.MisurazioneSensoreDAO;
import it.unisa.greenmonitoring.dataccess.dao.MisurazioneSensoreDAOImpl;
import it.unisa.greenmonitoring.dataccess.dao.SensoreDAO;
import it.unisa.greenmonitoring.dataccess.dao.SensoreDAOImpl;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;


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
            //MisurazioneSensoreBean msb(data,time,tipo,valore), Integer coltivazioneId, Integer sensoreId
            MisurazioneSensoreBean storico = new MisurazioneSensoreBean(new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()),
                    sensore.getTipo(), (int) valore);
            msd.createMisurazioneManuel(storico, sensore);

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
