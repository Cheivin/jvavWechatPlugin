package io.github.cheivin.assistant.plugin;


import io.github.cheivin.assistant.message.CommandMessage;

import java.util.Optional;

public class PluginRequest {
    private String msgID;
    private String uid;
    private String username;
    private String gid;
    private String groupName;
    private String message;
    private int msgType;
    private long time;
    private String command;

    public PluginRequest(String command, String parameters, CommandMessage message) {
        this.msgID = message.getMsgID();
        this.uid = message.getUid();
        this.username = message.getUsername();
        this.gid = message.getGid();
        this.groupName = message.getGroupName();
        this.message = Optional.ofNullable(parameters).orElse("").trim();
        this.msgType = message.getMsgType();
        this.time = message.getTime();
        this.command = command;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}