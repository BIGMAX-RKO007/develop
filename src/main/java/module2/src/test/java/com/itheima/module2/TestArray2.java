package com.itheima.module2;

public class TestArray2 {
    public static void main(String[] args) {
        // 1. 数组长度
//        String[] a = new String[]{"北京", "上海", "深圳"};
//        String[] a = new String[3]; // String[] 元素的默认值是 null
        int[] a = new int[3];         // int[] 元素的默认值是 0        double[] 元素的默认值是 0.0     boolean[] 元素的默认值是 false
        System.out.println("数组的长度:" + a.length);
        // 2. 数组越界
//        System.out.println(a[3]); // ArrayIndexOutOfBoundsException 数组索引越界异常
        // 3. 遍历数组
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]); // a[0] a[1] a[2]
        }
        // 4. 默认值
    }
}
