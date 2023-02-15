
package my;

//import it.unisa.greenmonitoring.businesslogic.gestionemonitoraggio.ColtivazioneManager;
import it.unisa.greenmonitoring.businesslogic.gestionesensore.SensoreManager;
//import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.beans.SensoreBean;
import it.unisa.greenmonitoring.dataccess.dao.SensoreDAO;
import it.unisa.greenmonitoring.dataccess.dao.SensoreDAOImpl;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;


/**
 * cose.
 */
public class Listener implements ServletContextListener {

    /**
     * sensore.
     */
    private SensoreDAO sd;
    /**
     *
     * @param sce
     */
    @Override
    public final void contextInitialized(final ServletContextEvent sce) {
        String brokerUrl = "tcp://localhost:1883";
        String clientId = "mySubscriberClientId";

        //prendi sensori dal db con avvia coltivazione true
        sd = new SensoreDAOImpl();
        ArrayList<SensoreBean> listaSensori = (ArrayList<SensoreBean>) sd.SensoriColtivazioneAvviata();
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

            /*start the message processing loop
            for (String sensor : sensors) {
                // subscribe to the MQTT topic
                client.subscribe(sensor);
            }
             */
            for (SensoreBean sensori : listaSensori) {
                client.subscribe(sensori.getIdM());
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
