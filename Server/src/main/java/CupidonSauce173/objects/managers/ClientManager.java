package CupidonSauce173.objects.managers;

import CupidonSauce173.objects.Client;

import java.util.HashMap;
import java.util.Map;

public class ClientManager {

    private static Map<String, Client> clientMap = new HashMap<>();

    /**
     * Get the current clientMap of the server.
     *
     * @return a Map of the clients logged in the server.
     */
    public static Map<String, Client> getClientMap() {
        return clientMap;
    }

    /**
     * Get a client object from the clientMap.
     *
     * @param username Username of the client.
     * @return A client object related to the username.
     */
    public static Client getClient(String username) {
        if (clientMap.containsKey(username)) {
            return clientMap.get(username);
        }
        return null;
    }

    /**
     * Get a client object from its channel id.
     *
     * @param id String id of the channel id of the client.
     * @return The client object related to the channel id.
     */
    public static Client getClientById(String id) {
        for (Client client : clientMap.values()) {
            if (client.id.equals(id)) {
                return client;
            }
        }
        return null;
    }

    /**
     * Register a client in the clientMap.
     *
     * @param client Client to be registered.
     */
    public static void registerClient(Client client) {
        clientMap.put(client.id, client);
    }

    /**
     * Unregister a client from the clientMap.
     *
     * @param client Client object to be unregistered.
     */
    public static void unregisterClient(Client client) {
        clientMap.remove(client.username);
    }

    /**
     * Unregister a client from the clientMap.
     *
     * @param client String username of the client to be unregistered.
     */
    public static void unregisterClient(String client) {
        clientMap.remove(client);
    }
}
