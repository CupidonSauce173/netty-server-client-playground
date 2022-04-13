package CupidonSauce173.events;

import CupidonSauce173.ChatServerHandler;
import CupidonSauce173.objects.Client;
import io.netty.channel.Channel;

import java.util.Map;

public class DisconnectEvent {

    public DisconnectEvent(Client client, String reason) {

        Map<String, Channel> clients = ChatServerHandler.getClients();
        String username = null;

        for (String u : clients.keySet()) {
            if (clients.get(u) == client) {
                username = u;
            }
        }


        // notify other users.
        clients.remove(username);
        for (Channel channel : ChatServerHandler.channels) {
            if (channel != client.channel) {
                channel.writeAndFlush("[" + username + "] has left the chat!");
            }
        }
    }
}
