package com.itheima;

public class TestCircle {
    public static void main(String[] args) {
//        System.out.println(area(1.0));
//        System.out.println(area(2.0));

        Circle c1 = new Circle(1.0);
        Circle.pi = 3.14;
        Circle c2 = new Circle(2.0);
        Circle.pi = 3;

        System.out.println(c1.area());
        System.out.println(c2.area());

    }


}
