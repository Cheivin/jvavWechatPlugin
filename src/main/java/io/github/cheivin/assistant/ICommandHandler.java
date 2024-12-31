package io.github.cheivin.assistant;


import io.github.cheivin.assistant.message.CommandMessage;

public interface ICommandHandler {

    boolean onCommand(String command, String parameters, CommandMessage message);

}
