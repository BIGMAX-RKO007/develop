package com.itheima.encapsulation;

public class Car {
//    private int y; // 私有的
//    private void test() { } // 私有的

//    int y; // 默认的
//    void test() {} // 默认的

//    protected int y; // 受保护的
//    protected void test() {} // 受保护的

    public int y; // 公共的
    public void test() {} // 公共的

    void update() {
        System.out.println(this.y);
        test();
    }

}
