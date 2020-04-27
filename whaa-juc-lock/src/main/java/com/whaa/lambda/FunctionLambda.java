package com.whaa.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * created by @author wangzelong 2020/4/27 16:52
 */
class User {
    private int id;
    private String username;
    private int age;

    public User(int id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int money) {
        this.age = money;
    }
}

public class FunctionLambda {
    public static void main(String[] args) {
//        lambdaTest();
        User user = new User(1, "a", 20);
        User user1 = new User(2, "b", 22);
        User user2 = new User(12, "c", 25);
        User user3 = new User(13, "d", 29);
        User user4 = new User(18, "e", 26);
        List<User> users = Arrays.asList(user, user1, user2, user3, user4);
        users.stream().filter(u -> {
            if (u.getId() % 2 != 0 || u.getAge() < 24) {
                return false;
            }
            return true;
        }).map(user5 -> user5.getUsername().toUpperCase())
                .sorted(Comparator.reverseOrder())
                .limit(1L)
                .forEach(System.out::println);
    }

    private static void lambdaTest() {
        Function<String, Integer> function1 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return null;
            }
        };
        Function<String, Integer> function = s -> {
            return s.length();
        };
        System.out.println(function.apply("lijie"));

//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return false;
//            }
//        };
        Predicate<String> predicate = s -> {
            return s.equals("lijie");
        };
        System.out.println(predicate.test("zhangsan"));

        Consumer<String> consumer = s -> {
            System.out.println(s);
        };
        consumer.accept("哈哈哈");

        Supplier<String> supplier = () -> {
            return "java";
        };
        System.out.println(supplier.get());
    }
}
