package com.whaa.queue;


import com.sun.deploy.util.StringUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void producer() throws InterruptedException {
        System.out.println("生产者启动");
        String data;
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";
            blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            System.out.println("生产者插入数据:" + data);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void consumer() throws InterruptedException {
        System.out.println("消费者线程启动");
        while (flag) {
            String poll = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == poll || "".equalsIgnoreCase(poll)) {
                flag = false;
                System.out.println("队列没有数据，停止");
                break;
            }
            System.out.println("消费者获取到数据:" + poll);
        }
    }

    public void stop() {
        this.flag = false;
    }
}

/**
 * 消息中间件简单实现通过blockingqueue
 * created by @author wangzelong 2020/4/1 17:24
 */
public class ProduConsumerBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new LinkedBlockingQueue<>(6));
        new Thread(() -> {
            try {
                myResource.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "producer start").start();

        new Thread(() -> {
            try {
                myResource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "consumer start").start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("5秒之后结束");
        myResource.stop();
    }
}
