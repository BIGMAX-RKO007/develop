package com.itheima.a;

import java.text.NumberFormat;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        // 类型 变量名 =
        // ctrl + /  注释或取消注释
        // alt + enter 快速生成前面的变量定义
//        var scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        // nextLine  nextInt  nextDouble
        System.out.println("请输入本金");
        double p = scanner.nextDouble();
        System.out.println("请输入年利率");
        double yr = scanner.nextDouble();
        double mr = yr / 12.0 / 100;
        System.out.println("请输入还款月数");
        int m = scanner.nextInt();
        double pow = Math.pow(1 + mr, m);
        double payment = p * mr * pow / (pow - 1);
        System.out.println(NumberFormat.getCurrencyInstance().format(payment));

    }
}
