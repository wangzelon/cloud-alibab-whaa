package com.whaa;

/**
 * created by @author wangzelong 2020/4/7 17:56
 */
public class TryCatchFinallyDemo {

    public static void main(String[] args) {
        System.out.println(tryTest());
    }

    public static int tryTest() {
        int result = 0;
        try {
            int a = 10;
            System.out.println(a / 0);
            return result;
        } catch (Exception e) {
            result = 1;
            return result;
        } finally {
            result = 2;
            return result;
        }
    }
}
