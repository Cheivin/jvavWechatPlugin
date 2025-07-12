package io.github.cheivin.assistant.command;

public class SendMessageParam {
    /**
     * 群id
     */
    private String gid;
    /**
     * 回复类型 1:文本,2:图片,3:视频,4:文件
     */
    private int type;
    /**
     * 回复内容,type=1时为文本内容,type=2/3/4时为资源地址
     */
    private String body;
    /**
     * 文件名称
     */
    private String filename;
    /**
     * 被at的用户id列表,用英文逗号分隔,仅type=1时有效,正文内容中要有与at的用户id对应的@xxx占位符,否则不生效
     */
    private String at;


    public SendMessageParam() {
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }
}
