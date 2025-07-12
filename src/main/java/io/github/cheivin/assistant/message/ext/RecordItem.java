package io.github.cheivin.assistant.message.ext;

import java.util.Arrays;

/**
 * 转发聊天记录
 */
public class RecordItem {
    private String name;
    private int time;
    private String content;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "RecordItem{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", media=" + media +
                ", web=" + web +
                ", appBrand=" + appBrand +
                ", record=" + Arrays.toString(record) +
                '}';
    }
}
