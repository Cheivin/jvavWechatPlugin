package io.github.cheivin.assistant;


import io.github.cheivin.assistant.message.Message;

public interface IMessageHandler {
    boolean onMessage(Message message);

    default void onException(Exception e) {
        e.printStackTrace();
    }
}
