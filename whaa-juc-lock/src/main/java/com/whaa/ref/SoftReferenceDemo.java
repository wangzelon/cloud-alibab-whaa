package com.whaa.ref;

import java.lang.ref.SoftReference;

/**
 * created by @author wangzelong 2020/4/8 17:55
 */
class User {
    private Integer age;
    private String name;

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }
}

/**
 * 弱引用内存够用不回收，内存不够就回收
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        User user = new User(1, "aa");
        SoftReference<User> userSoftReference = new SoftReference<>(user);
        System.out.println(user);
        System.out.println(userSoftReference.get());
        user = null;
        System.gc();
        System.out.println(user);
        System.out.println(userSoftReference.get());
    }

}
