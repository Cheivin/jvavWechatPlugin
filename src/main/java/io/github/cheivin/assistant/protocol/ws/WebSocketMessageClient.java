package io.github.cheivin.assistant.protocol.ws;

import java.net.URI;
import java.time.Duration;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cheivin.assistant.IMessageHandler;
import io.github.cheivin.assistant.IMessageSender;
import io.github.cheivin.assistant.message.Message;
import io.github.cheivin.assistant.protocol.IPluginClient;

public class WebSocketMessageClient extends WebSocketClient implements IPluginClient {
    private final ScheduledExecutorService reconnectTask = Executors.newSingleThreadScheduledExecutor();
    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private IMessageHandler handler;

    public WebSocketMessageClient(String serverUri, String username, String password) {
        super(URI.create(serverUri), Map.of("Authorization", String.format("Basic %s", Base64.getEncoder()
                .encodeToString(String.format("%s:%s", username, password).getBytes()))));
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        reconnectTask.scheduleWithFixedDelay(() -> {
            if (!isOpen()) {
                reconnect();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void onMessage(String payload) {
        if (handler == null) {
            return;
        }
        try {
            Message msg = mapper.readValue(payload, Message.class);
            handler.onMessage(msg);
        } catch (Exception e) {
            handler.onException(e);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {
        if (handler != null) {
            handler.onException(ex);
        }
    }

    public void start(IMessageHandler handler) throws InterruptedException {
        start(handler, Duration.ofSeconds(30));
    }

    public void start(IMessageHandler handler, Duration timeout) throws InterruptedException {
        if (this.handler != null) {
            return;
        }
        this.handler = handler;
        connectBlocking(timeout.toMillis(), TimeUnit.MILLISECONDS);
    }

    public void stop() throws InterruptedException {
        reconnectTask.shutdown();
        closeBlocking();
        handler = null;
    }

    public IMessageSender getSender() {
        return new WebSocketMessageSender(this);
    }
}
