package CupidonSauce173.events;

import CupidonSauce173.Server;
import CupidonSauce173.objects.Client;
import CupidonSauce173.objects.managers.ClientManager;
import CupidonSauce173.objects.managers.GroupManager;
import CupidonSauce173.objects.managers.PermissionManager;
import io.netty.channel.Channel;

public class ConnectEvent {

    public ConnectEvent(String username, Channel client) throws Exception {
        System.out.println(username);
        System.out.println(username + " logged in.");

        Client player = Client.newBuilder()
                .setDisplayName(username)
                .setGroup(GroupManager.getGroup("Default"))
                .setChannel(client)
                .build();

        ClientManager.registerClient(player);
        PermissionManager.attachClient(player);

        Client test = ClientManager.getClient(username);
        if (test != null) {
            test.sendMessage("Wow that worked!");
        }

        player.sendMessage("Welcome to the chat " + player.username);
        Server.broadcastMessage(player.username + " has joined the chat!");
    }
}
