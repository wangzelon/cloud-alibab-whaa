package com.whaa.socket.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * created by @author wangzelong 2020/5/29 15:51
 */
public class NettyBuffer {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello World 成都", CharsetUtil.UTF_8);
        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();
            System.out.println(new String(array, CharsetUtil.UTF_8));
        }
        System.out.println(byteBuf);
        char a= 'b';
        System.out.println(a);
        System.out.println((byte)a);
    }

}
