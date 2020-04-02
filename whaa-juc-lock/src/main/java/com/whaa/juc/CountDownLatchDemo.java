package com.whaa.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 线程在初始值计数递减到0,则主线程继续执行，否则主线程一直等待
 *
 * created by @author wangzelong 2020/3/30 18:31
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

    }

    public void test1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 线程结束");
    }

}
