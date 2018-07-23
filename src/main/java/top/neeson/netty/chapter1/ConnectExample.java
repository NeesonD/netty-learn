package top.neeson.netty.chapter1;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;


/**
 * Created by daile on 2017/6/20.
 */
public class ConnectExample {

    public static void connect(Channel channel) {
        ChannelFuture channelFuture = channel.connect(new InetSocketAddress("localhost", 25));
        channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
            if (channelFuture1.isSuccess()) {
                ByteBuf buf = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                ChannelFuture wf = channelFuture1.channel().writeAndFlush(buf);
            } else {
                Throwable cause = channelFuture1.cause();
                cause.printStackTrace();
            }
        });
    }
}
