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
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()) {
                    ByteBuf buf = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                    ChannelFuture wf = channelFuture.channel().writeAndFlush(buf);
                } else {
                    Throwable cause = channelFuture.cause();
                    cause.printStackTrace();
                }
            }
        });
    }
}
