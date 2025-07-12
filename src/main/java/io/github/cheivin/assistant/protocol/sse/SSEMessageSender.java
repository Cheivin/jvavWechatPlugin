package io.github.cheivin.assistant.protocol.sse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cheivin.assistant.IMessageSender;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SSEMessageSender implements IMessageSender {
    private final URI serverUri;
    private final String authorization;
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public SSEMessageSender(URI serverUri, String authorization, HttpClient client) {
        this.serverUri = serverUri.resolve("/msg/send");
        this.authorization = authorization;
        this.client = client;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> void sendCommandMessage(T param) {
        String json;
        try {
            json = objectMapper.writeValueAsString(param);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        client.sendAsync(HttpRequest.newBuilder()
                .uri(serverUri)
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .header("Authorization", authorization)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build(), HttpResponse.BodyHandlers.ofString());
    }
}
