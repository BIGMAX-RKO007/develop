package com.itheima.b;

import java.text.NumberFormat;

public class TestCalculateByMonth {
    public static void main(String[] args) {
        double p = 1000.0;  // 本金
        double mr = 1.0;    // 月利率
        int m = 2;          // 还款月数
        double pow = Math.pow(1 + mr, m);
        double payment = p * mr * pow / (pow - 1); // 每月还款总额
        System.out.println(payment);

        for (int i = 0; i < m; i++) {
            double payInterest = p * mr;                    // 偿还利息
            double payPrincipal = payment - payInterest;    // 偿还本金
            p -= payPrincipal;                              // 剩余本机
            System.out.print ("月份:" + (i + 1));
            System.out.print("\t本月还款:" + NumberFormat.getCurrencyInstance().format(payment));
            System.out.print("\t偿还本金:" + NumberFormat.getCurrencyInstance().format(payPrincipal));
            System.out.print("\t偿还利息:" + NumberFormat.getCurrencyInstance().format(payInterest));
            System.out.println("\t剩余本金:" + NumberFormat.getCurrencyInstance().format(p));
        }
        System.out.println("总还款额:" + NumberFormat.getCurrencyInstance().format(payment * m));
    }
}
