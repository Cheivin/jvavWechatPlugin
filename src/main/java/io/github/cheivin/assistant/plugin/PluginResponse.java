package io.github.cheivin.assistant.plugin;

public class PluginResponse {
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
     * 被at的用户id列表,仅type=1时有效,正文内容中要有与at的用户id对应的@xxx占位符,否则不生效
     */
    private String[] at;

    public PluginResponse() {
    }

    public static PluginResponse noReply() {
        return new PluginResponse();
    }

    public static PluginResponse error(String error) {
        return text(error);
    }

    public static PluginResponse text(String body, String... at) {
        PluginResponse response = new PluginResponse();
        response.setType(1);
        response.setBody(body);
        response.setAt(at);
        return response;
    }

    public static PluginResponse image(String uri, String filename) {
        PluginResponse response = new PluginResponse();
        response.setType(2);
        response.setBody(uri);
        response.setFilename(filename);
        return response;
    }

    public static PluginResponse video(String uri, String filename) {
        PluginResponse response = new PluginResponse();
        response.setType(3);
        response.setBody(uri);
        response.setFilename(filename);
        return response;
    }

    public static PluginResponse file(String uri, String filename) {
        PluginResponse response = new PluginResponse();
        response.setType(4);
        response.setBody(uri);
        response.setFilename(filename);
        return response;
    }

    @Override
    public String toString() {
        return "PluginResponse{" +
                "type=" + type +
                ", body='" + body + '\'' +
                ", filename='" + filename + '\'' +
                '}';
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

    public String[] getAt() {
        return at;
    }

    public void setAt(String[] at) {
        this.at = at;
    }
}