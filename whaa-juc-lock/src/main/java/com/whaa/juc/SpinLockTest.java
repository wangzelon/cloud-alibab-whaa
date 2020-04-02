package com.whaa.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁案列
 * created by @author wangzelong 2020/3/30 15:18
 */
public class SpinLockTest {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " come in");
        while (!atomicReference.compareAndSet(null, thread)) {
        }
    }
    

    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + " invoke myUnLock");
    }

    public static void main(String[] args) {
        SpinLockTest spinLockTest = new SpinLockTest();
        new Thread(() -> {
            spinLockTest.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockTest.unlock();
        }, "t1").start();
        //保证t1先启动、然后获得锁
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            spinLockTest.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockTest.unlock();
        }, "t2").start();
    }

}
