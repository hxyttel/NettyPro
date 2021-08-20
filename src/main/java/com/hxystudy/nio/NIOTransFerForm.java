package com.hxystudy.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Author ： hxy
 * @create ： 2021/8/20 17:18
 * @Description :transferFrom()文件拷贝
 */
public class NIOTransFerForm {
    public static void main(String[] args) throws IOException {
        // 创建相关流
        FileInputStream fileInputStream = new FileInputStream("D://a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("D://a2.jpg");
        // 获取各个流对应的通道Channel
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        // 使用transferFrom完成相关拷贝
        outputStreamChannel.transferFrom(fileInputStreamChannel,0,fileInputStreamChannel.size());
        // 关闭相关通道
        fileInputStreamChannel.close();
        outputStreamChannel.close();
        fileOutputStream.close();
        fileInputStream.close();
    }
}
