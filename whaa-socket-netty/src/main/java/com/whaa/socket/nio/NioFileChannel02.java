package com.whaa.socket.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * created by @author wangzelong 2020/5/21 14:47
 * 使用FileChannel完成文件的复制
 */
public class NioFileChannel02 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("E:\\work\\project\\cloud-alibaba\\cloud-alibab-whaa\\whaa-socket-netty\\1.txt");
        FileChannel inputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");

        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            byteBuffer.clear();
            int read = inputStreamChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            //反转
            byteBuffer.flip();
            //将缓冲区的数据写入2.txt
            outputStreamChannel.write(byteBuffer);
        }
    }
}
