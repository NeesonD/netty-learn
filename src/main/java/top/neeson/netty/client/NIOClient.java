package top.neeson.netty.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by daile on 2017/6/19.
 */
public class NIOClient {

    private static int flag = 0;
    private static int BLOCK = 4096;
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(BLOCK);
    private static ByteBuffer receiveBuffer = ByteBuffer.allocate(BLOCK);

    private final static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("localhost", 1111);

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(SERVER_ADDRESS);

        Set<SelectionKey> selectionKeys;
        Iterator<SelectionKey> iterator;
        SelectionKey selectionKey;
        SocketChannel client;
        String receiveText;
        String sendText;
        int count;

        //noinspection InfiniteLoopStatement
        while (true) {
            selector.select();
            selectionKeys = selector.selectedKeys();
            iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                selectionKey = iterator.next();
                if (selectionKey.isConnectable()) {
                    System.out.println("client connect");
                    client = (SocketChannel) selectionKey.channel();
                    if (client.isConnectionPending()) {
                        client.finishConnect();
                        System.out.println("完成连接");
                        sendBuffer.clear();
                        sendBuffer.put("HELLOSERVER".getBytes());
                        sendBuffer.flip();
                        client.write(sendBuffer);
                    }
                    client.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    client = (SocketChannel) selectionKey.channel();
                    receiveBuffer.clear();
                    count = client.read(receiveBuffer);
                    if (count > 0) {
                        receiveText = new String(receiveBuffer.array(), 0, count);
                        System.out.println("客户端接受服务端的数据：" + receiveText);
                        client.register(selector, SelectionKey.OP_WRITE);
                    }
                } else if (selectionKey.isWritable()) {
                    sendBuffer.clear();
                    client = (SocketChannel) selectionKey.channel();
                    sendText = "messageFormClient--" + flag++;
                    sendBuffer.put(sendText.getBytes());
                    sendBuffer.flip();
                    client.write(sendBuffer);
                    System.out.println("客户端向服务端发送数据：" + sendText);
                    client.register(selector, SelectionKey.OP_READ);
                }
            }
            selectionKeys.clear();
        }
    }
}
