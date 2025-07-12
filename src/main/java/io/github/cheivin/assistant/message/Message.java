package io.github.cheivin.assistant.message;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.cheivin.assistant.message.ext.*;

public class Message extends BaseMessage {
    /**
     * 正文
     */
    private String content;
    /**
     * 被艾特的用户
     */
    private At[] ates;
    /**
     * 引用消息
     */
    private Quote quote;
    /**
     * 撤回消息
     */
    private Revoke revoke;
    /**
     * 媒体消息,type=3
     */
    private Media media;

    /**
     * 转发记录消息,type=19
     */
    private String title;
    private String desc;
    private RecordItem[] datalist;

    /**
     * 系统事件消息
     */
    private String event;

    /**
     * 系统事件消息附加数据
     */
    private JsonNode data;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Revoke getRevoke() {
        return revoke;
    }

    public void setRevoke(Revoke revoke) {
        this.revoke = revoke;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RecordItem[] getDatalist() {
        return datalist;
    }

    public void setDatalist(RecordItem[] datalist) {
        this.datalist = datalist;
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
        msg.setAtes(getAtes());
        msg.setQuote(getQuote());
        return msg;
    }

}
