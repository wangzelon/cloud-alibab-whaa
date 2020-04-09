package com.whaa.jvm;

/**
 * created by @author wangzelong 2020/4/8 10:47
 */
public class HelloGc {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello gc");
//        byte[] bytes = new byte[50 * 1024 * 1024];
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        long totalMemory = Runtime.getRuntime().totalMemory();
//        System.out.println(maxMemory);
//        System.out.println(totalMemory);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
