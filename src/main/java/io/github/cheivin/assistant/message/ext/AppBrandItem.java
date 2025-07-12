package io.github.cheivin.assistant.message.ext;

/**
 * 小程序信息
 */
public class AppBrandItem {
    private String displayName;
    private String username;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AppBrandItem{" +
                "displayName='" + displayName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
