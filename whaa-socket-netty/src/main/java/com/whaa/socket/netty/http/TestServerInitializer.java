package com.whaa.socket.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * created by @author wangzelong 2020/5/28 14:53
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //得到管道
        ChannelPipeline pipeline = ch.pipeline();
        //加入netty提供的HttpServerCodec coded=>[coder decoder]
        //HttpServerCodec是netty提供的编解码器
        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());
        //增加一个自定义的处理器
        pipeline.addLast("MyHttpServerHandler",new TestHttpServerHandler());
    }
}
