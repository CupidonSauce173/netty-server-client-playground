package CupidonSauce173;

import CupidonSauce173.events.DisconnectEvent;
import CupidonSauce173.protocols.SendMessageProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import CupidonSauce173.events.MessageEvent;

import java.io.IOException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        new Client("localhost", 8000).run();
    }

    private final String host;
    private final int port;

    public static String clientName;

    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void run() throws IOException {
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            // Registering user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your username: ");
            if(scanner.hasNext()){
                clientName = scanner.nextLine();
                System.out.println("Welcome " + clientName);
            }

            // Connecting user to server
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer(clientName));
            Channel channel = bootstrap.connect(host, port).sync().channel();

            while (scanner.hasNext()) {
                SendMessageProtocol msg = new SendMessageProtocol();
                msg.username = clientName;
                msg.message = scanner.nextLine();
                channel.writeAndFlush(msg);
            }

            channel.writeAndFlush(new DisconnectEvent());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
