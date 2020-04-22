package java.lang;

/**
 * created by @author wangzelong 2020/4/20 16:17
 */
public class String {

    public static void main(String[] args) {
        System.out.println("hello Classloader");
        Thread thread = new Thread(() -> {
            System.out.println("aaa");

        }, "A");
        thread.start();
        thread.start();
    }
}
