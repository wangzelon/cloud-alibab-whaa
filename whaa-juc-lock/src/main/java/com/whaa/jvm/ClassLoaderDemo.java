package com.whaa.jvm;

/**
 * created by @author wangzelong 2020/4/20 15:38
 * Bootstrap c++ 启动类加载器 打印null
 * Extension java 扩展加载器
 * AppCloassLoader Java系统类加载器
 * java.lang.ClassLoader 继承
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        //自带的走Bootstrap加载器
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader());
        //自己写的走AppClassLoader
        ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo();
        System.out.println(classLoaderDemo.getClass().getClassLoader());
        System.out.println(classLoaderDemo.getClass().getClassLoader().getParent());
        System.out.println(classLoaderDemo.getClass().getClassLoader().getParent().getParent());

    }
}
