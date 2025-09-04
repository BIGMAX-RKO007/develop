package com.itheima.encapsulation;

import java.io.Serializable;

/**
 * JavaBean 规范
 *  1. 字段私有, public 方法来访问私有字段 get set is
 *  2. 最好提供一个无参的构造
 *  3.
 *
 *  属性
 *      有 getName 方法， Teacher 这个 Java Bean 有 name 属性
 *      有 isMarried 方法， Teacher 这个 Java Bean 有 married 属性
 *      没有 getAge， setAge 方法， Teacher 这个 Java Bean 没有 age 属性
 */
public class TestJavaBean {
    public static void main(String[] args) {
        Teacher t = new Teacher("张老师", false);
        System.out.println(t.getName());
        System.out.println(t.isMarried());
        t.setMarried(true);
        System.out.println(t.isMarried());
    }
}

class Teacher implements Serializable {
    private String name; // 小写
    private boolean married; // 已婚
    private int age;

    public boolean isMarried() { // 对 boolean 类型，用这种 isXXX
        return this.married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    // get 方法 用来获取私有字段值
    public String getName() { // get 后面单词首字母要大写
        return this.name;
    }

    // set 方法 用来修改私有字段值
    public void setName(String name) {
        this.name = name;
    }

    public Teacher(String name, boolean married) {
        this.name = name;
        this.married = married;
    }

    public Teacher() {
    }
/*
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }*/
}