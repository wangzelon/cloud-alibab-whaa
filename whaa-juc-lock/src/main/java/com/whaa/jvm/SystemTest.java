package com.whaa.jvm;

import java.util.Random;

/**
 * created by @author wangzelong 2020/4/22 16:54
 * jvm 调参 -Xms10m -Xmx10m -XX:+PrintGCDetails
 */
public class SystemTest {

    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
        long l = Runtime.getRuntime().maxMemory();
        System.out.println("-Xmx总的堆内存" + (double) l / 1024 / 1024 + "MB");
        long l1 = Runtime.getRuntime().totalMemory();
        System.out.println("-Xms初始堆内存" + (double) l1 / 1024 / 1024 + "MB");
//        String str="www.test.com";
//        while (true){
//            str+=str+new Random().nextInt(8888888)+new Random().nextInt(999999);
//        }
        byte[] bytes = new byte[40 * 1024 * 1024];

    }
}
