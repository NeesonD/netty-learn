package top.neeson.netty.client;


/**
 * Created by daile on 2017/6/16.
 */
public class TimeClient {

//    /*private static NioEventLoopGroup workerGroup;
//    private static Bootstrap bootstrap;
//
//    public static void main(String[] args) {
//        String host = args[0];
//        int port = Integer.parseInt(args[1]);
//        workerGroup = new NioEventLoopGroup();
//
//        try {
//            bootstrap = new Bootstrap();
//            bootstrap.group(workerGroup);
//            bootstrap.channel(NioSocketChannel.class);
//            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
//            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
//                public void initChannel(SocketChannel ch) {
//                    ch.pipeline().addLast(new TimeClient);
//                }
//            });
//
////            ChannelFuture f = bootstrap.connect(host, port).sync();
//
////            f.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdown();
//        }
//    }*/
}
