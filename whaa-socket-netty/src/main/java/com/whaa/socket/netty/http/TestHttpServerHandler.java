package com.whaa.socket.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * created by @author wangzelong 2020/5/28 14:52
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 读取客户端数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            System.out.println("msg类型:" + msg.getClass());
            System.out.println("客户端地址:" + ctx.channel().remoteAddress());
            HttpRequest httpRequest= (HttpRequest) msg;

            String uri = httpRequest.uri();
            System.out.println(uri);
            //回复信息给浏览器[http协议]
            String result = "{\"name\":\"Hello 我是服务端\"}";
            ByteBuf byteBuf = Unpooled.copiedBuffer(result, CharsetUtil.UTF_8);
            //构造一个Http响应
            DefaultFullHttpResponse defaultHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, byteBuf);

            defaultHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json")
                    .set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            ctx.writeAndFlush(defaultHttpResponse);

        }
    }
}
