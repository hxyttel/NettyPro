package com.hxystudy.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author ： hxy
 * @create ： 2021/8/20 16:56
 * @Description :
 */
public class NIOFileChannelAll {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true){ // 循环读取数据
            /*
            public final Buffer clear() {
                position = 0;
                limit = capacity;
                mark = -1;
                return this;
            }*/
            byteBuffer.clear(); // 清空buffer
            int read = fileInputStreamChannel.read(byteBuffer);
            if(read == -1){ // 表示读完
                break;
            }
            byteBuffer.flip(); // 切换写模式
            // 将buffer中的数据写入到outputStreamChannel中->2.txt
            outputStreamChannel.write(byteBuffer);
        }
        fileOutputStream.close();
        fileOutputStream.close();
    }
}
