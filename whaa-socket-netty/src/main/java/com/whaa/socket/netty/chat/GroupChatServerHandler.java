package com.whaa.socket.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by @author wangzelong 2020/5/29 16:18
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 定义一个Channel组,管理所有的Channel
     * GlobalEventExecutor.INSTANCE是全局的事件执行器
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * handlerAdded表示连接建立,一旦连接,第一个被执行
     * 将当前channel加入到channelGroup
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入聊天的信息推送给其他的在线客户端
        channelGroup.writeAndFlush("[客户端]" + "[" + simpleDateFormat.format(new Date()) + "]" + channel.remoteAddress() + "加入聊天");
        channelGroup.add(channel);
    }

    /**
     * 断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + "[" + simpleDateFormat.format(new Date()) + "]" + channel.remoteAddress() + "离开了");
        System.out.println("channelGroup:" + channelGroup.size());
    }

    /**
     * 表示Channel处于活动状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[" + simpleDateFormat.format(new Date()) + ctx.channel().remoteAddress() + "]上线了");
    }

    /**
     * channel处于非活动状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[" + simpleDateFormat.format(new Date()) + ctx.channel().remoteAddress() + "]离线了");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务器转发消息中-----》" + msg);
        //当前
        Channel channel = ctx.channel();
        channelGroup.forEach(c -> {
            c.writeAndFlush("[客户" + simpleDateFormat.format(new Date()) + channel.remoteAddress() + "]说:" + msg);
        });
    }
}
