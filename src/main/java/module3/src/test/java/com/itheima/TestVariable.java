package com.itheima;

/**
 * 四种变量对比
 */
public class TestVariable {
    public static void main(String[] args) {
        m(10);
        if (true) {
            C c1 = new C(30);
        }
    }

    static void m(int a) {  // 1. 参数变量, 作用范围是从方法调用开始，直到方法调用结束
        for (int i = 0; i < 10; i++) {
            int b = 20;         // 2. 局部变量, 作用范围从定义开始，到包围它的 } 为止, 必须赋初值才能使用
//            System.out.println(b);
        }
    }
}

class C {
    int c;  // 3. 对象变量(成员变量)  从对象创建开始, 到对象不能使用为止

    public C(int c) {
        this.c = c;
    }

    static int d = 40; // 4. 静态变量, 从类加载开始, 到类卸载为止
}
