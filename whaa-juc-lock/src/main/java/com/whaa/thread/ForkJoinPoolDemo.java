package com.whaa.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * created by @author wangzelong 2020/4/27 17:40
 */
class MyTask extends RecursiveTask<Integer> {

    private static final int DEFAULT_COMPUTE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= DEFAULT_COMPUTE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            int middle = (end + begin) / 2;
            MyTask myTask01 = new MyTask(begin, middle);
            MyTask myTask02 = new MyTask(middle + 1, end);
            myTask01.fork();
            myTask02.fork();
            result = myTask01.join() + myTask02.join();
        }
        return result;
    }
}


public class ForkJoinPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        System.out.println(submit.get());
        forkJoinPool.shutdown();
    }
}
