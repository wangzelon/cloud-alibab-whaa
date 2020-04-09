package com.whaa.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * created by @author wangzelong 2020/4/8 17:59
 * 有GC就回收
 */
public class WeakRefrenceDemo {

    public static void main(String[] args) {
        User user = new User(1, "aa");
        WeakReference<User> userWeakReference = new WeakReference<>(user);
        System.out.println(user);
        System.out.println(userWeakReference.get());
        user = null;
        System.gc();
        System.out.println(user);
        System.out.println(userWeakReference.get());

    }
}
