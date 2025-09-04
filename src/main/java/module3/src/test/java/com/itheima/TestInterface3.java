package com.itheima;

/**
 * 接口特性3 - 封装
 *
 * 集大成者
 */
public class TestInterface3 {
    public static void main(String[] args) {
        M m = new N(); // 用接口类型代表了实现类对象
        m.m();
//        m.n();
    }
}

interface M {
    void m(); // public abstract
}

class N implements M {
    public String name;

    @Override
    public void m() {
        System.out.println("m");
    }

    public void n() {
        System.out.println("n");
    }
}