package com.whaa.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * created by @author wangzelong 2020/4/17 11:24
 * <p>
 * 8 lock
 * 1、标准访问，先打印邮件还是短信
 * 2、暂停3秒在邮件方法，先打印邮件还是短信
 * 3、新增普通sayHello方法，先打印邮件还是hello
 * 4、两部手机，先打印邮件还是短信
 * 5、两个静态同步方法,同一部手机,先打印邮件还是短信
 * 6、两个静态同步方法,两部手机，先打印邮件还是短袖
 * 7、1个静态同步方法，一个普通同步方法，同一部手机，打印邮件还是短信
 * 8、1个静态同步方法，一个普通同步方法，2部手机，打印邮件还是短信
 *     总结
 *      一个对象里如果有多个synchronized方法，在某一个时刻，只有要一个线程去调用其中的synchronized方法
 *      其他的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问对象的synchronized方法
 *      锁当前对象this其他对象都不能进入其他的synchronized方法
 * <p>
 * 普通方法和同步锁无关
 * <p>
 * 换成两个对象后，不是同一把锁，也不会等待
 * <p>
 * 静态同步方法锁的是类，全局锁
 */

class Phone {
    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + "\t -->send Email");
    }

    public  synchronized void sendSms() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t -->send Sms");
    }

    public void sayHello() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t -->sayHello");
    }
}

public class LockDemo {

    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(() -> {
            try {
//                phone.sendEmail();
//                Phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "a").start();
//        Thread.sleep(100);
        new Thread(() -> {
            try {
                phone.sendSms();
//                phone.sayHello();
//                phone1.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "b").start();
    }
}
