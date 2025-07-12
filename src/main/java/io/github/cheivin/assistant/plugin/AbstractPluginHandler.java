package io.github.cheivin.assistant.plugin;

import io.github.cheivin.assistant.ICommandHandler;
import io.github.cheivin.assistant.IMessageSender;
import io.github.cheivin.assistant.message.CommandMessage;

import java.util.List;

public abstract class AbstractPluginHandler implements ICommandHandler {
    protected final IMessageSender messageSender;

    protected AbstractPluginHandler(IMessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public boolean onCommand(String command, String parameters, CommandMessage message) {
        if (!getCommands().contains(command)) {
            return false;
        }
        try {
            PluginResponse response = handle(new PluginRequest(command, parameters, message));
            if (response == null) {
                return false;
            }
            switch (response.getType()) {
                case 0:
                    if (response.getBody() != null && !response.getBody().isEmpty()) {
                        messageSender.sendTextMsgByGId(message.getGid(), response.getBody());
                    }
                    return true; // 不回复/错误
                case 1:
                    messageSender.sendTextMsgByGId(message.getGid(), response.getBody(), response.getAt());
                    return true; // 文本

                case 2:
                    messageSender.sendImageMsgByGId(message.getGid(), response.getBody(), response.getFilename());
                    return true; // 图片

                case 3:
                    messageSender.sendVideoMsgByGId(message.getGid(), response.getBody(), response.getFilename());
                    return true; // 视频

                case 4:
                    messageSender.sendFileMsgByGId(message.getGid(), response.getBody(), response.getFilename());
                    return true; // 文件
                case -1:// 不处理
                    return false;
                default:
                    return false;
            }
        } catch (Throwable e) {
            messageSender.sendTextMsgByGId(message.getGid(), command + ":[Exception]" + e.getMessage());
        }
        return true;
    }

    public abstract List<String> getCommands();

    public abstract String getDescription();

    public abstract PluginResponse handle(PluginRequest request);
}
