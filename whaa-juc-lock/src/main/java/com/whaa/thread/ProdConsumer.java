package com.whaa.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * created by @author wangzelong 2020/4/17 14:44
 * 题目 现在两个线程，可以操作初始值为零的一个变量
 * 实现一个线程对该变量加1，一个线程对该变量减1
 * 实现交替，操作十次，变量初始值为0
 * 1、高内聚，低耦合前提下，线程操作资源类
 * 2、判断、干活、通知
 * 3、防止虚假唤醒
 * 多线程的判断不能用if
 */

class Test {
    private int num = 0;

    //    public synchronized void add() throws InterruptedException {
//        while (num != 0) {
//            this.wait();
//        }
//        num++;
//        System.out.println(Thread.currentThread().getName() + "\t " + num);
//        this.notifyAll();
//    }
//
//    public synchronized void sub() throws InterruptedException {
//        while (num == 0) {
//            this.wait();
//        }
//        num--;
//        System.out.println(Thread.currentThread().getName() + "\t " + num);
//        this.notifyAll();
//    }
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void add() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t " + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sub() {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t " + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

public class ProdConsumer {

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    test.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "a").start();
        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                test.sub();
            }
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    test.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "c").start();
        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                test.sub();
            }
        }, "d").start();
    }
}
