package com.whaa.jvm;

import java.util.Stack;

/**
 * created by @author wangzelong 2020/4/8 10:47
 *6stack
 * 栈管运行
 * 堆管存储
 * 程序=算法+数据结构
 * 程序=框架+业务逻辑
 * 数据结构 队列（FIFO） 栈 先进后出 后进先出
 * 8种基本类型的变量+对象的引用变量+实例方法都是在函数的栈内存分配
 *  栈帧
 *  java.lang.StackOverflowError
 *
 */
public class HelloGc {

    public static void m1(){
        System.out.println("m1");
        m1();
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            m1();
        } catch (Error e) {
            e.printStackTrace();
        } finally {
        }
//        System.out.println("hello gc");
//        byte[] bytes = new byte[50 * 1024 * 1024];
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        long totalMemory = Runtime.getRuntime().totalMemory();
//        System.out.println(maxMemory);
//        System.out.println(totalMemory);
//        Thread.sleep(Integer.MAX_VALUE);
//        Stack<String> stack=new Stack<>();
//        stack.push("aaaa");
//        stack.push("bbbb");
//        String pop = stack.pop();
//        System.out.println(pop);
    }
}
