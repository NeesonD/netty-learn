package top.neeson.netty.segment01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.NotYetBoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: neeson
 * Date: 2018/3/24
 * Time: 22:31
 * Description:
 */
public class BlockIO {

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(11000);
        while (true) {
            System.out.println("======>1");
            Socket accept = serverSocket.accept();
            System.out.println("=======>2");
            pool.execute(() -> {
                try {
                    handle(accept);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void handle(Socket socket) throws IOException {
        try {
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            while (true) {
                int read = inputStream.read(bytes);
                System.out.println("=========>" + read);
                if (read == -1) {
                    break;
                } else {
                    System.out.println(new String(bytes, 0, read));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }

}
