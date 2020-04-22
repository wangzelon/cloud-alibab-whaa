package com.whaa.juc;

/**
 * created by @author wangzelong 2020/4/22 14:56
 */
class Person {
    public int age;


}

public class TestTransferValue {

    public void changeValue1(int age) {
        age = 12;
    }

    public void changeValue2(Person person) {
        person.age = 24;
    }

    public void changeValue3(String val) {
        val = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue testTransferValue = new TestTransferValue();
        int age = 0;
        testTransferValue.changeValue1(age);
        System.out.println("main age " + age);
        Person person = new Person();
        person.age = 99;
        testTransferValue.changeValue2(person);
        System.out.println("person age " + person.age);
        String xx = "abc";
        testTransferValue.changeValue3(xx);
        System.out.println("main string " + xx);
    }
}
