package com.whaa.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 *  不存储队列
 *  生产一个消费一个 才能继续生产
 * created by @author wangzelong 2020/4/1 15:22
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName() + "\t put2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "BBB").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t get1");
                System.out.println(blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t get2");
                System.out.println(blockingQueue.take());
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + "\t get3");
                System.out.println(blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "CCC").start();
    }
}
