package com.itheima.module4;

import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        System.out.println(list1.getClass());

        List<Integer> list2 = List.of(1, 2, 3, 4);
        System.out.println(list2.getClass());

        List<Integer> list3 = List.of(1, 2);
        System.out.println(list3.getClass());

//        list3.add(5); // Un supported Operation Exception  不支持的操作

        list1.addAll(list2);
//        System.out.println(list1);

        List<Integer> list4 = new ArrayList<>();
        list4.addAll(List.of(1,2,3,4,5));
        System.out.println(list4);
    }
}
