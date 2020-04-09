package com.whaa.ref;

/**
 * 强引用
 * created by @author wangzelong 2020/4/8 17:37
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object object = new Object();
        Object object1 = object;
        object = null;
        System.gc();
        System.out.println(object1);
    }
}
