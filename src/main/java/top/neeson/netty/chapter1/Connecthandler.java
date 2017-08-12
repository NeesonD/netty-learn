package top.neeson.netty.chapter1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by daile on 2017/6/20.
 */
public class Connecthandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(
                "Client" + ctx.channel().remoteAddress() + " connected"
        );
    }
}
