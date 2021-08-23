package com.hxystudy.nio;

import java.nio.ByteBuffer;

/**
 * @Author ： hxy
 * @create ： 2021/8/23 10:21
 * @Description : Put()/Get()
 */
public class ByteBufferPutGet {
    public static void main(String[] args) {
        // 创建一个byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        // 放入不同类型数据
        byteBuffer.putInt(10);
        byteBuffer.putLong(9L);
        byteBuffer.putChar((char) 'h');
        byteBuffer.putShort((short) 8);

        byteBuffer.flip();

        // 读取数据

        // 打印正常数据
       /* System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        */
       // 报错
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getInt());
    }
}
