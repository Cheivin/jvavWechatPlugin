package io.github.cheivin.assistant.message;

public class CommandMessage extends BaseMessage {
    private String rawMessage;
    private String command;
    private String parameter;

    public String getRawMessage() {
        return rawMessage;
    }

    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "CommandMessage{" +
                "rawMessage='" + rawMessage + '\'' +
                ", command='" + command + '\'' +
                ", parameter='" + parameter + '\'' +
                ", msgID='" + msgID + '\'' +
                ", msgType=" + msgType +
                ", gid='" + gid + '\'' +
                ", groupName='" + groupName + '\'' +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", time=" + time +
                '}';
    }
}
