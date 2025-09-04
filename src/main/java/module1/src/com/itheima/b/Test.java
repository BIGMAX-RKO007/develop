package com.itheima.b;

public class Test {
    public static void main(String[] args) {
        Student stu = new Student("张三");
        System.out.println(stu);
    }
}
class Student {
    String name;

    public Student(String name) {
        this.name = name;
    }
}
