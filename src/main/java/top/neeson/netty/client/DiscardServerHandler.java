package top.neeson.netty.client;

/**
 * Created by daile on 2017/6/16.
 */
//public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
//    public void inboundBufferUpdated(ChannelHandlerContext channelHandlerContext) throws Exception {
//
//    }
//
//    public ChannelBuf newInboundBuffer(ChannelHandlerContext channelHandlerContext) throws Exception {
//        return null;
//    }
//
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        /*ByteBuf in = (ByteBuf) msg;
//        try {
//            while (in.isDirect()){
//                System.out.println((char) in.readByte());
//                System.out.flush();
//            }
//        }finally {
//
//        }*/
//        ctx.write(msg);
//        ctx.flush();
//
//    }
//
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        cause.printStackTrace();
//        ctx.close();
//    }
//
//    public void channelActive(final ChannelHandlerContext ctx){
//        final ByteBuf time = ctx.nextInboundByteBuffer();
//    }
//}
