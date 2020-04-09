package com.whaa.ref;

import java.util.WeakHashMap;

/**
 * created by @author wangzelong 2020/4/8 18:28
 * WeakHashMap key不被引用后发生GC就清除
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        Integer key = new Integer(1);
        weakHashMap.put(key, "value");
        System.out.println(weakHashMap);
        key=null;
        System.out.println(weakHashMap);
        System.gc();
        System.out.println(weakHashMap);
    }

}
