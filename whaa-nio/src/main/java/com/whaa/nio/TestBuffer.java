package com.whaa.nio;

import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * created by @author wangzelong 2020/5/18 11:42
 * 1、缓冲区(Buffer):在 java NIO 中负责存储数据。缓冲区就是数组，用于存储不同数据类型的的数据
 * <p>
 * 更具数据类型不同（boolean 除外）,提供了相应的缓冲区:
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 上述缓冲区的管理方法几乎一致,通过allocate()获取缓冲区
 * put()：存入数据到缓冲区
 * get()：获取缓冲区的数据
 * 缓冲区的核心属性
 * Invariants: mark <= position <= limit <= capacity
 * private int mark = -1; 标记：标识记录当前position的位置，通过rest()恢复到mark的位置
 * private int position = 0; 位置，缓冲区中正在操作数据的位置
 * private int limit; 界限，缓冲区中可以操作数据的大小(limit后面的的数据不能读写操作)
 * private int capacity; 容量，最大存取数据的容量，一旦声明不能改变
 *
 * 直接缓冲区和分直接缓冲区：
 * 非直接缓冲区:通过allocate()方法分配缓冲区,将缓冲区简历在JVM内存中
 * 直接缓冲区：通过allocateDirect()方法分配直接缓冲区,将 缓冲区建立在物理内存中
 */
public class TestBuffer {
    @Test
    public void test(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

    }

    public static void main(String[] args) {
        //分配缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //put()存入数据
        byteBuffer.put("你好NIO".getBytes());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //flip()切换读取模式
        byteBuffer.flip();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //get()读取缓冲区数据
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //rewind():可重复读取数据
        byteBuffer.rewind();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        //clear() 清空缓冲区;但是缓冲区的数据还在，数据处于被遗忘状态
        byteBuffer.clear();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
    }
}
