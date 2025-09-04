package com.itheima;

import java.text.NumberFormat;

/**
 * 等额本息方式计算器
 */
public class Calculator0 extends Calculator {

    public Calculator0(double p, int m, double yr) {
        super(p, m, yr);
    }

    @Override
    String[] cal() {
        double mr = yr / 12 / 100.0;
        double pow = Math.pow(1 + mr, m);
        double payment = p * mr * pow / (pow - 1);
        return new String[]{
                NumberFormat.getCurrencyInstance().format(payment * m),
                NumberFormat.getCurrencyInstance().format(payment * m - p)
        };
    }

    @Override
    String[][] details() {
        String[][] a2 = new String[m][];
        double mr = yr / 12 / 100.0;
        double pow = Math.pow(1 + mr, m);
        double payment = p * mr * pow / (pow - 1);              // 月供
        for (int i = 0; i < m; i++) {
            double payInterest = p * mr;                        // 偿还利息
            double payPrincipal = payment - payInterest;        // 偿还本金
            p -= payPrincipal;                                  // 剩余本金

            a2[i] = createRow(i, payment, payPrincipal, payInterest);
        }
        return a2;
    }
}
