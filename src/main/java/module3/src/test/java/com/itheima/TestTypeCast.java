package com.itheima;

/*
 * 类型转换：同类型之间可以直接赋值、传参, 不同类型之间赋值、传参就需要类型转换
 * 例如：
 *      int a = 10;
 *      int b = a;
 *
 *      static void add(int a, int b) { ... }
 *      add(10, 20);
 */
/*      double c = a; 可以吗?
 *      byte d = a; 可以吗?
 *
 *      static void sub(double a, double b) { ... }
 *      sub(10, 20) 可以吗?
 */
public class TestTypeCast {
    public static void main(String[] args) {
        /*
            1. 基本类型之间转换规则
                顺箭头 隐式转换(自动)
                逆箭头 强制转换, 可能损失精度
            byte a = 10;
            int b = a;

            int c = 1000;
            byte d = (byte) c;
            System.out.println(d);
         */

        /*
            2. 包装类型与基本类型之间转换规则
            int a = 20;
            Integer b = a;

            Integer c = new Integer(30);
            int d = c;
         */

        /*
            3. 引用类型之间转换规则
                继承特点
                1) 单继承, 子类只能继承一个父类
                2) Object 是其它类型直接或间接的父类型, 定义 class 时, 不写 extends 此类也是继承自 Object
                3) 子类与父类、祖先类型之间，可以用【是一个 is a】的关系来表达

                a. 顺箭头 (待转换的对象和目标类型之间 要符合【是一个】的关系) 隐式转换, 向上转型, 使用父类型统一处理子类型
                b. 逆箭头 (待转换的对象和目标类型之间 要符合【是一个】的关系) 强制转换, 向下转型, 将对象还原
                               cat    dog
         */
        /*Animal a = new Cat();  // 对象还是那个对象, 只是用父类型来代表了它
        Object b = new Cat();
//        Appliance c = new Cat(); // 不合法

        Cat c = (Cat) a;
//        Dog d = (Dog) a; // 不行 类型转换异常 ClassCastException

        Cat e = (Cat) b; // 可以
//        Dog f = (Dog) b; // 不行 类型转换异常 ClassCastException
        Animal g = (Animal) b; // 可以

        Animal[] cats = new Animal[] {new Cat(), new Dog()};

        test(new Cat());
        test(new Dog());

        System.out.println(a.getClass()); // 是获取它所代表对象的真正类型
        System.out.println(b.getClass()); // 是获取它所代表对象的真正类型

        // 对象 instanceof 类型, 作用检查对象和类型之间是否符合【是一个】的关系
        System.out.println(a instanceof Cat);
        System.out.println(a instanceof Dog);
        System.out.println(b instanceof Animal);*/

        /*
            4. 不属于以上三种的, 借助转换方法
                String -> int
         */
        String a = "1";
        String b = "2";

        System.out.println(Integer.parseInt(a) + Integer.parseInt(b));
        System.out.println(a + b);
    }

    static void test(Animal cat) {

    }
}

class Animal extends Object {
}

class Cat extends Animal {
}

class Dog extends Animal {
}

class Appliance {
} // 家电