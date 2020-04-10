package com.whaa.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * created by @author wangzelong 2020/4/8 18:03
 * 虚拟引用
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue();
        Object aa = new Object();
        PhantomReference<Object> phantomReference = new PhantomReference(aa, referenceQueue);
        System.out.println(aa);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        aa = null;
        System.gc();
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
