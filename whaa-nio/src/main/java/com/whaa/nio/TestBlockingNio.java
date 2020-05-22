package com.whaa.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * created by @author wangzelong 2020/5/20 14:08
 * 一、使用NIO完成网络通信的三个核心:
 * 1、通道(Channel):负责连接
 * 2、缓冲区(Buffer)：负责数据存取
 * 3、选择器(Selector)：是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 */
public class TestBlockingNio {
    @Test
    public void client() throws IOException {
        SocketChannel open = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8578));
    }

    @Test
    public void server() {

    }
}
