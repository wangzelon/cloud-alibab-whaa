package com.whaa.socket.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * created by @author wangzelong 2020/5/29 17:59
 */
public class GroupChatClient {

    private final int port = 7000;

    public void run() throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //向pipeline加入一个解码器
                            pipeline.addLast("myDecoder", new StringDecoder());
                            //向pipeline加入一个编码器
                            pipeline.addLast("myEncoder", new StringEncoder());
                            //加入自己的业务处理器
                            pipeline.addLast(new GroupChatClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(port)).sync();
            Channel channel = channelFuture.channel();
            System.out.println("客户端启动成功" + channel.localAddress());
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg + "\t");
            }
        } finally {
            eventExecutors.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        GroupChatClient chatClient = new GroupChatClient();
        chatClient.run();
    }
}
