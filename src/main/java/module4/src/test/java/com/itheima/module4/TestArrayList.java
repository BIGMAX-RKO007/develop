package com.itheima.module4;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 核心类库学习 - ArrayList
 */
public class TestArrayList {
    public static void main(String[] args) {
        ArrayList list = new ArrayList(5);// 初始容量是 5, 默认的初始容量 10
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        // 内部封装了扩容逻辑, 按 1.5 倍扩容 5*1.5 => 7*1.5 => 10
        list.add("f");

        System.out.println(list);

        for (Object e : list) {
            System.out.println(e);
        }

        String[] arr0 = new String[]{"1", "2", "3", "4", "5"};
        for(String s : arr0) {
            System.out.println(s);
        }
    }

    private static void test1() {
        // 数组不能自动扩容
        String[] arr0 = new String[]{"a", "b", "c", "d", "e"}; // 5
        String[] arr1 = new String[6];

        System.out.println(Arrays.toString(arr0));
        System.out.println(Arrays.toString(arr1));

        for (int i = 0; i < arr0.length; i++) {
            arr1[i] = arr0[i];
        }

        arr1[5] = "f";

        System.out.println(Arrays.toString(arr0));
        System.out.println(Arrays.toString(arr1));
    }
}
