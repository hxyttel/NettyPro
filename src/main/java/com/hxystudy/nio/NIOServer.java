package com.hxystudy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author ： hxy
 * @create ： 2021/8/24 14:16
 * @Description : NIO服务端
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到Selector
        Selector selector = Selector.open();
        // 绑定一个6666端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 将serverSocketChannel注册到selector,关系事件为OP_APPCET
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        // 循环等待客户端连接
        while (true){
            // 等待1秒,如果没有事件发生,就返回
            if(selector.select(1000)==0){ // 没有事件
                System.out.println("等待1秒");
                continue;
            }
            // 如果大于0有事件发生,就获取SelectionKey相关集合
            /**
             * 1.如果返回的>0,表示已经获取到关注事件
             * 2.selector.selectedKeys()返回关注事件的集合
             * 3.通过SelectionKey反向获取通道
             * */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历selectionKeys,通过迭代器
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                // 根据key,对应的通道发生的事件做相应的处理
                if(selectionKey.isAcceptable()){ // 如果是OP_ACCEPT事件,有新的客户端连接
                    // 该客户端生成一个socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功,生成了socketChannel "+socketChannel.hashCode());
                    socketChannel.configureBlocking(false); // 设置非阻塞
                    // 将socketChannel注册到selector,关注事件为OP_READ,同时给socketChannel关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ,ByteBuffer.allocate(1024));
                }
                if(selectionKey.isReadable()){ //如果是OP_READ事件
                    // 通过key反向获取对应的channel
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    // 获取到该channel关联的buffer
                     ByteBuffer byteBuffer = (ByteBuffer)selectionKey.attachment();
                     channel.read(byteBuffer);
                     System.out.println("form 客户端 "+new String(byteBuffer.array()));
                }
                // 手动从集合中移除当前的selectionKey,防止重复操作
                keyIterator.remove();
            }

        }

    }
}
