package com.whaa.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * created by @author wangzelong 2020/4/17 10:24
 * 1、故障现象
 * java.util.ConcurrentModificationException
 * 2、故障原因
 * 多个线程修改统一资源类list
 * 3.解决方案
 * 3.1、new Vector<>()
 * 3.2、Collections.synchronizedList(new ArrayList<>())
 * 3.3、new CopyOnWriteArrayList<>() 写时复制 加入了可重入锁
 * 4优化建议
 */

public class NoteSafeDemo {
    public static void main(String[] args) {
        hashMapNotSafe();
    }

    private static void hashMapNotSafe() {
        Map<String, String> map = new ConcurrentHashMap<>();//new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 6));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();//new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();//new ArrayList<>();//Collections.synchronizedList(new ArrayList<>()); //new Vector<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
