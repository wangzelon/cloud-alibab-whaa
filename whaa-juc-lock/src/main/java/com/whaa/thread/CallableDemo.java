package com.whaa.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable start");
    }
}

class MyThreadCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("come in");
        return "Callable Interface";
    }
}

/**
 * created by @author wangzelong 2020/4/1 18:18
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyThreadCallable());
        new Thread(futureTask, "input thread name").start();
        String s = futureTask.get();
        System.out.println(s);
    }
}
