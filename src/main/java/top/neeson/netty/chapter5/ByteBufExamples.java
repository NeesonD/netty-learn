package top.neeson.netty.chapter5;/*
 * Created by daile on 2017/6/24.
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;
import java.util.Random;

public class ByteBufExamples {

    private final static Random RANDOM = new Random();

    public static void heapBuffer(ByteBuf byteBuf){
        if (byteBuf.hasArray()){
            byte[] array = byteBuf.array();
            int offset = byteBuf.arrayOffset() + byteBuf.readerIndex();
            int length = byteBuf.readableBytes();
            handleArray(array,offset,length);
        }
    }

    public static void directBuffer(ByteBuf byteBuf){
        if (!byteBuf.hasArray()){
            int length = byteBuf.readableBytes();
            byte[] array = byteBuf.array();
            byteBuf.getBytes(byteBuf.readerIndex(),array);
            handleArray(array,0,length);
        }
    }

    public  static void ByteBufferComposite(ByteBuffer header,ByteBuffer body){
        ByteBuffer[] message = {header,body};

        ByteBuffer message2 = ByteBuffer.allocate(
                header.remaining()+body.remaining()
        );
        message2.put(header);
        message2.put(body);
        message2.flip();
    }

    public static void ByteBufComposite(ByteBuf header,ByteBuf body){
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        messageBuf.addComponents(header,body);
        messageBuf.removeComponent(0);
        for (int i=0;i<messageBuf.numComponents();i++){
            System.out.println(messageBuf.component(i).toString());
        }
    }

    public static void byteBufCompositeArray(CompositeByteBuf compositeByteBuf){
        int length = compositeByteBuf.readableBytes();
        byte[] array = compositeByteBuf.array();
        compositeByteBuf.getBytes(compositeByteBuf.readerIndex(),array);
        handleArray(array,0,length);
    }

    public static void byteBufRelativeAccess(ByteBuf buf){
        for (int i =0;i<buf.capacity();i++){
            byte b = buf.getByte(i);
            System.out.println((char) b);
        }
    }

    public static void readAllData(ByteBuf byteBuf){
        while (byteBuf.isReadable()){
            System.out.println(byteBuf.readByte());
        }
    }

    public static void write(ByteBuf buf){
        while (buf.writableBytes()>=4){
            buf.writeInt(RANDOM.nextInt());
        }
    }



    private static void handleArray(byte[] array, int offset, int len) {

    }
}
