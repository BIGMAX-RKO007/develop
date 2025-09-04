package com.itheima.b;

public class TestMethod {
    static int add(int a , int b) { // 形参
        int c = a + b;
        return c;
    }

    public static void main(String[] args) {
        double p = Math.pow(2.0, 3.0);
        System.out.println(p);
        int d = TestMethod.add(100, 200);// 实参
        System.out.println(d);
    }
}
