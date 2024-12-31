package io.github.cheivin.assistant.message.ext;


public class At {
    private String uid;
    private String name;
    private boolean bot;
    private int offset;
    private int length;

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

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "At{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", bot=" + bot +
                ", offset=" + offset +
                ", length=" + length +
                '}';
    }
}
