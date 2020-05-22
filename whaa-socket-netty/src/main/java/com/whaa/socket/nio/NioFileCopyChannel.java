package com.whaa.socket.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * created by @author wangzelong 2020/5/21 15:24
 */
public class NioFileCopyChannel {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("d:\\1.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\2.jpg");

        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());
        //关闭
        inputStreamChannel.close();
        outputStreamChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
