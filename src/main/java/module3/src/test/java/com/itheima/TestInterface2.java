package com.itheima;

/**
 * 接口特性2 - 多态
 *
 *      回忆: 方法多态的两个条件
 *      1. 用父类型代表子类对象, 或者用接口类型来代表实现类对象
 *      2. 必须发生方法重写
 */
public class TestInterface2 {
    public static void main(String[] args) {
        E[] array = new E[] {
                new F(),
                new G()
        };
        for (int i = 0; i < array.length; i++) {
            E e = array[i];
            e.e(); // 多态
        }
    }
}
/*
    public > protected > 默认 > private
 */
interface E {
    // 默认方法
    /*default void e() { // public
        System.out.println("e");
    }*/

    // 抽象方法 只有方法声明，没有方法体
    /*
        1. 只能是 public 的， public abstract 都可以省略
        2. 为什么抽象方法设计为不需要方法体?
        3. 实现类，必须重写接口的抽象方法
     */
    void e();
}
class F implements E {
    @Override
    public void e() { // 默认
        System.out.println("f");
    }
}
class G implements E {
    @Override
    public void e() {
        System.out.println("g");
    }
}