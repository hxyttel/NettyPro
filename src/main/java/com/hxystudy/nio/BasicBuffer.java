package com.hxystudy.nio;

import java.nio.IntBuffer;

/**
 * @Author ： hxy
 * @create ： 2021/8/20 9:49
 * @Description : buffer基本案例
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // 创建一个Intbuffer,buffer有很多类型(Doble,float....)
        IntBuffer intBuffer =IntBuffer.allocate(5);

         // 向buffer里面放数据
       /* intBuffer.put(10);
        intBuffer.put(20);
        capacity()方法是buffer的容量大小
        */
        for(int i = 0;i<intBuffer.capacity();i++){
            intBuffer.put(i * 2);
        }
        // buffer读取数据
        /*
        读数据必须读写切换
           public final Buffer flip() {
                limit = position;
                position = 0;
                mark = -1;
                return this;
            }
        * */
        intBuffer.flip();
        // hasRemaining()判断buffer里面数据是不是还有
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
