package com.whaa.socket.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * created by @author wangzelong 2020/5/26 10:58
 */
public class NewIoClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(7071));
        String fileName = "sentinel-dashboard-1.7.1.jar";
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        //开始时间
        long startTime = System.currentTimeMillis();
        //linux下一个transferTo方法可以完成
        //在windows下一次调用只能发送8M的文件，就需要分段传输文件，注意传输时的位置
        //transferTo底层使用的零拷贝
        long size = fileChannel.size();
        long current = 0;
        long remaining = fileChannel.size();
        long count = 8 * 1000 * 1024;
        while (current < size) {
            if (remaining < count) {
                fileChannel.transferTo(current, size - current, socketChannel);
                current = size;
            } else {
                fileChannel.transferTo(current, count, socketChannel);
                current = current + count;
                remaining = size - current;
            }
        }
        System.out.println("总耗时" + (System.currentTimeMillis() - startTime));
        fileChannel.close();

    }
}
