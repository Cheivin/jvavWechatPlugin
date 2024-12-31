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
     * 发送媒体资源前的提示词,会自动撤回
     */
    private String prompt;

    public PluginResponse() {
    }

    public static PluginResponse noReply() {
        return new PluginResponse();
    }

    public static PluginResponse error(String error) {
        return text(error);
    }

    public static PluginResponse text(String body) {
        PluginResponse response = new PluginResponse();
        response.setType(1);
        response.setBody(body);
        return response;
    }

    public static PluginResponse image(String uri, String filename) {
        return PluginResponse.image(uri, filename, null);
    }

    public static PluginResponse image(String uri, String filename, String prompt) {
        PluginResponse response = new PluginResponse();
        response.setType(2);
        response.setBody(uri);
        response.setFilename(filename);
        response.setPrompt(prompt);
        return response;
    }

    public static PluginResponse video(String uri, String filename) {
        return PluginResponse.video(uri, filename, null);
    }

    public static PluginResponse video(String uri, String filename, String prompt) {
        PluginResponse response = new PluginResponse();
        response.setType(3);
        response.setBody(uri);
        response.setFilename(filename);
        response.setPrompt(prompt);
        return response;
    }

    public static PluginResponse file(String uri, String filename) {
        return PluginResponse.file(uri, filename, null);
    }

    public static PluginResponse file(String uri, String filename, String prompt) {
        PluginResponse response = new PluginResponse();
        response.setType(4);
        response.setBody(uri);
        response.setFilename(filename);
        response.setPrompt(prompt);
        return response;
    }

    @Override
    public String toString() {
        return "PluginResponse{" +
                ", type=" + type +
                ", body='" + body + '\'' +
                ", filename='" + filename + '\'' +
                ", prompt='" + prompt + '\'' +
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

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}