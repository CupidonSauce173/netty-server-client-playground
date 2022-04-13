package CupidonSauce173;

import CupidonSauce173.protocols.ClientConnectProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatClientHandler extends SimpleChannelInboundHandler<Object> {

    public String clientName;

    public ChatClientHandler(String username){
        this.clientName = username;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object s) throws Exception {
        System.out.println(s);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ClientConnectProtocol pk = new ClientConnectProtocol(Client.clientName);
        pk.username = clientName;
        ctx.channel().writeAndFlush(pk);
        System.out.println("Connection has been established.");
    }
}
