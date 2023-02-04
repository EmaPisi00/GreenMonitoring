
package my;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

/**
 * cose.
 */
public class Listener implements ServletContextListener {

    /**
     *
     * @param sce
     */
    @Override
    public final void contextInitialized(final ServletContextEvent sce) {
        String brokerUrl = "tcp://localhost:1883";
        String clientId = "mySubscriberClientId";

        //prendi sensori dal db con avvia coltivazione true
        //List of sensors
        List<String> sensors = new ArrayList<>();
        sensors.add("temperature/1");
        sensors.add("temperature/2");
        sensors.add("temperature/3");
        sensors.add("temperature/4");
        sensors.add("temperature/5");
        sensors.add("temperature/6");
        sensors.add("temperature/7");
        sensors.add("temperature/8");
        sensors.add("temperature/9");
        sensors.add("temperature/10");
        sensors.add("humidity/1");
        sensors.add("humidity/2");
        sensors.add("humidity/3");
        sensors.add("humidity/4");
        sensors.add("humidity/5");
        sensors.add("humidity/6");
        sensors.add("humidity/7");
        sensors.add("humidity/8");
        sensors.add("humidity/9");
        sensors.add("humidity/10");
        MemoryPersistence persistence = new MemoryPersistence();

        try {

            // create the MQTT client
            MqttClient client = new MqttClient(brokerUrl, clientId, persistence);

            // create the MQTT connection options
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);

            // connect to the MQTT broker
            client.connect(options);

            // print the received messages
            MqttMessagePrinter printer = new MqttMessagePrinter();
            client.setCallback(printer);

            // start the message processing loop
            for (String sensor : sensors) {
                // subscribe to the MQTT topic
                client.subscribe(sensor);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param sce
     */
    @Override
    public final void contextDestroyed(final ServletContextEvent sce) {

    }
}
