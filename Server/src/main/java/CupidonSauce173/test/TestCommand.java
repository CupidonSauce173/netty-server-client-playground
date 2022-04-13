package CupidonSauce173.test;

import CupidonSauce173.Server;
import CupidonSauce173.objects.Client;
import CupidonSauce173.objects.Command;


public class TestCommand extends Command {

    public TestCommand(String name, String permission) {
        super(name, permission);
    }

    @Override
    public void registerExecutable(Client sender, String[] args) {

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            message.append(args[i]).append(" ");
        }
        Server.broadcastMessage(sender.group.tag + " [" + sender.username + "] " + message);
        System.out.println(sender.username + " has executed the test command successfully!");
    }
}
