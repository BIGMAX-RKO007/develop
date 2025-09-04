package com.itheima.a;

import java.text.NumberFormat;
import java.util.Scanner;

public class Calculator2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入本金");
        double p = scanner.nextDouble();
        if(p <= 0) {
            System.out.println("贷款金额必须 > 0");
            return;
        }
        System.out.println("请输入年利率");
        double yr = scanner.nextDouble();
        if(yr < 1.0 || yr > 36.0) {
            System.out.println("年利率必须是 1 ~ 36");
            return;
        }
        double mr = yr / 12.0 / 100;
        System.out.println("请输入还款月数");
        int m = scanner.nextInt();
        if(m < 1 || m > 360) {
            System.out.println("贷款月数必须是 1 ~ 360");
            return;
        }
        double pow = Math.pow(1 + mr, m);
        double payment = p * mr * pow / (pow - 1);
        System.out.println(NumberFormat.getCurrencyInstance().format(payment));

        for (int i = 0; i < m; i++) {
            double payInterest = p * mr;                    // 偿还利息
            double payPrincipal = payment - payInterest;    // 偿还本金
            p -= payPrincipal;                              // 剩余本金
            System.out.print ("月份:" + (i + 1));
            System.out.print("\t本月还款:" + NumberFormat.getCurrencyInstance().format(payment));
            System.out.print("\t偿还本金:" + NumberFormat.getCurrencyInstance().format(payPrincipal));
            System.out.print("\t偿还利息:" + NumberFormat.getCurrencyInstance().format(payInterest));
            System.out.println("\t剩余本金:" + NumberFormat.getCurrencyInstance().format(p));
        }
        System.out.println("总还款额:" + NumberFormat.getCurrencyInstance().format(payment * m));
    }
}
