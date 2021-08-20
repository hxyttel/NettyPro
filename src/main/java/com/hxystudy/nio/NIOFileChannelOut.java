package com.hxystudy.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author ： hxy
 * @create ： 2021/8/20 15:24
 * @Description : 从数据写入文件
 */
public class NIOFileChannelOut {
    public static void main(String[] args) throws IOException {
        String str = "hello FileChannel!";
        // 创建一个输出流—>channel
        FileOutputStream fileOutputStream =  new FileOutputStream("D://fcTest.txt");
        // 通过fileOutPutStream 获取对应的FileChannel
        // 这个fileChannel 真是类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        // 通道读写数据都是通过缓冲区的
        // 创建一个ByteBuffer缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 将str放入buffer
        byteBuffer.put(str.getBytes());
        // 对buferr进行读写切换
        byteBuffer.flip();
        // 将buffer数据写入通道
        fileChannel.write(byteBuffer);
        // 关闭输出流
        fileOutputStream.close();
    }
}
