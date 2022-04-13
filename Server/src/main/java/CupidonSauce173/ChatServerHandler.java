package CupidonSauce173;

import CupidonSauce173.events.ConnectEvent;
import CupidonSauce173.events.DisconnectEvent;
import CupidonSauce173.events.MessageEvent;
import CupidonSauce173.objects.managers.ClientManager;
import CupidonSauce173.protocols.ClientConnectProtocol;
import CupidonSauce173.protocols.ClientDisconnectProtocol;
import CupidonSauce173.protocols.SendMessageProtocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.Map;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    public static final ChannelGroup channels = new DefaultChannelGroup("main", GlobalEventExecutor.INSTANCE);

    public static Map<String, Channel> Clients;

    public static ChatServerHandler instance;

    public ChatServerHandler() {
        Clients = new HashMap<>();
        instance = this;
    }

    public static Map<String, Channel> getClients() {
        return Clients;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        System.out.println("[SERVER] - " + incoming.remoteAddress() + " connected with success.");
        channels.add(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // only there to not get actual errors in the console.
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        channels.remove(ctx.channel());
        System.out.println("[SERVER] - " + incoming.remoteAddress() + " has left!");
        new DisconnectEvent(ClientManager.getClientById(incoming.id().toString()), "Client requested disconnection.");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
        Channel incoming = ctx.channel();
        switch (o.getClass().getSimpleName()) {
            case "ClientConnectProtocol" -> new ConnectEvent(((ClientConnectProtocol) o).username, incoming);
            case "SendMessageProtocol" -> new MessageEvent(ClientManager.getClientById(incoming.id().toString()), ((SendMessageProtocol) o).message);
            case "ClientDisconnectProtocol" -> new DisconnectEvent(ClientManager.getClientById(incoming.id().toString()), ((ClientDisconnectProtocol) o).reason);
        }
    }
}
