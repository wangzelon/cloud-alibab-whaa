package com.whaa.deadlock;

/**
 * 死锁造成的原因:持有自己的锁还想获得别人的锁
 * created by @author wangzelong 2020/4/7 16:37
 * linux ps -ef|grep xxx ls -l
 * windows下的java运行程序 有类似ps的查看进程的命令
 * jps = java ps     jps -l   jstack 进程号 查看堆栈
 */
class HoldLockThread implements Runnable {

    private String lockA;

    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "尝试获得" + lockB);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "尝试获得" + lockA);
            }
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        new Thread(new HoldLockThread("AAA", "BBB"), "Thread AAA").start();
        new Thread(new HoldLockThread("BBB", "AAA"), "Thread BBB").start();
    }
}
