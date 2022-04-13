package CupidonSauce173.objects;

import CupidonSauce173.objects.managers.GroupManager;
import CupidonSauce173.objects.managers.PermissionManager;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.Objects;

public class Client {

    public String username;
    public Channel channel;
    public String id;
    public Group group;
    public Map<String, Permission> permissionMap;

    private Client(Builder builder) {
        Objects.requireNonNull(builder.username);
        id = builder.channel.id().toString();
        username = builder.username;
        channel = builder.channel;
        group = builder.group;
        permissionMap = builder.permissionMap;
    }

    /**
     * Builder method to create a new Client object.
     *
     * @return Client builder to construct your object.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Send a message to the client without accessing the channel.
     *
     * @param message String message to be sent.
     */
    public void sendMessage(String message) {
        channel.writeAndFlush(message);
    }

    /**
     * Attach a new permission in the permissionMap.
     *
     * @param permission Permission to be attached.
     */
    public void attachPermission(Permission permission) {
        permissionMap.put(permission.name, permission);
    }

    /**
     * Attach new permission in the permissionMap
     *
     * @param permission String name of the permission to be attached.
     */
    public void attachPermission(String permission) {
        Permission perm = PermissionManager.getPermission(permission);
        if (perm != null) {
            attachPermission(perm);
        }
    }

    /**
     * Detach a permission from the permissionMap.
     *
     * @param permission Permission to be detached.
     */
    public void detachPermission(Permission permission) {
        permissionMap.remove(permission.name);
    }

    /**
     * Detach a permission from the permissionMap
     *
     * @param permission String name of the permission to be detached.
     */
    public void detachPermission(String permission) {
        permissionMap.remove(permission);
    }

    /**
     * Look if the client has the permission to execute certain actions
     *
     * @param permission Permission object to be looked up.
     * @return Boolean (true = has permission, false = does not have permission).
     */
    public Boolean hasPermission(Permission permission) {
        if (permissionMap.containsValue(permission)) {
            return true;
        } else {
            return group.permissionMap.containsValue(permission);
        }
    }

    /**
     * Look if the client has the permission to execute certain actions
     *
     * @param permission String name of the permission to be looked up.
     * @return Boolean (true = has permission, false = does not have permission).
     */
    public Boolean hasPermission(String permission) {
        Permission pO = PermissionManager.getPermission(permission);
        if (pO != null) {
            return hasPermission(pO);
        }
        return false;
    }

    /**
     * Builder class for the Client.
     */
    public static final class Builder {

        private String username;
        private Channel channel;
        private Group group;
        private Map<String, Permission> permissionMap;

        private Builder() {
        }

        public Builder setDisplayName(String name) {
            this.username = name;
            return this;
        }

        public Builder setChannel(Channel channel) {
            this.channel = channel;
            return this;
        }

        public Builder setGroup(Group group) {
            this.group = group;
            return this;
        }

        public Builder setGroup(String group) throws Exception {
            Group gO = GroupManager.getGroup(group);
            if (gO == null) {
                throw new Exception("Group does not exist.");
            }
            this.group = gO;
            return this;
        }

        public Builder setPermissionMap(Map<String, Permission> permissionMap) {
            this.permissionMap = permissionMap;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
