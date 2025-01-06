package io.github.cheivin.assistant.protocol;

import io.github.cheivin.assistant.IMessageHandler;
import io.github.cheivin.assistant.IMessageSender;

public interface IPluginClient {
    void start(IMessageHandler handler) throws Exception;

    void stop() throws Exception;

    IMessageSender getSender();
}
