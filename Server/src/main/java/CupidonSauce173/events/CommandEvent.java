package CupidonSauce173.events;

import CupidonSauce173.objects.Client;

import java.nio.channels.Channel;

public class CommandEvent {

    public String commandName;
    public Channel sender;

    public CommandEvent(Client client, String commandName, String[] args) {
        this.commandName = commandName;
        this.sender = sender;
    }
}
