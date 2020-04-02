package com.whaa.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 线程一直从0递加到初始值执行主线程方法
 * created by @author wangzelong 2020/3/31 16:43
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->
                System.out.println(Thread.currentThread().getName() + "召唤神龙"));
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集到底" + finalI + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }
    }
}
