package com.whaa.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * created by @author wangzelong 2020/4/13 17:11
 * <p>
 * 三个线程卖出30张票
 * 线程操作资源类
 */
class Ticket {
    private int number = 30;

    public Ticket() {
    }

    public Ticket(int number) {
        this.number = number;
    }

    ReentrantLock lock = new ReentrantLock();

    public void sale() {
//        synchronized (this) {
//            this.number--;
//            System.out.println("剩余数量" + this.number);
//        }
        lock.lock();
        try {
            if (this.number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t 卖出" + this.number);
                this.number--;
                System.out.println(Thread.currentThread().getName() + "\t 剩余数量" + this.number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class SaleTicketDemo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
           for(int i=0;i<40;i++){
               ticket.sale();
           }
        }, "aaa").start();
        new Thread(() -> {
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        }, "bbb").start();
        new Thread(() -> {
            for(int i=0;i<40;i++){
                ticket.sale();
            }
        }, "ccc").start();
//        Thread.State;
    }

}
