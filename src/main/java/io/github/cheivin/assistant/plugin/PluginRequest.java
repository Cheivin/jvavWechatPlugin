package io.github.cheivin.assistant.plugin;


import io.github.cheivin.assistant.message.BaseMessage;
import io.github.cheivin.assistant.message.CommandMessage;
import io.github.cheivin.assistant.message.ext.At;
import io.github.cheivin.assistant.message.ext.Quote;

import java.util.Optional;

public class PluginRequest extends BaseMessage {
    /**
     * 命令
     */
    private String command;
    /**
     * 参数
     */
    private String message;
    /**
     * 被艾特的用户,命令中的offset需要-1
     */
    private At[] ates;
    /**
     * 引用消息
     */
    private Quote quote;

    public PluginRequest(String command, String parameters, CommandMessage message) {
        this.msgID = message.getMsgID();
        this.msgType = message.getMsgType();
        this.gid = message.getGid();
        this.groupName = message.getGroupName();
        this.uid = message.getUid();
        this.username = message.getUsername();
        this.wechatName = message.getWechatName();
        this.time = message.getTime();
        this.message = Optional.ofNullable(parameters).orElse("").trim();
        this.command = command;
        this.ates = message.getAtes();
        this.quote = message.getQuote();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}