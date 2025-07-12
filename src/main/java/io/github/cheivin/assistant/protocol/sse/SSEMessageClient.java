package io.github.cheivin.assistant.protocol.sse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cheivin.assistant.IMessageHandler;
import io.github.cheivin.assistant.IMessageSender;
import io.github.cheivin.assistant.message.Message;
import io.github.cheivin.assistant.protocol.IPluginClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class SSEMessageClient implements IPluginClient {
    private static final Logger log = LoggerFactory.getLogger(SSEMessageClient.class);
    private final URI serverUri;
    private final String authorization;
    private final HttpClient client;
    private final HttpRequest request;
    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final ScheduledExecutorService reconnectTask = Executors.newSingleThreadScheduledExecutor();
    private final AtomicBoolean isOpen = new AtomicBoolean(false);
    private IMessageHandler handler;
    private CompletableFuture<?> connFuture;

    public SSEMessageClient(String serverUri, String username, String password) {
        this.serverUri = URI.create(serverUri);
        this.authorization = String.format("Basic %s", Base64.getEncoder().encodeToString(String.format("%s:%s", username, password).getBytes()));
        this.client = HttpClient.newHttpClient();
        this.request = HttpRequest.newBuilder()
                .uri(this.serverUri)
                .header("Authorization", authorization)
                .GET()
                .build();

    }

    @Override
    public void start(IMessageHandler handler) {
        if (this.handler != null) {
            return;
        }
        this.handler = handler;
        reconnectTask.scheduleWithFixedDelay(() -> {
            if (!isOpen.get()) {
                connect();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        reconnectTask.shutdown();
        handler = null;
        if (connFuture != null) {
            connFuture.cancel(true);
        }
    }

    @Override
    public IMessageSender getSender() {
        return new SSEMessageSender(serverUri, authorization, client);
    }

    private void connect() {
        isOpen.set(true);
        connFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofLines())
                .thenApply(response -> response.body()
                        .filter(line -> Objects.nonNull(line) && line.startsWith("data: "))
                        .map(line -> line.substring(6))
                )
                .thenAcceptAsync(lines -> lines.forEach(this::onMessage))
                .exceptionally(e -> {
                    isOpen.set(false);
                    if (e instanceof CompletionException) {
                        e = e.getCause();
                    }
                    if (e instanceof EOFException) {
                        log.error("EOFException", e);
                        return null;
                    } else if (e instanceof ConnectException) {
                        log.error("ConnectException", e);
                        return null;
                    } else if (e instanceof IOException) {
                        log.error("IOException", e);
                        return null;
                    }
                    handler.onException(new Exception(e));
                    return null;
                });
    }

    private void onMessage(String payload) {
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
}
