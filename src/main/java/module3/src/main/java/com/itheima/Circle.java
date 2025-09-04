package com.itheima;

public class Circle {

    double r; // 半径
    static double pi = 3.14; // 静态变量, final 让变量只能赋值一次

    public Circle(double r) {
        this.r = r;
    }

    /**
     * 求圆的面积
     * @return 面积
     */
    double area() {
        return Math.PI * r * r;
    }
}
