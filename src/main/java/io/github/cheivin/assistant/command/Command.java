package io.github.cheivin.assistant.command;

public class Command<T> {
    private String command;
    private T param;

    public Command() {
    }

    public Command(String command, T param) {
        this.command = command;
        this.param = param;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
