package com.whaa.socket.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * created by @author wangzelong 2020/5/21 14:22
 * 使用FileChannel写入数据到文件
 */
public class NioFileChannel {

    public static void main(String[] args) throws IOException {
        String str = "Hello 李杰";
        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");

        //通过输出流获取Channel
        FileChannel channel = fileOutputStream.getChannel();

        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //数据写入到缓冲区
        byteBuffer.put(str.getBytes());
        //改成写模式
        byteBuffer.flip();

        //缓冲区的数据写入通道
        channel.write(byteBuffer);
        //关闭
        fileOutputStream.close();
    }
}
