package CupidonSauce173;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {

    public String clientName;

    public ChatClientInitializer(String username){
        clientName = username;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new ObjectDecoder(ClassResolvers.weakCachingResolver(Client.class.getClassLoader())));
        pipeline.addLast(new ObjectEncoder());

        pipeline.addLast(new ChatClientHandler(clientName));
    }
}
