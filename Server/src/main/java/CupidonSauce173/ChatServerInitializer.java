package CupidonSauce173;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {

        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new ObjectDecoder(ClassResolvers.weakCachingResolver(Server.class.getClassLoader())));
        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new ChatServerHandler());
    }
}
