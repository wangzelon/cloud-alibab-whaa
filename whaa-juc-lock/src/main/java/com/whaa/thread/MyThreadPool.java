package com.whaa.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * created by @author wangzelong 2020/4/7 15:47
 */
public class MyThreadPool {

    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        System.out.println(Runtime.getRuntime().availableProcessors());
        /**
         * 如何舍子核心线程数
         * CPU密集型 CPU核心数 CPU数+1
         * IO密集型 IO密集型不是一直在执行任务 CPU数*2
         * maximumPoolSize设置 CPU核数/1-阻塞系数  阻塞系数0.8-0.9
         */
        ExecutorService threadPool = new ThreadPoolExecutor(
                7,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                namedThreadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            for (int i = 0; i < 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadPool.shutdown();

    }

}
