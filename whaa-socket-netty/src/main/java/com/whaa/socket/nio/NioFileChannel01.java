package com.whaa.socket.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * created by @author wangzelong 2020/5/21 14:39
 * 使用FileChannel读取数据
 */
public class NioFileChannel01 {

    public static void main(String[] args) throws IOException {
        //创建输入流
        FileInputStream fileInputStream = new FileInputStream("d:\\file01.txt");

        //获取Channel
        FileChannel inputStreamChannel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) inputStreamChannel.size());
        //读取数据
        inputStreamChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.array().length));
        //关闭流
        fileInputStream.close();
    }
}
