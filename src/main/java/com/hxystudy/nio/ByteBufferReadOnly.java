package com.hxystudy.nio;


import java.nio.ByteBuffer;

/**
 * @Author ： hxy
 * @create ： 2021/8/23 10:33
 * @Description : ByteBuffer转成只读ByteByffer
 */
public class ByteBufferReadOnly {
    public static void main(String[] args) {
        // 创建一个ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        for (int i= 0;i<64;i++){
            byteBuffer.put((byte) i);
        }
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer(); // 将byteBuffer成只读bytebuffer
        readOnlyBuffer.flip();
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
        readOnlyBuffer.put((byte) 100); // 只读buffer写入数据出现ReadOnlyBufferException
    }
}
