package io.github.cheivin.assistant.message.ext;

public class Revoke {
    public String oldMsgID;
    public String replaceMsg;

    public String getOldMsgID() {
        return oldMsgID;
    }

    public void setOldMsgID(String oldMsgID) {
        this.oldMsgID = oldMsgID;
    }

    public String getReplaceMsg() {
        return replaceMsg;
    }

    public void setReplaceMsg(String replaceMsg) {
        this.replaceMsg = replaceMsg;
    }

    @Override
    public String toString() {
        return "Revoke{" +
                "oldMsgID='" + oldMsgID + '\'' +
                ", replaceMsg='" + replaceMsg + '\'' +
                '}';
    }
}
