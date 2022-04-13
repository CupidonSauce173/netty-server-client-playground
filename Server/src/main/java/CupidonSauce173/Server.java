package CupidonSauce173;

import CupidonSauce173.objects.Client;
import CupidonSauce173.objects.Command;
import CupidonSauce173.objects.Group;
import CupidonSauce173.objects.Permission;
import CupidonSauce173.objects.managers.ClientManager;
import CupidonSauce173.objects.managers.CommandManager;
import CupidonSauce173.objects.managers.GroupManager;
import CupidonSauce173.objects.managers.PermissionManager;
import CupidonSauce173.test.TestCommand;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public record Server(int port) {

    public static void main(String[] args) throws InterruptedException, SocketException {
        new Server(8000).run();
    }

    public static void broadcastMessage(String message) {
        for (Client client : ClientManager.getClientMap().values()) {
            client.sendMessage(message);
        }
    }

    public void run() {
        init();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatServerInitializer());

            bootstrap.bind(port)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /*
    Test method for various systems in the server.
     */
    public void init() {
        System.out.println("Registering commands...");
        Command testCommand = new TestCommand("test", "core.permission.test");
        CommandManager.registerCommand(testCommand);

        // Permission field

        Permission permission1 = new Permission("testPermOne");
        Permission permission2 = new Permission("testPermTwo");
        Permission permission3 = new Permission("testPermThree");

        PermissionManager.registerPermission(permission1);
        PermissionManager.registerPermission(permission2);
        PermissionManager.registerPermission(permission3);

        Map<String, Permission> permissionMap = new HashMap<>();
        permissionMap.put("testPermOne", PermissionManager.getPermission("testPermOne"));
        permissionMap.put("testPermTwo", PermissionManager.getPermission("testPermTwo"));

        // Group field
        Group testGroup = Group.newBuilder()
                .setName("Default")
                .setTag("[Def]")
                .setPriority(0)
                .setPermissions(permissionMap)
                .setInvisible(false)
                .build();
        GroupManager.registerGroup(testGroup);
    }
}
