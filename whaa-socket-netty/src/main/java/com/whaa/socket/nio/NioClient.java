package com.whaa.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * created by @author wangzelong 2020/5/22 10:36
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞模式
        socketChannel.configureBlocking(false);
        //提供服务器的IP端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接需要时间,客户端不会阻塞");
            }
        }
        //连接成功发送数据
        Scanner scanner = new Scanner(System.in);
        String temp;
        while (scanner.hasNext()) {
            temp = scanner.next();
            //自定义大小,和字节数组大小相等,不需要指定大小
            ByteBuffer byteBuffer = ByteBuffer.wrap(temp.getBytes());
            //将buffer数据写入Channel
            socketChannel.write(byteBuffer);
        }

    }
}
