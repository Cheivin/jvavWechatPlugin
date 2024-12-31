package io.github.cheivin.assistant.message.ext;

public class Media {
    private String filename;
    private String src;
    private String size;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Media{" +
                "filename='" + filename + '\'' +
                ", src='" + src + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
