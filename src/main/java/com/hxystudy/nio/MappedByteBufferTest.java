package com.hxystudy.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author ： hxy
 * @create ： 2021/8/23 10:51
 * @Description : MappedByteBuffer让文件直接存内存(堆外的内存)中进行修改
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt","rw");
        // 获取文件对应的通道
        FileChannel fileChannel = randomAccessFile.getChannel();

        /*
        * 参数1: FileChannel.MapMode.READ_WRITE 使用读写模式
        * 参数2: 可以直接修改的起始位置
        * 参数3: 5是映射到内存的大小(不是索引位置),即将1.txt的多少个字节映射到内存中
        * 可以直接修改的范围(索引0-4)
        * 实际类型DirectByteBuffer
        * */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0,(byte) 'H');
        mappedByteBuffer.put(4,(byte)'7');

        mappedByteBuffer.put(5,(byte) '9'); // java.lang.IndexOutOfBoundsException

        randomAccessFile.close();
        System.out.println("文件修改成功!");
    }
}
