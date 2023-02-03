package my;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

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
    public void messageArrived(String topic, MqttMessage message) throws MqttException {
        System.out.println("Received message: " + "Topic= " + topic + new String(message.getPayload()));
        //chiama DAO per salvare nel db misurazione sensore (crea bean misurazioneSensore)(crea DAO)
    }

    /**
     * This method is called when a message is successfully delivered to the MQTT broker.
     * It can be used to handle any post-delivery logic.
     * @param iMqttDeliveryToken
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
