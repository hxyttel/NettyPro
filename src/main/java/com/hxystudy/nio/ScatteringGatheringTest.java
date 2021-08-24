package com.hxystudy.nio;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Author ： hxy
 * @create ： 2021/8/23 17:12
 * @Description :Scattering: 将数据写入到buffer时,可以采用buffer数组,依次写入【分散】
 *                Gathering:   从buffer读取数据时,可以采用buffer数组,依次读
 */
public class ScatteringGatheringTest {
    public static void main(String[] args) throws IOException {
        // 使用ServerSocketChannel 和SocketChannel网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 分配一个7777端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7777);
        // 绑定端口地址
        serverSocketChannel.socket().bind(inetSocketAddress);
        // 监听
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 创建ByteBuffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        //循环读取数据
        int messageData = 8; // 假设从客户端接受8个字节
        // 循环读取
        while (true){
            long byteRead = 0;
            while (byteRead < 8){
                long l = socketChannel.read(byteBuffers);
                byteRead +=l; // 累计读取的字节数
                System.out.println(byteRead);
                // 使用流打印数据
                Arrays.asList(byteBuffers).stream().map(byteBuffer->"position="+byteBuffer.position()+",limit="
                + byteBuffer.limit()).forEach(System.out::println);
            }

            // 将所有的buffer进行flip
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

            // 将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite<messageData){
                long read = socketChannel.write(byteBuffers);
                byteWrite+=read;
            }

            // 将所有bytebuff数据clear
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

           System.out.println("byteRead ===="+byteRead+"  byteWrite====="+byteWrite+"messageData ====="+messageData);


        }
    }
}
