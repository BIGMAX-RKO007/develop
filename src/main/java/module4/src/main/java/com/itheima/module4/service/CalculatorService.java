package com.itheima.module4.service;

import com.itheima.module4.dto.Calculator;

import java.text.NumberFormat;

/**
 * 业务逻辑接口
 */
public interface CalculatorService {

    String[] cal(Calculator c); // public

    String[][] details(Calculator c);

    default String[] createRow(int i, double payment, double payPrincipal, double payInterest, double p) {
        String[] row = new String[]{                       // 一行的数据
                (i + 1) + "",
                NumberFormat.getCurrencyInstance().format(payment),
                NumberFormat.getCurrencyInstance().format(payPrincipal),
                NumberFormat.getCurrencyInstance().format(payInterest),
                NumberFormat.getCurrencyInstance().format(p)
        };
        return row;
    }
}
