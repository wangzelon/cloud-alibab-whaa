package com.whaa.socket.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * created by whaa 2020/4/30
 */
public class SocketClientDemo {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        OutputStream outputStream = socket.getOutputStream();
        String json = "{\"userIdFrom\":\"1001\",\"userIdTo\":\"1002\",\"body\":\"你好1002\"}";
        outputStream.write(json.getBytes());
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        System.out.println(new java.lang.String(bytes));
    }

}
