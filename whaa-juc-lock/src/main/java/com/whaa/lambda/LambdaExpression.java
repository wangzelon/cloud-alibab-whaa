package com.whaa.lambda;


interface Foo {
    void sayHello();
}

@FunctionalInterface
interface FooParam {
    String sayHello(String name);

    default int add(int x, int y) {
        return x + y;
    }

    default int mul(int x, int y) {
        return x * y;
    }

    static int div(int x, int y) {
        return x / y;
    }
}

/**
 * created by @author wangzelong 2020/4/16 10:07
 * Lambda表达式学习之旅
 * 1、函数式编程
 * python
 * ruby
 * Scala
 * java8接口里允许有实现
 */
public class LambdaExpression {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3("李杰");
    }

    /**
     * 匿名内部类1
     */
    static void test1() {
        Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello foo");
            }
        };
        foo.sayHello();
    }

    static void test2() {
        Foo foo1 = () -> System.out.println("hello foo1 lambda expression");
        foo1.sayHello();
    }

    static void test3(String name) {
        FooParam fooParam = (String a) -> "lambda param" + a;
//        String hello = fooParam.sayHello(name);
//        System.out.println(hello);
        int mul = fooParam.mul(3, 5);
        System.out.println(mul);
        int div = FooParam.div(2, 2);
        System.out.println(div);
    }
}
