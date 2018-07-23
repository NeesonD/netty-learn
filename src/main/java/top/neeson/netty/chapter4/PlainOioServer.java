package top.neeson.netty.chapter4;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by daile on 2017/6/22.
 */
public class PlainOioServer {

    public void server(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        try {
            for (; ; ) {
                final Socket ClientSocket = socket.accept();
                System.out.println("Accepted connection from " + ClientSocket);

                executor.execute(() -> {
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
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
