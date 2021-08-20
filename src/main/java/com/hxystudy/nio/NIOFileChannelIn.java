package com.hxystudy.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author ： hxy
 * @create ： 2021/8/20 16:25
 * @Description : 从文件读出数据
 */
public class NIOFileChannelIn {
    public static void main(String[] args) throws IOException {
        // 创建文件输入流
        File file = new File("D://fcTest.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        // 通过fileInputStream 获取对应的FileChannel ->实际类型 FileChannelImpl
        FileChannel fileChannel = fileInputStream.getChannel();
        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // 将通道的数据读入到buffer
        fileChannel.read(byteBuffer);
        // 将buffer的字节数据 转成String
        System.out.println(new String(byteBuffer.array()));
    }
}
