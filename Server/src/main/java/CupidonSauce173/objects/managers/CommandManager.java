package CupidonSauce173.objects.managers;

import CupidonSauce173.objects.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private static Map<String, Command> commandMap = new HashMap<>();

    /**
     * Register a Command object to the commandMap.
     *
     * @param command Command object to register in the commandMap.
     */
    public static void registerCommand(Command command) {
        if (commandMap.containsKey(command.name)) {
            System.out.println("Command " + command.name + " has already been added!");
            return;
        }
        commandMap.put(command.name, command);
        System.out.println(command.name + " command has been registered with permission [" + command.permission + "]");
    }

    //region unregister methods

    /**
     * Unregister a Command object from the commandMap.
     *
     * @param name String name of the command.
     */
    public static void unregisterCommand(String name) {
        commandMap.remove("name");
    }

    /**
     * Unregister a Command object from the commandMap.
     *
     * @param command Command object to be registered.
     */
    public static void unregisterCommand(Command command) {
        commandMap.remove(command.name);
    }
    //endregion

    /**
     * Get a command object by name.
     *
     * @param name String name of the command.
     * @return Returns the Command Object.
     */
    public static Command getCommand(String name) {
        for (Command command : commandMap.values()) {
            if (command.name.equals(name)) {
                return command;
            }
        }
        return null;
    }
}
