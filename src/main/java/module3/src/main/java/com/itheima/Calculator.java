package com.itheima;

import java.text.NumberFormat;

public class Calculator {
    double p;
    int m;
    double yr;
    public Calculator(double p, int m, double yr) {
        this.p = p;
        this.m = m;
        this.yr = yr;
    }
    String[] createRow(int i, double payment, double payPrincipal, double payInterest) {
        String[] row = new String[]{                       // 一行的数据
                (i + 1) + "",
                NumberFormat.getCurrencyInstance().format(payment),
                NumberFormat.getCurrencyInstance().format(payPrincipal),
                NumberFormat.getCurrencyInstance().format(payInterest),
                NumberFormat.getCurrencyInstance().format(p)
        };
        return row;
    }
    String[] cal() {
        return null;
    }
    String[][] details() {
        return null;
    }
}
