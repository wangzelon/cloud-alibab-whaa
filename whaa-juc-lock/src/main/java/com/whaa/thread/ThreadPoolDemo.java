package com.whaa.thread;

import java.security.AccessController;
import java.util.concurrent.*;

/**
 * 各种池化技术-线程次-良好的管理线程
 * created by @author wangzelong 2020/4/3 16:03
 */
public class ThreadPoolDemo {


    public static void main(String[] args) {
//        ExecutorService threadPoll = Executors.newFixedThreadPool(5);
//        ExecutorService threadPoll = Executors.newSingleThreadExecutor();
        ExecutorService threadPoll = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 11; i++) {

                threadPoll.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t AAA");
                });
//                TimeUnit.MILLISECONDS.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoll.shutdown();
        }

    }

    /**
     * 使用给定的初始名称创建一个新的{@code ThreadPoolExecutor}
     * 参数。
     *
     *  @param corePoolSize保留在池中的线​​程数，即使
     * 如果它们空闲，除非设置了{@code allowCoreThreadTimeOut}
     *  @param maximumPoolSize在允许的最大线程数
     * 池
     *  @param keepAliveTime当线程数大于
     * 核心，这是多s余的空闲线程的最长时间
     * 将终止之前等待新任务。
     *  @param unit {@code keepAliveTime}参数的时间单位
     *  @param work将队列用于保存任务之前先将其排队
     * 已执行。此队列将仅容纳{@code Runnable}
     * 由{@code execute}方法提交的任务。
     *  @param threadFactory执行程序时要使用的工厂
     * 创建一个新线程
     *  @param handler阻止执行时使用的处理程序
     * 因为达到了线程界限和队列容量
     *  @throws IllegalArgumentException如果以下条件之一成立：<br>
     *  {@code corePoolSize <0} <br>
     *  {@code keepAliveTime <0} <br>
     *  {@code maximumPoolSize <= 0} <br>
     *  {@code maximumPoolSize <corePoolSize}
     * 如果{@code workQueue}，则抛出NullPointerException
     * 或{@code threadFactory}或{@code handler}为空
     *      
     */
//    public ThreadPoolExecutor(int corePoolSize,
//                              int maximumPoolSize,
//                              long keepAliveTime,
//                              TimeUnit unit,
//                              BlockingQueue<Runnable> workQueue,
//                              ThreadFactory threadFactory,
//                              RejectedExecutionHandler handler) {
//        if (corePoolSize < 0 ||
//                maximumPoolSize <= 0 ||
//                maximumPoolSize < corePoolSize ||
//                keepAliveTime < 0)
//            throw new IllegalArgumentException();
//        if (workQueue == null || threadFactory == null || handler == null) {
//            throw new NullPointerException();
//        }
//        this.acc = System.getSecurityManager() == null ?
//                null :
//                AccessController.getContext();
//        this.corePoolSize = corePoolSize;
//        this.maximumPoolSize = maximumPoolSize;
//        this.workQueue = workQueue;
//        this.keepAliveTime = unit.toNanos(keepAliveTime);
//        this.threadFactory = threadFactory;
//        this.handler = handler;
//    }
}
