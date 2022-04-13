package CupidonSauce173.objects.managers;

import CupidonSauce173.objects.Client;
import CupidonSauce173.objects.Permission;

import java.util.HashMap;
import java.util.Map;

public class PermissionManager {


    private static Map<String, Permission> permissionMap = new HashMap<>(); // Permission list in the server.
    private static Map<String, Client> clients = new HashMap<>();

    public static void attachClient(Client client) throws Exception {
        if (client.channel == null) {
            throw new Exception("No channel declared in the client object.");
        }
        clients.put(client.id, client);
    }

    /**
     * Detach a client from the PermissionManager.
     * @param client Client object to be detached.
     */
    public static void detachClient(Client client) {
        clients.remove(client.id);
    }

    /**
     * Get a Client object
     * @param client
     * @return
     */
    public static Client getClient(Client client) {
        if (clients.containsKey(client.id)) {
            return clients.get(client.id);
        }
        return null;
    }

    /**
     * Register a Permission object in the permissionMap.
     *
     * @param permission Permission object to be registered.
     */
    public static void registerPermission(Permission permission) {
        permissionMap.put(permission.name, permission);
        System.out.println(permission.name + " permission has been registered.");
    }

    //region unregister methods.

    /**
     * Unregister a Permission object from the permissionMap from name.
     *
     * @param name String name of the permission to be unregistered.
     */
    public static void unregisterPermission(String name) {
        permissionMap.remove(name);
    }

    /**
     * Unregister a Permission object from the permissionMap.
     *
     * @param permission Permission object to be unregistered.
     */
    public static void unregisterPermission(Permission permission) {
        permissionMap.remove(permission.name);
    }
    //endregion

    /**
     * Get the Map of the permissionMap.
     *
     * @return Return a Map<String,Permission> permissionMap.
     */
    public static Map<String, Permission> getPermissionMap() {
        return permissionMap;
    }

    /**
     * Get a Permission object from name.
     *
     * @param name String name of the Permission.
     * @return A Permission object.
     */
    public static Permission getPermission(String name) {
        for (Permission permission : permissionMap.values()) {
            if (permission.name.equals(name)) {
                return permission;
            }
        }
        return null;
    }
}
