package com.whaa.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;


class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Object get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读取数据" + key);
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取数据完成" );
        } finally {
            lock.readLock().unlock();
        }
        return "";
    }

    public void set(String key, Object val) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写入数据" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, val);
            System.out.println(Thread.currentThread().getName() + "写入数据完成" + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void clear() {
        map.clear();
    }


}

/**
 * 常用的java锁
 *      1、可重入锁-特点上层函数获得锁之后,下层函数自动获取锁
 *            synchronized
 *            ReentrantLock
 *      2、公平锁-非公平锁
 *          公平锁-线程获得锁需要排队queue
 *          非公平锁-线程会先去获取锁如果能获得锁则继续执行，如果不能获得则到queue队尾排队
 *          ReentrantLock 默认为非公平锁-优点，并发性高
 *      3、自旋锁
 *          通过CAS循环比较，如果期望值与实际值相等则修改成功,不相等则修改失败
 *          如果一直没有比较成功会类似于死循环，CPU负载过高
 *      4、读写锁
 *          常用于读多写少的场景，如缓存，读操作可以共享锁,写操作独占锁保证原子性
 *          ReentrantReadWriteLock
 * 读写锁
 * 读并发可以同时多个线程获取
 * 写同时只能一个线程操作
 * created by @author wangzelong 2020/3/30 17:05
 * 读-读可以共享
 * 读写不能共享
 * 写-保证原子性
 * 写-独占锁
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 11; i++) {
            int finalI = i;
            new Thread(() -> myCache.set("key" + finalI, "val" + finalI), "threadWrite" + i).start();
            new Thread(() -> myCache.get("key" + finalI), "threadRead" + i).start();
        }
    }
}
