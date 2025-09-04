package com.itheima.polymorphism;

/**
 * 如果同一个方法执行时，表现出了不同的行为，称此方法具备多态性
 *
 * poly morphism （多种形态）  子类型多态
 *
 * 前提条件
 * 1. 用父类型代表子类对象
 * 2. 子类和父类得有相同的 say 方法, 方法重写
 */
public class TestAnimal {

    public static Animal[] getAnimals(){
        return new Animal[]{
                new Cat(),
                new Dog(),
                new Pig()
        };
    }

    public static void main(String[] args) {
        Animal[] animals = getAnimals();
        for (int i = 0; i < animals.length; i++) {
            Animal a = animals[i];
            a.say();
        }
    }
}

class Animal {
    void say() {

    }
}

class Dog extends Animal {
    @Override // 重写
    void say() {
        System.out.println("汪汪汪");
    }
}

class Cat extends Animal {
//    @Override // 重写
    void say1() {
        System.out.println("喵~");
    }
}

class Pig extends Animal {
    @Override // 重写
    void say() {
        System.out.println("哼哧哼哧");
    }
}