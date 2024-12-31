package io.github.cheivin.assistant.protocol.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cheivin.assistant.IMessageSender;
import io.github.cheivin.assistant.command.Command;
import org.java_websocket.client.WebSocketClient;

public class WebSocketMessageSender implements IMessageSender {
    private final WebSocketClient client;
    private final ObjectMapper objectMapper;

    public WebSocketMessageSender(WebSocketClient client) {
        this.client = client;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> void sendCommandMessage(T param) {
        Command<T> command = new Command<>("sendMessage", param);
        try {
            client.send(objectMapper.writeValueAsString(command));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
