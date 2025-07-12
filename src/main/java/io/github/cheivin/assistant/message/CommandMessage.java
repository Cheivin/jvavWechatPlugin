package io.github.cheivin.assistant.message;

import io.github.cheivin.assistant.message.ext.At;
import io.github.cheivin.assistant.message.ext.Quote;

import java.util.Arrays;

public class CommandMessage extends BaseMessage {
    private String rawMessage;
    private String command;
    private String parameter;
    /**
     * 被艾特的用户,命令中的offset需要-1
     */
    private At[] ates;
    /**
     * 引用消息
     */
    private Quote quote;

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

    public At[] getAtes() {
        return ates;
    }

    public void setAtes(At[] ates) {
        this.ates = ates;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "CommandMessage{" +
                "rawMessage='" + rawMessage + '\'' +
                ", command='" + command + '\'' +
                ", parameter='" + parameter + '\'' +
                ", ates=" + Arrays.toString(ates) +
                ", quote=" + quote +
                ", msgID='" + msgID + '\'' +
                ", msgType=" + msgType +
                ", gid='" + gid + '\'' +
                ", groupName='" + groupName + '\'' +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", wechatName='" + wechatName + '\'' +
                ", time=" + time +
                '}';
    }
}
