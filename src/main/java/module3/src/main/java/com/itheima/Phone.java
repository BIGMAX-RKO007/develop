package com.itheima;

public class Phone {
    // 类型 名字
    String brand; // 品牌
    String memory; // 内存
    String size; // 大小
    String color; // 颜色
    double price; // 价格

    // 构造方法是创建对象时被调用，作用：给对象的字段赋初始值
    // 1. 和类同名  2. 不加返回值类型声明

    public Phone(String brand, String memory, String size, String color, double price) {
        this.brand = brand;
        this.memory = memory;
        this.size = size;
        this.color = color;
        this.price = price;
        this.available = true;
    }

    /*Phone(String brand, String memory, String size, String color, double price) {
            // 字段名 = 方法参数名
            this.brand = brand;
            this.memory = memory;
            this.size = size;
            this.color = color;
            this.price = price;
            available = true;
        }*/
    /*
        byte short int long char 默认值 0
        float double 默认值 0.0
        boolean 默认值 false
        剩余类型 默认值是 null, null 代表没有值
     */
    boolean available; // true 上架  false 下架
}
