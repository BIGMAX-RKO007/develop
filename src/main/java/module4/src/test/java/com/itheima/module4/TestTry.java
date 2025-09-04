package com.itheima.module4;

/**
 * 异常处理
 */
public class TestTry {

    public static void main(String[] args) {
        System.out.println(1);
        try { // 尝试
            test(0.0); // 没有异常一切照旧，有异常，需要配合 catch 语句处理异常
        } catch (Exception e) { // 捕捉， 用来获取异常对象，让程序恢复正常流程
            System.out.println(e); // e 代表的就是异常对象
        }
        System.out.println(3);
    }

    public static void test(double p) {
        if (p <= 0.0) {
            throw new IllegalArgumentException("本金必须大于 0"); // 1 处
        }
        System.out.println(2);
    }












}
