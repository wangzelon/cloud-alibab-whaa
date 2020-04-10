package com.whaa.hashmap;

import java.util.HashMap;

/**
 * created by @author wangzelong 2020/4/9 15:05
 * hashMap的数组长度必须是2的指数次幂
 * hashMap的默认加载因子0.75
 */
public class HashMapTest {

    public static void main(String[] args) {
        //jdk7 数组+链表 jdk8后 数组+链表+红黑树
        HashMap<String, String> hashMap = new HashMap<>(13);
        hashMap.put("1", "2");
        //根据key的hash值 与数组的长度取模得到数组的下标
        //当key的hash值产生hash冲突的时候就产生链表
        //链表长度>=8的时候就转换成红黑树
        String oldValue = hashMap.put("1", "3");
        System.out.println(oldValue);
        System.out.println("李杰".hashCode());
    }
}
