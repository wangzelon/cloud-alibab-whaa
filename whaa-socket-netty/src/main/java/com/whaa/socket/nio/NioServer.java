package com.whaa.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * created by @author wangzelong 2020/5/22 10:35
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个Selector对象
        Selector selector = Selector.open();
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把serverSocketChannel注册到selector关心事件为ON_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while (true) {
            //等待一秒没有事件发生继续
            if (selector.select(10000) == 0) {
                System.out.println("服务器等待十秒无连接");
                continue;
            }
            //返回不是0,获取相关的selectionKey集合
            //大于0,表示已经获取到关注的事件
            //select.selectKeys()返回的关注的事件的集合
            //通过selectionKeys反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //获取selectionKey
                SelectionKey next = iterator.next();
                //根据key获取对应的通道做响应的处理
                //如果是ON_ACCEPT有新的客户端连接
                if (next.isAcceptable()) {
                    //就为当前连接生成一个新的客户端连接SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector,关注事件为read事件,可以给这个通道关联一个buffer
                    System.out.println("客户端连接成功 socketChannel" + socketChannel.hashCode());
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                //发生read事件
                if (next.isReadable()) {
                    //通过key反向获取对应的channel
                    SocketChannel channel = (SocketChannel) next.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer) next.attachment();
                    channel.read(byteBuffer);
                    System.out.println("客户端发送的数据:" + new String(byteBuffer.array()));
                }
                //手动从集合中删除当前事件,防止重复操作
                iterator.remove();

            }
        }
    }
}
