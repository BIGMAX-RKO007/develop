package com.itheima;

/**
 * 接口 - 解决单继承问题
 *      1. 需要放入接口的方法, 必须加 default 关键字（默认方法）
 *      2. default 方法只能是 public, public 可以省略
 */
public class TestInterface1 {
    public static void main(String[] args) {
        Duck d = new Duck();
        d.swim();
        d.fly();
    }
}

interface Swimmable {
    default void swim() {
        System.out.println("游泳");
    }
}

interface Flyable {
    default void fly() {
        System.out.println("飞翔");
    }
}

class Duck implements Swimmable, Flyable {

}