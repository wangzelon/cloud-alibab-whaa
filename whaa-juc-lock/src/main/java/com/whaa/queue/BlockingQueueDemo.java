package com.whaa.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1 、队列 FIFO先进先出
 * 2、阻塞队列
 * 2.1阻塞队列有没有好的地方
 * 2.2不得不阻塞,如何管理
 * <p>
 * created by @author wangzelong 2020/3/31 17:06
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {

//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //超过指定队列数量直接报错
//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("C"));
//        System.out.println(blockingQueue.add("d"));
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
        //插入超出返回false,获取没有返回null
//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("x"));
//        System.out.println(blockingQueue.peek());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
        //插入获取一直阻塞
//        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
//        blockingQueue.put("a");
//        blockingQueue.put("b");
//        blockingQueue.put("c");
////        blockingQueue.put("a");
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
        //超时控制更理性
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));
        blockingQueue.poll(2, TimeUnit.SECONDS);

    }
}
