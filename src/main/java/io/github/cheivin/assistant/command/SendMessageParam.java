package io.github.cheivin.assistant.command;

public class SendMessageParam {
    /**
     * 群id
     */
    private String gid;
    /**
     * 回复类型 -1:不处理,0:不回复,1:文本,2:图片,3:视频,4:文件
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
     * 发送媒体资源前的提示词,会自动撤回
     */
    private String prompt;

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

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
