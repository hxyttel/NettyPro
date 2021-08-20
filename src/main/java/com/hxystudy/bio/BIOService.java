package com.hxystudy.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author ： hxy
 * @create ： 2021/8/19 10:17
 * @Description : BIO服务端
 */
public class BIOService {
    /**
     *  思路
     *  1.创建一个想吃
     *  2.如果有客户端连接,就创建一个线程,与之通讯(单独写一个方法)
     */
    public static void main(String[] args) throws IOException {
        // 创建线程池
        ExecutorService executorService =  Executors.newCachedThreadPool();

        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        while (true){
            // 监听,等到客户端客户端
            System.out.println("1  线程 id="+ Thread.currentThread().getId()+" 线程名字==="+Thread.currentThread().getName());
            System.out.println("监听中=======");
           final Socket socket = serverSocket.accept();
            System.out.println("2  线程 id="+ Thread.currentThread().getId()+" 线程名字==="+Thread.currentThread().getName());
           System.out.println("连接到一个客户端");
           executorService.execute(new Runnable() {
               public void run() {
                   // 和客户端通讯
                   handler(socket);
               }
           });
        }
    }

    //  通讯方法
    public static  void handler(Socket socket){
        try{
            System.out.println("handler  线程 id="+ Thread.currentThread().getId()+" 线程名字==="+Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        InputStream inputStream = socket.getInputStream();
        while (true){
            System.out.println("read========");
            int read = inputStream.read(bytes);
            if(read >= -1){
                System.out.println(new String(bytes,0,read));
            }
          }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
                System.out.println("关闭和客户端的连接");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
