package com.whaa.socket.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.security.Key;
import java.util.Iterator;

/**
 * created by @author wangzelong 2020/5/22 16:12
 * 利用NIO实现消息转发
 * 1、先编写服务端
 * 服务器启动监听端口6667
 * 服务器接收客户端消息并实现转发
 */
public class NioChatServer {
    /**
     * 定义相关属性
     */
    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 6667;

    /**
     * 初始化参数
     */
    public NioChatServer() {
        try {
            //得到选择器
            this.selector = Selector.open();
            //获取ServerSocketChannel
            this.listenChannel = ServerSocketChannel.open();
            //绑定端口
            this.listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞模式
            this.listenChannel.configureBlocking(false);
            //将listenChannel注册到选择器上
            this.listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                int res = this.selector.select(3000);
                //有事件处理
                if (res > 0) {
                    Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        //坚挺到accept事件
                        if (selectionKey.isAcceptable()) {
                            SocketChannel socketChannel = this.listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            //将该socketChannel注册到Selector
                            socketChannel.register(this.selector, SelectionKey.OP_READ);
                            String address = socketChannel.getRemoteAddress().toString();
                            System.out.println(address + "上线了");
                        }
                        //监听到read事件,及通道可读的状态
                        if (selectionKey.isReadable()) {
                            readData(selectionKey);
                        }
                        //删除当前key,避免重复处理
                        iterator.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取客户端消息
     */

    private void readData(SelectionKey selectionKey) {
        SocketChannel channel = null;
        try {
            //取SocketChannel
            channel = (SocketChannel) selectionKey.channel();
            //创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = channel.read(byteBuffer);
            //根据read的值做处理
            if (read > 0) {
                String msg = new String(byteBuffer.array());
                System.out.println("客户端收到消息:" + msg);
                //向其他的客户端转发消息
                sendInfoToOtherClient(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + "离线了....");
                //取消注册
                selectionKey.cancel();
                //关闭通道
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 转发消息给其他客户端
     *
     * @param msg
     */
    private void sendInfoToOtherClient(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中");
        //遍历所有注册到selector上的SocketChannel,并且排除自己
        for (SelectionKey selectionKey : this.selector.keys()) {
            //拿到channel
            Channel channel = selectionKey.channel();
            //排除自己
            if (channel instanceof SocketChannel) {
                SocketChannel dest = (SocketChannel) channel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                //写入数据
                dest.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        NioChatServer nioChatServer = new NioChatServer();
        nioChatServer.listen();
    }
}
