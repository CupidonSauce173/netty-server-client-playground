package CupidonSauce173.events;

import CupidonSauce173.objects.Client;
import CupidonSauce173.objects.managers.ClientManager;
import CupidonSauce173.objects.managers.CommandManager;

import java.util.Objects;

public class MessageEvent {

    public MessageEvent(Client sender, String message) {
        Objects.requireNonNull(sender);
        if (message.charAt(0) == '/') { // command requested.
            String requestCmd = message.substring(1, message.indexOf(" "));
            String newMessage = message.substring(message.indexOf(" ") + 1);
            String[] args = newMessage.split(" ");

            System.out.println("Command requested by " + sender);
            System.out.println("Target Command: " + requestCmd);

            var cmd = CommandManager.getCommand(requestCmd);
            if (cmd != null) {
                cmd.registerExecutable(sender, args);
            }
            return;
        }

        // Send message to everyone
        String msg = "[" + sender.username + "] " + message;
        System.out.println(msg);
        for (Client client : ClientManager.getClientMap().values()) {
            if (client != sender) {
                client.sendMessage(msg);
            }
        }
    }
}
