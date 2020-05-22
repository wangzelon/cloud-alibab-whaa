package com.whaa.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
 * <p>
 * 直接缓冲区和分直接缓冲区：
 * 非直接缓冲区:通过allocate()方法分配缓冲区,将缓冲区简历在JVM内存中
 * 直接缓冲区：通过allocateDirect()方法分配直接缓冲区,将 缓冲区建立在物理内存中
 */
public class TestBuffer {
    @Test
    public void test() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

    }

    /**
     * 通道:用于源节点与目标节点的连接。在java nio中负责缓冲区中的数据传输。Channel本身不存储数据,
     * 因此需要配合缓冲区进行传输
     * 主要类：
     * java.nio.channels.Channel 接口
     * FileChannel
     * SocketChannel
     * ServerSocketChannel
     * DatagramChannel
     * 获取通道
     * 1、Java针对支持通道的类提供了getChannel()方法
     * FileInputStream/FileOutputStream
     * RandomAccessFile
     * 网络IO
     * Socket
     * ServerScoket
     * DatagramSocket
     * 2、jdk1.7中的NIO.2针对各个通道提供了静态方法open()
     * 3、jdk1.7中的NIO.2的Files工具类的newByteChannel()
     * 4、通道之间的数据传输
     * transferFrom()
     * transferTo();
     * 5、分散(Scatter)与聚集(Gather)
     * 分散读取(Scattering Reads):将通道中的数据分散到多个缓冲区中
     * 聚集写入(Gathering Writes)：将多个缓冲区中的数据聚集写到通道中
     * 6、字符集:Charset
     * 编码:
     * 解码:
     *
     */
    @Test
    public void testChannel() throws IOException {
        long start = System.currentTimeMillis();
        //利用通道完成文件的复制(非直接缓冲区)
        FileInputStream fileInputStream = new FileInputStream("F:\\BaiduNetdiskDownload\\CentOS6.9.iso");
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\BaiduNetdiskDownload\\CentOS6.9.3.iso");
        //获取通道
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        //分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将通道中的数据写入缓冲区
        while (inputStreamChannel.read(byteBuffer) != -1) {
            //切换读取数据模式
            byteBuffer.flip();
            //将缓冲区中的数据写入通道
            outputStreamChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        outputStreamChannel.close();
        inputStreamChannel.close();
        fileOutputStream.close();
        fileInputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:" + (end - start) + "ms");
    }

    @Test
    public void testRandomAccessFile() throws FileNotFoundException {
        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");
        //获取通道
        FileChannel raf1Channel = raf1.getChannel();
        //分配缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer byf2 = ByteBuffer.allocate(1024);



    }


    @Test
    public void testFileChannel() throws IOException {
        long start = System.currentTimeMillis();
        //使用直接缓冲区完成复制(内存映射文件)
        FileChannel inChannel = FileChannel.open(Paths.get("F:\\BaiduNetdiskDownload\\CentOS6.9.iso"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("F:\\BaiduNetdiskDownload\\CentOS6.9.2.iso"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ);
        //内存映射文件
        MappedByteBuffer inMapperBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapperBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        //直接对缓冲区进行数据操作
        byte[] dst = new byte[inMapperBuffer.limit()];
        inMapperBuffer.get(dst);
        outMapperBuffer.put(dst);
        //关闭
        inChannel.close();
        outChannel.close();
        long end = System.currentTimeMillis();
        System.out.println("耗费时间:" + (end - start) + "ms");
    }

    @Test
    public void testTransferFileChannel() throws IOException {
        //通道之间的数据传输
        FileChannel inChannel = FileChannel.open(Paths.get("F:\\BaiduNetdiskDownload\\CentOS6.9.iso"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("F:\\BaiduNetdiskDownload\\CentOS6.9.1.iso"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inChannel.close();
        outChannel.close();
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
