package com.whaa.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * created by @author wangzelong 2020/4/20 14:01
 * 线程操作资源类
 * 多线程编程需要标志位
 * A线程五次 B线程10次 C线程15次
 * 来十次
 */

class ShareData {

    private int number = 1; //A1 B2 C3

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void sharePrint(int num) throws InterruptedException {
        if (num == 5) {
            printA(num);
        }
        if (num == 10) {
            printB(num);
        }
        if (num == 15) {
            printC(num);
        }
    }


    public void printA(int num) throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                c1.await();
            }
            myPrint(num);
            this.number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printB(int num) throws InterruptedException {

        lock.lock();
        try {
            while (number != 2) {
                c2.await();
            }
            myPrint(num);
            this.number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void printC(int num) throws InterruptedException {

        lock.lock();
        try {
            while (number != 3) {
                c3.await();
            }
            myPrint(num);
            this.number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    private void myPrint(int num) {
        for (int i = 1; i <= num; i++) {
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
    }

}

public class ConditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.sharePrint(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.sharePrint(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.sharePrint(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }
}
