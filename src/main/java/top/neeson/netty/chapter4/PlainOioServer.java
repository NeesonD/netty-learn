package top.neeson.netty.chapter4;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by daile on 2017/6/22.
 */
public class PlainOioServer {

    public void server(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (; ; ) {
                final Socket ClientSocket = socket.accept();
                System.out.println("Accepted connection from " + ClientSocket);

                new Thread((Runnable) () -> {
                    OutputStream out;
                    try {
                        out = ClientSocket.getOutputStream();
                        out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                        out.flush();
                        ClientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            ClientSocket.close();
                        } catch (IOException ex) {

                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
