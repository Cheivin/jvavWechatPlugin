package io.github.cheivin.assistant.protocol.mqtt;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cheivin.assistant.IMessageHandler;
import io.github.cheivin.assistant.IMessageSender;
import io.github.cheivin.assistant.message.Message;
import io.github.cheivin.assistant.protocol.IPluginClient;
import org.eclipse.paho.mqttv5.client.IMqttMessageListener;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttSubscription;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

public class MQTTMessageClient implements IPluginClient {
    private final static String TOPIC = "message";
    private final MqttClient client;
    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private boolean isStart = false;

    public MQTTMessageClient(String broker, String username, String password, String clientId) throws MqttException {
        if (clientId == null || clientId.isEmpty()) {
            try {
                InetAddress localhost = InetAddress.getLocalHost();
                String hostname = localhost.getHostName();
                clientId = username + "@" + hostname;
            } catch (UnknownHostException ignore) {
                clientId = username + "@unknown";
            }
        }
        MemoryPersistence persistence = new MemoryPersistence();
        client = new MqttClient(broker, clientId, persistence);
        MqttConnectionOptions connOptions = new MqttConnectionOptions();
        connOptions.setUserName(username);
        connOptions.setPassword(password.getBytes());
        connOptions.setAutomaticReconnect(true);
        connOptions.setSessionExpiryInterval(Duration.ofDays(1).toSeconds());
        client.connect(connOptions);


    }

    public MQTTMessageClient(String broker, String username, String password) throws MqttException {
        this(broker, username, password, null);
    }

    public void start(String topic, IMessageHandler handler) throws MqttException {
        if (isStart) {
            return;
        }
        isStart = true;
        client.subscribe(new MqttSubscription[]{new MqttSubscription(topic)}, new IMqttMessageListener[]{(t, message) -> {
            try {
                Message msg = mapper.readValue(message.getPayload(), Message.class);
                handler.onMessage(msg);
            } catch (Exception e) {
                handler.onException(e);
            }
        }});
    }

    public void start(IMessageHandler handler) throws MqttException {
        start(TOPIC, handler);
    }

    public void stop() throws MqttException {
        client.disconnect();
    }

    public IMessageSender getSender() {
        return new MQTTMessageSender(client);
    }

}
