package io.github.cheivin.assistant.message.ext;

public class Quote {
    private String uid;
    private String name;
    private boolean bot;
    private String content;

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

    @Override
    public String toString() {
        return "Quote{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", bot=" + bot +
                ", content='" + content + '\'' +
                '}';
    }
}
