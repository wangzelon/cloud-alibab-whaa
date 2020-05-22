package com.whaa.socket.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created by @author wangzelong 2020/5/20 16:07
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        //线程池机制
        //创建一个线程池
        //如果有客户端连接,就创建一个线程与之通讯
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动成功");
        while (true) {
            //监听等待客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(() -> {
                //和客户端通讯
                handler(socket);
            });

        }
    }

    private static void handler(Socket socket) {
        System.out.println("当前通讯的线程是:"+Thread.currentThread().getName());
        try {
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            while (-1 != inputStream.read(bytes)) {
                System.out.println("接收到的数据为:"+new String(bytes, 0, bytes.length));
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("关闭连接");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
