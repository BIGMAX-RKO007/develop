package com.itheima;

public class TestPhone {
    public static void main(String[] args) {
        // new Scanner(System.in)
        Phone p1 = new Phone("苹果", "128G", "6.1寸", "星光色", 5799.0);

        Phone p2 = new Phone("红米", "4G", "6.53寸", "金色", 1249.0);

        Phone p3 = new Phone("华为", "4G", "6.3寸", "幻夜黑", 999.0);

//        System.out.println(p1.color);
//        System.out.println(p1.price);
//
//        p1.price = 3000.0;
//        System.out.println(p1.price);
        System.out.println(p1.available);
        System.out.println(p2.available);
        System.out.println(p3.available);

        System.out.println(p1.brand);
    }
}
