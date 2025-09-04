package com.itheima;

/**
 * 测试类型系统
 */
public class TestType {
    /*
        1. 基本类型 primitive (原始 简单)
            byte short int long float double char boolean
        2. 引用类型 reference
            String (byte[] -> byte)
            byte[]
            int[]
            Phone (double String)
            Student
            Calculator
            包装类型
                Byte Short Integer Long Float Double Character Boolean

            null 值
     */
    public static void main(String[] args) {
        Integer a = new Integer(10);
        int b = 20;

        Student s1 = new Student();
        s1.name = "张小三";
        if(s1.name != null) {
            System.out.println("姓名的长度是: " + s1.name.length());
        }

        // 当引用类型取值为 null 时，进一步使用它(调用方法，访问字段) NullPointerException 空指针异常
    }
}
