package com.whaa.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * created by @author wangzelong 2020/4/1 16:23
 * 1、原始构成
 * synchronized是jvm层面是java的关键字
 * lock是java的一个类
 * 2、使用方法
 * synchronized不会死锁不需要手动加锁解锁
 * lock需要lock unlock
 * 3、是否可以中断
 * synchronized不会中断
 * lock可以中断
 * 4、是否公平
 * synchronized 非公平锁
 * ReentrantLock 两者都可以
 * 5、唤醒机制
 * synchronized随机唤醒或者全部唤醒
 * ReentrantLock可以精确唤醒
 */

class ShareResource {
    private int number = 1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printFive() {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                condition1.await();
            }
            //干活
            for (int i = 1; i < 6; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printTen() {
        lock.lock();
        try {
            //判断
            while (number != 2) {
                condition2.await();
            }
            //干活
            for (int i = 1; i < 11; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printFifteen() {
        lock.lock();
        try {
            //判断
            while (number != 3) {
                condition3.await();
            }
            //干活
            for (int i = 1; i < 16; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SyncAndReentrantLock {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 0; i < 11; i++) {
                shareResource.printFive();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 11; i++) {
                shareResource.printTen();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 11; i++) {
                shareResource.printFifteen();
            }
        }, "C").start();
    }
}
