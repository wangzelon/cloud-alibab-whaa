package com.whaa.socket.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * created by @author wangzelong 2020/5/30 14:49
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = "";
            switch (event.state()) {
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                default:
                    eventType = "未知";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "超时事件:" + eventType);
        }
    }
}
