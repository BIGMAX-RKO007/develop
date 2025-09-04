package com.itheima;

import java.text.NumberFormat;

/**
 * 等额本金方式的计算器
 */
public class Calculator1 extends Calculator {

    public Calculator1(double p, int m, double yr) {
        super(p, m, yr);
    }

    @Override
    String[] cal() {
        double payPrincipal = p / m;        // 偿还本金
        double backup = p;                  // 备份本金
        double mr = yr / 12 / 100.0;
        double payInterestTotal = 0.0;      // 总利息
        for (int i = 0; i < m; i++) {
            double payInterest = p * mr;    // 偿还利息
            p -= payPrincipal;              // 剩余本金
            payInterestTotal += payInterest;
        }
        // [0]还款总额   [1]总利息
        return new String[]{
                NumberFormat.getCurrencyInstance().format(backup + payInterestTotal),
                NumberFormat.getCurrencyInstance().format(payInterestTotal)
        };
    }

    @Override
    String[][] details() {
        // 等额本金
        double payPrincipal = p / m;                        // 偿还本金
        double mr = yr / 12 / 100.0;
        String[][] a2 = new String[m][];
        for (int i = 0; i < m; i++) {
            double payInterest = p * mr;                    // 偿还利息
            p -= payPrincipal;                              // 剩余本金
            double payment = payPrincipal + payInterest;    // 月供
            a2[i] = createRow(i, payment, payPrincipal, payInterest);
        }
        return a2;
    }
}
