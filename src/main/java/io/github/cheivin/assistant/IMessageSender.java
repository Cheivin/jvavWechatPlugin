package io.github.cheivin.assistant;


import io.github.cheivin.assistant.command.SendMessageParam;

public interface IMessageSender {

    <T> void sendCommandMessage(T param);

    default void sendTextMsgByGId(String gid, String msg) {
        SendMessageParam param = new SendMessageParam();
        param.setGid(gid);
        param.setType(1);
        param.setBody(msg);
        sendCommandMessage(param);
    }

    default void sendTextMsgByGId(String gid, String msg, String... at) {
        SendMessageParam param = new SendMessageParam();
        param.setGid(gid);
        param.setType(1);
        param.setBody(msg);
        if (at != null && at.length > 0) {
            param.setAt(String.join(",", at));
        }
        sendCommandMessage(param);
    }

    default void sendImageMsgByGId(String gid, String uri, String filename) {
        SendMessageParam param = new SendMessageParam();
        param.setGid(gid);
        param.setType(2);
        param.setFilename(filename);
        param.setBody(uri);
        sendCommandMessage(param);
    }

    default void sendVideoMsgByGId(String gid, String uri, String filename) {
        SendMessageParam param = new SendMessageParam();
        param.setGid(gid);
        param.setType(3);
        param.setFilename(filename);
        param.setBody(uri);
        sendCommandMessage(param);
    }

    default void sendFileMsgByGId(String gid, String uri, String filename) {
        SendMessageParam param = new SendMessageParam();
        param.setGid(gid);
        param.setType(4);
        param.setFilename(filename);
        param.setBody(uri);
        sendCommandMessage(param);
    }

}
