package io.github.cheivin.assistant.protocol.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cheivin.assistant.IMessageSender;
import io.github.cheivin.assistant.command.Command;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;

public class MQTTMessageSender implements IMessageSender {
    private final MqttClient client;
    private final String topic;
    private final ObjectMapper objectMapper;

    public MQTTMessageSender(MqttClient client) {
        this.client = client;
        this.topic = "command";
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> void sendCommandMessage(T param) {
        Command<T> command = new Command<>("sendMessage", param);
        try {
            client.publish(topic, objectMapper.writeValueAsBytes(command), 1, false);
        } catch (MqttException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
