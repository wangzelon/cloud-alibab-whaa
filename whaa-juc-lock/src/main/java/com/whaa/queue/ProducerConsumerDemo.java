package com.whaa.queue;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            //判断
            while (num != 0) {
                condition.await();
            }
            //加
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            //判断
            while (num == 0) {
                condition.await();
            }
            //减
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            //通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}

/**
 * created by @author wangzelong 2020/4/1 15:28
 * 题：初始值为0两个线程交替操作,一个减1一个加1，来5轮
 * 1 线程     操作      资源类
 * 2 判断     干活      通知
 * 3防止虚假唤醒机制
 * synchronized 和 Lock的区别
 *
 */
public class ProducerConsumerDemo {


    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "DD").start();
    }
}
