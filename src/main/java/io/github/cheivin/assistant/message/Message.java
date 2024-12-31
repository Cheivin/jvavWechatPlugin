package io.github.cheivin.assistant.message;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.cheivin.assistant.message.ext.At;
import io.github.cheivin.assistant.message.ext.Media;
import io.github.cheivin.assistant.message.ext.Quote;
import io.github.cheivin.assistant.message.ext.Revoke;

public class Message extends BaseMessage {
    private String content;
    private Quote quote;
    private Revoke revoke;
    private At at;
    private Media media;
    private String event;
    private JsonNode data;

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public Revoke getRevoke() {
        return revoke;
    }

    public void setRevoke(Revoke revoke) {
        this.revoke = revoke;
    }

    public At getAt() {
        return at;
    }

    public void setAt(At at) {
        this.at = at;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public CommandMessage toCommandMessage() {
        if (getContent().length() < 2 || !getContent().startsWith("#")) {
            return null;
        }
        CommandMessage msg = new CommandMessage();
        msg.setMsgID(getMsgID());
        msg.setMsgType(getMsgType());
        msg.setGid(getGid());
        msg.setGroupName(getGroupName());
        msg.setUid(getUid());
        msg.setUsername(getUsername());
        msg.setTime(getTime());
        msg.setRawMessage(getContent());
        String[] part = getContent().substring(1).trim().split(" ", 2);
        msg.setCommand(part[0]);
        if (part.length > 1) {
            msg.setParameter(part[1]);
        } else {
            msg.setParameter("");
        }
        return msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", quote=" + quote +
                ", revoke=" + revoke +
                ", at=" + at +
                ", media=" + media +
                ", event='" + event + '\'' +
                ", data=" + data +
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
