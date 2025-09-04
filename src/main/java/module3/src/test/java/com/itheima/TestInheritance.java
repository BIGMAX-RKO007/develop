package com.itheima;

/**
 * 测试继承
 */
public class TestInheritance {
    public static void main(String[] args) {
        B b = new B("张三");
        System.out.println(b.name);
        b.test();
    }
}

class A {
    String name;

    public A(String name) {
        this.name = name;
    }

    void test() {
        System.out.println("父类测试方法");
    }
}

class B extends A { // new B("张三")
    B(String name) {
        // 调用父类带参构造
        super(name);
    }
}

class D extends A {
    public D(String name) {
        super(name);
    }
}