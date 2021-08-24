package com.hxystudy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author ： hxy
 * @create ： 2021/8/24 15:13
 * @Description : NIO客户端
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();

        //设置非阻塞
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        // 连接服务器
        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("客户端连接需要时间，可以做其他事情,不会一直阻塞");
            }
        }
        String message = "hello,nio服务端";
        // ByteBuffer.wrap() = ByteByffer.allocate();
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
        // 发送数据,将buffer写入channel
        socketChannel.write(byteBuffer);
        System.in.read();

    }
}
