# jvav群小助手java插件开发

## 安装

```xml
<dependency>
    <groupId>io.github.cheivin</groupId>
    <artifactId>jvavwechatplugin</artifactId>
    <version>1.0.1</version>
</dependency>
```

### mqtt协议客户端示例

```java

import io.github.cheivin.assistant.message.CommandMessage;
import io.github.cheivin.assistant.message.Message;
import io.github.cheivin.assistant.plugin.AbstractPluginHandler;
import io.github.cheivin.assistant.plugin.PluginRequest;
import io.github.cheivin.assistant.plugin.PluginResponse;
import io.github.cheivin.assistant.protocol.mqtt.MQTTMessageClient;
import org.eclipse.paho.mqttv5.common.MqttException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class MQTTMessageListenerTest {
    public static void main(String[] args) throws MqttException {
        MQTTMessageClient client = new MQTTMessageClient("tcp://127.0.0.1:1883", "username", "password");

        ExampleHandler handler = new ExampleHandler().addPlugin(new AbstractPluginHandler(client.getSender()) {
            @Override
            public List<String> getCommands() {
                return List.of("ping");
            }

            @Override
            public String getDescription() {
                return "ping pong消息";
            }

            @Override
            public PluginResponse handle(PluginRequest request) {
                return PluginResponse.text("pong");
            }
        });

        client.start(handler);

        CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread(latch::countDown));

        System.out.println("开始监听消息，按 Ctrl+C 退出...");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("程序即将退出...");
    }

    static class ExampleHandler implements IMessageHandler {
        private final Map<String, ICommandHandler> commands;

        public ExampleHandler() {
            this.commands = new HashMap<>();
        }

        public ExampleHandler addPlugin(AbstractPluginHandler handler) {
            for (String command : handler.getCommands()) {
                commands.put(command, handler);
            }
            return this;
        }

        @Override
        public boolean onMessage(Message message) {
            System.out.printf("%d %s: %s\n", message.getTime(), message.getUsername(), message.getContent());
            CommandMessage cmd = message.toCommandMessage();
            if (cmd != null && commands.containsKey(cmd.getCommand())) {
                commands.get(cmd.getCommand()).onCommand(cmd.getCommand(), cmd.getParameter(), cmd);
            }
            return true;
        }
    }
}
```

### ws协议客户端示例

```java

import io.github.cheivin.assistant.message.CommandMessage;
import io.github.cheivin.assistant.message.Message;
import io.github.cheivin.assistant.plugin.AbstractPluginHandler;
import io.github.cheivin.assistant.plugin.PluginRequest;
import io.github.cheivin.assistant.plugin.PluginResponse;
import io.github.cheivin.assistant.protocol.ws.WebSocketMessageClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class WebSocketMessageListenerTest {
    public static void main(String[] args) throws InterruptedException {
        WebSocketMessageClient client = new WebSocketMessageClient("tcp://127.0.0.1:8080", "username", "password");
        ExampleHandler handler = new ExampleHandler().addPlugin(new AbstractPluginHandler(client.getSender()) {
            @Override
            public List<String> getCommands() {
                return List.of("ping");
            }

            @Override
            public String getDescription() {
                return "ping pong消息";
            }

            @Override
            public PluginResponse handle(PluginRequest request) {
                return PluginResponse.text("pong");
            }
        });

        client.start(handler);

        CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(new Thread(latch::countDown));

        System.out.println("开始监听消息，按 Ctrl+C 退出...");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("程序即将退出...");
    }

    static class ExampleHandler implements IMessageHandler {
        private final Map<String, ICommandHandler> commands;

        public ExampleHandler() {
            this.commands = new HashMap<>();
        }

        public ExampleHandler addPlugin(AbstractPluginHandler handler) {
            for (String command : handler.getCommands()) {
                commands.put(command, handler);
            }
            return this;
        }

        @Override
        public boolean onMessage(Message message) {
            System.out.printf("%d %s: %s\n", message.getTime(), message.getUsername(), message.getContent());
            CommandMessage cmd = message.toCommandMessage();
            if (cmd != null && commands.containsKey(cmd.getCommand())) {
                commands.get(cmd.getCommand()).onCommand(cmd.getCommand(), cmd.getParameter(), cmd);
            }
            return true;
        }
    }
}
```