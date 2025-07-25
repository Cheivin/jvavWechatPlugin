package io.github.cheivin.assistant.message.ext;

import java.util.Arrays;

/**
 * 引用消息
 */
public class Quote {
    private String msgID;
    private String uid;
    private String name;
    private boolean bot;
    private String content;
    /**
     * 19为转发聊天记录
     */
    private int type;
    /**
     * 引用的媒体,type=3
     */
    private Media media;
    /**
     * 引用的网页,type=5
     */
    private WebItem web;
    /**
     * 小程序信息
     */
    private AppBrandItem appBrand;

    /**
     * 转发的聊天记录
     */
    private RecordItem[] record;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public WebItem getWeb() {
        return web;
    }

    public void setWeb(WebItem web) {
        this.web = web;
    }

    public AppBrandItem getAppBrand() {
        return appBrand;
    }

    public void setAppBrand(AppBrandItem appBrand) {
        this.appBrand = appBrand;
    }

    public RecordItem[] getRecord() {
        return record;
    }

    public void setRecord(RecordItem[] record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "msgID='" + msgID + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", bot=" + bot +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", media=" + media +
                ", web=" + web +
                ", appBrand=" + appBrand +
                ", record=" + Arrays.toString(record) +
                '}';
    }
}
