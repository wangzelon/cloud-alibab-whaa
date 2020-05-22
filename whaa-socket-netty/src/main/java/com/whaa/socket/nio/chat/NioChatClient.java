package com.whaa.socket.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * created by @author wangzelong 2020/5/22 16:47
 * 客户端
 * 连接服务器
 * 发送消息
 * 接受消息
 */
public class NioChatClient {
    /**
     * 定义基础属性
     */
    private SocketChannel socketChannel;

    private static final String hostname = "127.0.0.1";

    private static final int port = 6667;

    private Selector selector;

    private String username;

    public NioChatClient() {
        try {
            this.socketChannel = SocketChannel.open(new InetSocketAddress(hostname, port));
            this.socketChannel.configureBlocking(false);
            this.selector = Selector.open();
            this.socketChannel.register(selector, SelectionKey.OP_READ);
            this.username = this.socketChannel.getLocalAddress().toString();
            System.out.println(this.username + "is ok...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(String msg) {
        msg = this.username + " 说:" + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveData() {
        try {
            int readChannel = selector.select(3000);
            //有可以用的通道
            if (readChannel > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        String msg = new String(byteBuffer.array());
                        System.out.println(msg);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NioChatClient nioChatClient = new NioChatClient();
        //启动一个线程,每隔三秒从服务器读取数据
        new Thread(() -> {
            while (true) {
                nioChatClient.receiveData();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1").start();
        //客户端发送数据给服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            nioChatClient.sendData(msg);
        }

    }
}
