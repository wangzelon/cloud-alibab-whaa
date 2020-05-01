package com.whaa.socket.socket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by whaa 2020/4/30
 */
public class SocketServer {

    private static Map<String, Socket> sessionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        String s = new String(bytes);
        JSONObject jsonObject = JSON.parseObject(s);
        String userId = jsonObject.getString("userIdFrom");
        Socket oldSocket = sessionMap.get(userId);
        if (oldSocket == null) {
            sessionMap.put(userId, socket);
            String userIdTo = jsonObject.getString("userIdTo");
            Socket toSocket = sessionMap.get(userIdTo);
            while (null == toSocket) {
                toSocket = sessionMap.get(userIdTo);
            }
            OutputStream outputStream = toSocket.getOutputStream();
            outputStream.write(jsonObject.getString("body").getBytes());
            OutputStream outputStream1 = socket.getOutputStream();
            outputStream1.write("发送成功".getBytes());
        } else {
            String userIdTo = jsonObject.getString("userIdTo");
            Socket toSocket = sessionMap.get(userIdTo);
            while (null == toSocket) {
                toSocket = sessionMap.get(userIdTo);
            }
            OutputStream outputStream = toSocket.getOutputStream();
            outputStream.write(jsonObject.getString("body").getBytes());
            OutputStream outputStream1 = socket.getOutputStream();
            outputStream1.write("发送成功".getBytes());
        }
    }
}
